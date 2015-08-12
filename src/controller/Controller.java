package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AdminDAO;
import model.BusinessProfileDAO;
import model.CampaignDAO;
import model.CustomerAnalysisDAO;
import model.Model;
import model.RegionDAO;

import org.genericdao.RollbackException;

import databeans.AdminBean;
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
		
	
		initializeTable(model.getAdminDAO(), model.getBusinessProfileDAO(), model.getCustomerAnalysisDAO(), model.getCampaignDAO(), model.getRegionDAO());
		
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

		throw new ServletException(Controller.class.getName()
				+ ".sendToNextPage(\"" + nextPage + "\"): invalid extension.");
	}

	private String getActionName(String path) {
		int slash = path.lastIndexOf('/');
		return path.substring(slash + 1);
	}
	
	
	public void initializeTable(AdminDAO adminDAO, BusinessProfileDAO businessprofileDAO, 
			CustomerAnalysisDAO customerAnalysisDAO, CampaignDAO campaignDAO, RegionDAO regionDAO) {

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
			if (businessprofileDAO.read(1) == null) {
				BusinessProfileBean business = new BusinessProfileBean();
				RegionBean region = new RegionBean();
				region.setRegionId(1);
				region.setRegionName("M");
				regionDAO.create(region);
				
				region.setRegionId(2);
                region.setRegionName("F");
                regionDAO.create(region);
		
				business.setBusiness_id(1);
				business.setPassword("123456");
				business.setUsername("business1");
				business.setPhone("347-654-1234");
				business.setName("TestBusiness");
				business.setWebsite("www.business.com");
				business.setDescription("Hi this is for ");
				business.setInLat("40.450899");
				business.setInLng("-79.94972000000001");
				business.setRegionId(1);
				businessprofileDAO.create(business);
				
				business.setBusiness_id(2);
				business.setPassword("123456");
				business.setUsername("SushiFukuOakland");
				business.setPhone("347-654-1234");
				business.setName("Sushi Fuku Oakland");
				business.setWebsite("www.business.com");
				business.setDescription("Hello Sushi Fuku ");
				business.setInLat("40.44166");
				business.setInLng("-79.95734");
				business.setAddress("120 Oakland Avenue");
				business.setRegionId(2);
				businessprofileDAO.create(business);
				
				business.setBusiness_id(3);
				business.setPassword("123456");
				business.setUsername("WilliamPennTavern");
				business.setPhone("347-654-1234");
				business.setName("William Penn Tavern");
				business.setWebsite("www.business.com");
				business.setDescription("Hello William Penn Tavern ");
				business.setInLat("40.45136");
				business.setInLng("-79.93378");
				business.setAddress("739 Bellefonte Street");
				businessprofileDAO.create(business);
				
				business.setBusiness_id(4);
				business.setPassword("123456");
				business.setUsername("SushiFukuCraigStreet");
				business.setPhone("347-654-1234");
				business.setName("Sushi Fuku Craig Street");
				business.setWebsite("www.business.com");
				business.setDescription("Hello Sushi Fuku ");
				business.setInLat("40.4448,");
				business.setInLng("-79.94851");
				business.setAddress("417 South Craig Street");
				businessprofileDAO.create(business);
				
				business.setBusiness_id(5);
				business.setPassword("123456");
				business.setUsername("Sorrento'sPizza");
				business.setPhone("347-654-1234");
				business.setName("Sorrento's Pizza");
				business.setWebsite("www.business.com");
				business.setDescription("Hello Sorrento's Pizza ");
				business.setInLat("40.4404");
				business.setInLng("-79.9565");
				business.setAddress("233 Atwood Street");
				businessprofileDAO.create(business);
				
				business.setBusiness_id(6);
				business.setPassword("123456");
				business.setUsername("Social");
				business.setPhone("347-654-1234");
				business.setName("Social");
				business.setWebsite("www.business.com");
				business.setDescription("Hello Sushi Fuku ");
				business.setInLat("40.45689");
				business.setInLng("-79.91615");
				business.setAddress("6425 Penn Avenue");
				businessprofileDAO.create(business);
				
				business.setBusiness_id(7);
				business.setPassword("123456");
				business.setUsername("ShadyGrove");
				business.setPhone("347-654-1234");
				business.setName("Shady Grove");
				business.setWebsite("www.business.com");
				business.setDescription("Hello Shady Grove ");
				business.setInLat("40.45086");
				business.setInLng("-79.9335");
				business.setAddress("5500 Walnut Street");
				businessprofileDAO.create(business);
				
				business.setBusiness_id(8);
				business.setPassword("123456");
				business.setUsername("PizzaRomano");
				business.setPhone("347-654-1234");
				business.setName("Pizza Romano");
				business.setWebsite("www.business.com");
				business.setDescription("Hello Pizza Romano");
				business.setInLat("40.44066");
				business.setInLng("-79.95695");
				business.setAddress("219 Atwood Street");
				businessprofileDAO.create(business);
				
				business.setBusiness_id(9);
				business.setPassword("123456");
				business.setUsername("Peter'sPub");
				business.setPhone("347-654-1234");
				business.setName("Peter's Pub");
				business.setWebsite("www.business.com");
				business.setDescription("Hello Peter's Pub ");
				business.setInLat("40.44176, ");
				business.setInLng("-79.95734");
				business.setAddress("116 Oakland Avenue");
				businessprofileDAO.create(business);
				
				business.setBusiness_id(10);
				business.setPassword("123456");
				business.setUsername("OriginalMilanoPizza");
				business.setPhone("347-654-1234");
				business.setName("Original Milano Pizza");
				business.setWebsite("www.business.com");
				business.setDescription("Hello Original Milano Pizza");
				business.setInLat("40.44133");
				business.setInLng("-79.95875");
				business.setAddress("3606 Fifth Avenue");
				businessprofileDAO.create(business);
				
				business.setBusiness_id(11);
				business.setPassword("123456");
				business.setUsername("Mario'sEastSideSaloon");
				business.setPhone("347-654-1234");
				business.setName("Mario's East Side Saloon");
				business.setWebsite("www.business.com");
				business.setDescription("Hello Sushi Fuku ");
				business.setInLat("40.451");
				business.setInLng("-79.93386");
				business.setAddress("5442 Walnut Street");
				businessprofileDAO.create(business);
				
				business.setBusiness_id(12);
				business.setPassword("123456");
				business.setUsername("LivNutrition");
				business.setPhone("347-654-1234");
				business.setName("Liv Nutrition");
				business.setWebsite("www.business.com");
				business.setDescription("Hello Liv Nutrition");
				business.setInLat("40.44169");
				business.setInLng("-79.95838");
				business.setAddress("110 Atwood St");
				businessprofileDAO.create(business);
				
				business.setBusiness_id(13);
				business.setPassword("123456");
				business.setUsername("LarryCarol'sPizza");
				business.setPhone("347-654-1234");
				business.setName("Larry & Carol's Pizza");
				business.setWebsite("www.business.com");
				business.setDescription("Hello Larry & Carol's Pizza ");
				business.setInLat("40.43674");
				business.setInLng("-79.95478");
				business.setAddress("410 Semple Street");
				businessprofileDAO.create(business);
				
				business.setBusiness_id(14);
				business.setPassword("123456");
				business.setUsername("JittersCafe&IceCream");
				business.setPhone("347-654-1234");
				business.setName("Jitters Cafe & Ice Cream");
				business.setWebsite("www.business.com");
				business.setDescription("Hello Jitters Cafe & Ice Cream");
				business.setInLat("40.4518");
				business.setInLng("-79.93237");
				business.setAddress("5541 Walnut Street");
				businessprofileDAO.create(business);
				
				business.setBusiness_id(15);
				business.setPassword("123456");
				business.setUsername("Hemingway'sCafe");
				business.setPhone("347-654-1234");
				business.setName("Hemingway's Cafe");
				business.setWebsite("www.business.com");
				business.setDescription("Hello Hemingway's Cafe");
				business.setInLat("40.44229");
				business.setInLng("-79.95625");
				business.setAddress("3911 Forbes Avenue");
				businessprofileDAO.create(business);
				
				business.setBusiness_id(16);
				business.setPassword("123456");
				business.setUsername("GarageDoorSaloon");
				business.setPhone("347-654-1234");
				business.setName("Garage Door Saloon");
				business.setWebsite("www.business.com");
				business.setDescription("Hello Garage Door Saloon");
				business.setInLat("40.44065");
				business.setInLng("-79.95682");
				business.setAddress("223 Atwood Street");
				businessprofileDAO.create(business);
				
				business.setBusiness_id(17);
				business.setPassword("123456");
				business.setUsername("FuelandFuddle");
				business.setPhone("347-654-1234");
				business.setName("Fuel and Fuddle");
				business.setWebsite("www.business.com");
				business.setDescription("Hello Sushi Fuku ");
				business.setInLat("40.44115");
				business.setInLng("-79.95672");
				business.setAddress("212 Oakland Avenue");
				businessprofileDAO.create(business);
				
				business.setBusiness_id(18);
				business.setPassword("123456");
				business.setUsername("ForbesGyro");
				business.setPhone("347-654-1234");
				business.setName("Forbes Gyro");
				business.setWebsite("www.business.com");
				business.setDescription("Hello SForbes Gyro ");
				business.setInLat("40.44143");
				business.setInLng("-79.9574");
				business.setAddress("3715 Forbes Avenue");
				businessprofileDAO.create(business);
				
				business.setBusiness_id(19);
				business.setPassword("123456");
				business.setUsername("Everyday�s A Sundae & Caf�");
				business.setPhone("347-654-1234");
				business.setName("Everyday�s A Sundae & Caf�");
				business.setWebsite("www.business.com");
				business.setDescription("Hello Everyday�s A Sundae & Caf�");
				business.setInLat("40.45992");
				business.setInLng("-79.92431");
				business.setAddress("6014 Centre Avenue");
				businessprofileDAO.create(business);
				
				business.setBusiness_id(20);
				business.setPassword("123456");
				business.setUsername("Empire Tattoo");
				business.setPhone("347-654-1234");
				business.setName("Empire Tattoo");
				business.setWebsite("www.business.com");
				business.setDescription("Hello Empire Tattoo ");
				business.setInLat("40.43963");
				business.setInLng("-79.9575");
				business.setAddress("230 Meyran Avenue");
				businessprofileDAO.create(business);
				
				business.setBusiness_id(21);
				business.setPassword("123456");
				business.setUsername("Eatunique");
				business.setPhone("347-654-1234");
				business.setName("Eatunique");
				business.setWebsite("www.business.com");
				business.setDescription("Hello Eatunique ");
				business.setInLat("40.4456");
				business.setInLng("-79.94857");
				business.setAddress("305 South Craig Street");
				businessprofileDAO.create(business);
				
				business.setBusiness_id(22);
				business.setPassword("123456");
				business.setUsername("Dave & Andy's Homemade Ice Cream");
				business.setPhone("347-654-1234");
				business.setName("Dave & Andy's Homemade Ice Cream");
				business.setWebsite("www.business.com");
				business.setDescription("Hello Dave & Andy's Homemade Ice Cream ");
				business.setInLat("40.44094");
				business.setInLng("-79.95724");
				business.setAddress("207 Atwood Street");
				businessprofileDAO.create(business);
				
				business.setBusiness_id(23);
				business.setPassword("123456");
				business.setUsername("Cappy's Cafe");
				business.setPhone("347-654-1234");
				business.setName("Cappy's Cafe");
				business.setWebsite("www.business.com");
				business.setDescription("Hello Cappy's Cafe");
				business.setInLat("40.4511");
				business.setInLng("-79.93436");
				business.setAddress("5431 Walnut Street");
				businessprofileDAO.create(business);
				
				business.setBusiness_id(24);
				business.setPassword("123456");
				business.setUsername("Buddy's Brews on Carson");
				business.setPhone("347-654-1234");
				business.setName("Buddy's Brews on Carson");
				business.setWebsite("www.business.com");
				business.setDescription("Hello Buddy's Brews on Carson");
				business.setInLat("40.4283");
				business.setInLng("-79.97571");
				business.setAddress("2112 East Carson Street");
				businessprofileDAO.create(business);
				
				business.setBusiness_id(25);
				business.setPassword("123456");
				business.setUsername("Bangal Kabab House & Restaurant");
				business.setPhone("347-654-1234");
				business.setName("Bangal Kabab House & Restaurant");
				business.setWebsite("www.business.com");
				business.setDescription("Hello Sushi Fuku ");
				business.setInLat("40.43909");
				business.setInLng("-79.95539");
				business.setAddress("320 Atwood Street");
				businessprofileDAO.create(business);
				
				business.setBusiness_id(26);
				business.setPassword("123456");
				business.setUsername("Ace Athletic");
				business.setPhone("347-654-1234");
				business.setName("Ace Athletic");
				business.setWebsite("www.business.com");
				business.setDescription("Hello Ace Athletic ");
				business.setInLat("40.44075");
				business.setInLng("-79.9577");
				business.setAddress("3614 Forbes Ave");
				businessprofileDAO.create(business);
			}
		} catch (RollbackException e) {
			e.printStackTrace();
		}

		try {
			if (campaignDAO.read(1) == null) {
				CampaignBean campaign = new CampaignBean();
		
				campaign.setCampaign_id(1);              
				campaign.setBusiness_id(1);
				campaign.setTitle("Buy two get one FREE");
				campaign.setMessage("From the time period, buy two Bud Light to get another one for free!");
				campaign.setDate_create("2015-08-05");
				campaign.setDate_post("2015-08-08");
				campaign.setTime_from("2015-08-08T12");
				campaign.setTime_to("2015-08-08T18");
				campaign.setTotal_amount(85);
				campaign.setUsed_amount(26);
				campaignDAO.create(campaign);
				
				campaign.setCampaign_id(2);              
				campaign.setBusiness_id(1);
				campaign.setTitle("Friday Night!");
				campaign.setMessage("Enjoy the food for 20% off this Friday!");
				campaign.setDate_create("2015-08-05");
				campaign.setDate_post("2015-08-07");
				campaign.setTime_from("2015-08-07T12");
				campaign.setTime_to("2015-08-07T18");
				campaign.setTotal_amount(186);
				campaign.setUsed_amount(79);
				campaignDAO.create(campaign);
				
				campaign.setCampaign_id(3);              
				campaign.setBusiness_id(1);
				campaign.setTitle("Wings 39 cents!!");
				campaign.setMessage("During the promotion time, have our delicious wings for only 39 cent!");
				campaign.setDate_create("2015-08-05");
				campaign.setDate_post("2015-08-11");
				campaign.setTime_from("2015-08-11T18");
				campaign.setTime_to("2015-08-11T22");
				campaign.setTotal_amount(96);
				campaign.setUsed_amount(53);
				campaignDAO.create(campaign);
			}
		} catch (RollbackException e) {
			e.printStackTrace();
		}
		
		
		try {
			if (customerAnalysisDAO.read(1,1) == null) {
				CustomerAnalysisBean analysis = new CustomerAnalysisBean();
		
				analysis.setId(1);              
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(12);
                analysis.setAmount_enter(0);
                analysis.setTotal_visit(0);
                analysis.setTime("2015-08-01T00");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(2);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(3);
                analysis.setAmount_enter(0);
                analysis.setTotal_visit(0);
                analysis.setTime("2015-08-01T02");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(3);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(12);
                analysis.setAmount_enter(0);
                analysis.setTotal_visit(0);
                analysis.setTime("2015-08-01T04");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(4);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(12);
                analysis.setAmount_enter(0);
                analysis.setTotal_visit(0);
                analysis.setTime("2015-08-01T06");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(5);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(25);
                analysis.setAmount_enter(0);
                analysis.setTotal_visit(0);
                analysis.setTime("2015-08-01T08");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(6);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(36);
                analysis.setAmount_enter(0);
                analysis.setTotal_visit(0);
                analysis.setTime("2015-08-01T10");
                customerAnalysisDAO.create(analysis);
            
                analysis.setId(7);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(25);
                analysis.setAmount_enter(3);
                analysis.setTotal_visit(3);
                analysis.setTime("2015-08-01T12");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(8);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(30);
                analysis.setAmount_enter(8);
                analysis.setTotal_visit(11);
                analysis.setTime("2015-08-01T14");
                customerAnalysisDAO.create(analysis);

                analysis.setId(9);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(20);
                analysis.setAmount_enter(5);
                analysis.setTotal_visit(16);
                analysis.setTime("2015-08-01T16");
                customerAnalysisDAO.create(analysis);

                analysis.setId(10);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(50);
                analysis.setAmount_enter(11);
                analysis.setTotal_visit(27);
                analysis.setTime("2015-08-01T18");
                customerAnalysisDAO.create(analysis);

                analysis.setId(11);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(30);
                analysis.setAmount_enter(8);
                analysis.setTotal_visit(35);
                analysis.setTime("2015-08-01T20");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(12);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(21);
                analysis.setAmount_enter(3);
                analysis.setTotal_visit(38);
                analysis.setTime("2015-08-01T22");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(13);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(235);
                analysis.setAmount_enter(98);
                analysis.setTotal_visit(103);
                analysis.setTime("2015-07-31T00");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(14);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(189);
                analysis.setAmount_enter(36);
                analysis.setTotal_visit(65);
                analysis.setTime("2015-07-30T00");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(15);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(276);
                analysis.setAmount_enter(122);
                analysis.setTotal_visit(1156);
                analysis.setTime("2015-07-29T00");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(16);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(196);
                analysis.setAmount_enter(62);
                analysis.setTotal_visit(103);
                analysis.setTime("2015-07-28T00");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(17);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(203);
                analysis.setAmount_enter(77);
                analysis.setTotal_visit(133);
                analysis.setTime("2015-07-27T00");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(18);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(174);
                analysis.setAmount_enter(62);
                analysis.setTotal_visit(103);
                analysis.setTime("2015-07-26T00");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(19);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(199);
                analysis.setAmount_enter(58);
                analysis.setTotal_visit(103);
                analysis.setTime("2015-07-25T00");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(20);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(155);
                analysis.setAmount_enter(43);
                analysis.setTotal_visit(103);
                analysis.setTime("2015-07-24T00");
                customerAnalysisDAO.create(analysis);
                
                
                analysis.setId(21);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(211);
                analysis.setAmount_enter(87);
                analysis.setTotal_visit(103);
                analysis.setTime("2015-07-23T00");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(22);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(194);
                analysis.setAmount_enter(89);
                analysis.setTotal_visit(103);
                analysis.setTime("2015-07-22T00");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(23);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(168);
                analysis.setAmount_enter(58);
                analysis.setTotal_visit(103);
                analysis.setTime("2015-07-21T00");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(24);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(207);
                analysis.setAmount_enter(66);
                analysis.setTotal_visit(103);
                analysis.setTime("2015-07-20T00");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(25);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(177);
                analysis.setAmount_enter(98);
                analysis.setTotal_visit(103);
                analysis.setTime("2015-07-19T00");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(26);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(216);
                analysis.setAmount_enter(102);
                analysis.setTotal_visit(103);
                analysis.setTime("2015-07-18T00");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(27);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(178);
                analysis.setAmount_enter(43);
                analysis.setTotal_visit(103);
                analysis.setTime("2015-07-17T00");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(28);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(212);
                analysis.setAmount_enter(59);
                analysis.setTotal_visit(103);
                analysis.setTime("2015-07-16T00");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(29);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(196);
                analysis.setAmount_enter(76);
                analysis.setTotal_visit(103);
                analysis.setTime("2015-07-15T00");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(30);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(212);
                analysis.setAmount_enter(98);
                analysis.setTotal_visit(103);
                analysis.setTime("2015-07-14T00");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(31);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(180);
                analysis.setAmount_enter(58);
                analysis.setTotal_visit(103);
                analysis.setTime("2015-07-13T00");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(32);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(200);
                analysis.setAmount_enter(98);
                analysis.setTotal_visit(103);
                analysis.setTime("2015-07-12T00");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(33);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(170);
                analysis.setAmount_enter(79);
                analysis.setTotal_visit(103);
                analysis.setTime("2015-07-11T00");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(34);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(3563);
                analysis.setAmount_enter(1025);
                analysis.setTotal_visit(103);
                analysis.setTime("2015-06-01T00");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(35);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(2988);
                analysis.setAmount_enter(825);
                analysis.setTotal_visit(103);
                analysis.setTime("2015-05-01T00");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(36);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(3263);
                analysis.setAmount_enter(1325);
                analysis.setTotal_visit(103);
                analysis.setTime("2015-04-01T00");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(37);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(3412);
                analysis.setAmount_enter(1225);
                analysis.setTotal_visit(103);
                analysis.setTime("2015-03-01T00");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(38);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(3163);
                analysis.setAmount_enter(825);
                analysis.setTotal_visit(103);
                analysis.setTime("2015-02-01T00");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(39);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(3258);
                analysis.setAmount_enter(725);
                analysis.setTotal_visit(103);
                analysis.setTime("2015-01-01T00");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(40);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(2863);
                analysis.setAmount_enter(685);
                analysis.setTotal_visit(103);
                analysis.setTime("2014-12-01T00");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(41);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(3077);
                analysis.setAmount_enter(825);
                analysis.setTotal_visit(103);
                analysis.setTime("2014-11-01T00");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(42);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(3112);
                analysis.setAmount_enter(925);
                analysis.setTotal_visit(103);
                analysis.setTime("2014-10-01T00");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(43);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(2663);
                analysis.setAmount_enter(525);
                analysis.setTotal_visit(103);
                analysis.setTime("2014-09-01T00");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(44);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(3058);
                analysis.setAmount_enter(841);
                analysis.setTotal_visit(103);
                analysis.setTime("2014-08-01T00");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(45);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(2763);
                analysis.setAmount_enter(665);
                analysis.setTotal_visit(103);
                analysis.setTime("2014-07-01T00");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(46);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(3241);
                analysis.setAmount_enter(1125);
                analysis.setTotal_visit(103);
                analysis.setTime("2014-06-01T00");
                customerAnalysisDAO.create(analysis);
                
                analysis.setId(47);
                analysis.setBusiness_id(1);
                analysis.setAmount_passby(22125);
                analysis.setAmount_enter(7250);
                analysis.setTotal_visit(103);
                analysis.setTime("2013-01-01T00");
                customerAnalysisDAO.create(analysis);
                
			}
		} catch (RollbackException e) {
			e.printStackTrace();
		}
			
	}
	
}