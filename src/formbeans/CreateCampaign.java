package formbeans;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CreateCampaign extends FormBean {
	private String title;
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
	
	public String getTitle()                          { return title; }
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

	public void setTitle(String s)                          { title = s.trim(); }
	public void setMessage(String s)                   { message = s.trim();  }
	public void setDate_create(String s)               	{ date_create = s;	}
	public void setDate_post(String s) 		            { date_post = s;		}
	public void setDate_from(String s) 	          	{ date_from = s;		}
	public void setDate_to(String s) 	                { date_to = s; }
	public void setTime_from(String s) 	          	{ time_from = s;		}
	public void setTime_to(String s) 	                { time_to = s; }
	public void setRepeats_on(String s)              { repeats_on = s.trim(); }
	public void setTotal_amount(int s) 			{ total_amount = s;		}
	public void setUsed_amount(int s)           { used_amount = s;		}

}
