package iOS_Communication;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databeans.BusinessProfileBean;
import databeans.CampaignBean;
import databeans.CustomerAnalysisBean;
import model.BeaconDAO2;
import model.BusinessProfileDAO2;
import model.CampaignDAO2;
import model.CustomerAnalysisDAO2;
import model.MyDAOException;

public class UpdateWalkinServlet extends HttpServlet {
   
    private CustomerAnalysisDAO2 customerAnalysisDAO;
    private CampaignDAO2 campaignDAO;

    public void init() throws ServletException {
        String jdbcDriverName = "com.mysql.jdbc.Driver";
        String jdbcURL = "jdbc:mysql://aatlnydnhg5jd9.cw0kvjz4dk33.us-east-1.rds.amazonaws.com:3306/ebdb?user=nectr&password=123456789";

        try {
           
            customerAnalysisDAO = new CustomerAnalysisDAO2(jdbcDriverName, jdbcURL,
                    "customeranalysis");
            campaignDAO = new CampaignDAO2(jdbcDriverName, jdbcURL,
                    "campaign");

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
        List<CampaignBean> campaign;
        try {
//            customer = customerAnalysisDAO.getAnalysisById((Integer.parseInt(req
//                    .getParameter("businessId"))), req
//                    .getParameter("time"));
           String curDate = req.getParameter("time");
           
          
            System.out.println(customerAnalysisDAO.distinctVisistInHour(req
                    .getParameter("time"), req.getParameter("venderId")));
            if(customerAnalysisDAO.distinctVisistInHour(req
                    .getParameter("time"), req.getParameter("venderId"))){
            	  customerAnalysisDAO.updatewWalkinById(Integer.parseInt(req.getParameter("businessId")),req
                          .getParameter("time"), req.getParameter("venderId"));
                  System.out.println("customer update");
            campaign = campaignDAO.getCampaignByCampaign(Integer.parseInt(req.getParameter("businessId")));
            if(campaign.size()!=0){
    			System.out.println("has campaign");

            	for(int i = 0;i<campaign.size();i++){
            		String day_from = campaign.get(i).getDate_from();
            		String day_to = campaign.get(i).getDate_to();
            		String time_from = campaign.get(i).getTime_from().substring(0,2);
            		String time_to = campaign.get(i).getTime_to().substring(0, 2);
            		String curDay = curDate.substring(0, 10);
            		String curTime = curDate.substring(11,13);
            		long tmpDayFrom;
            		long tmpDayTo;
            	
            		long curDayLong;
            		
            		tmpDayFrom = new SimpleDateFormat("yyyy-MM-dd").parse(day_from).getTime();
            		tmpDayTo = new SimpleDateFormat("yyyy-MM-dd").parse(day_to).getTime();
            		curDayLong = new SimpleDateFormat("yyyy-MM-dd").parse(curDay).getTime();
            		
            		if(curDayLong>tmpDayFrom&&curDayLong<tmpDayTo){
            			System.out.println("day right");
            			if(Integer.parseInt(curTime)>Integer.parseInt(time_from)&&
            					Integer.parseInt(curTime)<Integer.parseInt(time_to)){
                			System.out.println("time right");

            				campaignDAO.updateTotalById(campaign.get(i).getCampaign_id());
            				
                			System.out.println("campaign.get(i).getCampaign_id()"+campaign.get(i).getCampaign_id());

            			}
            		}
            		
            	}
            }
            }    
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MyDAOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ;

    }
}
