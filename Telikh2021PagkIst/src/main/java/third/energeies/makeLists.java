package third.energeies;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import third.DBplay2;
import third.xrhstes.Doctor;

public class makeLists {

	public static ArrayList<Doctor> makedoclists() {
	DBplay2 db = new DBplay2();
    Connection con = db.getCon();
	String query = "select * from doctorappointment.doctor";
	ArrayList<Doctor> doctorList = new ArrayList<Doctor>();
	try {
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		while(resultSet.next()) {
			int doctoramka = resultSet.getInt("doctoramka");
			String username = resultSet.getString("username");
			String speciality = resultSet.getString("specialty");
			Doctor doc = new Doctor(doctoramka, username, speciality);
			doctorList.add(doc);
		}
		resultSet.close();
	    statement.close();
	    con.close();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return doctorList;

	
}
}
