package iOS_Communication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.genericdao.RollbackException;

import model.BusinessProfileDAO;
import model.Model;
import model.RegionDAO;

import com.google.gson.Gson;

import databeans.BusinessProfileBean;
import databeans.RegionBean;

public class GetBusinessServlet extends HttpServlet {
    private BusinessProfileDAO customerDAO;
    private RegionDAO regionDAO;
	public final static double AVERAGE_RADIUS_OF_EARTH = 6371*1000;

//	public GetBusinessServlet(Model model) {
//        customerDAO = model.getBusinessProfileDAO();
//        regionDAO = model.getRegionDAO();
//    }
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if(req.getParameter("regionId") == null){
			return;
		}
		BusinessProfileBean[] businessList;
		try {
            businessList = customerDAO.getBusinessList();
            HashSet<String> list = new HashSet<String>();
            if (businessList.length != 0) {
                for (int i = 0; i < businessList.length; i++) {
                    System.out.print(businessList[i].getRegionId());
                    RegionBean regionList = regionDAO.getAnalysis(businessList[i].getRegionId());
                    list.add(regionList.getRegionName());
                }
            }
        } catch (RollbackException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        HashMap<Integer, ArrayList<String[]>> business = new HashMap<Integer, ArrayList<String[]>>();
		Response response = new Response();
		response.business = business;
		response.id = req.getParameter("userID");
		String json = new Gson().toJson(response);
		
		
		resp.setContentType("application/json");
		resp.getWriter().println(json);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req,resp);
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	

public double calculateDistance(double userLat, double userLng,
		  double venueLat, double venueLng) {

		    double latDistance = Math.toRadians(userLat - venueLat);
		    double lngDistance = Math.toRadians(userLng - venueLng);

		    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
		      + Math.cos(Math.toRadians(userLat)) * Math.cos(Math.toRadians(venueLat))
		      * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

		    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		    return (Math.round(AVERAGE_RADIUS_OF_EARTH * c));
		}
}
