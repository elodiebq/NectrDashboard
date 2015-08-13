package controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.CampaignDAO;
import model.Model;

import org.mybeans.form.FormBeanFactory;

import databeans.BusinessProfileBean;
import databeans.CampaignBean;
import formbeans.ChangeDayForm;

public class ViewHistoryAction extends Action {
	private FormBeanFactory<ChangeDayForm> formBeanFactory = FormBeanFactory.getInstance(ChangeDayForm.class);
    private CampaignDAO campaignDAO;
    
    public ViewHistoryAction(Model model) {
    	campaignDAO = model.getCampaignDAO();
    }
    
    public String getName() {
        return "view_history.do";
    }
    
    public String perform(HttpServletRequest request) {
        BusinessProfileBean business = (BusinessProfileBean) request.getSession(false)
                .getAttribute("business");
       
        if (business == null) {
            return "login.jsp";
        }
        int a = business.getBusiness_id();
        try {
        	
        	CampaignBean[] campaignList = campaignDAO.getCampaign();

 
        	request.setAttribute("campaignList", campaignList);
         
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "campaign_history.jsp";
        
        
    }
}
