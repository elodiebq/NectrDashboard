
package databeans;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.genericdao.PrimaryKey;
import org.mybeans.form.FileProperty;

@PrimaryKey("business_id")
public class BusinessProfileBean  {
	public static final List<String> EXTENSIONS = Collections.unmodifiableList(Arrays.asList( new String[] {
			".jpg", ".gif", ".JPG"
	} ));
	
	
	private int    business_id;
	private int    regionId;
	private int    id;
	private String udid;
	private String name;
	private String phone;
	private String operation_hours;
	private String website;
	private String description;
	private String facebook;
	private String twitter;
	private Date created_at; 
	private String image;
	private String password;
	private String username;
	private String address;
	private String city;
	private String inLng;
	private String inLat;
	private String category;
	private String monday;
	private String tuesday;
	private String wednesday;
	private String thursday;
	private String friday;
	private String saturday;
	private String sunday;
	
	
	
	public int    getBusiness_id()   { return business_id;}
	public int    getRegionId()   { return regionId;}
	public int    getBeaconId()   { return id;}
	public String getName()    { return name; }
	public String getPhone()     { return phone;  }
	public String getOperation_hours() 	{ return operation_hours;	}
	public String getWebsite() 		{ return website;		}
	public String getDescription() 		{ return description;		}
	public String getFacebook() 	{ return facebook; }
	public String getTwitter() 			{ return twitter;		}
	public Date getCreated_at() 		{ return created_at;		}
	public String getPassword()  {return password;}
	public String getUsername() {return username;}
	public String getImage()    {return image;}
	public String getAddress() {return address;}
	public String getCity()    {return city;}
	public String getInLng() {return inLng;}
	public String getInLat() {return inLat;}
	public String getCategory() {return category;}
	public String getUdid() {return udid;}
	public String getMonday() {return monday;}
	public String getTuesday() {return tuesday;}
	public String getWednesday() {return wednesday;}
	public String getThursday() {return thursday;}
	public String getFriday() {return friday;}
	public String getSaturday() {return saturday;}
	public String getSunday() {return sunday;}





	public void setBusiness_id(int i) 	  {	business_id = i; }
	public void setRegionId(int i)    { regionId = i; }
	public void setBeaconId(int i)    { id = i; }
	public void setName(String s) 		  {	name      = s; }
    public void setPhone(String s) 	  {	phone  = s; }
	public void setOperation_housrs(String s)     {	operation_hours   = s; }
	public void setWebsite(String s) 		  {	website       = s;	}
	public void setDescription(String s)    {	description  = s; }
	public void setFacebook(String s)    {	facebook  = s; }
	public void setTwitter(String s) 		  { twitter       = s;	}
	public void setCreated_at(Date d)		  { created_at     = d; }
	public void setPassword(String s) {password = s;}
	public void setUsername(String s) {username = s;}
	public void setAddress(String s) {address = s;}
	public void setImage(String s)       {image = s;}
	public void setCity(String s)       {city = s;}
	public void setInLng(String s){inLng = s;}
	public void setInLat(String s){inLat = s;}
	public void setCategory(String s){category = s;}
	public void setUdid(String s){udid = s;}
	public void setMonday(String s){monday = s;}
	public void setTuesday(String s){tuesday = s;}
	public void setWednesday(String s){wednesday = s;}
	public void setThursday(String s){thursday = s;}
	public void setFriday(String s){friday = s;}
	public void setSaturday(String s){saturday = s;}
	public void setSunday(String s){sunday = s;}


	


	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}
	public String toString() {
		return name;
	}
	
	
}
