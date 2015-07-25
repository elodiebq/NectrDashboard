package controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.AdminDAO;
import model.BusinessProfileDAO;
import model.Model;


import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.AdminBean;
import databeans.BusinessProfileBean;
import formbeans.LoginForm;


/*
 * Processes the parameters from the form in login.jsp.
 * If successful, set the "user" session attribute to the
 * user's User bean and then redirects to view the originally
 * requested photo.  If there was no photo originally requested
 * to be viewed (as specified by the "redirect" hidden form
 * value), just redirect to manage.do to allow the user to manage
 * his photos.
 */
public class LoginAction extends Action {
	private FormBeanFactory<LoginForm> formBeanFactory = FormBeanFactory.getInstance(LoginForm.class);
	
	private AdminDAO adminDAO;
	private BusinessProfileDAO  businessDAO;


	public LoginAction(Model model) {
		adminDAO = model.getAdminDAO();
		businessDAO = model.getBusinessProfileDAO();
	}

	public String getName() { return "login.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        HttpSession session = request.getSession(false);
        session.setAttribute("errors",errors);
        
        try {
        	
	    	LoginForm form = formBeanFactory.create(request);
	    	
	    	session.setAttribute("form",form);


	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "login.jsp";
	        }

	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "login.jsp";
	        }

	        
	        // Look up the user
        	System.out.println(form.getType());

	        if (form.getType().equals("business")) {
	        	BusinessProfileBean business = businessDAO.getBusiness(form.getUsername());
	        	if (business == null) {
		            errors.add("This business does not exist");
		            System.out.println("This business does not exist");
		            return "login.jsp";
		        }
	        	
	        	if (!business.checkPassword(form.getPassword())) {
	        		
		            errors.add("Incorrect password");
		            return "login.jsp";
		        }
	        	//prevent multiple sessions
	        	BusinessProfileBean busi = (BusinessProfileBean) request.getSession(false).getAttribute("business");
	      		if(busi!=null)
	      		{
	      			return "login.jsp";
	      		}
	        	
	        	session.setAttribute("business",business);
				return "employee_login.jsp";
	        }
	        else {
	        	AdminBean admin = adminDAO.getAdmin(form.getUsername());
	    
	        	if (admin == null) {
		            errors.add("This admin does not exist");
		            return "login.jsp";
		        }
	        	System.out.println("username" + admin.getUsername());
	        	System.out.println("Password" + admin.getPassword());


	        	if (!admin.checkPassword(form.getPassword())) {
		            errors.add("Incorrect password");
		            return "login.jsp";
		        }
	        	
//	        	//prevent multiple sessions
//	    		AdminBean a = (AdminBean) request.getSession(false).getAttribute("admin");
//	    		if(a!=null)
//	    		{
//	    			return "login.jsp";
//	    		}
	        	
	        	session.setAttribute("admin",admin);
				return "create_campaign.jsp";
	        }
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}
