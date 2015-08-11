package model;


import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;






import databeans.BusinessProfileBean;
import databeans.RegionBean;

public class RegionDAO extends GenericDAO<RegionBean>  {

	public RegionDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(RegionBean.class, tableName, pool);
	}
	
	public RegionBean getRegionList(int name) throws RollbackException {
	    RegionBean region[] = match(MatchArg.equals("regionId", name));
		int a = region.length - 1;
		if (region.length == 0) {
			return null;
		}
		else {
			return region[a];
		}
	}
	
	public RegionBean[] getRegionList() throws RollbackException{
		RegionBean[] regionlist = match();
		if(regionlist.length == 0) return null;
		return regionlist;
	}
	
	
	
}
