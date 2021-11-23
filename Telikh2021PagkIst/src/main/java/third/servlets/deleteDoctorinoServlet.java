package third.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import third.DBplay2;

/**
 * Servlet implementation class deleteDoctorinoServlet
 */
@WebServlet("/deleteDoctorinoServlet")
public class deleteDoctorinoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public deleteDoctorinoServlet() {
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
		 HttpSession session = request.getSession(false); //Create session if not exists
	        response.setHeader("Cache-Control","no-cache");
	        response.setHeader("Cache-Control","no-store");
	        response.setHeader("Pragma","no-cache");
	        response.setDateHeader ("Expires", 0);

	        if(session==null || session.getAttribute("usertype")== null) {
	        	response.sendRedirect("redirectionjsp.jsp");
		    }else { 
		
		        DBplay2 db = new DBplay2();
		        Connection con = db.getCon();
		        String query= "DELETE FROM doctorappointment.doctor where doctoramka =? and username=?";
		        try {
					PreparedStatement ps = con.prepareStatement(query);
					int id = Integer.parseInt(request.getParameter("deldoc"));
					ps.setInt(1, id);
					ps.setString(2, request.getParameter("docuname"));
					ps.executeUpdate();
					String query2 = "DELETE FROM doctorappointment.allusers where username =?";
					PreparedStatement ps2 = con.prepareStatement(query2);
					ps2.setString(1,request.getParameter("docuname"));
					ps2.executeUpdate();
			        String query3= "DELETE FROM doctorappointment.doctor where doctoramka =? and username=?";
			        
					request.getRequestDispatcher("AdminIndex.jsp").include(request, response);
					response.getWriter().print("Doctor deleted succesfully!");
				} catch (SQLException e) {
					request.getRequestDispatcher("AdminIndex.jsp").include(request, response);
					response.getWriter().print("Doctor did not delete, check again id!!");
				}
		    }


	}
}