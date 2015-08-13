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






import databeans.RegionBean;

public class RegionDAO2{
	private List<Connection> connectionPool = new ArrayList<Connection>();
	private String jdbcDriver;
	private String jdbcURL;
	private String tableName;
	public RegionDAO2(String jdbcDriver, String jdbcURL, String tableName) throws MyDAOException {
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

	public List<RegionBean> getRegionList() throws MyDAOException {
		Connection con = null;
		try {
			con = getConnection();

			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM "
					+ tableName);
			ResultSet rs = pstmt.executeQuery();
			
			List<RegionBean> regionlist = new ArrayList<RegionBean>();
			
			while (rs.next()){
				
				RegionBean temp = new RegionBean();
				temp.setRegionId(Integer.parseInt(rs.getString("regionId")));
				temp.setCenterLat(Double.parseDouble(rs.getString("centerLat")));
				temp.setCenterLng(Double.parseDouble(rs.getString("centerLng")));
				temp.setRadius(Double.parseDouble(rs.getString("radius")));
				temp.setRegionName(rs.getString("regionName"));
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
