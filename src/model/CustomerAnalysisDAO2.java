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

    public CustomerAnalysisBean getAnalysisById(int id, String time)
            throws MyDAOException {
        Connection con = null;
        try {
            con = getConnection();
            String str = "SELECT * FROM "
                    + "customeranalysis " + "WHERE business_id = " + id
                    + " and time= \'"+ time +"\'";
            PreparedStatement pstmt = con.prepareStatement(str);

            System.out.println("STRING SQL" + str);
            ResultSet rs = pstmt.executeQuery();
            CustomerAnalysisBean business;
            if (!rs.next()) {
                business = null;
            } else {

                business = new CustomerAnalysisBean();
                business.setBusiness_id((Integer.parseInt(rs
                        .getString("business_id"))));
                business.setAmount_passby(Integer.parseInt(rs
                        .getString("amount_passby")));
                business.setAmount_enter(Integer.parseInt(rs
                        .getString("amount_enter")));
                business.setTime(((rs.getString("time"))));
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

    public void updateById(int id, String time) throws MyDAOException {
        Connection con = null;
        CustomerAnalysisBean customer = getAnalysisById(id, time);
        int count = customer.getAmount_passby() + 1;
        System.out.println(count);
        PreparedStatement pstmt;
        try {
            con = getConnection();
            String str = "UPDATE customeranalysis SET amount_passby = " + count
                    + " WHERE business_id = " + id+ " and time= \'"+ time +"\'";
            System.out.println(str);
            pstmt = con.prepareStatement(str);

            pstmt.executeUpdate();
            pstmt.close();
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

    public void updatewWalkinById(int id, String time) throws MyDAOException {
        Connection con = null;
        CustomerAnalysisBean customer = getAnalysisById(id, time);
        int count = customer.getAmount_enter() + 1;
        System.out.println(count);
        PreparedStatement pstmt;
        try {
            con = getConnection();
            String str = "UPDATE customeranalysis SET amount_enter = " + count
                    + " WHERE business_id = " + id + " and time= \'"+ time +"\'";
            System.out.println(str);
            pstmt = con.prepareStatement(str);

            pstmt.executeUpdate();
            pstmt.close();
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

}
