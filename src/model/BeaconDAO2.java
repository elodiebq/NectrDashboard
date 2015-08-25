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









import databeans.BeaconBean;
import databeans.BusinessProfileBean;
import databeans.CampaignBean;
import databeans.RegionBean;

public class BeaconDAO2{
	private List<Connection> connectionPool = new ArrayList<Connection>();
	private String jdbcDriver;
	private String jdbcURL;
	private String tableName;
	public BeaconDAO2(String jdbcDriver, String jdbcURL, String tableName) throws MyDAOException {
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
	public List<BeaconBean> getBeacon(int name) throws MyDAOException {
	    Connection con = null;
        try {
            con = getConnection();

            String str = "SELECT * FROM "
                    + "beacon " + "WHERE id=" + name ;
            PreparedStatement pstmt = con.prepareStatement(str);
            System.out.println("sql" + str);
            //pstmt.setInt(1, name);
            ResultSet rs = pstmt.executeQuery();
            
            List<BeaconBean> regionlist = new ArrayList<BeaconBean>();
            
            while (rs.next()){
                
                BeaconBean temp = new BeaconBean();
                temp.setId(((Integer.parseInt(rs.getString("id")))));
                temp.setMajor_value((Integer.parseInt(rs.getString("major_value"))));
                temp.setMinor_value(((Integer.parseInt(rs.getString("minor_value")))));
               
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
    public int getLastId()
            throws MyDAOException {
        Connection con = null;
        try {
            con = getConnection();
            String str = "select id from beacon order by id desc limit 1";
            PreparedStatement pstmt = con.prepareStatement(str);
            int id=0;
            System.out.println(str);
            ResultSet rs = pstmt.executeQuery(str);
            if(rs.next()){
            id = Integer.parseInt(rs.getString("id"));
            }
            rs.close();
            pstmt.close();
            releaseConnection(con);
            return id;
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
