package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databeans.CampaignBean;
import databeans.CustomerAnalysisBean;
import databeans.RegionBean;

public class CustomerAnalysisDAO extends GenericDAO<CustomerAnalysisBean> {

    public CustomerAnalysisDAO(String tableName, ConnectionPool pool)
            throws DAOException {
        super(CustomerAnalysisBean.class, tableName, pool);
    }

    public String getWalkinByHour(String time) throws SQLException {
        // ConnectionPool con = null;
        PreparedStatement pstmt;

        Connection tmp = DriverManager.getConnection("jdbc:mysql:///test");
        time = time.substring(0, 10);
        String str = "select * from (select timeRegion,count(*) from (select (convert(substring(time,12,2),unsigned)div 2) as timeRegion, time, business_id,amount_enter,amount_passby from test.customeranalysis as temp where (substring(time,1,10)  = \'"
                + time
                + "\') and amount_enter = 1) as final group by timeRegion) as Final where (timeRegion >=0 and timeRegion<=11) order by timeRegion ";
        System.out.println(str);
        pstmt = (tmp).prepareStatement(str);
        pstmt.execute();
        ResultSet rs = pstmt.executeQuery();
        StringBuilder defaultWalkInView = new StringBuilder();
       
        if (rs.next()) {
            defaultWalkInView.append(rs.getInt(2));
            while (rs.next()) {
               
                defaultWalkInView.append("," + rs.getInt(2));
            }
        }
        pstmt.close();
        // con.releaseConnection(tmp);
        return defaultWalkInView.toString();
    }
    public String getWalkbyByHour(String time) throws SQLException {
        // ConnectionPool con = null;
        PreparedStatement pstmt;

        Connection tmp = DriverManager.getConnection("jdbc:mysql:///test");
        time = time.substring(0, 10);
        String str = "select * from (select timeRegion,count(*) from (select (convert(substring(time,12,2),unsigned)div 2) as timeRegion, time, business_id,amount_enter,amount_passby from test.customeranalysis as temp where (substring(time,1,10)  = \'"
                + time
                + "\') and amount_passby = 1) as final group by timeRegion) as Final where (timeRegion >=0 and timeRegion<=11) order by timeRegion ";
        System.out.println(str);
        pstmt = (tmp).prepareStatement(str);
        pstmt.execute();
        ResultSet rs = pstmt.executeQuery();
        StringBuilder defaultWalkInView = new StringBuilder();
       
        if (rs.next()) {
            defaultWalkInView.append(rs.getInt(2));
            while (rs.next()) {
               
                defaultWalkInView.append("," + rs.getInt(2));
            }
        }
        pstmt.close();
        // con.releaseConnection(tmp);
        return defaultWalkInView.toString();
    }
    
    public String getWalkinByDay(String time) throws SQLException {
        // ConnectionPool con = null;
        PreparedStatement pstmt;

        Connection tmp = DriverManager.getConnection("jdbc:mysql:///test");
        time = time.substring(0, 10);
        String str = "select * from (select timeRegion,count(*) from (select (convert(substring(time,1,10),date)) as timeRegion, time, business_id,amount_enter,amount_passby from test.customeranalysis as temp where (convert(substring(time,1,10),date)) >= DATE(DATE_SUB(CONVERT(\'"+time+"\',DATE),INTERVAL 6 DAY)) and amount_enter = 1) as final group by timeRegion) as Final  order by timeRegion" ;
        System.out.println(str);
        pstmt = (tmp).prepareStatement(str);
        pstmt.execute();
        ResultSet rs = pstmt.executeQuery();
        StringBuilder defaultWalkInView = new StringBuilder();
       
        if (rs.next()) {
            defaultWalkInView.append(rs.getInt(2));
            while (rs.next()) {
               
                defaultWalkInView.append("," + rs.getInt(2));
            }
        }
        pstmt.close();
        // con.releaseConnection(tmp);
        return defaultWalkInView.toString();
    }
    
    public String getWalkbyByDay(String time) throws SQLException {
        // ConnectionPool con = null;
        PreparedStatement pstmt;

        Connection tmp = DriverManager.getConnection("jdbc:mysql:///test");
        time = time.substring(0, 10);
        String str = "select * from (select timeRegion,count(*) from (select (convert(substring(time,1,10),date)) as timeRegion, time, business_id,amount_enter,amount_passby from test.customeranalysis as temp where (convert(substring(time,1,10),date)) >= DATE(DATE_SUB(CONVERT(\'"+time+"\',DATE),INTERVAL 6 DAY)) and amount_passby = 1) as final group by timeRegion) as Final  order by timeRegion" ;
        System.out.println(str);
        pstmt = (tmp).prepareStatement(str);
        pstmt.execute();
        ResultSet rs = pstmt.executeQuery();
        StringBuilder defaultWalkInView = new StringBuilder();
       
        if (rs.next()) {
            defaultWalkInView.append(rs.getInt(2));
            while (rs.next()) {
               
                defaultWalkInView.append("," + rs.getInt(2));
            }
        }
        pstmt.close();
        // con.releaseConnection(tmp);
        return defaultWalkInView.toString();
    }
    
    public String getWalkinByMonth(String timeFrom, String timeTo) throws SQLException {
        // ConnectionPool con = null;
        PreparedStatement pstmt;

        Connection tmp = DriverManager.getConnection("jdbc:mysql:///test");
        
        String str ="select * from (select timeRegion,count(*) from (select (substring(time,1,7) )as timeRegion, time, business_id,amount_enter,amount_passby from test.customeranalysis where (((convert(substring(time,1,4),unsigned)) * 12 + convert(substring(time,6,2),unsigned)) between (convert(substring(\'" + timeFrom+"\',1,4),unsigned)) * 12 + convert(substring(\'"+timeFrom+"\',6,2),unsigned) and (convert(substring(\'" +timeTo +"\',1,4),unsigned)) * 12 + convert(substring(\'" + timeTo+"\',6,2),unsigned)) and amount_enter >= 1) as temp group by timeRegion) as Final";
        System.out.println(str);
        pstmt = (tmp).prepareStatement(str);
        pstmt.execute();
        ResultSet rs = pstmt.executeQuery();
        StringBuilder defaultWalkInView = new StringBuilder();
       
        if (rs.next()) {
            defaultWalkInView.append(rs.getInt(2));
            while (rs.next()) {
               
                defaultWalkInView.append("," + rs.getInt(2));
            }
        }
        pstmt.close();
        // con.releaseConnection(tmp);
        return defaultWalkInView.toString();
    }
    
    public String getWalkbyByMonth(String timeFrom, String timeTo) throws SQLException {
        // ConnectionPool con = null;
        PreparedStatement pstmt;

        Connection tmp = DriverManager.getConnection("jdbc:mysql:///test");
        
        String str ="select * from (select timeRegion,count(*) from (select (substring(time,1,7) )as timeRegion, time, business_id,amount_enter,amount_passby from test.customeranalysis where (((convert(substring(time,1,4),unsigned)) * 12 + convert(substring(time,6,2),unsigned)) between (convert(substring(\'" + timeFrom+"\',1,4),unsigned)) * 12 + convert(substring(\'"+timeFrom+"\',6,2),unsigned) and (convert(substring(\'" +timeTo +"\',1,4),unsigned)) * 12 + convert(substring(\'" + timeTo+"\',6,2),unsigned)) and amount_passby >= 1) as temp group by timeRegion) as Final";
        System.out.println(str);
        pstmt = (tmp).prepareStatement(str);
        pstmt.execute();
        ResultSet rs = pstmt.executeQuery();
        StringBuilder defaultWalkInView = new StringBuilder();
       
        if (rs.next()) {
            defaultWalkInView.append(rs.getInt(2));
            while (rs.next()) {
               
                defaultWalkInView.append("," + rs.getInt(2));
            }
        }
        pstmt.close();
        // con.releaseConnection(tmp);
        return defaultWalkInView.toString();
    }
    
    public String getWalkinByYear(String timeFrom, String timeTo) throws SQLException {
        // ConnectionPool con = null;
        PreparedStatement pstmt;

        Connection tmp = DriverManager.getConnection("jdbc:mysql:///test");
        String str = "select * from (select timeRegion,count(*) from (select (substring(time,1,4) )as timeRegion, time, business_id,amount_enter,amount_passby from test.customeranalysis where (((convert(substring(time,1,4),unsigned))) between (convert(substring(\'"+timeFrom+"\',1,4),unsigned)) and (convert(substring(\'"+timeTo+"\',1,4),unsigned))) and amount_enter >= 1) as temp group by timeRegion) as Final;";
        System.out.println(str);
        pstmt = (tmp).prepareStatement(str);
        pstmt.execute();
        ResultSet rs = pstmt.executeQuery();
        StringBuilder defaultWalkInView = new StringBuilder();
       
        if (rs.next()) {
            defaultWalkInView.append(rs.getInt(2));
            while (rs.next()) {
               
                defaultWalkInView.append("," + rs.getInt(2));
            }
        }
        pstmt.close();
        // con.releaseConnection(tmp);
        return defaultWalkInView.toString();
    }
    
    public String getWalkbyByYear(String timeFrom, String timeTo) throws SQLException {
        // ConnectionPool con = null;
        PreparedStatement pstmt;

        Connection tmp = DriverManager.getConnection("jdbc:mysql:///test");
        String str = "select * from (select timeRegion,count(*) from (select (substring(time,1,4) )as timeRegion, time, business_id,amount_enter,amount_passby from test.customeranalysis where (((convert(substring(time,1,4),unsigned))) between (convert(substring(\'"+timeFrom+"\',1,4),unsigned)) and (convert(substring(\'"+timeTo+"\',1,4),unsigned))) and amount_passby >= 1) as temp group by timeRegion) as Final;";
        System.out.println(str);
        pstmt = (tmp).prepareStatement(str);
        pstmt.execute();
        ResultSet rs = pstmt.executeQuery();
        StringBuilder defaultWalkInView = new StringBuilder();
       
        if (rs.next()) {
            defaultWalkInView.append(rs.getInt(2));
            while (rs.next()) {
               
                defaultWalkInView.append("," + rs.getInt(2));
            }
        }
        pstmt.close();
        // con.releaseConnection(tmp);
        return defaultWalkInView.toString();
    }
    public int getTotalVisit() throws SQLException {
        // ConnectionPool con = null;
        PreparedStatement pstmt;

        Connection tmp = DriverManager.getConnection("jdbc:mysql:///test");
        String str = "SELECT count(*) from customeranalysis where amount_enter >= 1";
        System.out.println(str);
        pstmt = (tmp).prepareStatement(str);
        pstmt.execute();
        ResultSet rs = pstmt.executeQuery();
        int defaultWalkInView = -1;
       
        if (rs.next()) {
            defaultWalkInView = (rs.getInt(1));
            
        }
        pstmt.close();
        // con.releaseConnection(tmp);
        return defaultWalkInView;
    }
    
    public CustomerAnalysisBean getAnalysis(int name) throws RollbackException {
        CustomerAnalysisBean admin[] = match(MatchArg.equals("business_id",
                name));
        int a = admin.length - 1;
        if (admin.length == 0) {
            return null;
        } else {
            return admin[a];
        }
    }

    public CustomerAnalysisBean[] getAnalysisArray(int name)
            throws RollbackException {
        CustomerAnalysisBean[] admin = match(MatchArg.equals("business_id",
                name));
        if (admin.length == 0) {
            return null;
        } else {
            return admin;
        }
    }

}
