package controller;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import model.CustomerAnalysisDAO;
import model.Model;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanFactory;

import databeans.BusinessProfileBean;
import databeans.CustomerAnalysisBean;
import formbeans.ChangeDayForm;

public class SetViewDataByDayAction extends Action {
    private FormBeanFactory<ChangeDayForm> formBeanFactory = FormBeanFactory
            .getInstance(ChangeDayForm.class);
    private CustomerAnalysisDAO customerAnalysisDAO;

    public SetViewDataByDayAction(Model model) {
        customerAnalysisDAO = model.getCustomerAnalysisDAO();
    }

    public String getName() {
        return "view_data_day.do";
    }
    public String[] getPercentege(String defaultWalkInView) {
        String[] array = defaultWalkInView.split(",");
        
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
        BusinessProfileBean business = (BusinessProfileBean) request
                .getSession(false).getAttribute("business");

        if (business == null) {
            return "login.jsp";
        }
        try {
            int a = business.getBusiness_id();

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date currTime;
            try {
                String curr = request.getParameter("chooseday");

                if (curr == null) {

                    currTime = format.parse(new SimpleDateFormat("yyyy-MM-dd")
                            .format(Calendar.getInstance().getTime()));

                } else {
                    currTime = format.parse(curr);
                }
                CustomerAnalysisBean[] customer;

                customer = customerAnalysisDAO.getAnalysisArray(a);
                

                String time = new SimpleDateFormat("yyyy-MM-dd")
                        .format(currTime.getTime());
                for (int i = 1; i < 7; i++) {
                    time = time
                            + ","
                            + new SimpleDateFormat("yyyy-MM-dd")
                                    .format(currTime.getTime() - i * 24 * 3600
                                            * 1000);
                }
                
                String defaultWalkInView;
                String defaultWalkByView;
                try {
                    defaultWalkInView = customerAnalysisDAO.getWalkinByDay(time);
                    System.out.println("currTime" + time);
                    defaultWalkByView = customerAnalysisDAO.getWalkbyByDay(time);
                    
                    int totalVisit = customerAnalysisDAO.getTotalVisit();
                    int totalWalkBy = customerAnalysisDAO.getTotalWalkBy(business.getBusiness_id());
                    int totalVisitThisWeek = customerAnalysisDAO.totalVisitThisWeek(business.getBusiness_id());
                    int newCustomerThisWeek = customerAnalysisDAO.getNewCustomer(business.getBusiness_id());
                    int repeatCustomerThisWeek = customerAnalysisDAO.getRepeatCustomer(business.getBusiness_id());
                    int walkbyThisWeek = customerAnalysisDAO.getWalkbyThisWeek(business.getBusiness_id());
                    String loyalCustomer = customerAnalysisDAO.getLoyalCustomer(a);
                    String[] result = getPercentege(loyalCustomer);
                    int total = getTotalCustomer(loyalCustomer);
                    
                    request.setAttribute("currTime",  new SimpleDateFormat("yyyy-MM-dd")
                    .format(currTime));
                    request.setAttribute("defaultWalkInView", defaultWalkInView);
                    request.setAttribute("defaultWalkByView", defaultWalkByView);
//                    request.setAttribute("OneDayWalkIn", OneDayWalkIn);
//                    request.setAttribute("OneDayWalkBy", OneDayWalkBy);
                    request.setAttribute("timeByDay", time);
                    request.setAttribute("totalVisit", totalVisit);
                    
                    
                    request.setAttribute("loyalCustomer", loyalCustomer);
                    //request.setAttribute("walkInByDay", walkIn);
//                    request.setAttribute("walkByDay", walkBy);
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

                    System.out.println("walkin" + defaultWalkInView);
                    System.out.println("walkby" + defaultWalkByView);
                    
                    System.out.println(time);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
                
                
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (RollbackException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "view_dataDay.jsp";
    }
}
