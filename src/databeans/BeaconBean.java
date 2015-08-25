
package databeans;


import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class BeaconBean {
	
	private int    id;
	private String udid;
	private int major_value;
	private int minor_value;
	
	
	
	
	
	public int getId()   { return id;}
	public String getUdid()  {return udid;}
	public int getMajor_value()    { return major_value; }
	public int getMinor_value()     { return minor_value;  }

	
	public void setId(int i) 	  {	id = i; }
	public void setUdid(String s)     {udid = s;}
	public void setMajor_value(int i) 		  {	major_value      = i; }
    public void setMinor_value(int i) 	  {	minor_value  = i; }
	

	
	
	
}
