package hmc_model.ManagerClasses;

import hmc_model.Occupation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OccupationManager implements HMCDBCRUD {
      private String jobSelectSQL     = "SELECT * FROM job";
      private String loginInsertSQL   = "INSERT INTO TABLE login VALUES(?,?,?)";
      private Connection conn   = null;
       private Occupation job;
      private PreparedStatement ps    = null;
      private ResultSet rs            = null;

    public OccupationManager() {
        job = new Occupation();
    }
	    
      
   public void setConnection(Connection conn) {
          this.conn = conn;
      }
      
    	public ArrayList<Occupation> listTableRows() throws SQLException {
          ArrayList<Occupation> listOfJobs = new ArrayList<>();
          while (rs.next()) {
                // confirm if user credentials exist in the database
                Integer jobid   = rs.getInt("jobid");
                String jobdesc  = rs.getString("jobdesc");
                Double comm     = rs.getDouble("comm");
                job.setJobid(jobid);
                job.setJobdesc(jobdesc);
                job.setComm(comm);
                listOfJobs.add(job);
          }
          return listOfJobs;
    	}
    	
    	/*
    	  @param ujob is the job role of the user who is trying to login
    	  @return  String representation of the user job 
    	*/
    	public Occupation jobDesc(Occupation ujob) throws SQLException {
    	    ArrayList<Occupation> jobs = listTableRows();
          for(int i = 0; i < jobs.size(); i++){
              
              if(jobs.get(i).getJobid().equals(job.getJobid())){ 
                  ujob.setJobdesc(jobs.get(i).getJobdesc());
              }
          }
          return ujob; // return ujob with job description set
      }

    @Override
    public void insertTableRow(Object obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterTableRow(Object obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

} // 
