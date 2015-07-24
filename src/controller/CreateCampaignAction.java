package controller;

import java.util.ArrayList;
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

            CreateCampaign form = (CreateCampaign) formBeanFactory.create(request);

            if (!form.isPresent()) return "create_campaign.jsp";
            
            CampaignBean campaign = new CampaignBean();
            campaign.setTitle(form.getTitle());
            campaign.setTime_from(form.getTime_from());            
            campaign.setTime_to(form.getTime_to());
            campaign.setRepeats(form.getRepeats());
            campaign.setMessage(form.getMessage());
            
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
