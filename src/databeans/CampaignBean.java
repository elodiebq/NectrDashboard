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
	private String message;
	private Date date_create;
	private Date date_post;
	private String time_from;
	private String time_to;
	private String repeats;
	private String total_amount;
	private String used_amount;
	
	public int    getCampaign_id()              { return campaign_id;}
	public int    getBusiness_id()                 { return business_id;}
	public String getTitle()                          { return title; }
	public String getMessage()                   { return message;  }
	public Date getDate_create()               	{ return date_create;	}
	public Date getDate_post() 		            { return date_post;		}
	public String getTime_from() 	          	{ return time_from;		}
	public String getTime_to() 	                { return time_to; }
	public String getRepeats() 			        { return repeats;		}
	public String getTotal_amount() 			{ return total_amount;		}
	public String getUsed_amount()           { return used_amount;		}

	public void setCampaign_id(int i)                 { campaign_id = i; }
	public void setBusiness_id(int i)                    { business_id = i;}
	public void setTitle(String s)                          { title = s; }
	public void setMessage(String s)                   { message = s;  }
	public void setDate_create(Date d)               	{ date_create = d;	}
	public void setDate_post(Date d) 		            { date_post = d;		}
	public void setTime_from(String s) 	          	{ time_from = s;		}
	public void setTime_to(String s) 	                { time_to = s; }
	public void setRepeats(String s) 			        { repeats = s;		}
	public void setTotal_amount(String s) 			{ total_amount = s;		}
	public void setUsed_amount(String s)           { used_amount = s;		}
	
}
