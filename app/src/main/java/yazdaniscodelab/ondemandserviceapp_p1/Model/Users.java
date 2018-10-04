package yazdaniscodelab.ondemandserviceapp_p1.Model;

/**
 * Created by Yazdani on 10/4/2018.
 */

public class Users {

    private String name;
    private String email;
    private String pass;
    private String phone;

    private String userId;
    private String date;

    public Users(){

    }

    public Users(String name, String email, String pass, String phone, String userId, String date) {
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.phone = phone;
        this.userId = userId;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
