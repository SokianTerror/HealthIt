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
 * Servlet implementation class cancelAppointmentDoctorServlet
 */
@WebServlet("/cancelAppointmentDoctorServlet")
public class cancelAppointmentDoctorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		String url_query = request.getQueryString();
		if(session == null || session.getAttribute("usertype")== null || !session.getAttribute("usertype").equals("doctor") || url_query == null) {
        	response.sendRedirect("redirectionjsp.jsp");
			System.out.println("RD");

		}else {
			request.getRequestDispatcher("cancelAppointmentDoctor.jsp").include(request, response);
			String[] param = url_query.split("=");
			int appointment_id = Integer.parseInt(param[1]);
			System.out.println(appointment_id);

			String query = "DELETE FROM doctorappointment.appointment where appointment_id = ?;";
			DBplay2 db = new DBplay2();
			Connection con = db.getCon();
			try {
				PreparedStatement ps = con.prepareStatement(query);
				ps.setInt(1, appointment_id);
				int rs = ps.executeUpdate();
				if (rs == 1) {
					response.getWriter().print("Your appointment canceled succesfully!");
				}else {
					response.getWriter().print("Your appointment could not get canceled!");


				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				response.getWriter().print("An error has occured, please try again later!");

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
		if(session == null || session.getAttribute("usertype")== null || !session.getAttribute("usertype").equals("doctor")) {
        	response.sendRedirect("redirectionjsp.jsp");
		}else {
		    DBplay2 db = new DBplay2();
            Connection con = db.getCon();
        	String query = "SELECT date, startslottime, userid, appointment_id\n"
        			+ "FROM doctorappointment.appointment, doctorappointment.patient\n"
        			+ "WHERE doctor_doctoramka = ? and patientamka = patient_patientamka\n"
        			+ "and CURRENT_TIMESTAMP - interval '3' day < startslottime;";
        	try {
        		request.getRequestDispatcher("cancelAppointmentDoctor.jsp").include(request, response);
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
    		    	                "<th>Username</th>" +
    		    	                "<th>Action</th>" +
    		    	            "</tr>" +
    		    	    		"</thead>");
        				exists=true;
        			}
    	    		out.print("<tr> <td>" + rs.getString("date".toString())+"</td>");
    	    		out.print("<td>" + rs.getString("startslottime".toString())+"</td>");
    	    		out.print("<td>" + rs.getString("userid".toString()) +"</td>");
    	    		out.print("<td><a style=color:blue href=cancelAppointmentDoctorServlet?appointment_id="+ rs.getInt("appointment_id")+"> Cancel" +
    	    			 " </td></tr>");
        		}
        		out.print("</tbody> </table>");
        		if(exists==false)out.print("You do not have any appointments for canceling"); 

    			out.close();
        	}catch(Exception e){
        		System.out.println(e);
	    		response.getWriter().print("Something gone wrong please try again later!");

        	}
			
		}
	}
}
