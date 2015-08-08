
package databeans;

import org.genericdao.PrimaryKey;

@PrimaryKey("address")
public class AddressBean {
	
	
	private String address;
	private String inLng;
	private String inLat;

	public String getAddress() {return address;}
	public String getInLng() {return inLng;}
	public String getInLat() {return inLat;}

	public void setAddress(String s) {address = s;}
	public void setInLng(String s){inLng = s;}
	public void setInLat(String s){inLat = s;}
	
	
}
