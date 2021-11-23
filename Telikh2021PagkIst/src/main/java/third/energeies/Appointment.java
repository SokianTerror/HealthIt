package third.energeies;

import javax.print.Doc;
import third.xrhstes.*;
import java.util.Scanner;

public class Appointment {

    Boolean[][] pepe = new Boolean[31][24];
    public void makeAppointment(){
        Scanner scapp = new Scanner(System.in);
        Doctor doctor = new Doctor(" ");
        Patient patient = new Patient(123);
        System.out.println("Type the number of day: ");
        int day = scapp.nextInt();
        System.out.println("Type the hour: ");
        int hour = scapp.nextInt();
        System.out.println("Appointment added succesfully");
        System.out.println("Some of the information you added is invalid, or you have another appointment same time!");
        scapp.close();
    }

    public void deleteAppointmentToMonth(){
        Scanner scde = new Scanner(System.in);
        Doctor doctor = new Doctor(" ");
        Patient patient = new Patient(123);
        System.out.println("Type the number of day: ");
        int day = scde.nextInt();
        System.out.println("Type the hour: ");
        int hour = scde.nextInt();
        System.out.println("Appointment deleted succesfully");
        System.out.println("Some of the information you added is invalid, or you have another appointment same time!");
        scde.close();
    }
}
