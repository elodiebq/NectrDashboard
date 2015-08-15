
package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.AdminBean;

public class AdminDAO extends GenericDAO<AdminBean> {
	

	public AdminDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(AdminBean.class, tableName, pool);
	}
	public AdminBean getAdmin(String name) throws RollbackException {
		AdminBean admin[] = match(MatchArg.equals("username", name));
		if (admin.length == 0) {
			return null;
		}
		else {
			return admin[0];
		}
	}
	public void setPassword(String userName, String password) throws RollbackException {
        try {
        	Transaction.begin();
        	AdminBean adminName = read(userName);
			
			if (adminName == null) {
				throw new RollbackException("Admin "+ userName +" no longer exists");
			}
			
			adminName.setPassword(password);
			
			update(adminName);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
}
