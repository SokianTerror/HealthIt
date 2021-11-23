package third.xrhstes;

import java.util.Scanner;

public class Admin extends Users{
    public Admin(String usrnm, String passwd) {
        super(usrnm, passwd);
    }
    public void InsertDoctorine(){
        Scanner scadm3 = new Scanner(System.in);
        System.out.println("Please add doctor's name: ");
        scadm3.nextLine();
        System.out.println("Please add doctor's surname: ");
        scadm3.nextLine();
        System.out.println("Please add doctor's username: ");
        scadm3.nextLine();
        System.out.println("Please add doctor's password: ");
        scadm3.nextLine();
        System.out.println("Doctorini Added!");
        scadm3.close();
    }
    public void DeleteDoctorine(){
        Scanner scadm = new Scanner(System.in);
        scadm.nextLine();
        System.out.println("Beep beep boop Deleted!!");
        scadm.close();
    }
    public void EditAppointment(){
        Scanner scadm2 = new Scanner(System.in);
        scadm2.nextLine();
        System.out.println("Beep beep boop Edited!!");
        scadm2.close();
    }
}
