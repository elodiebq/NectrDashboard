package iOS_Communication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databeans.BusinessProfileBean;
import databeans.CustomerAnalysisBean;
import model.BeaconDAO2;
import model.BusinessProfileDAO2;
import model.CampaignDAO2;
import model.CustomerAnalysisDAO2;
import model.MyDAOException;

public class UpdatePassbyServlet extends HttpServlet {

    private CustomerAnalysisDAO2 customerAnalysisDAO;

    public void init() throws ServletException {
        String jdbcDriverName = "com.mysql.jdbc.Driver";
        String jdbcURL = "jdbc:mysql://aatlnydnhg5jd9.cw0kvjz4dk33.us-east-1.rds.amazonaws.com:3306/ebdb?user=nectr&password=123456789";

        try {

            customerAnalysisDAO = new CustomerAnalysisDAO2(jdbcDriverName,
                    jdbcURL, "customeranalysis");

        } catch (MyDAOException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (req.getParameter("businessId") == null) {
            return;
        }
       
        try {
              if(customerAnalysisDAO.distinctVisistInHour(req
                      .getParameter("time"), req.getParameter("venderId"))){
                  customerAnalysisDAO.updateById(Integer.parseInt(req.getParameter("businessId")),req
                            .getParameter("time"), req.getParameter("venderId"));
                  System.out.println("customer update");

              }
           
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MyDAOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

    }
}
