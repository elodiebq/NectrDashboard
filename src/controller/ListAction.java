
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
import formbeans.CreateRegion;

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
	private FormBeanFactory<CreateRegion> formBeanFactory = FormBeanFactory
			.getInstance(CreateRegion.class);

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
					if (request.getParameter(businessList[i].getName()) != null){
						int id = Integer.parseInt( request.getParameter(businessList[i].getName()));
						if (businessList[i].getRegionId() != id){
							//System.out.println("?? "+ businessList[i].getRegionId() + " "+request.getParameter(businessList[i].getName()));
							businessList[i].setRegionId(id);
							businessDAO.update(businessList[i]);
						}
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
            StringBuilder list = new StringBuilder();
			if (businesslist.length != 0) {

				for (int i = 0; i < businesslist.length; i++) {
				list.append(businesslist[i].getInLat());
				list.append(",");
				list.append(businesslist[i].getInLng());
				list.append(",");
				list.append(businesslist[i].getName());
				list.append(";");
			}
			String addList = list.toString();
			request.setAttribute("businesslist", businesslist);
			request.setAttribute("addList", addList);
			}
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
		
		try {
			RegionBean[] regionlist = regionDAO.getRegionList();
            StringBuilder list2 = new StringBuilder();
        	if(regionlist.length !=0) {
				for (int i = 0; i < regionlist.length; i++) {
					list2.append(regionlist[i].getCenterLat());
					list2.append(",");
					list2.append(regionlist[i].getCenterLng());
					list2.append(",");
					list2.append(regionlist[i].getRadius());
					list2.append(",");
					list2.append(regionlist[i].getRegionName());
					list2.append(";");
				}
				String addList2 = list2.toString();
				request.setAttribute("addList2", addList2);
				System.out.println(addList2);
				request.setAttribute("regionlist", regionlist);
			}
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
		
		try {
			CreateRegion form = (CreateRegion) formBeanFactory.create(request);
			if (!form.isPresent()) return "manage_region.jsp";
			
			RegionBean region2 = new RegionBean();
			
			double rad = Double.valueOf(request.getParameter("radius"));
			double lat = Double.valueOf(request.getParameter("centerLat"));
			double lng = Double.valueOf(request.getParameter("centerLng"));
			
			
			region2.setRegionName(form.getRegionName());
			region2.setCenterLat(lat);
			region2.setCenterLng(lng);
			region2.setRadius(rad);
			
			 try {
	                regionDAO.create(region2);
	                request.setAttribute("msg", region2.getRegionName() + " " + " has been created");
	                return "list.do";
	            } catch (RollbackException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
		} catch (FormBeanException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return "manage_region.jsp"; 
	}
}
