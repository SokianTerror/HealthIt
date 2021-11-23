package third.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import third.DBplay2;
import third.energeies.makeLists;

/**
 * Servlet implementation class bookAppointmentServlet
 */
@WebServlet("/bookAppointmentServlet")
public class bookAppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public bookAppointmentServlet() {
    	
    	
            }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setAttribute("doctorList", makeLists.makedoclists());
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("usertype")== null || !session.getAttribute("usertype").equals("patient"))
        	response.sendRedirect("redirectionjsp.jsp");
		else {
	
	        String action = request.getQueryString();
	        String page = null;
	        try {
		        String[] params = action.split("&");
		        if(params[0].equalsIgnoreCase("action=search")) {
		        	String[] param2 = params[1].split("=");
		        	String spc = param2[1];
		    		DBplay2 db = new DBplay2();
		            Connection con = db.getCon();
		        	String query = "select appointment.date, doctor.username, appointment.startslottime, appointment.appointment_id\n"
		        			+ "from doctorappointment.doctor, doctorappointment.appointment\n"
		        			+ "where doctor.doctoramka = appointment.doctor_doctoramka and doctor.specialty = ? and appointment.isavailable = 0 and CURRENT_TIMESTAMP < startslottime";
		        	try {
		        		PrintWriter out=response.getWriter();
		        		PreparedStatement ps = con.prepareStatement(query);
		        		ps.setString(1,spc);
		        		ResultSet rs = ps.executeQuery();
		        		request.getRequestDispatcher("bookAppointment.jsp").include(request, response);
		        		boolean exists = false;
		        		while(rs.next()) {
		        			if(exists==false) {
		        				out.print("<table border=1>" +
		    		    	    		"<thead>" +
		    		    	            "<tr>" +
		    		    	                "<th>Date</th>"+
		    		    	                "<th>Start time</th>" +
		    		    	                "<th>username</th>" +
		    		    	                "<th>Action</th>" +
		    		    	            "</tr>" +
		    		    	    		"</thead>");
		        				exists=true;
		        			}
		    	    		out.print("<tr> <td>" + rs.getString("date".toString())+"</td>");
		    	    		out.print("<td>" + rs.getString("startslottime".toString())+"</td>");
		    	    		out.print("<td>" + rs.getString("username".toString()) +"</td>");
		    	    		out.print("<td><a style=color:blue href=bookAppointmentServlet?action=book&appointment_id="+ rs.getInt("appointment_id")+"> BOOK" +
		    	    			 " </td></tr>");
		        		}
		        		out.print("</tbody> </table>");
		    			out.close();
		        	}catch(Exception e){
		
		    		request.getRequestDispatcher("bookAppointment.jsp").include(request, response);
		    		response.getWriter().print("None Doctor has a free appointment!");
		
		        	}
		        }else if(params[0].equalsIgnoreCase("action=book")) {
		        	String[] param2 = params[1].split("=");
		        	int app_id = Integer.parseInt(param2[1]);
		    		DBplay2 db = new DBplay2();
		            Connection con = db.getCon();
		            String query = "UPDATE doctorappointment.appointment SET patient_patientamka = ?, isavailable = 1 WHERE appointment_id="+app_id +";";
					try {
						PreparedStatement ps = con.prepareStatement(query);
						ps.setInt(1, (int) session.getAttribute("id"));
						int rs = ps.executeUpdate();
						if(rs==0) {
							request.setAttribute("message","Your appointment could not get booked, please try again later");
						}else {
							request.setAttribute("message","Your appointment is now booked!");
		
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		request.getRequestDispatcher("bookAppointment.jsp").include(request, response);
		        }
	        }catch(Exception e) {
	        	page = "redirectionjsp.jsp";
	        }
	        if(page!=null) { 
	        	RequestDispatcher dd=request.getRequestDispatcher(page);
	        	dd.forward(request, response);
	        }
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("usertype")== null || !session.getAttribute("usertype").equals("patient"))
        	response.sendRedirect("redirectionjsp.jsp");
		else {
			request.setAttribute("doctorList", makeLists.makedoclists());
	    	request.getRequestDispatcher("bookAppointment.jsp").forward(request, response);
		}
	}
		// TODO Auto-generated method stub
		

}
