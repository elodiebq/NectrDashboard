package model;



import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;


import databeans.CampaignBean;


public class CampaignDAO extends GenericDAO<CampaignBean>  {

	public CampaignDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(CampaignBean.class, tableName, pool);
	}
	
	public CampaignBean getBusiness(String name) throws RollbackException {
		CampaignBean admin[] = match(MatchArg.equals("name", name));
		if (admin.length == 0) {
			return null;
		}
		else {
			return admin[0];
		}
	}
	
}
