package third;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DBplay2
 */
@WebServlet("/dbplay2")
public class DBplay2 extends HttpServlet implements Provider{
	private static final long serialVersionUID = 1L;

	static Connection con = null;

	public static Connection getCon(){		//Conenction with database 
        try{
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(connURL,username,password); //Elements has been given from Provider.java as we implement it
            System.out.println("Connection established.");

        }catch (Exception e){
            System.out.println("Connection not established.");
            e.printStackTrace();
        }
        return con;

	}
}
	


