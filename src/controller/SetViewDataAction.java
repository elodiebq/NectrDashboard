package controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;


import javax.servlet.http.HttpServletRequest;


import databeans.BusinessProfileBean;
import databeans.CustomerAnalysisBean;
import model.CustomerAnalysisDAO;
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
            int totalVisit = customerAnalysisDAO.getAnalysis(a).getTotal_visit();
            //System.out.print(currTime); 
            CustomerAnalysisBean[] customer = customerAnalysisDAO.getAnalysisArray(a);
            int walkIn = 0;
            for (CustomerAnalysisBean cus: customer) {
                //System.out.print(cus.getTime().substring(0, 10));
                if (cus.getTime().substring(0, 10).equals(currTime)) {
                    walkIn = walkIn + cus.getAmount_enter();
                }
            }
            int walkBy = 0;
            for (CustomerAnalysisBean cus: customer) {
                //System.out.print(cus.getTime().substring(0, 10));
                if (cus.getTime().substring(0, 10).equals(currTime)) {
                    walkBy = walkBy + cus.getAmount_passby();
                }
            }
            
            int[] visitTimebyHour = new int[12];
            int[] walkByTimebyHour = new int[12];
            int[] existTime = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
            int[] existTime2 = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
            for (CustomerAnalysisBean cus: customer) {
                        //System.out.print(cus.getTime().substring(0, 10));
                if (cus.getTime().substring(0, 10).equals(currTime)) {
                    existTime[Integer.parseInt(cus.getTime().substring(11, 13))/2] = cus.getAmount_enter();
                    existTime2[Integer.parseInt(cus.getTime().substring(11, 13))/2] = cus.getAmount_passby();
                    System.out.print(Integer.parseInt(cus.getTime().substring(11, 13)));
                }
                   
                    
                }
            for (int i = 0; i < 12; i++) {
                if (existTime[i] == -1) {
                    visitTimebyHour[i] = 0;
                } else {
                    visitTimebyHour[i] = existTime[i];
                }
                
                if (existTime2[i] == -1) {
                    walkByTimebyHour[i] = 0;
                } else {
                    walkByTimebyHour[i] = existTime2[i];
                }
            }
            String defaultWalkInView = String.valueOf(visitTimebyHour[0]);
            String defaultWalkByView = String.valueOf(walkByTimebyHour[0]);
            for (int i = 1; i < 12; i++) {
                defaultWalkInView = defaultWalkInView + "," + String.valueOf(visitTimebyHour[i]);
                defaultWalkByView = defaultWalkByView + "," + String.valueOf(walkByTimebyHour[i]);
            }
            System.out.println(defaultWalkInView);
            System.out.println(defaultWalkByView);
            
            request.setAttribute("currTime", currTime);
            request.setAttribute("defaultWalkInView", defaultWalkInView);
            request.setAttribute("defaultWalkByView", defaultWalkByView);
            request.setAttribute("walkInByDay", walkIn);
            request.setAttribute("walkByDay", walkBy);
            request.setAttribute("totalVisit", totalVisit);
         
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "view_data.jsp";
        
        
    }
}

