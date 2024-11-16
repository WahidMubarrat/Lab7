import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public  class User{
    String username;
    String email;
    String password;
    String id;
    String type;
    public  User(String id,String username,String email,String password,String type) {
        username = this.username;
        email = this.email;
        password = this.password;
        id = this.id;
        type = this.type;

    }
    public String getUsername(){
    return username;}
    public String getId(){
        return id;}
    public String getEmail(){
        return email;}
    public String getPassword(){
        return password;}
    public String getType(){
        return type;}

    public String toString() {
        return  username + "," + email + "," + password + "," +id+","+ type;
    }

    }



