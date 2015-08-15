package model;


import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;






import databeans.CampaignBean;
import databeans.CustomerAnalysisBean;
import databeans.RegionBean;

public class CustomerAnalysisDAO extends GenericDAO<CustomerAnalysisBean>  {

	public CustomerAnalysisDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(CustomerAnalysisBean.class, tableName, pool);
	}
	
	public CustomerAnalysisBean getAnalysis(int name) throws RollbackException {
		CustomerAnalysisBean admin[] = match(MatchArg.equals("business_id", name));
		int a = admin.length - 1;
		if (admin.length == 0) {
			return null;
		}
		else {
			return admin[a];
		}
	}
	
	
	
	public CustomerAnalysisBean[] getAnalysisArray(int name) throws RollbackException {
        CustomerAnalysisBean[] admin = match(MatchArg.equals("business_id", name));
        if (admin.length == 0) {
            return null;
        }
        else {
            return admin;
        }
    }
	
}
