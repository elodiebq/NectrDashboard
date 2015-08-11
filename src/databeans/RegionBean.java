package databeans;

import org.genericdao.PrimaryKey;

@PrimaryKey("regionId")
public class RegionBean {
	private int     regionId;
	private double  centerLat;
	private double  centerLng;
	private double  radius;
	private String  regionName;
	
	public int    getId()              { return regionId;}

	public double    getCenterLat()              { return centerLat;}
	public double    getCenterLng()                 { return centerLng;}
	public double    getRadius()                 { return radius;}
	public String    getRegionName()              { return regionName;}
	
	
	public void setId(int i)                 { regionId = i; }

	public void setCenterLat(double i)                 { centerLat = i; }
	public void setCenterLng(double i)                    { centerLng = i;}
	public void setRadius(double i)                    { radius = i;}
	public void setRegionName(String i)                 { regionName = i; }
	
}
