package iOS_Communication;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.genericdao.RollbackException;

import model.BeaconDAO;
import model.BeaconDAO2;
import model.BusinessProfileDAO;
import model.BusinessProfileDAO2;
import model.CampaignDAO;
import model.CampaignDAO2;
import model.Model;
import model.MyDAOException;
import model.RegionDAO;

import com.google.gson.Gson;

import databeans.BeaconBean;
import databeans.BusinessProfileBean;
import databeans.CampaignBean;
import databeans.RegionBean;

public class GetCampaignServlet extends HttpServlet {
	private BusinessProfileDAO2 businessDAO;
	private CampaignDAO2 campaignDAO;

	public void init() throws ServletException {
		String jdbcDriverName = "com.mysql.jdbc.Driver";
        String jdbcURL = "jdbc:mysql://aatlnydnhg5jd9.cw0kvjz4dk33.us-east-1.rds.amazonaws.com:3306/ebdb?user=nectr&password=123456789";

		try {
			businessDAO = new BusinessProfileDAO2(jdbcDriverName, jdbcURL, "businessprofile");
			campaignDAO = new CampaignDAO2(jdbcDriverName, jdbcURL, "campaign");
		
		} catch (MyDAOException e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameter("campaign_id") == null) {
			return;
		}
		System.out.println("I'm here~~");
	
		try {
			CampaignBean campaign;
			BusinessProfileBean business;
			campaign = campaignDAO.getCampaignById(Integer.parseInt(req.getParameter("campaign_id")));
			business = businessDAO.getBusinessById(campaign.getBusiness_id());
			campaignDAO.updateById(campaign.getCampaign_id());
            String from = campaign.getDate_from() + " " + campaign.getTime_from();
            String to = campaign.getDate_to() + " " + campaign.getTime_to();
           
            Long tmpFrom;
            Long tmpTo;
            try {
                tmpFrom = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(from.substring(0,19)).getTime();
               
                tmpTo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(to.substring(0,19)).getTime();
               
                Response campaignRes = new Response();
                campaignRes.businessId = campaign.getBusiness_id();
                campaignRes.campaignTitle = campaign.getTitle();
                campaignRes.campaignMessage = campaign.getMessage();
                campaignRes.businessName = business.getName();
                campaignRes.image = business.getImage();
                campaignRes.success = true;
                 
                campaignRes.from = String.valueOf(Long.toString(tmpFrom/1000));
                campaignRes.to = String.valueOf(Long.toString(tmpTo/1000));
                String json = new Gson().toJson(campaignRes);
                resp.setContentType("application/json");
                resp.getWriter().println(json);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            
			
			
			
		} catch (MyDAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
