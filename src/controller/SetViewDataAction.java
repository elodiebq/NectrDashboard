package controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;



import javax.servlet.http.HttpServletRequest;



import databeans.BusinessProfileBean;
import databeans.CustomerAnalysisBean;
import model.CustomerAnalysisDAO;
import model.CustomerAnalysisDAO2;
import model.Model;

public class SetViewDataAction extends Action {
  
    private CustomerAnalysisDAO customerAnalysisDAO;
   
    
    public SetViewDataAction(Model model) {
        customerAnalysisDAO = model.getCustomerAnalysisDAO();
    }

    public String getName() {
        return "view_data.do";
    }
    
    public String perform(HttpServletRequest request) {
        BusinessProfileBean business = (BusinessProfileBean) request.getSession(false)
                .getAttribute("business");
       
        if (business == null) {
            return "login.jsp";
        }
        int a = business.getBusiness_id();
        
        try {
            String currTime = request.getParameter("chooseday");
            System.out.println(currTime);
            if (currTime == null) {
                currTime = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
                
            } 
            int totalVisit = customerAnalysisDAO.getTotalVisit();
            System.out.print(currTime); 
            String defaultWalkInView = customerAnalysisDAO.getWalkinByHour(currTime);
            String defaultWalkByView = customerAnalysisDAO.getWalkbyByHour(currTime);
//           
//            System.out.println(defaultWalkInView);
            //System.out.println(defaultWalkByView);
            
            request.setAttribute("currTime", currTime);
            request.setAttribute("defaultWalkInView", defaultWalkInView);
            System.out.println("default:" + defaultWalkInView);
            request.setAttribute("defaultWalkByView", defaultWalkByView);
            //request.setAttribute("walkInByDay", walkIn);
//            request.setAttribute("walkByDay", walkBy);
            request.setAttribute("totalVisit", totalVisit);
         
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "view_data.jsp";
        
        
    }
}

