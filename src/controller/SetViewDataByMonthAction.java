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

public class SetViewDataByMonthAction extends Action {
    private FormBeanFactory<ChangeDayForm> formBeanFactory = FormBeanFactory
            .getInstance(ChangeDayForm.class);
    private CustomerAnalysisDAO customerAnalysisDAO;

    public SetViewDataByMonthAction(Model model) {
        customerAnalysisDAO = model.getCustomerAnalysisDAO();
    }

    public String getName() {
        return "view_data_month.do";
    }

    public String perform(HttpServletRequest request) {
        BusinessProfileBean business = (BusinessProfileBean) request
                .getSession(false).getAttribute("business");

        if (business == null) {
            return "login.jsp";
        }
        try {
            int a = business.getBusiness_id();

            DateFormat format = new SimpleDateFormat("yyyy-MM");
            Date currTime;
            Date timeTo;
            try {
                String curr = request.getParameter("chooseMonthFrom");
                String currTo = request.getParameter("chooseMonthTo");

                if (curr == null && currTo == null) {

                    currTo = new SimpleDateFormat("yyyy-MM")
                    .format(Calendar.getInstance().getTime());
                    
                    timeTo = format.parse(new SimpleDateFormat("yyyy-MM")
                            .format(Calendar.getInstance().getTime()));
                    Calendar cal1 = Calendar.getInstance();
                    cal1.setTime(timeTo);
                    cal1.add(Calendar.MONTH, -7);
                    curr = new SimpleDateFormat("yyyy-MM")
                    .format(cal1.getTime());
                     currTime = format.parse(new SimpleDateFormat("yyyy-MM")
                            .format(cal1.getTime()));

                } else {
                    currTime = format.parse(curr);
                    timeTo = format.parse(currTo);
                }
                CustomerAnalysisBean[] customer;

                customer = customerAnalysisDAO.getAnalysisArray(a);
                int MonthWalkIn = 0;
                Calendar cal = Calendar.getInstance();
                cal.setTime(currTime);
                DateFormat format1 = new SimpleDateFormat("yyyy-MM");
                Calendar dateFrom = Calendar.getInstance();
                dateFrom.setTime(format1.parse(curr));
                Calendar dateTo  = Calendar.getInstance();
                dateTo.setTime(format1.parse(currTo));

                int diffYear = dateTo.get(Calendar.YEAR)
                        - dateFrom.get(Calendar.YEAR);
                int diffMonth = diffYear * 12 + dateTo.get(Calendar.MONTH)
                        - dateFrom.get(Calendar.MONTH) + 1;
                cal.setTime(currTime);
                for (int i = 0; i < diffMonth; i++) {
                    cal.add(Calendar.MONTH, 1);
                    for (CustomerAnalysisBean cus : customer) {
                        // System.out.print(cus.getTime().substring(0, 10));

                        if (cus.getTime()
                                .substring(0, 7)
                                .equals(new SimpleDateFormat("yyyy-MM")
                                        .format(cal.getTime()))) {
                            MonthWalkIn = MonthWalkIn + cus.getAmount_enter();
                        }

                    }

                }
                System.out.println(MonthWalkIn);
                int MonthWalkBy = 0;
                cal.setTime(currTime);
                for (int i = 0; i < diffMonth; i++) {
                    cal.add(Calendar.MONTH, 1);
                    for (CustomerAnalysisBean cus : customer) {
                        // System.out.print(cus.getTime().substring(0, 10));

                        if (cus.getTime()
                                .substring(0, 7)
                                .equals(new SimpleDateFormat("yyyy-MM")
                                        .format(cal.getTime()))) {
                            MonthWalkBy = MonthWalkBy + cus.getAmount_passby();
                        }

                    }

                }

                System.out.println(MonthWalkBy);
                int[] visitTimebyMonth = new int[diffMonth];
                int[] walkByTimebyMonth = new int[diffMonth];
                int[] existTime = new int[diffMonth];
                int[] existTime2 = new int[diffMonth];
                for (int i = 0; i < diffMonth; i++) {
                    visitTimebyMonth[i] = -1;
                    walkByTimebyMonth[i] = -1;
                }
                cal.setTime(currTime);
                for (int i = 0; i < diffMonth; i++) {
                    
                    for (CustomerAnalysisBean cus : customer) {
                        // System.out.print(cus.getTime().substring(0, 10));

                        if (cus.getTime()
                                .substring(0, 7)
                                .equals(new SimpleDateFormat("yyyy-MM")
                                        .format(cal.getTime()))) {
                            existTime[diffMonth - 1 - i] += cus
                                    .getAmount_enter();
                            existTime2[diffMonth - 1 - i] += cus
                                    .getAmount_passby();

                        }
                    }
                    cal.add(Calendar.MONTH, 1);

                }

                cal.setTime(currTime);
                String timeMonth = new SimpleDateFormat("yyyy-MM")
                        .format(currTime.getTime());
                for (int i = 1; i < diffMonth; i++) {
                    cal.add(Calendar.MONTH, 1);
                    timeMonth = timeMonth
                            + ","
                            + new SimpleDateFormat("yyyy-MM").format(cal
                                    .getTime());
                }
                for (int i = 0; i < diffMonth; i++) {
                    if (existTime[i] == -1) {
                        visitTimebyMonth[i] = 0;
                    } else {
                        visitTimebyMonth[i] = existTime[i];
                    }

                    if (existTime2[i] == -1) {
                        walkByTimebyMonth[i] = 0;
                    } else {
                        walkByTimebyMonth[i] = existTime2[i];
                    }
                }

                String defaultWalkInView = String.valueOf(visitTimebyMonth[0]);
                String defaultWalkByView = String.valueOf(walkByTimebyMonth[0]);
                for (int i = 1; i < diffMonth; i++) {
                    defaultWalkInView = defaultWalkInView + ","
                            + String.valueOf(visitTimebyMonth[i]);
                    defaultWalkByView = defaultWalkByView + ","
                            + String.valueOf(walkByTimebyMonth[i]);
                }
                
                String monthFrom = new SimpleDateFormat("yyyy-MM").format(currTime);
                String monthTo = new SimpleDateFormat("yyyy-MM").format(timeTo);
                
                
                try {
                    String defaultWalkInView1 = customerAnalysisDAO.getWalkinByMonth(monthFrom, monthTo);
                    String defaultWalkByView2 = customerAnalysisDAO.getWalkbyByMonth(monthFrom, monthTo);
                    int totalVisit = customerAnalysisDAO.getTotalVisit();
                    request.setAttribute("currTime",
                            new SimpleDateFormat("yyyy-MM").format(currTime));
                    request.setAttribute("timeTo",
                            new SimpleDateFormat("yyyy-MM").format(timeTo));
                    request.setAttribute("defaultWalkInView", defaultWalkInView1);
                    request.setAttribute("defaultWalkByView", defaultWalkByView2);
                    request.setAttribute("MonthWalkIn", MonthWalkIn);
                    request.setAttribute("MonthWalkBy", MonthWalkBy);
                    request.setAttribute("timeMonth", timeMonth);
                    request.setAttribute("totalVisit", totalVisit);
                    
                    System.out.println(defaultWalkInView1);
                    System.out.println(defaultWalkByView2);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
               

               
                System.out.println(MonthWalkIn);
                System.out.println(MonthWalkBy);
                System.out.println(timeMonth);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (RollbackException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "view_dataMonth.jsp";
    }
}
