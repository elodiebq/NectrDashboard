package controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;





import javax.servlet.http.HttpServletRequest;





import databeans.BusinessProfileBean;
import databeans.CustomerAnalysisBean;
import model.BusinessProfileDAO;
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
    public String[] getPercentege(String loyalCustomer) {
        String[] array = loyalCustomer.split(",");
        
        int total = 0;
        for (int i = 0 ; i < array.length; i++) {
            total += Integer.parseInt((array[i]));
        }
        System.out.println("total" + total);
        float loyal = (Integer.parseInt(array[0])*100)/total;
        float firstTime = (Integer.parseInt(array[1])*100)/total;
        float lukeWarm = (Integer.parseInt(array[2])*100)/total;
        String[] result = new String[3];
        result[0] =  String.format("%.0f%%", loyal);
        result[1] =  String.format("%.0f%%", lukeWarm);
        result[2] =  String.format("%.0f%%", firstTime);
       
        return result;
    }
    public int getTotalCustomer(String defaultWalkInView) {
        String[] array = defaultWalkInView.split(",");
        
        int total = 0;
        for (int i = 0 ; i < array.length; i++) {
            total += Integer.parseInt((array[i]));
        }
        System.out.println("total" + total);
     
        return total;
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
            int totalWalkBy = customerAnalysisDAO.getTotalWalkBy(business.getBusiness_id());
            int totalVisitThisWeek = customerAnalysisDAO.totalVisitThisWeek(business.getBusiness_id());
            int newCustomerThisWeek = customerAnalysisDAO.getNewCustomer(business.getBusiness_id());
            int repeatCustomerThisWeek = customerAnalysisDAO.getRepeatCustomer(business.getBusiness_id());
            int walkbyThisWeek = customerAnalysisDAO.getWalkbyThisWeek(business.getBusiness_id());

            System.out.print(currTime); 
            String defaultWalkInView = customerAnalysisDAO.getWalkinByHour(currTime);
            String defaultWalkByView = customerAnalysisDAO.getWalkbyByHour(currTime);
            String loyalCustomer = customerAnalysisDAO.getLoyalCustomer(business.getBusiness_id());
            System.out.println("this my loyal customer number: " +loyalCustomer );
            
            String[] result = getPercentege(loyalCustomer);
            int total = getTotalCustomer(loyalCustomer);
//           
           //System.out.println("loyalCustomer"+loyalCustomer);
            //System.out.println(defaultWalkByView);
            
            request.setAttribute("currTime", currTime);
            request.setAttribute("defaultWalkInView", defaultWalkInView);
            System.out.println("default:" + defaultWalkInView);
            request.setAttribute("defaultWalkByView", defaultWalkByView);
            System.out.println("default:" + defaultWalkByView);

            request.setAttribute("loyalCustomer", loyalCustomer);
            //request.setAttribute("walkInByDay", walkIn);
//            request.setAttribute("walkByDay", walkBy);
            request.setAttribute("totalVisit", totalVisit);
            request.setAttribute("totalWalkBy", totalWalkBy);
            request.setAttribute("loyalPercentege", result[0]);
            request.setAttribute("lukeWarmPercentege", result[1]);
            request.setAttribute("firstTimePercentege", result[2]);
            request.setAttribute("total", total);
            request.setAttribute("totalVisitThisWeek", totalVisitThisWeek);
            request.setAttribute("newCustomerThisWeek", newCustomerThisWeek);
            request.setAttribute("repeatCustomerThisWeek", repeatCustomerThisWeek);
            request.setAttribute("walkbyThisWeek", walkbyThisWeek);

         
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "view_data.jsp";
        
        
    }
}

