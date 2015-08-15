package databeans;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.Random;

import org.genericdao.PrimaryKey;


@PrimaryKey("campaign_id")
public class CampaignBean {
	
	private int     campaign_id;
	private int     business_id;
	private String title;
	private int type;
	private int inStore;
	private String message;
	private String date_create;
	private String date_post;
	private String date_from;
	private String date_to;
	private String time_from;
	private String time_to;
	private String repeats_on;
	private int total_amount;
	private int used_amount;
	
	public int    getCampaign_id()              { return campaign_id;}
	public int    getBusiness_id()                 { return business_id;}
	public String getTitle()                          { return title; }
	public int getType()                           {return type;}
	public int getInStore()							{return inStore;}
	public String getMessage()                   { return message;  }
	public String getDate_create()               	{ return date_create;	}
	public String getDate_post() 		            { return date_post;		}
	public String getDate_from() 	          	{ return date_from;		}
	public String getDate_to() 	                { return date_to; }
	public String getTime_from() 	          	{ return time_from;		}
	public String getTime_to() 	                { return time_to; }
	public String getRepeats_on()               { return repeats_on;  }
	public int getTotal_amount() 			{ return total_amount;		}
	public int getUsed_amount()           { return used_amount;		}

	public void setCampaign_id(int i)                 { campaign_id = i; }
	public void setBusiness_id(int i)                    { business_id = i;}
	public void setTitle(String s)                          { title = s; }
	public void setType(int i)								{type = i;}
	public void setInStore(int i)                           {inStore = i;}
	public void setMessage(String s)                   { message = s;  }
	public void setDate_create(String s)               	{ date_create = s;	}
	public void setDate_post(String d) 		            { date_post = d;		}
	public void setDate_from(String s) 	          	{ date_from = s;		}
	public void setDate_to(String s) 	                { date_to = s; }
	public void setTime_from(String d) 	          	{ time_from = d;		}
	public void setTime_to(String s) 	                { time_to = s; }
	public void setRepeats_on(String s)              { repeats_on = s; }
	public void setTotal_amount(int s) 			{ total_amount = s;		}
	public void setUsed_amount(int s)           { used_amount = s;		}
	
}
