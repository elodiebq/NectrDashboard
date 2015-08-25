package formbeans;

import org.mybeans.form.FormBean;

public class CreateBeacon extends FormBean {
	private String    id;
	private String udid;
	private String major_value;
	private String minor_value;

	public String getId()   { return id;}
	public String getUdid()  {return udid;}
	public String getMajor_value()    { return major_value; }
	public String getMinor_value()     { return minor_value;  }

	
	public void setId(String i) 	  {	id = i; }
	public void setUdid(String s)     {udid = s;}
	public void setMajor_value(String i) 		  {	major_value      = i; }
    public void setMinor_value(String i) 	  {	minor_value  = i; }
	
}
