
package databeans;


import java.sql.Date;


import org.genericdao.PrimaryKey;

@PrimaryKey("business_id")
public class BusinessProfileBean {
	
	private int    business_id;
	private String name;
	private String phone;
	private String operation_hours;
	private String website;
	private String description;
	private String facebook;
	private String twitter;
	private Date created_at; 
	private String password;
	//private String email;
	
	
	
	public int    getBusiness_id()   { return business_id;}
	public String getName()    { return name; }
	//public String getEmail()    { return email; }
	public String getPhone()     { return phone;  }
	public String getOperation_hours() 	{ return operation_hours;	}
	public String getWebsite() 		{ return website;		}
	public String getDescription() 		{ return description;		}
	public String getFacebook() 	{ return facebook; }
	public String getTwitter() 			{ return twitter;		}
	public Date getCreated_at() 		{ return created_at;		}

	public void setBusiness_id(int i) 	  {	business_id = i; }
	public void setName(String s) 		  {	name      = s; }
	//public void setEmail(String s)    { email = s; }
    public void setPhone(String s) 	  {	phone  = s; }
	public void setOperation_housrs(String s)     {	operation_hours   = s; }
	public void setWebsite(String s) 		  {	website       = s;	}
	public void setDescription(String s)    {	description  = s; }
	public void setFacebook(String s)    {	facebook  = s; }
	public void setTwitter(String s) 		  { twitter       = s;	}
	public void setCreated_at(Date d)		  { created_at     = d; }


	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}
	public String toString() {
		return name;
	}
	
	
}
