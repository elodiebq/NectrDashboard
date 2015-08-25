package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databeans.CampaignBean;
import databeans.CustomerAnalysisBean;
import databeans.RegionBean;

public class CustomerAnalysisDAO extends GenericDAO<CustomerAnalysisBean> {
    private List<Connection> connectionPool = new ArrayList<Connection>();
//    private String jdbcDriver;
//    private String tableName;
   public String jdbcURL = "jdbc:mysql://aatlnydnhg5jd9.cw0kvjz4dk33.us-east-1.rds.amazonaws.com:3306/ebdb?user=nectr&password=123456789";

    public CustomerAnalysisDAO(String tableName, ConnectionPool pool)
            throws DAOException {
        super(CustomerAnalysisBean.class, tableName, pool);
    }

    private synchronized Connection getConnection() throws MyDAOException {
        if (connectionPool.size() > 0) {
            return connectionPool.remove(connectionPool.size() - 1);
        }

        // try {
        // Class.forName(jdbcDriver);
        // } catch (ClassNotFoundException e) {
        // throw new MyDAOException(e);
        // }

        try {
            return DriverManager.getConnection(jdbcURL);
        } catch (SQLException e) {
            throw new MyDAOException(e);
        }
    }

    private synchronized void releaseConnection(Connection con) {
        connectionPool.add(con);
    }

    public String getWalkinByHour(String time) throws SQLException {
        // ConnectionPool con = null;
        PreparedStatement pstmt;
        StringBuilder defaultWalkInView = new StringBuilder();
        Connection tmp;
        try {
            tmp = getConnection();
            try {
                time = time.substring(0, 10);
                String str = "select * from (select timeRegion,count(*) from (select (convert(substring(time,12,2),unsigned)div 2) as timeRegion, time, business_id,amount_enter,amount_passby from customeranalysis as temp where (substring(time,1,10)  = \'"
                        + time
                        + "\') and amount_enter = 1) as final group by timeRegion) as Final where (timeRegion >=0 and timeRegion<=11) order by timeRegion ";
                System.out.println("select walk in by hour sql:" + str);
                pstmt = (tmp).prepareStatement(str);
                pstmt.execute();
                ResultSet rs = pstmt.executeQuery();
                defaultWalkInView = new StringBuilder();
                int[] temp = new int[12];
                for (int i = 0; i < 12; i++) {
                    temp[i] = 0;
                }
               
                    while (rs.next()) {
                        temp[rs.getInt(1)] = rs.getInt(2);

                    }
                
                defaultWalkInView.append(temp[0]);
                for (int i = 1; i < 12; i++) {
                    defaultWalkInView.append("," + temp[i]);
                }
                pstmt.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            releaseConnection(tmp);
        } catch (MyDAOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return defaultWalkInView.toString();
    }

    public String getWalkbyByHour(String time) throws SQLException {
        // ConnectionPool con = null;
        PreparedStatement pstmt;

        Connection tmp;
        StringBuilder defaultWalkInView = new StringBuilder();
        try {
            tmp = getConnection();
            try {
                time = time.substring(0, 10);
                String str = "select * from (select timeRegion,count(*) from (select (convert(substring(time,12,2),unsigned)div 2) as timeRegion, time, business_id,amount_enter,amount_passby from customeranalysis as temp where (substring(time,1,10)  = \'"
                        + time
                        + "\') and amount_passby >= 1) as final group by timeRegion) as Final where (timeRegion >=0 and timeRegion<=11) order by timeRegion ";
                System.out.println("select walk by by hour sql:"+ str);
                pstmt = (tmp).prepareStatement(str);
                pstmt.execute();
                ResultSet rs = pstmt.executeQuery();
                defaultWalkInView = new StringBuilder();

                int[] temp = new int[12];
                for (int i = 0; i < 12; i++) {
                    temp[i] = 0;
                }
                //System.out.println("pass by rs next:" + rs.next());
                  while (rs.next()) {
                        temp[rs.getInt(1)] = rs.getInt(2);
                        //System.out.println("timeRegion: " + rs.getInt(1) + "count: " + rs.getInt(2));

                    }
                
                defaultWalkInView.append(temp[0]);
                for (int i = 1; i < 12; i++) {
                    defaultWalkInView.append("," + temp[i]);
                }
                pstmt.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            releaseConnection(tmp);
        } catch (MyDAOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return defaultWalkInView.toString();
    }

    public String getWalkinByDay(String time) throws SQLException {
        // ConnectionPool con = null;
        PreparedStatement pstmt;
        StringBuilder defaultWalkInView = new StringBuilder();
        Connection tmp;
        try {
            tmp = getConnection();

            time = time.substring(0, 10);
            String str = "select * from (select timeRegion,count(*) from (select (convert(substring(time,1,10),date)) as timeRegion, time, business_id,amount_enter,amount_passby from customeranalysis as temp where (convert(substring(time,1,10),date)) >= DATE(DATE_SUB(CONVERT(\'"
                    + time
                    + "\',DATE),INTERVAL 6 DAY)) and amount_enter >= 1) as final group by timeRegion) as Final  order by timeRegion";
            System.out.println("walk in by day sql: " +str);
            pstmt = (tmp).prepareStatement(str);
            pstmt.execute();
            ResultSet rs = pstmt.executeQuery();
            defaultWalkInView = new StringBuilder();
            HashMap<Long, Integer> map = new HashMap<Long, Integer>();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            long currTime;
            try {
                currTime =  format.parse(time).getTime()
                        / (24 * 60 * 60 * 1000);

                System.out.println("walk in by day curr Time" + currTime);

                    while (rs.next()) {
                       
                        map.put( format.parse(rs.getString(1)).getTime()
                                / (24 * 60 * 60 * 1000), rs.getInt(2));
                    }

                
                
              

                    for (int i = 0; i < 7; i++) {

                        if (map.containsKey(currTime)) {
                            
                            defaultWalkInView.append(map.get(currTime) + ",");
                            currTime--;
                        } else {
                            defaultWalkInView.append("0,");
                            currTime--;
                        }

                    }

                
            } catch (ParseException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            pstmt.close();
            releaseConnection(tmp);
        } catch (MyDAOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("walk in by day:" + defaultWalkInView);
        return defaultWalkInView.toString();
    }

    public String getWalkbyByDay(String time) throws SQLException {
        // ConnectionPool con = null;
        PreparedStatement pstmt;
        StringBuilder defaultWalkInView = new StringBuilder();
        Connection tmp;
        try {
            tmp = getConnection();

            time = time.substring(0, 10);
            String str = "select * from (select timeRegion,count(*) from (select (convert(substring(time,1,10),date)) as timeRegion, time, business_id,amount_enter,amount_passby from customeranalysis as temp where (convert(substring(time,1,10),date)) >= DATE(DATE_SUB(CONVERT(\'"
                    + time
                    + "\',DATE),INTERVAL 6 DAY)) and amount_passby = 1) as final group by timeRegion) as Final  order by timeRegion";
            System.out.println("walk by by day sql: " +str);
            pstmt = (tmp).prepareStatement(str);
            pstmt.execute();
            ResultSet rs = pstmt.executeQuery();
            defaultWalkInView = new StringBuilder();

            HashMap<Long, Integer> map = new HashMap<Long, Integer>();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            long currTime;
            try {
                currTime = format.parse(time).getTime()
                        / (24 * 60 * 60 * 1000);

                System.out.print("let's see currtime:" + currTime);
                

                    while (rs.next()) {
                        map.put( format.parse(rs.getString(1)).getTime()
                                / (24 * 60 * 60 * 1000), rs.getInt(2));
                    }

                
               
                

                    for (int i = 0; i < 7; i++) {

                        if (map.containsKey(currTime)) {
                            
                            defaultWalkInView.append(map.get(currTime) + ",");
                            currTime--;
                        } else {
                            defaultWalkInView.append("0,");
                            currTime--;
                        }

                    }

                
            } catch (ParseException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            pstmt.close();
            System.out.println("walk by by day:" + defaultWalkInView);

            releaseConnection(tmp);
        } catch (MyDAOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return defaultWalkInView.toString();
    }

    public String getWalkinByMonth(String timeFrom, String timeTo)
            throws SQLException {
        // ConnectionPool con = null;
        PreparedStatement pstmt;
        StringBuilder defaultWalkInView = new StringBuilder();

        Connection tmp;
        try {
            tmp = getConnection();

            String str = "select * from (select timeRegion,count(*) from (select (substring(time,1,7) )as timeRegion, time, business_id,amount_enter,amount_passby from customeranalysis where (((convert(substring(time,1,4),unsigned)) * 12 + convert(substring(time,6,2),unsigned)) between (convert(substring(\'"
                    + timeFrom
                    + "\',1,4),unsigned)) * 12 + convert(substring(\'"
                    + timeFrom
                    + "\',6,2),unsigned) and (convert(substring(\'"
                    + timeTo
                    + "\',1,4),unsigned)) * 12 + convert(substring(\'"
                    + timeTo
                    + "\',6,2),unsigned)) and amount_enter >= 1) as temp group by timeRegion) as Final";
            System.out.println(str);
            pstmt = (tmp).prepareStatement(str);
            pstmt.execute();
            ResultSet rs = pstmt.executeQuery();
            defaultWalkInView = new StringBuilder();

            HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
            Calendar cal = Calendar.getInstance();
            Calendar cal1 = Calendar.getInstance();
            DateFormat format = new SimpleDateFormat("yyyy-MM");
            int from;
            int to;
            try {
                cal.setTime(format.parse(timeFrom));
                cal1.setTime(format.parse(timeTo));

                from = cal.get(Calendar.MONTH) + cal.get((Calendar.YEAR)) * 12;
                to = cal1.get(Calendar.MONTH) + cal1.get(Calendar.YEAR) * 12;

               
                    while (rs.next()) {
                        Calendar tmpt = Calendar.getInstance();
                        tmpt.setTime(format.parse(rs.getString(1)));
                        int temp;
                        temp = tmpt.get(Calendar.MONTH)
                                + tmpt.get(Calendar.YEAR) * 12;
                        System.out.println("map time: " + temp);
                        map.put(temp, rs.getInt(2));
                    }
                
                System.out.println("from: " + from);
                System.out.println("to: " + to);

                for (int i = from; i <= to; i++) {

                    if (map.containsKey(i)) {

                        defaultWalkInView.append(map.get(i));

                    } else {
                        defaultWalkInView.append("0");

                    }
                    if (i < to) {
                        defaultWalkInView.append(",");
                    }
                }

            } catch (ParseException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            pstmt.close();
            System.out.println("walk in by month:" + defaultWalkInView);

            releaseConnection(tmp);
        } catch (MyDAOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return defaultWalkInView.toString();
    }

    public String getWalkbyByMonth(String timeFrom, String timeTo)
            throws SQLException {
        // ConnectionPool con = null;
        PreparedStatement pstmt;
        StringBuilder defaultWalkInView = new StringBuilder();

        Connection tmp;
        try {
            tmp = getConnection();
            try {
                String str = "select * from (select timeRegion,count(*) from (select (substring(time,1,7) )as timeRegion, time, business_id,amount_enter,amount_passby from customeranalysis where (((convert(substring(time,1,4),unsigned)) * 12 + convert(substring(time,6,2),unsigned)) between (convert(substring(\'"
                        + timeFrom
                        + "\',1,4),unsigned)) * 12 + convert(substring(\'"
                        + timeFrom
                        + "\',6,2),unsigned) and (convert(substring(\'"
                        + timeTo
                        + "\',1,4),unsigned)) * 12 + convert(substring(\'"
                        + timeTo
                        + "\',6,2),unsigned)) and amount_passby >= 1) as temp group by timeRegion) as Final";
                System.out.println(str);
                pstmt = (tmp).prepareStatement(str);
                pstmt.execute();
                ResultSet rs = pstmt.executeQuery();
                defaultWalkInView = new StringBuilder();

                HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
                Calendar cal = Calendar.getInstance();
                Calendar cal1 = Calendar.getInstance();
                DateFormat format = new SimpleDateFormat("yyyy-MM");
                int from;
                int to;

                cal.setTime(format.parse(timeFrom));
                cal1.setTime(format.parse(timeTo));

                from = cal.get(Calendar.MONTH) + cal.get((Calendar.YEAR)) * 12;
                to = cal1.get(Calendar.MONTH) + cal1.get(Calendar.YEAR) * 12;

                
                    while (rs.next()) {
                        Calendar tmpt = Calendar.getInstance();
                        tmpt.setTime(format.parse(rs.getString(1)));
                        int temp;
                        temp = tmpt.get(Calendar.MONTH)
                                + tmpt.get(Calendar.YEAR) * 12;
                        System.out.println("map time: " + temp);
                        map.put(temp, rs.getInt(2));
                    }
                
                System.out.println("from: " + from);
                System.out.println("to: " + to);

                for (int i = from; i <= to; i++) {

                    if (map.containsKey(i)) {

                        defaultWalkInView.append(map.get(i));

                    } else {
                        defaultWalkInView.append("0");

                    }
                    if (i < to) {
                        defaultWalkInView.append(",");
                    }
                }
                pstmt.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            releaseConnection(tmp);
        } catch (MyDAOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return defaultWalkInView.toString();
    }

    public String getWalkinByYear(String timeFrom, String timeTo)
            throws SQLException {
        PreparedStatement pstmt;
        StringBuilder defaultWalkInView = new StringBuilder();

        Connection tmp;
        try {
            tmp = getConnection();
            try {
                String str = "select * from (select timeRegion,count(*) from (select (substring(time,1,4) )as timeRegion, time, business_id,amount_enter,amount_passby from customeranalysis where (((convert(substring(time,1,4),unsigned))) between (convert(substring(\'"
                        + timeFrom
                        + "\',1,4),unsigned)) and (convert(substring(\'"
                        + timeTo
                        + "\',1,4),unsigned))) and amount_enter >= 1) as temp group by timeRegion) as Final;";
                System.out.println(str);
                pstmt = (tmp).prepareStatement(str);
                pstmt.execute();
                ResultSet rs = pstmt.executeQuery();
                defaultWalkInView = new StringBuilder();

                HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
                Calendar cal = Calendar.getInstance();
                Calendar cal1 = Calendar.getInstance();
                DateFormat format = new SimpleDateFormat("yyyy");
                int from;
                int to;

                cal.setTime(format.parse(timeFrom));
                cal1.setTime(format.parse(timeTo));

                from = cal.get((Calendar.YEAR));
                to = cal1.get(Calendar.YEAR);

                
                    while (rs.next()) {
                        Calendar tmpt = Calendar.getInstance();
                        tmpt.setTime(format.parse(rs.getString(1)));
                        int temp;
                        temp = tmpt.get(Calendar.YEAR);
                        System.out.println("map time: " + temp);
                        map.put(temp, rs.getInt(2));
                    }
                
                System.out.println("from: " + from);
                System.out.println("to: " + to);

                for (int i = from; i <= to; i++) {

                    if (map.containsKey(i)) {

                        defaultWalkInView.append(map.get(i));

                    } else {
                        defaultWalkInView.append("0");

                    }
                    if (i < to) {
                        defaultWalkInView.append(",");
                    }
                }
                pstmt.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            releaseConnection(tmp);
        } catch (MyDAOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return defaultWalkInView.toString();
    }

    public String getWalkbyByYear(String timeFrom, String timeTo)
            throws SQLException {
        // ConnectionPool con = null;
        PreparedStatement pstmt;
        StringBuilder defaultWalkInView = new StringBuilder();

        Connection tmp;
        try {
            tmp = getConnection();
            try {
                String str = "select * from (select timeRegion,count(*) from (select (substring(time,1,4) )as timeRegion, time, business_id,amount_enter,amount_passby from customeranalysis where (((convert(substring(time,1,4),unsigned))) between (convert(substring(\'"
                        + timeFrom
                        + "\',1,4),unsigned)) and (convert(substring(\'"
                        + timeTo
                        + "\',1,4),unsigned))) and amount_passby >= 1) as temp group by timeRegion) as Final;";
                System.out.println(str);
                pstmt = (tmp).prepareStatement(str);
                pstmt.execute();
                ResultSet rs = pstmt.executeQuery();
                defaultWalkInView = new StringBuilder();

                HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
                Calendar cal = Calendar.getInstance();
                Calendar cal1 = Calendar.getInstance();
                DateFormat format = new SimpleDateFormat("yyyy");
                int from;
                int to;

                cal.setTime(format.parse(timeFrom));
                cal1.setTime(format.parse(timeTo));

                from = cal.get((Calendar.YEAR));
                to = cal1.get(Calendar.YEAR);

               
                    while (rs.next()) {
                        Calendar tmpt = Calendar.getInstance();
                        tmpt.setTime(format.parse(rs.getString(1)));
                        int temp;
                        temp = tmpt.get(Calendar.YEAR);
                        System.out.println("map time: " + temp);
                        map.put(temp, rs.getInt(2));
                    }
                
                System.out.println("from: " + from);
                System.out.println("to: " + to);

                for (int i = from; i <= to; i++) {

                    if (map.containsKey(i)) {

                        defaultWalkInView.append(map.get(i));

                    } else {
                        defaultWalkInView.append("0");

                    }
                    if (i < to) {
                        defaultWalkInView.append(",");
                    }
                }
                pstmt.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            releaseConnection(tmp);
        } catch (MyDAOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return defaultWalkInView.toString();
    }

    public int getTotalVisit() throws SQLException {
        // ConnectionPool con = null;
        PreparedStatement pstmt;
        int defaultWalkInView = 0;
        Connection tmp;
        try {
            tmp = getConnection();
            try {
                String str = "SELECT count(*) from customeranalysis where amount_enter >= 1";
                System.out.println(str);
                pstmt = (tmp).prepareStatement(str);
                pstmt.execute();
                ResultSet rs = pstmt.executeQuery();
                defaultWalkInView = -1;

                if (rs.next()) {
                    defaultWalkInView = (rs.getInt(1));

                }
                pstmt.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            releaseConnection(tmp);
        } catch (MyDAOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return defaultWalkInView;
    }
    
    public int getTotalWalkBy(int businessId) throws SQLException {
        // ConnectionPool con = null;
        PreparedStatement pstmt;
        int defaultWalkInView = 0;
        Connection tmp;
        try {
            tmp = getConnection();
            try {
                String str = "SELECT count(*) from customeranalysis where amount_passby >= 1 and business_id="+businessId;
                System.out.println(str);
                pstmt = (tmp).prepareStatement(str);
                pstmt.execute();
                ResultSet rs = pstmt.executeQuery();
                defaultWalkInView = -1;

                if (rs.next()) {
                    defaultWalkInView = (rs.getInt(1));

                }
                pstmt.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            releaseConnection(tmp);
        } catch (MyDAOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return defaultWalkInView;
    }

    // get
    public String getLoyalCustomer(int businessId) {
        int loyal = 0;
        int lukewarm = 0;
        int firstTime = 0;
        Connection tmp;
        try {
            PreparedStatement pstmt;
            tmp = getConnection();

            try {
                String str = "select count,venderId from (SELECT *,count(*) as count FROM customeranalysis where amount_enter >= 1 and business_id = "
                        + businessId
                        + " group by venderId) as tmp where count >=4;";
                System.out.println("!!!!!" +str);
                pstmt = (tmp).prepareStatement(str);
                pstmt.execute();
                ResultSet rs = pstmt.executeQuery();
                loyal = 0;

                while (rs.next()) {
                    loyal++;

                }

                String str1 = "select count,venderId from (SELECT *,count(*) as count FROM customeranalysis where amount_enter >= 1 and business_id = "
                        + businessId
                        + " group by venderId) as tmp where count < 4 and count > 1;";
                System.out.println(str1);
                pstmt = (tmp).prepareStatement(str1);
                pstmt.execute();
                ResultSet rs1 = pstmt.executeQuery();
                lukewarm = 0;

                while (rs1.next()) {
                    lukewarm++;
                }

                String str2 = "select count,venderId from (SELECT *,count(*) as count FROM customeranalysis where amount_enter >= 1 and business_id = "
                        + businessId
                        + " group by venderId) as tmp where count = 1;";
                System.out.println(str2);
                pstmt = (tmp).prepareStatement(str2);
                pstmt.execute();
                ResultSet rs2 = pstmt.executeQuery();
                firstTime = 0;

                while (rs2.next()) {
                    firstTime++;
                }

                pstmt.close();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            releaseConnection(tmp);
        } catch (MyDAOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return loyal + "," + lukewarm + "," + firstTime ;
    }
    public int getNewCustomer(int businessId){
        String today = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        Calendar to = Calendar.getInstance();
        int number = to.get(Calendar.DAY_OF_WEEK);
        PreparedStatement pstmt;
        int defaultWalkInView = 0;
        Connection tmp;
        try {
            tmp = getConnection();
            try {
                String str = "select count,venderId from (SELECT *,count(*) as count FROM customeranalysis where amount_enter >= 1 and business_id = "
                                + businessId
                                + " group by venderId) as tmp where count = 1 and  (convert(substring(time,1,10),date)) >= DATE(DATE_SUB(CONVERT(\'"+today+"\',DATE),INTERVAL "+number+" DAY));";             
                System.out.println(str);
                pstmt = (tmp).prepareStatement(str);
                pstmt.execute();
                ResultSet rs = pstmt.executeQuery();
                defaultWalkInView = 0;

                while (rs.next()) {
                    defaultWalkInView ++;

                }
                pstmt.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            releaseConnection(tmp);
        } catch (MyDAOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return defaultWalkInView;
    }
  
    public int getRepeatCustomer(int businessId){
        String today = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        Calendar to = Calendar.getInstance();
        int number = to.get(Calendar.DAY_OF_WEEK);
        PreparedStatement pstmt;
        int defaultWalkInView = 0;
        Connection tmp;
        try {
            tmp = getConnection();
            try {
                String str = "select count,venderId from (SELECT *,count(*) as count FROM customeranalysis where amount_enter >= 1 and business_id = "
                                + businessId
                                + " group by venderId) as tmp where count > 1 and  (convert(substring(time,1,10),date)) >= DATE(DATE_SUB(CONVERT(\'"+today+"\',DATE),INTERVAL "+number+" DAY));";             
                System.out.println("repeat SQL:" + str);
                pstmt = (tmp).prepareStatement(str);
                pstmt.execute();
                ResultSet rs = pstmt.executeQuery();
                defaultWalkInView = 0;

                while (rs.next()) {
                    defaultWalkInView ++;

                }
                pstmt.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            releaseConnection(tmp);
        } catch (MyDAOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return defaultWalkInView;
    }
    
    public int getWalkbyThisWeek(int businessId){
        String today = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        Calendar to = Calendar.getInstance();
        int number = to.get(Calendar.DAY_OF_WEEK);
        PreparedStatement pstmt;
        int defaultWalkInView = 0;
        Connection tmp;
        try {
            tmp = getConnection();
            try {
                String str =  "SELECT count(*) from customeranalysis where amount_passby >= 1 and business_id= "+businessId+" and (convert(substring(time,1,10),date)) >= DATE(DATE_SUB(CONVERT(\'"+today+"\',DATE),INTERVAL "+number+" DAY));";

                       
                System.out.println(str);
                pstmt = (tmp).prepareStatement(str);
                pstmt.execute();
                ResultSet rs = pstmt.executeQuery();
                defaultWalkInView = 0;

                if (rs.next()) {
                    defaultWalkInView = (rs.getInt(1));

                }
                pstmt.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            releaseConnection(tmp);
        } catch (MyDAOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return defaultWalkInView;
    }
    
    public int totalVisitThisWeek(int businessId){
        String today = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        Calendar to = Calendar.getInstance();
        int number = to.get(Calendar.DAY_OF_WEEK);
        PreparedStatement pstmt;
        int defaultWalkInView = 0;
        Connection tmp;
        try {
            tmp = getConnection();
            try {
                String str = 
                        "SELECT count(*) from customeranalysis where amount_enter >= 1 and business_id= "+businessId+" and (convert(substring(time,1,10),date)) >= DATE(DATE_SUB(CONVERT(\'"+today+"\',DATE),INTERVAL "+number+" DAY));";
                System.out.println(str);
                pstmt = (tmp).prepareStatement(str);
                pstmt.execute();
                ResultSet rs = pstmt.executeQuery();
                defaultWalkInView = -1;

                if (rs.next()) {
                    defaultWalkInView = (rs.getInt(1));

                }
                pstmt.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            releaseConnection(tmp);
        } catch (MyDAOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
