
package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.BusinessProfileDAO;
import model.Model;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;


import databeans.BusinessProfileBean;;

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

	public ListAction(Model model) {
		businessDAO = model.getBusinessProfileDAO();
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
		

		//prevent multiple sessions
		      		BusinessProfileBean business = (BusinessProfileBean) request.getSession(false).getAttribute("business");
		      		if(business!=null)
		      		{
		      			return "login.jsp";
		      		}
		
		try {
			BusinessProfileBean[] businesslist = businessDAO.getBusinessList();
            StringBuilder list = new StringBuilder();
			if (businesslist.length != 0) {

				for (int i = 0; i < businesslist.length; i++) {
				list.append(businesslist[i].getInLat());
				list.append(",");
				list.append(businesslist[i].getInLng());
				list.append(" ");
				
				}
			String addList = list.toString();
			request.setAttribute("addList", addList);
			}
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
		return "create_business.jsp"; 
	}
}
