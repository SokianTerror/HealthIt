package third.xrhstes;

import java.util.Scanner;

public class Patient extends Users {
    private  int amka;
    private String iname;
    public Patient(String usrnm, String passwd, String nm, String snm,int am) {
      super(usrnm, passwd, nm, snm);
        amka = am;
        iname = usrnm;
    }
    public Patient(int amka){
        super();

    }
    public int getAmka(){return amka;}
    public String getUsername() {return iname;}


    public void registration(){
        Scanner scpat = new Scanner(System.in);
        System.out.println("Please Give us your Username:");
        scpat.nextLine();
        System.out.println("Please Give us your Password:");
        String password = scpat.nextLine();
        System.out.println("Please confirm your password:");
        String password2 = scpat.nextLine();
        System.out.println("Please Give us your amka:");
        scpat.nextInt();
        if(password.equals(password2)){
            System.out.println("Beep beep bop\nSuccesfull Registration");
        }
        scpat.close();
    }
    public void dateForRequestedDoctor(){
        Scanner scpat2 = new Scanner(System.in);
        System.out.println("Please type the name of Doctor who wants to test you: ");
        scpat2.nextLine();
        System.out.println("When would you like to get a date?");
        scpat2.nextLine();
        scpat2.close();
    }
    public void dateHistory(){
        System.out.println("History: ...");
    }

    public void nextDates(){
        System.out.println("Your next dates are: ");
    }

}
