package iOS_Communication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.genericdao.RollbackException;

import com.google.gson.Gson;

import databeans.BusinessProfileBean;
import databeans.RegionBean;
import model.RegionDAO;

public class GetRegionServlet extends HttpServlet {
	
    private RegionDAO regionDAO;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		
			try {
				RegionBean[] regionlist = regionDAO.getRegionList();
				Response[] responselist = new Response[regionlist.length];
				for(int i = 0;i<regionlist.length;i++){
					responselist[i].regionId = regionlist[i].getRegionId();
					responselist[i].radius = regionlist[i].getRadius();
					responselist[i].centerLat = regionlist[i].getCenterLat();
					responselist[i].centerLng = regionlist[i].getCenterLng();
				}
				
				RegionList reglist = new RegionList();
				reglist.regionList = responselist;
				String json = new Gson().toJson(reglist);
				
				
				resp.setContentType("application/json");
				resp.getWriter().println(json);

			} catch (RollbackException e) {
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
