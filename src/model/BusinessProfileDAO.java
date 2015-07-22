
package model;

import java.util.Arrays;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.BusinessProfileBean;


public class BusinessProfileDAO extends GenericDAO<BusinessProfileBean> {
	

	public BusinessProfileDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(BusinessProfileBean.class, tableName, pool);
	}

	public BusinessProfileBean getBusiness(String name) throws RollbackException {
		BusinessProfileBean admin[] = match(MatchArg.equals("name", name));
		if (admin.length == 0) {
			return null;
		}
		else {
			return admin[0];
		}
	}
	
//	public void setPassword(int id, String password) throws RollbackException {
//        try {
//        	Transaction.begin();
//			BusinessProfileBean dbBusiness = read(id);
//			
//			if (dbBusiness == null) {
//				throw new RollbackException(id +" no longer exists");
//			}
//			
//			dbBusiness.setPassword(password);
//			
//			update(dbBusiness);
//			Transaction.commit();
//		} finally {
//			if (Transaction.isActive()) Transaction.rollback();
//		}
//	}
	
//	public CustomerBean getCustomerByEmail(String email) throws RollbackException {
//			CustomerBean[] dbCustomer = match(MatchArg.equals("email", email));
//			if (dbCustomer.length == 0) return null;
//			return dbCustomer[0];
//
//	}
}
