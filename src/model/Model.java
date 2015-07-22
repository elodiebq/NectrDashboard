
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
	private AdminDAO     adminDAO;

	

	public Model(ServletConfig config) throws ServletException {
		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL    = config.getInitParameter("jdbcURL");
		
			ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL);
			adminDAO     = new AdminDAO("admin", pool);
			businessDAO   = new BusinessProfileDAO("businessProfile",pool);
	
		} catch (DAOException e) {
			throw new ServletException(e);
		}
		
		
	}
	public AdminDAO    getAdminDAO()    { return adminDAO;    }
	public BusinessProfileDAO    getBusinessProfileDAO()    { return businessDAO;    }
}

