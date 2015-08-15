
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
	private BeaconDAO beaconDAO;

	private CustomerAnalysisDAO customerAnalysisDAO;
	private RegionDAO regionDAO;

	

	public Model(ServletConfig config) throws ServletException {
		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL    = config.getInitParameter("jdbcURL");
		
			ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL);
			adminDAO     = new AdminDAO("admin", pool);
			businessDAO   = new BusinessProfileDAO("businessProfile",pool);
			campaignDAO = new CampaignDAO("campaign",pool);
			customerAnalysisDAO = new CustomerAnalysisDAO("customerAnalysis", pool);
			regionDAO = new RegionDAO("region", pool);
			beaconDAO = new BeaconDAO("beacon", pool);

	
		} catch (DAOException e) {
			throw new ServletException(e);
		}
		
	}
	public AdminDAO    getAdminDAO()    { return adminDAO;    }
	public BusinessProfileDAO    getBusinessProfileDAO()    { return businessDAO;    }
	public CampaignDAO getCampaignDAO() {return campaignDAO;}
	public CustomerAnalysisDAO getCustomerAnalysisDAO() {return customerAnalysisDAO;}
	public RegionDAO getRegionDAO() {return regionDAO;}
	public BeaconDAO getBeaconDAO() {return beaconDAO;}

	
}

