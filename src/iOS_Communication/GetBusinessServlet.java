package iOS_Communication;

import java.io.IOException;
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
import model.CustomerAnalysisDAO2;
import model.Model;
import model.MyDAOException;
import model.RegionDAO;

import com.google.gson.Gson;

import java.sql.Timestamp;

import databeans.BeaconBean;
import databeans.BusinessProfileBean;
import databeans.CampaignBean;
import databeans.RegionBean;

public class GetBusinessServlet extends HttpServlet {
	private BusinessProfileDAO2 businessDAO;
	private CampaignDAO2 campaignDAO;
	private BeaconDAO2 beaconDAO;
    private CustomerAnalysisDAO2 customerAnalysisDAO;


	public void init() throws ServletException {
		String jdbcDriverName = "com.mysql.jdbc.Driver";
        String jdbcURL = "jdbc:mysql://aatlnydnhg5jd9.cw0kvjz4dk33.us-east-1.rds.amazonaws.com:3306/ebdb?user=nectr&password=123456789";

		try {
			businessDAO = new BusinessProfileDAO2(jdbcDriverName, jdbcURL, "businessprofile");
			campaignDAO = new CampaignDAO2(jdbcDriverName, jdbcURL, "campaign");
			beaconDAO = new BeaconDAO2(jdbcDriverName, jdbcURL, "beacon");
			 customerAnalysisDAO = new CustomerAnalysisDAO2(jdbcDriverName,
	                    jdbcURL, "customeranalysis");
		} catch (MyDAOException e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameter("regionId") == null) {
			return;
		}
		List<BusinessProfileBean> businessList;
		List<CampaignBean> campaignList;
		System.out.print(Integer.parseInt(req.getParameter("regionId")));
		try {
			businessList = businessDAO.getBusinessByRegionId(Integer.parseInt(req.getParameter("regionId")));
			for(int j = 0;j<businessList.size();j++){
			customerAnalysisDAO.updateById(
					businessList.get(j).getBusiness_id(),
		                    req.getParameter("time"),req.getParameter("venderId"));
				
			}
			Response[] responselist = new Response[businessList.size()];
			for (int i = 0; i < businessList.size(); i++) {
				responselist[i] = new Response();

				responselist[i].businessId = (businessList.get(i)).getBusiness_id();
				responselist[i].uuid = (businessList.get(i)).getUdid();
				responselist[i].businessLng = (businessList.get(i)).getInLng();
				responselist[i].businessLat = (businessList.get(i)).getInLat();
				
				List<BeaconBean> beacon = beaconDAO.getBeacon((businessList.get(i)).getBeaconId());
				System.out.println(beacon.size());
			
				if (beacon.size() == 0) {
					responselist[i].major = -1;
					responselist[i].minor = -1;
				} else {
					responselist[i].major = (beacon.get(0)).getMajor_value();
					responselist[i].minor = (beacon.get(0)).getMinor_value();
				}
				campaignList = campaignDAO.getCampaignByCampaign((businessList.get(i)).getBusiness_id());
				ArrayList<Campaign> campaigns = new ArrayList<Campaign>();
				for (int a = 0; a < campaignList.size(); a++) {
	                String from = campaignList.get(a).getDate_from() + " " + campaignList.get(a).getTime_from();
	                String to = campaignList.get(a).getDate_to() + " " + campaignList.get(a).getTime_to();
	                System.out.println("time to"+to);
	                System.out.println("time to"+to.length());

	                

	                long tmpFrom;
	                long tmpTo;
                    try {
                        tmpFrom = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(from.substring(0,19)).getTime();
                      //  System.out.println("Timestamp from:" + Long.toString(tmpFrom/1000));
                        tmpTo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(to.substring(0,19)).getTime();
	                
	               
	               
	                Campaign campagin  = new Campaign();
	                
	                Timestamp curr = new Timestamp(System.currentTimeMillis()); 
	               // || curr.after(tmpTo) || curr.before(tmpFrom)
	                if (campaignList.size() == 0  ) {
	                    
	                    campagin.campaignId = -1;
	                    campagin.description = null;
	                    campagin.start = null;
	                    campagin.expire = null;
	                   

	                } else {

	                    campagin.campaignId = (campaignList.get(a)).getCampaign_id();
	                  //  System.out.println("i"+i+(campaignList.get(a)).getCampaign_id());
	                    campagin.description = (campaignList.get(a)).getMessage();
	                  // System.out.println((campaignList.get(i)).getMessage()+"*******");
	                    campagin.start = String.valueOf(Long.toString(tmpFrom/1000));
	                    campagin.expire = String.valueOf(Long.toString(tmpTo/1000));
	                   
	                }
                   
	                campaigns.add(campagin);
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
				}
				
				responselist[i].campagin = campaigns;
				

			}
			BusinessList buslist = new BusinessList();
			buslist.businessList = responselist;

			String json = new Gson().toJson(buslist);
			resp.setContentType("application/json");
			resp.getWriter().println(json);
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
