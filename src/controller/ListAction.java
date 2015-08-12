
package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.BusinessProfileDAO;
import model.Model;
import model.RegionDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;




import databeans.BusinessProfileBean;
import databeans.RegionBean;

/*
 * Looks up the photos for a given "user".
 * 
 * If successful:
 *   (1) Sets the "userList" request attribute in order to display
 *       the list of users on the navbar.
 *   (2) Sets the "photoList" request attribute in order to display
 *       the list of given user's photos for selection.
 *   (3) Forwards to list.jsp.
 */
public class ListAction extends Action {
//	private FormBeanFactory<ListForm> formBeanFactory = FormBeanFactory
//			.getInstance(ListForm.class);

	private BusinessProfileDAO businessDAO;
	private RegionDAO regionDAO;

	public ListAction(Model model) {
		businessDAO = model.getBusinessProfileDAO();
		regionDAO = model.getRegionDAO();
	}

	public String getName() {
		return "list.do";
	}

	public String perform(HttpServletRequest request) {
		// Set up the request attributes (the errors list and the form bean so
		// we can just return to the jsp with the form if the request isn't
		// correct)
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		if (request.getParameter("action") != null){
			BusinessProfileBean[] businessList;
			try {
				businessList = businessDAO.getBusinessList();
				for (int i = 0; i < businessList.length; i++){
					int id = Integer.parseInt( request.getParameter(businessList[i].getName()));
					if (businessList[i].getRegionId() != id){
						//System.out.println("?? "+ businessList[i].getRegionId() + " "+request.getParameter(businessList[i].getName()));
						businessList[i].setRegionId(id);
						businessDAO.update(businessList[i]);
					}
				}
			} catch (RollbackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//prevent multiple sessions
		      		BusinessProfileBean business = (BusinessProfileBean) request.getSession(false).getAttribute("business");
		      		RegionBean region = (RegionBean) request.getSession(false).getAttribute("region");
		      		if(business!=null)
		      		{
		      			return "login.jsp";
		      		}
		      		
		try {
			BusinessProfileBean[] businesslist = businessDAO.getBusinessList();
			RegionBean[] regionlist = regionDAO.getRegionList();
            StringBuilder list = new StringBuilder();
			if (businesslist.length != 0) {

				for (int i = 0; i < businesslist.length; i++) {
				list.append(businesslist[i].getInLat());
				list.append(",");
				list.append(businesslist[i].getInLng());
				list.append(" ");
				
				}
			String addList = list.toString();
			request.setAttribute("businesslist", businesslist);
			request.setAttribute("addList", addList);
			request.setAttribute("regionlist", regionlist);
			}
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
		return "manage_region.jsp"; 
	}
}
