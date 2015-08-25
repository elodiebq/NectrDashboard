package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AdminDAO;
import model.BeaconDAO;
import model.BusinessProfileDAO;
import model.CampaignDAO;
import model.CustomerAnalysisDAO;
import model.Model;
import model.RegionDAO;

import org.genericdao.RollbackException;

import databeans.AdminBean;
import databeans.BeaconBean;
import databeans.BusinessProfileBean;
import databeans.CampaignBean;
import databeans.CustomerAnalysisBean;
import databeans.RegionBean;

public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;
	int i=0;
	public void init() throws ServletException {
		Model model = new Model(getServletConfig());
		
		Action.add(new LoginAction(model));
		Action.add(new LogoutAction(model));
		Action.add(new ViewHistoryAction(model));
		Action.add(new RegionAction(model));
		Action.add(new CreateBusinessAction(model));
		Action.add(new CreateCampaignAction(model));
        Action.add(new ListAction(model));
		Action.add(new SetViewDataAction(model));
		Action.add(new SetViewDataByDayAction(model));
		Action.add(new SetViewDataByMonthAction(model));
		Action.add(new SetViewDataByYearAction(model));
		Action.add(new CreateBeaconAction(model));
		
		
	
		initializeTable(model.getAdminDAO(), model.getBusinessProfileDAO(), model.getCustomerAnalysisDAO(), model.getCampaignDAO(), model.getRegionDAO(),model.getBeaconDAO());
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = performTheAction(request);
		sendToNextPage(nextPage, request, response);
	}

	private String performTheAction(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String servletPath = request.getServletPath();
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		BusinessProfileBean business = (BusinessProfileBean) session.getAttribute("business");
		CampaignBean campaign = (CampaignBean) session.getAttribute("campaign");
		CustomerAnalysisBean analysis = (CustomerAnalysisBean) session.getAttribute("customerAnalysis");

		String action = getActionName(servletPath);


		if (action.equals("register.do") || action.equals("login.do")
				|| action.equals("list.do") || action.equals("update.do")) {
			// Allow these actions without logging in
			return Action.perform(action, request);
		}

		if (admin == null && business == null) {
			// If the user hasn't logged in, direct him to the login page
			return Action.perform("login.do", request);
		}

		// Let the logged in user run his chosen action
		return Action.perform(action, request);
	}

	private void sendToNextPage(String nextPage, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		if (nextPage == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND,
					request.getServletPath());
			return;
		}

		if (nextPage.endsWith(".do")) {
			response.sendRedirect(nextPage);
			return;
		}

		if (nextPage.endsWith(".jsp")) {
			//"WEB-INF/"+ 
			RequestDispatcher d = request.getRequestDispatcher(nextPage);
			
			d.forward(request, response);
			return;
		}
		
		if (nextPage.equals("image")) {
	   		RequestDispatcher d = request.getRequestDispatcher(nextPage);
	   		d.forward(request,response);
	   		return;
    	}

		throw new ServletException(Controller.class.getName()
				+ ".sendToNextPage(\"" + nextPage + "\"): invalid extension.");
	}

	private String getActionName(String path) {
		int slash = path.lastIndexOf('/');
		return path.substring(slash + 1);
	}
	
	
	public void initializeTable(AdminDAO adminDAO, BusinessProfileDAO businessprofileDAO, 
			CustomerAnalysisDAO customerAnalysisDAO, CampaignDAO campaignDAO, RegionDAO regionDAO,BeaconDAO beaconDAO) {

		try {
			if (adminDAO.read("admin") == null) {
				AdminBean admin = new AdminBean();
		
				admin.setPassword("123456");
				admin.setUsername("admin");
				adminDAO.create(admin);
			}
		} catch (RollbackException e) {
			e.printStackTrace();
		}
		
		try {
			if (regionDAO.read(1) == null) {
				RegionBean region = new RegionBean();
		
				region.setRegionId(1);
				region.setRegionName("how lee");
				region.setCenterLat(40.438398);
				region.setCenterLng(-79.919424);
				region.setRadius(100);
				regionDAO.create(region);
				
				region.setRegionId(2);
				region.setRegionName("rite aid");
				region.setCenterLat(40.437778);
				region.setCenterLng(-79.923124);
				region.setRadius(100);
				regionDAO.create(region);
				
				region.setRegionId(3);
				region.setRegionName("gates");
				region.setCenterLat(40.443921);
				region.setCenterLng(-79.944424);
				region.setRadius(100);
				regionDAO.create(region);
			}
		} catch (RollbackException e) {
			e.printStackTrace();
		}
		
		try {
			if (businessprofileDAO.read(1) == null) {
				BusinessProfileBean business = new BusinessProfileBean();
				BeaconBean beacon = new BeaconBean();
				
				business.setName("My home");
				business.setImage("images/upload/abc4129516826.jpg");
				business.setBusiness_id(1);
				business.setInLat("40.437508");
				business.setInLng("-79.919568");
				business.setRegionId(1);
				business.setBeaconId(10);
				business.setUsername("home");
				business.setPassword("123456");
				business.setUdid("B9407F30-F5F8-466E-AFF9-25556B57FE6D");
				beacon.setId(10);
				beacon.setMajor_value(5598);
				beacon.setMinor_value(22154);
				beaconDAO.create(beacon);
				businessprofileDAO.create(business);
				
				business.setName("campus");
				business.setBusiness_id(2);
				business.setImage("images/upload/abc4129516826.jpg");
				business.setInLat("40.4436438");
				business.setInLng("-79.9434304");
				business.setRegionId(3);
				business.setBeaconId(11);
				business.setUdid("B9407F30-F5F8-466E-AFF9-25556B57FE6D");
				business.setUsername("campus");
				business.setPassword("123456");
				beacon.setId(11);
				beacon.setMajor_value(36773);
				beacon.setMinor_value(58018);
				beaconDAO.create(beacon);
				businessprofileDAO.create(business);
				
				business.setName("gates1");
				business.setBusiness_id(3);
				business.setImage("images/upload/abc4129516826.jpg");
				business.setInLat("40.44014");
				business.setInLng("-79.9444224");
				business.setRegionId(3);
				business.setBeaconId(11);
				business.setUdid("B9407F30-F5F8-466E-AFF9-25556B57FE6D");
				business.setUsername("gates1");
				business.setPassword("123456");
				businessprofileDAO.create(business);
				
				business.setName("gates2");
				business.setBusiness_id(4);
				business.setImage("images/upload/abc4129516826.jpg");
				business.setInLat("40.443833");
				business.setInLng("-79.944341");
				business.setRegionId(3);
				business.setBeaconId(11);
				business.setUdid("B9407F30-F5F8-466E-AFF9-25556B57FE6D");
				business.setUsername("gates2");
				business.setPassword("123456");
				businessprofileDAO.create(business);
				
				business.setName("gates3");
				business.setBusiness_id(5);
				business.setImage("images/upload/abc4129516826.jpg");
				business.setInLat("40.443997");
				business.setInLng("-79.944602");
				business.setRegionId(3);
				business.setBeaconId(11);
				business.setUdid("B9407F30-F5F8-466E-AFF9-25556B57FE6D");
				business.setUsername("gates3");
				business.setPassword("123456");
				businessprofileDAO.create(business);
				
			
				

		
			

			}
		} catch (RollbackException e) {
			e.printStackTrace();
		}

		try {
			if (campaignDAO.read(1) == null) {
				CampaignBean campaign = new CampaignBean();
				campaign.setBusiness_id(3);
				campaign.setCampaign_id(1);              

				campaign.setMessage("Enjoy our lunch special!");
				campaign.setTitle("home");
				campaign.setDate_from("2015-08-05");
				campaign.setDate_to("2015-12-12");
				campaign.setDate_post("2015-08-05");
				campaign.setTime_from("12:00:00");
				campaign.setTime_to("16:00:00");
				campaign.setTotal_amount(120);
				campaign.setUsed_amount(35);
				campaignDAO.create(campaign);
				
				campaign.setBusiness_id(3);
				campaign.setCampaign_id(2);              

				campaign.setMessage("Joing our happy Beer Day!");
				campaign.setTitle("home2");
				campaign.setDate_from("2015-08-06");
				campaign.setDate_to("2015-08-10");
				campaign.setDate_post("2015-08-06");
				campaign.setTime_from("12:00:00");
				campaign.setTime_to("18:00:00");
				campaign.setTotal_amount(210);
				campaign.setUsed_amount(62);
				campaignDAO.create(campaign);
				
				campaign.setBusiness_id(3);
				campaign.setCampaign_id(3);  
				campaign.setTitle("campus");

	         

				campaign.setMessage("Come and enjoy our afternoon tea!");
				campaign.setTitle("home3");
				campaign.setDate_from("2015-08-08");
				campaign.setDate_to("2015-08-12");
				campaign.setDate_post("2015-08-08");
				campaign.setTime_from("14:00:00");
				campaign.setTime_to("18:00:00");
				campaign.setTotal_amount(85);
				campaign.setUsed_amount(29);
				campaignDAO.create(campaign);
				
				

		
			}
		} catch (RollbackException e) {
			e.printStackTrace();
		}
		

        try {
            if (customerAnalysisDAO.read(1) == null) {
                CustomerAnalysisBean analysis = new CustomerAnalysisBean();
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(1);
                analysis.setAmount_passby(1);
                analysis.setTime("2015-08-25T12");
                analysis.setVenderId("1");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(1);
                analysis.setAmount_passby(1);
                analysis.setTime("2015-08-25T14");
                analysis.setVenderId("1");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(0);
                analysis.setAmount_passby(1);
                analysis.setTime("2015-08-25T16");
                analysis.setVenderId("1");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(1);
                analysis.setAmount_passby(0);
                analysis.setTime("2015-08-25T13");
                analysis.setVenderId("2");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(0);
                analysis.setAmount_passby(1);
                analysis.setTime("2015-08-25T16");
                analysis.setVenderId("2");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(1);
                analysis.setAmount_passby(0);
                analysis.setTime("2015-08-25T12");
                analysis.setVenderId("3");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(0);
                analysis.setAmount_passby(1);
                analysis.setTime("2015-08-25T08");
                analysis.setVenderId("3");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(1);
                analysis.setAmount_passby(0);
                analysis.setTime("2015-08-25T14");
                analysis.setVenderId("4");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(0);
                analysis.setAmount_passby(1);
                analysis.setTime("2015-08-25T16");
                analysis.setVenderId("5");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(0);
                analysis.setAmount_passby(1);
                analysis.setTime("2015-08-25T14");
                analysis.setVenderId("6");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(0);
                analysis.setAmount_passby(1);
                analysis.setTime("2015-08-25T14");
                analysis.setVenderId("7");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(0);
                analysis.setAmount_passby(1);
                analysis.setTime("2015-08-25T14");
                analysis.setVenderId("8");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(0);
                analysis.setAmount_passby(1);
                analysis.setTime("2015-08-25T14");
                analysis.setVenderId("9");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(1);
                analysis.setAmount_passby(1);
                analysis.setTime("2015-08-25T18");
                analysis.setVenderId("10");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(1);
                analysis.setAmount_passby(0);
                analysis.setTime("2015-08-25T18");
                analysis.setVenderId("11");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(1);
                analysis.setAmount_passby(1);
                analysis.setTime("2015-08-25T18");
                analysis.setVenderId("12");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(1);
                analysis.setAmount_passby(1);
                analysis.setTime("2015-08-24T18");
                analysis.setVenderId("1");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(1);
                analysis.setAmount_passby(1);
                analysis.setTime("2015-08-24T12");
                analysis.setVenderId("2");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(1);
                analysis.setAmount_passby(1);
                analysis.setTime("2015-08-23T12");
                analysis.setVenderId("1");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(1);
                analysis.setAmount_passby(1);
                analysis.setTime("2015-08-23T12");
                analysis.setVenderId("3");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(1);
                analysis.setAmount_passby(1);
                analysis.setTime("2015-08-23T12");
                analysis.setVenderId("4");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(1);
                analysis.setAmount_passby(0);
                analysis.setTime("2015-08-23T12");
                analysis.setVenderId("44");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(0);
                analysis.setAmount_passby(1);
                analysis.setTime("2015-08-23T12");
                analysis.setVenderId("45");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(0);
                analysis.setAmount_passby(1);
                analysis.setTime("2015-08-23T12");
                analysis.setVenderId("46");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(1);
                analysis.setAmount_passby(0);
                analysis.setTime("2015-08-23T14");
                analysis.setVenderId("5");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(1);
                analysis.setAmount_passby(0);
                analysis.setTime("2015-08-23T12");
                analysis.setVenderId("6");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(1);
                analysis.setAmount_passby(1);
                analysis.setTime("2015-08-23T12");
                analysis.setVenderId("2");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(1);
                analysis.setAmount_passby(1);
                analysis.setTime("2015-08-22T12");
                analysis.setVenderId("1");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(1);
                analysis.setAmount_passby(0);
                analysis.setTime("2015-08-22T12");
                analysis.setVenderId("5");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(1);
                analysis.setAmount_passby(1);
                analysis.setTime("2015-08-22T14");
                analysis.setVenderId("2");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(0);
                analysis.setAmount_passby(1);
                analysis.setTime("2015-08-22T16");
                analysis.setVenderId("7");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(0);
                analysis.setAmount_passby(1);
                analysis.setTime("2015-08-22T12");
                analysis.setVenderId("8");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(0);
                analysis.setAmount_passby(1);
                analysis.setTime("2015-08-22T18");
                analysis.setVenderId("1");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(1);
                analysis.setAmount_passby(0);
                analysis.setTime("2015-08-21T12");
                analysis.setVenderId("1");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(0);
                analysis.setAmount_passby(1);
                analysis.setTime("2015-08-21T12");
                analysis.setVenderId("1");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(0);
                analysis.setAmount_passby(1);
                analysis.setTime("2015-08-21T12");
                analysis.setVenderId("1");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(0);
                analysis.setAmount_passby(1);
                analysis.setTime("2015-08-21T12");
                analysis.setVenderId("1");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
              

                analysis.setBusiness_id(3);
                analysis.setAmount_enter(1);
                analysis.setAmount_passby(0);
                analysis.setTime("2015-08-20T12");
                analysis.setVenderId("1");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(1);
                analysis.setAmount_passby(0);
                analysis.setTime("2015-08-20T12");
                analysis.setVenderId("90");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(0);
                analysis.setAmount_passby(1);
                analysis.setTime("2015-08-20T12");
                analysis.setVenderId("90");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(0);
                analysis.setAmount_passby(1);
                analysis.setTime("2015-08-20T12");
                analysis.setVenderId("90");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(0);
                analysis.setAmount_passby(1);
                analysis.setTime("2015-08-20T12");
                analysis.setVenderId("90");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(1);
                analysis.setAmount_passby(0);
                analysis.setTime("2015-08-20T10");
                analysis.setVenderId("100");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(1);
                analysis.setAmount_passby(0);
                analysis.setTime("2015-08-19T12");
                analysis.setVenderId("1");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(0);
                analysis.setAmount_passby(1);
                analysis.setTime("2015-08-19T12");
                analysis.setVenderId("1");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(0);
                analysis.setAmount_passby(1);
                analysis.setTime("2015-08-19T12");
                analysis.setVenderId("1");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(0);
                analysis.setAmount_passby(1);
                analysis.setTime("2015-08-19T12");
                analysis.setVenderId("1");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(0);
                analysis.setAmount_passby(1);
                analysis.setTime("2015-08-19T12");
                analysis.setVenderId("1");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(0);
                analysis.setAmount_passby(1);
                analysis.setTime("2015-08-19T12");
                analysis.setVenderId("1");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(0);
                analysis.setAmount_passby(1);
                analysis.setTime("2015-08-19T12");
                analysis.setVenderId("1");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
                
                analysis.setBusiness_id(3);
                analysis.setAmount_enter(0);
                analysis.setAmount_passby(1);
                analysis.setTime("2015-08-19T12");
                analysis.setVenderId("1");
                analysis.setTotal_visit(0);
                customerAnalysisDAO.create(analysis);
               
                
                

        
            }
        } catch (RollbackException e) {
            e.printStackTrace();
        }
		
		
			
	}
	
}