
package formbeans;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FileProperty;
import org.mybeans.form.FormBean;

public class CreateBusinessProfileForm extends FormBean {
	private String name;
	private String phone;
	private String operation_hours;
	private String website;
	private String description;
	private String facebook;
	private String twitter;
	private String created_at;  
	private String password;
	private String username;
	private FileProperty image;
	private String address;
	private String city;
	private String region;
	private String inLng;
	private String inLat;
	private String category;
	private String udid;
	private String monday_from;
	private String monday_to;
	private String tuesday_from;
	private String tuesday_to;
	private String wednesday_from;
	private String wednesday_to;
	private String thursday_from;
	private String thursday_to;
	private String friday_from;
	private String friday_to;
	private String saturday_from;
	private String saturday_to;
	private String sunday_from;
	private String sunday_to;

	
	public String getName()    { return name; }
	public String getPhone()     { return phone;  }
	public String getOperation_hours() 	{ return operation_hours;	}
	public String getWebsite() 		{ return website;		}
	public String getDescription() 		{ return description;		}
	public String getFacebook() 	{ return facebook; }
	public String getTwitter() 			{ return twitter;		}
	public String getCreated_at() 		{ return created_at;		}
	public String getPassword()      { return password;        }
	public String getUsername() {return username;}
	public FileProperty     getImage()    {return image;}
	public String getAddress() {return address;}
	public String getCity()    {return city;}
	public String getRegion() {return region;}
	public String getInLnt() {return inLng;}
	public String getInLat() {return inLat;}
	public String getCategory() {return category;}
	public String getUdid() {return udid;}
	public String getMonday_from() {return monday_from;}
	public String getMonday_to()  {return monday_to;}
	public String getTuesday_from() {return tuesday_from;}
	public String getTuesday_to()  {return tuesday_to;}
	public String getWednesday_from() {return wednesday_from;}
	public String getWednesday_to()  {return wednesday_to;}
	public String getThursday_from() {return thursday_from;}
	public String getThursday_to()  {return thursday_to;}
	public String getFriday_from() {return friday_from;}
	public String getFriday_to()  {return friday_to;}
	public String getSaturday_from() {return saturday_from;}
	public String getSaturday_to()  {return saturday_to;}
	public String getSunday_from() {return sunday_from;}
	public String getSunday_to()  {return sunday_to;}



	public void setName(String s) 		  {	name   = s.trim(); }
    public void setPhone(String s) 	  {	phone  = s.trim(); }
	public void setOperation_housrs(String s)     {	operation_hours   = s.trim(); }
	public void setWebsite(String s) 		  {	website       = s.trim();	}
	public void setDescription(String s)    {	description  = s.trim(); }
	public void setFacebook(String s)    {	facebook  = s.trim(); }
	public void setTwitter(String s) 		  { twitter       = s.trim();	}
	public void setCreated_at(String s)		  { created_at     = s.trim(); }
	public void setPassword(String s)      { password = s.trim();        }
	public void setUsername(String s) {username = s;}
	public void setImage(FileProperty s)        {this.image = s;}
	public void setAddress(String s) {address = s;}
	public void setCity(String s)   {city = s;}
	public void setRegion(String s) {region = s;}
	public void setInLng(String s){inLng = s;}
	public void setInLat(String s){inLat = s;}
	public void setCategory(String s){category = s;}
	public void setUdid(String s){udid = s;}
	public void setMonday_from(String s) {monday_from = s;}
	public void setMonday_to(String s) {monday_to = s;}
	public void setTuesday_from(String s) {tuesday_from = s;}
	public void setTuesday_to(String s) {tuesday_to = s;}
	public void setWednesday_from(String s) {wednesday_from = s;}
	public void setWednesday_to(String s) {wednesday_to = s;}
	public void setThursday_from(String s) {thursday_from = s;}
	public void setThursday_to(String s) {thursday_to = s;}
	public void setFriday_from(String s) {friday_from = s;}
	public void setFriday_to(String s) {friday_to = s;}
	public void setSaturday_from(String s) {saturday_from = s;}
	public void setSaturday_to(String s) {saturday_to = s;}
	public void setSunday_from(String s) {sunday_from = s;}
	public void setSunday_to(String s) {sunday_to = s;}
	



	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (name == null || name.length() == 0)
			errors.add("Username is required");
		if (password == null || password.length() == 0)
			errors.add("Password is required");

		if (errors.size() > 0)
			return errors;
		
        if (name.matches(".*[<>?*\"].*")) errors.add("User Name may not contain angle brackets or quotes");

		return errors;
	}

}
