package hmc_model.ManagerClasses;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author k_gsquared
 */
public interface HMCDBCRUD {
    /*
        HMC Database CRUD Operations 
    */   
    public ArrayList<?> listTableRows()  throws SQLException;
    public void insertTableRow(Object obj) throws SQLException;
    public void alterTableRow(Object obj) throws SQLException;
    public void setConnection(Connection conn) throws SQLException;
} 
