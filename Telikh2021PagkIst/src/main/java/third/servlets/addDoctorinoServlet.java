package third.servlets;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import third.DBplay2;
import third.energeies.Encrypt;

/**
 * Servlet implementation class addDoctorinoServlet
 */
@WebServlet("/addDoctorinoServlet")
public class addDoctorinoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public addDoctorinoServlet() {
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
    
    
	protected void doPost(HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        HttpSession session = request.getSession(false); //Create session if not exists
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Cache-Control","no-store");
        response.setHeader("Pragma","no-cache");
        response.setDateHeader ("Expires", 0);

        if(session.getAttribute("usertype")== null || !session.getAttribute("usertype").equals("admin")){
        	response.sendRedirect("redirectionjsp.jsp");
	    }else { 
	
	        DBplay2 db = new DBplay2();
	
	        Connection con = db.getCon();
	
	        String query = "INSERT INTO doctorappointment.doctor VALUES(?,?,?,?,?,?,?,?)";
	        try {
	        	SecureRandom random = new SecureRandom();
	    		byte bytes[]= new byte[20];
	    		random.nextBytes(bytes);
				PreparedStatement ps = con.prepareStatement(query);
				String salt = random.toString();
				String pass = Encrypt.getHashMD5(request.getParameter("password"), salt);
				int doc_id = Integer.parseInt(request.getParameter("doc_amka"));
				ps.setInt(1,doc_id);
				ps.setString(2,request.getParameter("username"));
				ps.setString(3,pass);
				ps.setString(4,request.getParameter("name"));
				ps.setString(5,request.getParameter("surname"));
				ps.setString(6,request.getParameter("speciality"));
				ps.setInt(7,(int)session.getAttribute("id"));
				ps.setString(8,salt);
				ps.executeUpdate();
				String query2 = "INSERT INTO doctorappointment.allusers VALUES(?,?,?,?)";
				PreparedStatement ps2 = con.prepareStatement(query2);
				ps2.setString(1,request.getParameter("username"));
				ps2.setString(2,pass);
				ps2.setString(3,"doctor");
				ps2.setString(4,salt);
				ps2.executeUpdate();

				request.getRequestDispatcher("AdminIndex.jsp").include(request,response);
				response.getWriter().print("Doctorino succesfully inserted!");
	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				request.getRequestDispatcher("AdminIndex.jsp").include(request,response);
	
				response.getWriter().print("Could not insert doctor!");
	
			}

	    }	
    }

}
