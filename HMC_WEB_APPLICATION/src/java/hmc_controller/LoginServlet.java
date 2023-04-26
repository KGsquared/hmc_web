/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmc_controller;


// Service Class is a business model which represents the a single item 
// Service Is Model which represents single row in the database 
import hmc_model.Login;
import hmc_model.ManagerClasses.LoginManager;
import hmc_model.Service;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
@WebServlet("/LoginServlet.do")
public class LoginServlet extends HttpServlet {
    // statement to execute when user logs in 
    private String sql = "SELECT * FROM login";
    private String loginDSql = "SELECT descrption FROM job, login where login.jobid = job.jobid";
    private String sqlServ = "SELECT * FROM services";
    private String sqlInv = "SELECT invno from sales;";
    private List<Service> servList = new ArrayList<>();
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private RequestDispatcher rdp = null;
    //private LoginManager LoginManager;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // cureent session of the user logged on
        HttpSession session = request.getSession();
        
        
        
        // username stored entered by the user
        String username = session.getAttribute("username").toString();
        String password = session.getAttribute("password").toString();
        
        // enscapulation of the class members 
        Login ulogin = new Login();
        ulogin.setUsername(username);
        ulogin.setPassword(password);
        
        // models of the objects offered by the business
        Service serv = new Service();
        
        try {
            // Get The ServletContextListener 
            // Obtain the prepared statement
            conn = (Connection) getServletContext().getAttribute("conn");
           
            
            // user name and password confirmation fiels
            
            LoginManager logim = new LoginManager();
            
           // logim.setConnection(conn);
            
            // confirm the credentinals entered by the user
            if(logim.loginIsValid(ulogin)) {
                // credentials entered are correct
                ps = conn.prepareStatement(loginDSql);
                rs = ps.executeQuery();
                
                // open revelevant JSP page 
                while(rs.next()) {
                    // confirm user type
                    // open JSP
                    if(logim.getUserType().equalsIgnoreCase("cashier")) {
                       ps = conn.prepareStatement(sqlServ);
                       rs = ps.executeQuery();
                       // obtain the attributes from the SQL database
                       // build services array list
                       while(rs.next()){
                             serv.setService_id(rs.getInt(1));
                             serv.setDescription(rs.getString(2));
                             serv.setPrice(rs.getDouble(3));
                             serv.setVat(rs.getDouble(4));
                             servList.add(serv);
                       }
                       
                       // getting last invoice number
                       rs = ps.getResultSet();
                       Integer invNo;
                       while(rs.next()){
                           invNo = rs.getInt(1);
                           session.setAttribute("invNo", invNo);
                       }
                        session.setAttribute("servList", servList);
                        rdp = request.getRequestDispatcher("pos.jsp");
                    } else {
                        // do nothing at this point 
                        // ill add code to forward the request to revelevant code once it's available
                    }
                }
                if(rdp != null) rdp.forward(request, response);
            } else {
                // username or password is incorrect
             
            }
        } catch (SQLException ex) {
               System.err.print("sql in login verification failed");
        }
    }
}// end class
