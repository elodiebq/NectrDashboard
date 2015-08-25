package controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;

import model.BeaconDAO;
import model.BusinessProfileDAO;
import model.CampaignDAO;
import model.Model;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.BeaconBean;
import databeans.BusinessProfileBean;
import databeans.CampaignBean;
import formbeans.CreateBeacon;
import formbeans.CreateCampaign;

public class CreateBeaconAction extends Action{
	private FormBeanFactory<CreateBeacon> formBeanFactory = FormBeanFactory.getInstance(CreateBeacon.class);
    private BeaconDAO beaconDAO;
    
    public CreateBeaconAction(Model model) {
    	beaconDAO = model.getBeaconDAO();
    }

    public String getName() {
        return "create_beacon.do";
    }
    
    public String perform(HttpServletRequest request) {
    	try {

            List<String> errors = new ArrayList<String>();
            request.setAttribute("errors", errors);
        
            
            CreateBeacon form = (CreateBeacon) formBeanFactory.create(request);

            if (!form.isPresent()) return "create_business.jsp";
            System.out.println("here");
            
            BeaconBean beacon = new BeaconBean();
            beacon.setMajor_value(Integer.parseInt(form.getMajor_value()));
            beacon.setMinor_value(Integer.parseInt(form.getMinor_value()));
            beacon.setUdid(form.getUdid());

       
          
            try {
                beaconDAO.create(beacon);
            } catch (RollbackException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            request.setAttribute("msg", "Beacon " + beacon.getUdid() + " " + " has been created");

    	}catch (FormBeanException e) {
//          // TODO Auto-generated catch block
          e.printStackTrace();
      }
      return "create_business.jsp";
    }
}
