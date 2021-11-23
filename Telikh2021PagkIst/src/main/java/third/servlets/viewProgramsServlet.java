package third.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import third.DBplay2;

/**
 * Servlet implementation class viewProgramsServlet
 */
@WebServlet("/viewProgramsServlet")
public class viewProgramsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public viewProgramsServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        HttpSession session = request.getSession(false);				//if no one has logged in redirect to index
        if(session.getAttribute("uname") == null || !session.getAttribute("usertype").equals("patient")) {
        	response.sendRedirect("redirectionjsp.jsp");
        }else {
		DBplay2 db = new DBplay2();
		Connection con = db.getCon();
		String query = "select appointment.*, doctor.username from doctorappointment.appointment,doctorappointment.doctor"  
				+ " where patient_patientamka = ? and doctor.doctoramka = appointment.doctor_doctoramka and CURRENT_TIMESTAMP > startslottime";
		try {
			int ok = (int) session.getAttribute("id"); 
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, ok);
			ResultSet resultSet = ps.executeQuery();	 //make a select query for appointments with x amka
			
			response.setHeader("Cache-Control","no-cache");
			response.setHeader("Cache-Control","no-store");
			response.setHeader("Pragma","no-cache");
			response.setDateHeader ("Expires", 0);
			request.getRequestDispatcher("PatientIndex.jsp").include(request, response); //print patientindex 
			if(!resultSet.next()) {
				response.getWriter().print("No data!"); //if there is no result print no data
			}
			else {
				int i =0;
				do { //otherwise for each row print it's elements
					if( i == 0) response.getWriter().print("<table> <tr> <th>Date</th> <th>Starting TIme</th>  <th>Ending Time</th> <th> Doctor's Username</th></tr>");
					i+=1;
				    String date =(String) resultSet.getString("date");
				    String s_time =(String) resultSet.getString("startslottime");
				    s_time = timeString(s_time);
				    String e_time =(String) resultSet.getString("endslottime");
				    e_time = timeString(e_time);
				    String doc_uname =(String) resultSet.getString("username");
				    response.getWriter().print("<tr> <td style=text-align:center>"+date+"</td> <td style=text-align:center>"+s_time+"</td>  <td style=text-align:center>"+e_time+"</td> <td style=text-align:center>"+doc_uname +"</td></tr>");
		
				}while(resultSet.next());
				 response.getWriter().print("</table>");
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}}
	public String timeString(String full_time) {
		return full_time.substring(full_time.length() - 8); //timestamp has form yyyy-mm-dd 00:00:00 and we want to type only the 00:00:00
		
	}
	}
