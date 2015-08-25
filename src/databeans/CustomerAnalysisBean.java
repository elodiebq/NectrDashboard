package databeans;

import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class CustomerAnalysisBean {
	private int     id;
	private int     business_id;
	private int amount_passby;
	private int amount_enter;
	private int total_visit;
	private String time;
	private String venderId;
	
	public int    getId()              { return id;}
	public int    getBusiness_id()                 { return business_id;}
	public int    getAmount_passby()              { return amount_passby;}
	public int    getAmount_enter()                 { return amount_enter;}
	public int    getTotal_visit()              { return total_visit;}
	public String getTime()                          { return time; }
	public String getVenderId()                          { return venderId; }
	
	public void setId(int i)                 { id = i; }
	public void setBusiness_id(int i)                    { business_id = i;}
	public void setAmount_passby(int i)                 { amount_passby = i; }
	public void setAmount_enter(int i)                    { amount_enter = i;}
	public void setTotal_visit(int i)                 { total_visit = i; }
	public void setTime (String s)                          { time = s; }
	public void setVenderId (String s)                          { venderId = s; }
	
}
