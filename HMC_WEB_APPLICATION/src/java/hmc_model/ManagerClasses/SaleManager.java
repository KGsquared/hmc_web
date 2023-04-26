/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmc_model.ManagerClasses;

import hmc_model.Employee;
import hmc_model.Sale;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class SaleManager implements HMCDBCRUD{

    private String saleSelectSQL = "SELECT * FROM sale";
      
      private Connection conn   = null;
      private Sale sale;
      private Employee emp;
      private PreparedStatement ps  = null;
      private ResultSet rs = null;

    public SaleManager() {
        this.sale = new Sale();
        this.emp = new Employee();
    }

    @Override
    public ArrayList<?> listTableRows() throws SQLException {
        
         ArrayList<Sale> listSale = new ArrayList<>();
          while (rs.next()) {
                
                Integer invno = rs.getInt("invno");
                Date invdate = rs.getDate("invdate"); // change the invdate data type in database to Date
                Integer capture = rs.getInt("capture");
                emp.setEmpno(capture);
                
                sale.setInvno(invno);
                sale.setInvdate(invdate.toLocalDate());
                sale.setCapture(emp);
                
                listSale.add(sale);
          }
          return listSale;
  
    }

    @Override    
    public void setConnection(Connection conn) {
      this.conn = conn;
    }

    @Override
    public void insertTableRow(Object obj) throws SQLException {
        
    }

    @Override
    public void alterTableRow(Object obj) throws SQLException {
   
    }
    
}
