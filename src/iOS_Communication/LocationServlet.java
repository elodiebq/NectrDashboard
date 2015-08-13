package iOS_Communication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class LocationServlet extends HttpServlet {
	public final static double AVERAGE_RADIUS_OF_EARTH = 6371*1000;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if(req.getParameter("latitude") == null || req.getParameter("longitude") == null || req.getParameter("userID") == null){
			return;
		}
		double userLatitude = Double.valueOf(req.getParameter("latitude"));
		double userLongitude = Double.valueOf(req.getParameter("longitude"));
		double distance = calculateDistance(userLatitude,userLongitude,40.438374, -79.922614);
				
		Response response = new Response();
	//	response.distance = distance;
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
