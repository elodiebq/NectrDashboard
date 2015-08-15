package model;



import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;



import databeans.BusinessProfileBean;
import databeans.CampaignBean;


public class CampaignDAO extends GenericDAO<CampaignBean>  {

	public CampaignDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(CampaignBean.class, tableName, pool);
	}
	
	public CampaignBean[] getCampaign() throws RollbackException {
        CampaignBean[] campaigns = match();
        // We want them sorted by last and first names (as per
        // User.compareTo());
        return campaigns;
    }
	public CampaignBean[] getCampaigns(int name) throws RollbackException {
		CampaignBean[] campaigns = match(MatchArg.equals("business_id", name));
		// We want them sorted by last and first names (as per
		// User.compareTo());
		return campaigns;
	}
	
	public CampaignBean[] getCampaignList() throws RollbackException{
        CampaignBean[] list = match();
        if(list.length == 0) return null;
        return list;
    }
	public CampaignBean[] getCampaignByCampaign(int name) throws RollbackException {
        CampaignBean[] admin = match(MatchArg.equals("business_id", name));
        if (admin.length == 0) {
            return null;
        }
        else {
            return admin;
        }
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
	public void updateById(int id) throws RollbackException{
		CampaignBean campaign = match(MatchArg.equals("campaign_id", id))[0];
		update(campaign);
		
	}
	
}
