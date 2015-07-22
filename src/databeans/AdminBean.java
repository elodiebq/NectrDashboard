
package databeans;

import org.genericdao.PrimaryKey;

@PrimaryKey("username")
public class AdminBean {
	
	private String username;
	private String password;

	public String getUsername()    { return username; }
	public String getPassword()     { return password;  }


	public void setUsername(String s) 		  {	username      = s; }
    public void setPassword(String s) 	  {	password  = s; }
	
	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}
	
	
}
