// login manager

package hmc_model.ManagerClasses;

import hmc_model.Login;
import hmc_model.Occupation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginManager implements HMCDBCRUD {
    
    private String loginSelectSQL   = "SELECT * FROM login;";
    private String loginInsertSQL   = "INSERT INTO TABLE login VALUES(?,?,?);";
    private String loginAlterSQL    = "ALTER TABLE login SET username = WHERE username;";
    private boolean loginIsCorrect  = false;
    private Login login;
    private String loginTable       = "Login";
    private Connection conn         = null;
    private PreparedStatement ps    = null;
    private ResultSet rs            = null;
    private Occupation occup = null;
    private OccupationManager occpManger  = null;

    public LoginManager() {
        occup = new Occupation();
        login = new Login();
        occpManger = new OccupationManager();
    }
    
    
    
    /*
    @return list of all login credentials stored in the database
    */
    public ArrayList<Login> listTableRows() throws SQLException {
            // execute sql select statement 
            ps = conn.prepareStatement(loginSelectSQL);
            rs = ps.executeQuery();
            
            // list of all users in the login table
            ArrayList<Login> loginCredentials = new ArrayList<Login>();
            while (rs.next()) {
                // confirm if user credentials exist in the database
                login.setUsername(rs.getString("username"));
                login.setPassword(rs.getString("password"));
                // add object to db
                loginCredentials.add(login);
                
            } // end while loop
            return loginCredentials;
    }
    
    /*
      insert a row in login database
    */
    public void insertTableRow() throws SQLException {
        ps = conn.prepareStatement(loginSelectSQL);
        rs = ps.executeQuery();
    }
    
    /*
      change login credentials already stored in the database
    */
    public void alterTableRow() throws SQLException {
    
    }
    
    /*
      @param Login - this the login credentials entered by the user
      @return boolean value indicating whether the login credentials are correct
    */
    public boolean loginIsValid(Login ulogin) throws SQLException {
        // login details in the database
        ArrayList<Login> credentialsInDB = listTableRows();
        while (rs.next()) {
                // confirm if user credentials exist in the database
                if (ulogin.getUsername().equals(rs.getString("username")) && ulogin.getPassword().equals(rs.getString("password"))) {
                       loginIsCorrect = true;
                      
                       
                       Integer jobid = ulogin.getJobid().getJobid();
                       occup.setJobid(jobid);
                       ulogin.setJobid(occup);
                    
                       return loginIsCorrect;
                } 
        }
        return loginIsCorrect;
    }
    
    /*
      @return String representation of the type of user
    */
    public String getUserType() throws SQLException {
        return  new OccupationManager().jobDesc(occup).getJobdesc();
    }
    
    /*
    @param database <b>conn</b> connection object
    */
    @Override
    public void setConnection(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insertTableRow(Object obj) throws SQLException {
    }

    @Override
    public void alterTableRow(Object obj) throws SQLException {
    }


} // end LoginManager
