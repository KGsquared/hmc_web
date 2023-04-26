/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hmc_model;
/**
 *
 * @author Approve
 */
public class Login {
    private String username;
    private String password;
    private Occupation jobid;
    
    public Login() {
    }

    public Login(String username, String password, Occupation jobid) {
        this.username = username;
        this.password = password;
        this.jobid = jobid;
    }

    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Occupation getJobid() {
        return jobid;
    }

    public void setJobid(Occupation jobid) {
        this.jobid = jobid;
    }
    
    

    @Override
    public String toString() {
        return "login{" + "username=" + username + 
               ", password=" + password + '}';
    }
} // end the Login class
