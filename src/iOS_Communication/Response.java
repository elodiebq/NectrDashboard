package iOS_Communication;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

public class Response {
	public String id;
	public ArrayList<Campaign> campagin;
	public int businessId;
	public int uuid;
	public int major;
	public int minor;
	public String businessLat;
	public String businessLng;
	public Timestamp from;
	public Timestamp to;
	

	
	public int     regionid;
	public double  latitude;
	public double  longtitude;
	public double  radius;
	
	public String campaignTitle;
	public String businessName;
	public String campaignMessage;
	public String image;
}

