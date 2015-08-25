package iOS_Communication;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.genericdao.ConnectionPool;
import org.genericdao.RollbackException;

import com.google.gson.Gson;

import databeans.BusinessProfileBean;
import databeans.RegionBean;
import model.MyDAOException;
import model.RegionDAO2;

public class GetRegionServlet extends HttpServlet {
	private RegionDAO2 regionDAO;
	
	public void init() throws ServletException {
		String jdbcDriverName = "com.mysql.jdbc.Driver";
        String jdbcURL = "jdbc:mysql://aatlnydnhg5jd9.cw0kvjz4dk33.us-east-1.rds.amazonaws.com:3306/ebdb?user=nectr&password=123456789";

		
		try {
			regionDAO = new RegionDAO2(jdbcDriverName,jdbcURL,"region");
		} catch (MyDAOException e) {
			throw new ServletException(e);
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		
			try {
				List<RegionBean> regions = regionDAO.getRegionList();
				
//				RegionBean region = regionDAO.getRegion("Walnut");
//				System.out.println(region.getRegionName());
//				RegionBean[] regionlist = regionDAO.getRegionList();
				Response[] responselist = new Response[regions.size()];
				for(int i = 0;i<regions.size();i++){
					Response tmp = new Response();
					tmp.regionid = regions.get(i).getRegionId();
					tmp.radius = regions.get(i).getRadius();
					tmp.latitude = regions.get(i).getCenterLat();
					tmp.longtitude= regions.get(i).getCenterLng();
					responselist[i] = tmp;
				}
				
				RegionList reglist = new RegionList();
				reglist.regions = responselist;
				String json = new Gson().toJson(reglist);
				
				
				resp.setContentType("application/json");
				resp.getWriter().println(json);

			} catch (MyDAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

				
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req,resp);
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	


}
