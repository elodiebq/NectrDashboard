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

public class UpdateWalkinServlet extends HttpServlet {
   
    private CustomerAnalysisDAO2 customerAnalysisDAO;

    public void init() throws ServletException {
        String jdbcDriverName = "com.mysql.jdbc.Driver";
        String jdbcURL = "jdbc:mysql:///test";

        try {
           
            customerAnalysisDAO = new CustomerAnalysisDAO2(jdbcDriverName, jdbcURL,
                    "customeranalysis");

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
        CustomerAnalysisBean customer;
        try {
            customer = customerAnalysisDAO.getAnalysisById((Integer.parseInt(req
                    .getParameter("businessId"))), req
                    .getParameter("time"));
            customerAnalysisDAO.updatewWalkinById((customer.getBusiness_id()),req
                    .getParameter("time"));
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MyDAOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ;

    }
}
