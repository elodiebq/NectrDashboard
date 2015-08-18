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
                try {
                    defaultWalkInView = customerAnalysisDAO.getWalkinByDay(time);
                    System.out.println("currTime" + time);
                    String defaultWalkByView = customerAnalysisDAO.getWalkbyByDay(time);
                    int totalVisit = customerAnalysisDAO.getTotalVisit();
                    request.setAttribute("currTime",  new SimpleDateFormat("yyyy-MM-dd")
                    .format(currTime));
                    request.setAttribute("defaultWalkInView", defaultWalkInView);
                    request.setAttribute("defaultWalkByView", defaultWalkByView);
//                    request.setAttribute("OneDayWalkIn", OneDayWalkIn);
//                    request.setAttribute("OneDayWalkBy", OneDayWalkBy);
                    request.setAttribute("timeByDay", time);
                    request.setAttribute("totalVisit", totalVisit);

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
