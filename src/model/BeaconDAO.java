package model;



import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;




import databeans.BeaconBean;
import databeans.BusinessProfileBean;
import databeans.CampaignBean;


public class BeaconDAO extends GenericDAO<BeaconBean>  {

	public BeaconDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(BeaconBean.class, tableName, pool);
	}
	
	public BeaconBean getBeacon(int name) throws RollbackException {
		BeaconBean[] beacon = match(MatchArg.equals("id", name));
		
		if (beacon.length == 0){
		    return null;
		}
		return beacon[0];
	}
	
	
	
}
