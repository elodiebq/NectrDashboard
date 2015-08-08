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
	
	public CampaignBean[] getCampaigns() throws RollbackException {
		CampaignBean[]campaigns = match();
		// We want them sorted by last and first names (as per
		// User.compareTo());
		return campaigns;
	}
	
	public CampaignBean getBusiness(int name) throws RollbackException {
		CampaignBean admin[] = match(MatchArg.equals("business_id", name));
		if (admin.length == 0) {
			return null;
		}
		else {
			return admin[0];
		}
	}
	
}
