/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmc_model.ManagerClasses;

import hmc_model.Employee;
import hmc_model.Inventory;
import hmc_model.Location;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class InventoryManager implements HMCDBCRUD{
     private String saleSelectSQL = "SELECT * FROM inventory";
      private Connection conn = null;
      private Inventory inventory;
      private Employee emp;
      private Location location;
      private PreparedStatement ps  = null;
      private ResultSet rs = null;

    public InventoryManager() {
        inventory =  new Inventory();
        emp = new Employee();
        location = new Location();
    }

    @Override
    public ArrayList<?> listTableRows() throws SQLException {
         
         ArrayList<Inventory> listInventory = new ArrayList<>();
          while (rs.next()) {
                
                Integer pid = rs.getInt("pid");
                String brand = rs.getString("brand"); // change the invdate data type in database to Date
                String cond = rs.getString("cond");
                String desc = rs.getString("desc");
                Integer locid = rs.getInt("locid");
                
                location.setLocid(locid);
                
                Double cost = rs.getDouble("cost");
                Double price = rs.getDouble("price");
                Integer qty = rs.getInt("qty");
                
                
                inventory.setPid(pid);
                inventory.setBrand(brand);
                inventory.setCond(cond);
                inventory.setDescription(desc);
                inventory.setLocid(location); // here you passed Location object as the param of locid in the class of inventory
                inventory.setCost(cost);
                inventory.setPrice(price);
                inventory.setQuantity(qty);
               
                
                listInventory.add(inventory);
          }
          return listInventory;
         
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
