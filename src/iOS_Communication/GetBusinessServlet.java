package iOS_Communication;

import java.io.IOException;
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

public class GetBusinessServlet extends HttpServlet {
	private BusinessProfileDAO2 businessDAO;
	private CampaignDAO2 campaignDAO;
	private BeaconDAO2 beaconDAO;

	public void init() throws ServletException {
		String jdbcDriverName = "com.mysql.jdbc.Driver";
		String jdbcURL = "jdbc:mysql:///test";

		try {
			businessDAO = new BusinessProfileDAO2(jdbcDriverName, jdbcURL, "businessprofile");
			campaignDAO = new CampaignDAO2(jdbcDriverName, jdbcURL, "campaign");
			beaconDAO = new BeaconDAO2(jdbcDriverName, jdbcURL, "beacon");
		} catch (MyDAOException e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameter("regionId") == null) {
			return;
		}
		System.out.print("I'm here~~");
		List<BusinessProfileBean> businessList;
		List<CampaignBean> campaignList;
		System.out.print(Integer.parseInt(req.getParameter("regionId")));
		try {
			businessList = businessDAO.getBusinessByRegionId(Integer.parseInt(req.getParameter("regionId")));
			Response[] responselist = new Response[businessList.size()];
			for (int i = 0; i < businessList.size(); i++) {
				responselist[i] = new Response();

				responselist[i].businessId = (businessList.get(i)).getBusiness_id();
				responselist[i].beaconId = (businessList.get(i)).getBeaconId();
				List<BeaconBean> beacon = beaconDAO.getBeacon((businessList.get(i)).getBeaconId());
				if (beacon.size() == 0) {
					responselist[i].beaconMajor = -1;
					responselist[i].beaconMinor = -1;
				} else {
					responselist[i].beaconMajor = (beacon.get(0)).getMajor_value();
					responselist[i].beaconMinor = (beacon.get(0)).getMinor_value();
				}
				campaignList = campaignDAO.getCampaignByCampaign((businessList.get(i)).getBusiness_id());
				String[] campagin = new String[4];
				if (campaignList.size() == 0) {
					campagin[0] = null;
					campagin[1] = null;
					campagin[2] = null;
					campagin[3] = null;

				} else {

					campagin[0] = Integer.toString((campaignList.get(i)).getCampaign_id());
					campagin[1] = (campaignList.get(i)).getMessage();
					campagin[2] = Integer.toString((campaignList.get(i)).getType());
					campagin[3] = Integer.toString((campaignList.get(i)).getInStore());
				}
				responselist[i].campagin = campagin;
				

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
