package third.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import third.DBplay2;

/**
 * Servlet implementation class cancelAppointmentServlet
 */
@WebServlet("/cancelAppointmentServlet")
public class cancelAppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public cancelAppointmentServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		 String action = request.getQueryString();

		if(session == null || session.getAttribute("usertype")== null || !session.getAttribute("usertype").equals("patient") || action==null)
        	response.sendRedirect("redirectionjsp.jsp");
		else {
		     System.out.println(action);
		     String[] param = action.split("=");
		     DBplay2 db = new DBplay2();
	         Connection con = db.getCon();
		     int appointment_id = Integer.parseInt(param[1]);
		     String query = "UPDATE doctorappointment.appointment SET isavailable=0, patient_patientamka = null WHERE appointment_id = ?";
		     try {
				PreparedStatement ps = con.prepareStatement(query);
				ps.setInt(1,appointment_id);
				int rs = ps.executeUpdate();
				request.getRequestDispatcher("cancelAppointment.jsp").include(request, response);
				if(rs == 0) {
		    		response.getWriter().print("Something gone wrong please try again later!");
				}else {
		    		response.getWriter().print("Your appointment canceled succesfully!");

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("usertype")== null || !session.getAttribute("usertype").equals("patient"))
        	response.sendRedirect("redirectionjsp.jsp");
		else {
			/*String query= "SELECT date, startslottime, username, appointment_id"+
					"FROM doctorappointment.appointment, doctorappointment.doctor"+
					"WHERE patient_patientamka = ? and doctoramka = doctor_doctoramka and CURRENT_TIMESTAMP < startslottime "+
					"AND (EXTRACT(DAY FROM CURRENT_TIMESTAMP) <= EXTRACT(DAY FROM startslottime) -3 OR"+
						"(EXTRACT(MONTH FROM CURRENT_TIMESTAMP) < EXTRACT(MONTH FROM startslottime)"+
					 		"AND ABS(EXTRACT(DAY FROM CURRENT_TIMESTAMP) - DATE_PART('days', DATE_TRUNC('month', CURRENT_TIMESTAMP) + '1 MONTH'::INTERVAL - '1 DAY'::INTERVAL))"+
					 	"+ EXTRACT(DAY FROM startslottime)> 3 ))";*/
			
		/*	DBplay2 db = new DBplay2();
			Connection con = db.getCon();
			request.getRequestDispatcher("cancelAppointment.jsp").include(request, response);
			try {
        		PrintWriter out=response.getWriter();
        		String query= "SELECT date, startslottime, username, appointment_id"+
    					"FROM doctorappointment.appointment, doctorappointment.doctor WHERE patient_patientamka = ? ";
        		//and doctoramka = doctor_doctoramka and CURRENT_TIMESTAMP < startslottime";
        		PreparedStatement ps = con.prepareStatement(query);
				int id = (int) session.getAttribute("id");
				ps.setInt(1, id);
				System.out.println(id);
        		ResultSet rs = ps.executeQuery();
				System.out.println("Mpee");

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
    	    		out.print("<td><a style=color:blue href=bookAppointmentServlet?action=book&appointment_id="+ rs.getInt("appointment_id")+"> Delete" +
    	    			 " </td></tr>");
        		}
        		out.print("</tbody> </table>");
    			out.close();
    			if(exists==false) {
    				request.getRequestDispatcher("login.jsp").include(request, response);
    				out.print("You don't have any appointment to cancel!");
    			}
			} catch (Exception e) {
				System.out.println(e);
	    		response.getWriter().print("Something gone wrong please try again later!");
			}*/
			DBplay2 db = new DBplay2();
            Connection con = db.getCon();
        	String query = "SELECT date, startslottime, username, appointment_id\n"
        			+ "FROM doctorappointment.appointment, doctorappointment.doctor\n"
        			+ "WHERE patient_patientamka = ? and doctoramka = doctor_doctoramka and "
        			+ "CURRENT_TIMESTAMP - interval '3' day < startslottime";
        	try {
        		request.getRequestDispatcher("cancelAppointment.jsp").include(request, response);
        		PrintWriter out=response.getWriter();
        		PreparedStatement ps = con.prepareStatement(query);
				int id = (int) session.getAttribute("id");
        		ps.setInt(1,id);
        		ResultSet rs = ps.executeQuery();
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
    	    		out.print("<td><a style=color:blue href=cancelAppointmentServlet?appointment_id="+ rs.getInt("appointment_id")+"> Cancel" +
    	    			 " </td></tr>");
        		}
        		out.print("</tbody> </table>");
        		if(exists==false)out.print("You do not have any appointments for canceling"); 

    			out.close();
        	}catch(Exception e){
        		e.printStackTrace();
        		System.out.println(e);
	    		response.getWriter().print("Something gone wrong please try again later!");

        	}
			
		}
	}
	
	}
