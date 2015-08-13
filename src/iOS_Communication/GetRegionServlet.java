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
		String jdbcURL        = "jdbc:mysql:///test";

		
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
				List<RegionBean> regionlist = regionDAO.getRegionList();
				
//				RegionBean region = regionDAO.getRegion("Walnut");
//				System.out.println(region.getRegionName());
//				RegionBean[] regionlist = regionDAO.getRegionList();
				Response[] responselist = new Response[regionlist.size()];
				for(int i = 0;i<regionlist.size();i++){
					Response tmp = new Response();
					tmp.regionId = regionlist.get(i).getRegionId();
					System.out.println("tmp.regionId" + tmp.regionId);
					tmp.radius = regionlist.get(i).getRadius();
					System.out.println("tmp.radius" + tmp.radius);
					tmp.centerLat = regionlist.get(i).getCenterLat();
					System.out.println("tmp.centerLat" + tmp.centerLat);
					tmp.centerLng = regionlist.get(i).getCenterLng();
					System.out.println("tmp.centerLng" + tmp.centerLng);
					responselist[i] = tmp;
				}
				
				RegionList reglist = new RegionList();
				reglist.regionList = responselist;
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
