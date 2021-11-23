package third.xrhstes;

import java.util.Scanner;

public class Doctor extends Users {

    private String speciality,username;
    private int doctoramka;
    public Doctor(String usrnm, String passwd, String nm, String snm, String sp) {
        super(usrnm, passwd, nm, snm);
        this.speciality = sp;
    }
    public Doctor(String usrnm){
        super();
    }
    
    public Doctor(int doctoramka, String username, String speciality){
        super();
        setDoctorAmka(doctoramka);
        setUsername(username);
        setSpeciality(speciality);
        
    }
    
    public void setDoctorAmka(int doctoramka) {this.doctoramka = doctoramka;}
    
    public void setUsername(String username) {this.username = username;}

    public void setSpeciality(String speciality) {this.speciality= speciality;}

    
    public String getSpeciality() {return speciality;}
    public String getUsername() { return username; }
    public int getDoctorAmka() { return doctoramka; }

    public void appointmentHistory(){
        System.out.println("Your appointment history is: ");
    }

    public void nextAppointment(){
        System.out.println("Your next appointment is: ");
    }

    public void freeTime(){
        System.out.println("Your free time for a Appointment is: ...");
    }

}
