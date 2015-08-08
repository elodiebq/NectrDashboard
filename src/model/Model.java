
package model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.RollbackException;

import databeans.AdminBean;
import databeans.BusinessProfileBean;

public class Model {
	private BusinessProfileDAO 	businessDAO;
	private CampaignDAO campaignDAO;
	private AdminDAO     adminDAO;
	private CustomerAnalysisDAO customerAnalysisDAO;

	

	public Model(ServletConfig config) throws ServletException {
		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL    = config.getInitParameter("jdbcURL");
		
			ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL);
			adminDAO     = new AdminDAO("admin", pool);
			businessDAO   = new BusinessProfileDAO("businessProfile",pool);
			campaignDAO = new CampaignDAO("campaign",pool);
			customerAnalysisDAO = new CustomerAnalysisDAO("customerAnalysis", pool);
	
		} catch (DAOException e) {
			throw new ServletException(e);
		}
		
<<<<<<< HEAD
=======
		try {
			if (businessDAO.read(1) == null) {
				BusinessProfileBean business = new BusinessProfileBean();
		
				business.setPassword("123456");
				business.setUsername("business1");
				business.setPhone("347-654-1234");
				business.setName("TestBusiness");
				business.setWebsite("www.business.com");
				business.setDescription("Hi this is for ");
				businessDAO.create(business);
			}
		} catch (RollbackException e) {
			e.printStackTrace();
		}
		
		
>>>>>>> origin/master
	}
	public AdminDAO    getAdminDAO()    { return adminDAO;    }
	public BusinessProfileDAO    getBusinessProfileDAO()    { return businessDAO;    }
	public CampaignDAO getCampaignDAO() {return campaignDAO;}
	public CustomerAnalysisDAO getCustomerAnalysisDAO() {return customerAnalysisDAO;}
}

