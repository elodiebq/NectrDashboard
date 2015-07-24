package formbeans;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CreateCampaign extends FormBean {
	private String title;
	private String message;
	private String date_create;
	private String date_post;
	private String time_from;
	private String time_to;
	private String repeats;
	private String total_amount;
	private String used_amount;
	
	public String getTitle()                          { return title; }
	public String getMessage()                   { return message;  }
	public String getDate_create()               	{ return date_create;	}
	public String getDate_post() 		            { return date_post;		}
	public String getTime_from() 	          	{ return time_from;		}
	public String getTime_to() 	                { return time_to; }
	public String getRepeats() 			        { return repeats;		}
	public String getTotal_amount() 			{ return total_amount;		}
	public String getUsed_amount()           { return used_amount;		}

	public void setTitle(String s)                          { title = s.trim(); }
	public void setMessage(String s)                   { message = s.trim();  }
	public void setDate_create(String s)               	{ date_create = s.trim();	}
	public void setDate_post(String s) 		            { date_post = s.trim();		}
	public void setTime_from(String s) 	          	{ time_from = s.trim();		}
	public void setTime_to(String s) 	                { time_to = s.trim(); }
	public void setRepeats(String s) 			        { repeats = s.trim();		}
	public void setTotal_amount(String s) 			{ total_amount = s.trim();		}
	public void setUsed_amount(String s)           { used_amount = s.trim();		}

	
}
