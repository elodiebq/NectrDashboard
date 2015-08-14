//Hua-Ming Lee
//huamingl
//08-600
//hw9
//2014/12/1

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
