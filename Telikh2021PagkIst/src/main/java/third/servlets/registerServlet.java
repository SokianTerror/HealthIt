package third.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import third.DBplay2;
import third.energeies.Encrypt;

/**
 * Servlet implementation class registerServlet
 */
@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		DBplay2 db = new DBplay2();
		Connection con = db.getCon();
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		Boolean flag = false;
		try {
			SecureRandom random = new SecureRandom();
    		byte bytes[]= new byte[20];
    		random.nextBytes(bytes);
			String salt = random.toString();
			String pass = Encrypt.getHashMD5(request.getParameter("password"), salt);
			int new_id = findNewId(con);
			String query = "INSERT INTO doctorappointment.patient VALUES (?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,new_id);
			ps.setString(2, username);
			ps.setString(3, pass);
			ps.setString(4, name);
			ps.setString(5, surname);
			ps.setString(6, salt);
			int rs = ps.executeUpdate();
			if(rs==1) {
				HttpSession session = request.getSession(); 
				session.setAttribute("usertype", "patient");
				session.setAttribute("uname", username);
				session.setAttribute("name", name);
				session.setAttribute("surname", surname);
				session.setAttribute("id", new_id);
				String query2 = "INSERT INTO doctorappointment.allusers VALUES (?,?,?,?)";
				PreparedStatement ps2 = con.prepareStatement(query2);
				ps2.setString(1,username);
				ps2.setString(2, pass);
				ps2.setString(3,"patient");
				ps2.setString(4, salt);
				int rs2 = ps2.executeUpdate();
				if (rs2 == 1)
					response.sendRedirect("PatientIndex.jsp");
				else {
					flag = true;
				}
			}else {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = true;
		}
		if (flag == true) {
			request.getRequestDispatcher("Register.jsp").include(request, response); //print patientindex 
			response.getWriter().print("An error has occured please try again later!");
		}
	}
	
	private int findNewId(Connection con) throws SQLException {
		String query = "Select * from doctorappointment.patient ORDER BY patientamka DESC LIMIT 1";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		if(rs.next())
			return rs.getInt(1) + 1;
		else
			return 1;
	}

}
