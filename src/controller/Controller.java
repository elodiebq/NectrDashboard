
package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AdminDAO;
import model.Model;

import org.genericdao.RollbackException;

import databeans.AdminBean;
import databeans.BusinessProfileBean;

public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;
	int i=0;
	public void init() throws ServletException {
		Model model = new Model(getServletConfig());
		
		Action.add(new LoginAction(model));
		
	
	initializeTable(model.getAdminDAO());
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = performTheAction(request);
		sendToNextPage(nextPage, request, response);
	}

	private String performTheAction(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String servletPath = request.getServletPath();
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		BusinessProfileBean business = (BusinessProfileBean) session.getAttribute("business");

		String action = getActionName(servletPath);


		if (action.equals("register.do") || action.equals("login.do")
				|| action.equals("list.do") || action.equals("update.do")) {
			// Allow these actions without logging in
			return Action.perform(action, request);
		}

		if (admin == null && business == null) {
			// If the user hasn't logged in, direct him to the login page
			return Action.perform("login.do", request);
		}

		// Let the logged in user run his chosen action
		return Action.perform(action, request);
	}

	private void sendToNextPage(String nextPage, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		if (nextPage == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND,
					request.getServletPath());
			return;
		}

		if (nextPage.endsWith(".do")) {
			response.sendRedirect(nextPage);
			return;
		}

		if (nextPage.endsWith(".jsp")) {
			//"WEB-INF/"+ 
			RequestDispatcher d = request.getRequestDispatcher(nextPage);
			
			d.forward(request, response);
			return;
		}

		throw new ServletException(Controller.class.getName()
				+ ".sendToNextPage(\"" + nextPage + "\"): invalid extension.");
	}

	private String getActionName(String path) {
		int slash = path.lastIndexOf('/');
		return path.substring(slash + 1);
	}
	
	
	public void initializeTable(AdminDAO adminDAO) {

		try {
			if (adminDAO.read("admin") == null) {
				AdminBean admin = new AdminBean();
		
				admin.setPassword("123456");
				admin.setUsername("admin");
				adminDAO.create(admin);
			}
		} catch (RollbackException e) {
			e.printStackTrace();
		}

	}
	
}
