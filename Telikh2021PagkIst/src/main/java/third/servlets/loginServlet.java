package third.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
//import mainpackage.SQLException;
import third.DBplay2;
import third.energeies.Encrypt;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public loginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		DBplay2 db = new DBplay2();   //make a db arguement
		Connection con = db.getCon(); //connection with db
        HttpSession session = request.getSession(); //Create session if not exists
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Cache-Control","no-store");
        response.setHeader("Pragma","no-cache");
        response.setDateHeader ("Expires", 0);
		String uname = request.getParameter("uname");
		String pass = request.getParameter("password");
		String salt_query = "SELECT * FROM doctorappointment.allusers WHERE username = ?"; //search query
		Boolean flag = false;
		try {
			PreparedStatement salt_ps = con.prepareStatement(salt_query);
			salt_ps.setString(1, uname);  
			ResultSet salt_resultSet = salt_ps.executeQuery();
			if (salt_resultSet.next()) {
				String salt = salt_resultSet.getString("salt");
				pass = Encrypt.getHashMD5(pass, salt);
				String type = salt_resultSet.getString("usertype");
				if(type.equals("admin")) {
					String query = "SELECT * FROM doctorappointment.admin WHERE username = ? and hashedpassword = ?"; //search query
					try {
						PreparedStatement ps = con.prepareStatement(query);
						ps.setString(1, uname);  
						ps.setString(2, pass);  
						ResultSet resultSet = ps.executeQuery();
						if(resultSet.next()) {  //If user exists set Attributes to session and redirect to patientIndex
							session.setAttribute("usertype", "admin");
							session.setAttribute("uname", resultSet.getString("username"));
							session.setAttribute("id", resultSet.getInt("userid"));
							response.sendRedirect("AdminIndex.jsp");
						}else {
							flag = true;
						}
					}catch(Exception e) {e.printStackTrace();}
							
				}else if(type.equals("patient")) {
					String query = "SELECT * FROM doctorappointment.patient WHERE userid = ? and hashedpassword = ?"; //search query
					try {
						PreparedStatement ps = con.prepareStatement(query);
						ps.setString(1, uname);  
						ps.setString(2, pass);  
						ResultSet resultSet = ps.executeQuery();
						if(resultSet.next()) {  //If user exists set Attributes to session and redirect to patientIndex
							session.setAttribute("usertype", "patient");
							session.setAttribute("uname", resultSet.getString("userid"));
							session.setAttribute("name", resultSet.getString("name"));
							session.setAttribute("surname", resultSet.getString("surname"));
							session.setAttribute("id", resultSet.getInt("patientamka"));
							response.sendRedirect("PatientIndex.jsp");
						}else {
							flag = true;
						}
					}catch(Exception e) {e.printStackTrace();}
					
				}else if(type.equals("doctor")) {
					String query = "SELECT * FROM doctorappointment.doctor WHERE username = ? and hashedpassword = ?"; //search query
					try {
						PreparedStatement ps = con.prepareStatement(query);
						ps.setString(1, uname);  
						ps.setString(2, pass);  
						ResultSet resultSet = ps.executeQuery();
						if(resultSet.next()) {  //If user exists set Attributes to session and redirect to patientIndex
							session.setAttribute("usertype", "doctor");
							session.setAttribute("name", resultSet.getString("name"));
							session.setAttribute("surname", resultSet.getString("surname"));
							session.setAttribute("id", resultSet.getInt("doctoramka"));
							session.setAttribute("uname", resultSet.getString("username"));
							response.sendRedirect("DoctorIndex.jsp");
						}else {
							flag = true;
						}
					}catch(Exception e) {e.printStackTrace();}
				}
			}else {
				flag = true;
			}
		}catch (Exception e) {e.printStackTrace();}
		if (flag==true) {
			request.getRequestDispatcher("login.jsp").include(request, response); //print patientindex 
			PrintWriter out=response.getWriter();
			out.print("Wrong username and/or password!");
		}
	
	}
		
}