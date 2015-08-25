package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databeans.BusinessProfileBean;
import databeans.CampaignBean;
import databeans.CustomerAnalysisBean;
import databeans.RegionBean;

public class CustomerAnalysisDAO2 {
    private List<Connection> connectionPool = new ArrayList<Connection>();
    private String jdbcDriver;
    private String jdbcURL;
    private String tableName;

    // private final String SELECT_QUERY =
    // "SELECT * FROM region INNER JOIN businessprofile ON region.regionId = businessprofile.Id";
    public CustomerAnalysisDAO2(String jdbcDriver, String jdbcURL,
            String tableName) throws MyDAOException {
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

    public int getLastId()
            throws MyDAOException {
        Connection con = null;
        try {
            con = getConnection();
            String str = "select id from customeranalysis order by id desc limit 1";
            PreparedStatement pstmt = con.prepareStatement(str);

            System.out.println("STRING SQL" + str);
            ResultSet rs = pstmt.executeQuery(str);
            
            rs.close();
            pstmt.close();
            releaseConnection(con);
            return rs.getConcurrency();
        } catch (Exception e) {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e2) { /* ignore */
            }
            throw new MyDAOException(e);
        }
    }

    public void updateById(int id, String time, String venderid) throws MyDAOException {
  
       Connection con = null;
        
        PreparedStatement pstmt;
        PreparedStatement pstmt1;

        
        try {
            con = getConnection();
            String pre = "select * from customeranalysis where venderId = "+ "\'"+ venderid+"\'"+" and time = "+"\'"+time+"\'";
            pstmt1 = con.prepareStatement(pre);
            ResultSet rs = pstmt1.executeQuery();
            if (!rs.next()) {
            	System.out.println("new record");
                String str = "INSERT INTO customeranalysis (amount_passby, time, business_id,venderId) VALUES (1,\'" 
                        + time + "\'," + id +","+"\'"+ venderid+"\'"+")";
                System.out.println(str);
                pstmt = con.prepareStatement(str);
                pstmt.execute();
                pstmt.close();
            }
      

            pstmt1.close();
            releaseConnection(con);

        } catch (Exception e) {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e2) { /* ignore */
            }
            throw new MyDAOException(e);
        }

    }


    
    
    public String getWalkinByHour(String time) throws MyDAOException {
        Connection con = null;
        PreparedStatement pstmt;
        try {
            con = getConnection();
            time = time.substring(0,10);
            String str = "select * from (select timeRegion,count(*) from (select (convert(substring(time,12,2),unsigned)div 2) as timeRegion, time, business_id,amount_enter,amount_passby from edbd.customeranalysis as temp where (substring(time,1,10)  = \'"+ time +"\') and amount_enter = 1) as final group by timeRegion) as Final where (timeRegion >=0 and timeRegion<=11) order by timeRegion ";
            System.out.println(str);
            pstmt = con.prepareStatement(str);
            pstmt.execute();
            ResultSet rs = pstmt.executeQuery();
            StringBuilder defaultWalkInView = new StringBuilder();
            if (rs.next()) {
                defaultWalkInView.append(rs.getInt(1));
                while (rs.next()) {
                    defaultWalkInView.append("," + rs.getInt(1));
                }
            }
            pstmt.close();
            releaseConnection(con);
            return defaultWalkInView.toString();

        } catch (Exception e) {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e2) { /* ignore */
            }
            throw new MyDAOException(e);
        }

    }

    public void updatewWalkinById(int id, String time, String venderId) throws MyDAOException {
        Connection con = null;
        
        PreparedStatement pstmt;
        PreparedStatement pstmt1;

        
        try {
            con = getConnection();
            String pre = "select * from customeranalysis where venderId = "+ "\'"+ venderId+"\'"+" and time = "+"\'"+time+"\'";
            pstmt1 = con.prepareStatement(pre);
            ResultSet rs = pstmt1.executeQuery();
            if (!rs.next()) {
            	System.out.println("new record");
                String str = "INSERT INTO customeranalysis (amount_enter, time, business_id, venderId) VALUES (1,\'" 
                        + time + "\'," + id +","+ "\'"+ venderId+"\'"+")" ;
                System.out.println(str);
                pstmt = con.prepareStatement(str);
                pstmt.execute();
                pstmt.close();
            }
      

      
            releaseConnection(con);

        } catch (Exception e) {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e2) { /* ignore */
            }
            throw new MyDAOException(e);
        }

    }
    public boolean distinctVisistInHour(String time,String venderId) throws MyDAOException{
        Connection con = null;
        
        PreparedStatement pstmt1;
        try {
            con = getConnection();
            String pre = "select * from customeranalysis where venderId = "+ "\'"+ venderId+"\'"+" and time = "+"\'"+time+"\'";
            pstmt1 = con.prepareStatement(pre);
            ResultSet rs = pstmt1.executeQuery();
            if (!rs.next()) {
                releaseConnection(con);

            	return true;
            }
            releaseConnection(con);

            return false;
      

      

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
