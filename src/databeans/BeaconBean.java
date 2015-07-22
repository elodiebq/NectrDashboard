
package databeans;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.Random;

import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class BeaconBean {
	
	private int    id;
	private int major_value;
	private int minor_value;
	
	
	
	
	public int getId()   { return id;}
	public int getMajor_value()    { return major_value; }
	public int getMinor_value()     { return minor_value;  }

	public void setId(int i) 	  {	id = i; }
	public void setMajor_value(int i) 		  {	major_value      = i; }
    public void setMinor_value(int i) 	  {	minor_value  = i; }
	

	
	
	
}
