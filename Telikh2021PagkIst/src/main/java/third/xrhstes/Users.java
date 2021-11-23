package third.xrhstes;

public class Users {

    public static int users_counter;
    private String username,password,name,surname;

    public Users(String usrnm,String passwd,String nm,String snm) {
        username = usrnm;
        password = passwd;
        name = nm;
        surname = snm;
        users_counter++;
    }
    public Users(){
    }
    public Users(String usrnm,String passwd) {
        username = usrnm;
        password = passwd;
        users_counter++;
    }

    public String getUsername(){ return username; }
    public String getPassword(){ return password; }
    public String getName(){ return name; }
    public String getSurname(){ return surname; }

    public void setUsername(String usrnm){username=usrnm;}
    public void setPassword(String passwd){password=passwd;}
    public void setName(String nm){name=nm;}
    public void setSurname(String snm){surname=snm;}













}
