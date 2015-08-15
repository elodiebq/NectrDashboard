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

public class CampaignDAO2{
	private List<Connection> connectionPool = new ArrayList<Connection>();
	private String jdbcDriver;
	private String jdbcURL;
	private String tableName;
	public CampaignDAO2(String jdbcDriver, String jdbcURL, String tableName) throws MyDAOException {
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
	public List<CampaignBean> getCampaignByCampaign(int name) throws MyDAOException {
	    Connection con = null;
        try {
            con = getConnection();

            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM "
                    + "campaign " + "WHERE business_id = " + name);
           
            ResultSet rs = pstmt.executeQuery();
            
            List<CampaignBean> regionlist = new ArrayList<CampaignBean>();
            
            while (rs.next()){
                
                CampaignBean temp = new CampaignBean();
                temp.setCampaign_id((Integer.parseInt(rs.getString("campaign_id"))));
                temp.setBusiness_id(Integer.parseInt(rs.getString("business_id")));
                temp.setInStore((Integer.parseInt(rs.getString("inStore"))));
                temp.setType(((Integer.parseInt(rs.getString("type")))));
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
