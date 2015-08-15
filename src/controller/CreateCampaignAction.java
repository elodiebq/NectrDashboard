package controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;

import model.BusinessProfileDAO;
import model.CampaignDAO;
import model.Model;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.BusinessProfileBean;
import databeans.CampaignBean;
import formbeans.CreateCampaign;

public class CreateCampaignAction extends Action{
	private FormBeanFactory<CreateCampaign> formBeanFactory = FormBeanFactory.getInstance(CreateCampaign.class);
    private CampaignDAO campaignDAO;
    
    public CreateCampaignAction(Model model) {
        campaignDAO = model.getCampaignDAO();
    }

    public String getName() {
        return "create_campaign.do";
    }
    
    public String perform(HttpServletRequest request) {
    	try {

            List<String> errors = new ArrayList<String>();
            request.setAttribute("errors", errors);
            String timeStamp = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
            String c[] = request.getParameterValues("repeats_on");
            StringBuffer sb = new StringBuffer();
            if (request.getParameterValues("repeats_on")!=null ){
            	
            
            	for (int i = 0; i < c.length; i++)
            		sb.append(c[i]);
            }
            
            CreateCampaign form = (CreateCampaign) formBeanFactory.create(request);
            String datepost = form.getDate_from().substring(0, 10);

            if (!form.isPresent()) return "create_campaign.jsp";
            
            CampaignBean campaign = new CampaignBean();
            campaign.setDate_create(timeStamp);
            campaign.setDate_post(datepost);
            campaign.setDate_from(form.getDate_from());
            campaign.setDate_to(form.getDate_to());          
            campaign.setTitle(form.getTitle());
            campaign.setType(Integer.parseInt(form.getType()));
            campaign.setTime_from(form.getTime_from()+ ":00");            
            campaign.setTime_to(form.getTime_to() + ":00");
            if (sb.toString() != null || c.length != 0)
            	campaign.setRepeats_on(sb.toString());
            campaign.setMessage(form.getMessage());
            BusinessProfileBean busi = (BusinessProfileBean) request.getSession(false).getAttribute("business");
            campaign.setBusiness_id(busi.getBusiness_id());
            
            try {
                campaignDAO.create(campaign);
            } catch (RollbackException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            request.setAttribute("msg", "Campaign for " + campaign.getTitle() + " " + " has been created");
    	}catch (FormBeanException e) {
//          // TODO Auto-generated catch block
          e.printStackTrace();
      }
      return "create_campaign.jsp";
    }
}
