package third.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import third.DBplay2;

/**
 * Servlet implementation class addAppointmentServlet
 */
@WebServlet("/addAppointmentServlet")
public class addAppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public addAppointmentServlet() {
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

        if(session.getAttribute("usertype")== null || !session.getAttribute("usertype").equals("doctor")){
        	response.sendRedirect("redirectionjsp.jsp");
	    }else { 
			String date = request.getParameter("cal");
			String time = request.getParameter("time");
			String[] h_m = time.split(":");
			DBplay2 db = new DBplay2();
	        Connection con = db.getCon();
	        String query = "INSERT INTO doctorappointment.appointment VALUES(?,?,?,?,?,?,?)";
	        String new_time = end_time(time);
	        int id = (int) session.getAttribute("id");
	        int new_id = last_id(con,db);
	        if (new_id==0) {
	        	new_id = 1000;
	        }else {
	        	new_id += 1;
	        }
	        try {
	            
				PreparedStatement ps = con.prepareStatement(query);
				ps.setDate(1, date_new(date));
				ps.setTimestamp(2, new_time(date, time));
				ps.setTimestamp(3, new_time(date, new_time));
				ps.setNull(4, java.sql.Types.INTEGER);
				ps.setInt(5, id);
				ps.setInt(6, 0);
				ps.setInt(7, new_id);
				int rs = ps.executeUpdate();
				if(rs==1) {
					request.getRequestDispatcher("addAppointment.jsp").include(request,response);
					response.getWriter().print("Appointment inserted succesfully!");
				}else {
					request.getRequestDispatcher("addAppointment.jsp").include(request,response);
					response.getWriter().print("Appointment could not insert, try again later!");
				}
				
			} catch (Exception e) {
				request.getRequestDispatcher("addAppointment.jsp").include(request,response);
				response.getWriter().print("An error has occured please try again later!");
			}
	    }

	}
	
	 private Timestamp new_time(String date, String time) throws ParseException {

	        String dateTime = date +" "+ time;
	        DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	        LocalDateTime localDateTime = LocalDateTime.from(formatDateTime.parse(dateTime));
	        Timestamp ts = Timestamp.valueOf(localDateTime);
	        return ts;
	    }
	
	private java.sql.Date date_new(String date) throws ParseException{
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = format.parse(date);
        java.sql.Date new_date = new java.sql.Date(parsed.getTime());
		return new_date;
		
	}
	
	private int last_id(Connection con, DBplay2 db) {
		String query = "Select appointment_id from doctorappointment.appointment ORDER BY appointment_id DESC LIMIT 1;";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			int id;
			if(rs.next()) {
				id = rs.getInt(1);
			}else {
				id = 0;
			}
			return id;
		} catch (SQLException e) {
			return 0;
		}
	}
	
	private String end_time(String time) {
		
		String[] h_m = time.split(":");
		int minutes = Integer.parseInt(h_m[1]);
		int hours = Integer.parseInt(h_m[0]);
		if (minutes + 30 > 60) {
			hours++;
			minutes -=30;
		}else {
			minutes += 30;
		}
		String hours_str, minutes_str;
		if (minutes < 10 ) {
			minutes_str = "0" + String.valueOf(minutes);
		}else {
			minutes_str = String.valueOf(minutes);
		}
		if (hours < 10 ) {
			hours_str = "0" + String.valueOf(hours);
		}else {
			hours_str = String.valueOf(hours);
		}
		time = hours_str +":"+ minutes_str;
		return time;
	}
}
