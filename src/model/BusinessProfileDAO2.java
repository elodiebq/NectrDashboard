package model;


import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;








import databeans.BusinessProfileBean;
import databeans.CampaignBean;
import databeans.RegionBean;

public class BusinessProfileDAO2{
	private List<Connection> connectionPool = new ArrayList<Connection>();
	private String jdbcDriver;
	private String jdbcURL;
	private String tableName;
   // private final String SELECT_QUERY = "SELECT * FROM region INNER JOIN businessprofile ON region.regionId = businessprofile.Id";
    public BusinessProfileDAO2(String jdbcDriver, String jdbcURL, String tableName) throws MyDAOException {
		this.jdbcDriver = jdbcDriver;
		this.jdbcURL = jdbcURL;
		this.tableName = tableName;
	}
	
	private synchronized Connection getConnection() throws MyDAOException {
		if (connectionPool.size() > 0) {
			return connectionPool.remove(connectionPool.size() - 1);
		}

		try {
			Class.forName(jdbcDriver);
		} catch (ClassNotFoundException e) {
			throw new MyDAOException(e);
		}

		try {
			return DriverManager.getConnection(jdbcURL);
		} catch (SQLException e) {
			throw new MyDAOException(e);
		}
	}
	
	private synchronized void releaseConnection(Connection con) {
		connectionPool.add(con);
	}
	
	
	
	public BusinessProfileBean getBusinessById(int name) throws MyDAOException {
	    Connection con = null;
        try {
        	 con = getConnection();

             PreparedStatement pstmt = con.prepareStatement("SELECT * FROM "
                     + "businessprofile " + "WHERE business_id = " + name);
            
             ResultSet rs = pstmt.executeQuery();
             BusinessProfileBean business;
             if(!rs.next()){
            	 business = null;
             }else{
             
             business = new BusinessProfileBean();
             
         
                 
                 business.setBusiness_id(Integer.parseInt(rs.getString("business_id")));
                 business.setName(rs.getString("name"));
                 business.setImage(rs.getString("image"));
             }    
             rs.close();
             pstmt.close();
             releaseConnection(con);
             return business;
        } catch (Exception e) {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e2) { /* ignore */
            }
            throw new MyDAOException(e);
        }
    }


	public List<BusinessProfileBean> getBusinessByRegionId(int name) throws MyDAOException {
	    Connection con = null;
        try {
            con = getConnection();


            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM "
                    + "businessprofile "+"WHERE regionId="+ name );
           //pstmt.setInt(1, name);
            ResultSet rs = pstmt.executeQuery();
            
            List<BusinessProfileBean> regionlist = new ArrayList<BusinessProfileBean>();
            
            while (rs.next()){
                
                BusinessProfileBean temp = new BusinessProfileBean();
                temp.setRegionId(Integer.parseInt(rs.getString("regionId")));
                temp.setBusiness_id(Integer.parseInt(rs.getString("business_id")));
               
                regionlist.add(temp);
            }

            rs.close();
            pstmt.close();
            releaseConnection(con);
            return regionlist;

        } catch (Exception e) {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e2) { /* ignore */
            }
            throw new MyDAOException(e);
        }
    }
}
