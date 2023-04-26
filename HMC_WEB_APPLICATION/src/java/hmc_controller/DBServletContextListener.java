/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmc_controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author k_gsquared
 */

public class DBServletContextListener implements ServletContextListener {

    private Connection conn = null;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();

        //getting the database connection using servlet context parameter in web.xml file
        String url = sce.getServletContext().getAttribute("db").toString();

        // database connection parameters
       // String url = ;
     

        try {
            conn = DriverManager.getConnection(url);
            sc.setAttribute("conn", conn);

        } catch (SQLException e) {
            System.err.println("database did not connect");
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // close the database connection
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("something went wrong closing the database");
            }
        }
    }
}
