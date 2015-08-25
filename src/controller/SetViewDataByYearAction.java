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

public class SetViewDataByYearAction extends Action {
    private FormBeanFactory<ChangeDayForm> formBeanFactory = FormBeanFactory
            .getInstance(ChangeDayForm.class);
    private CustomerAnalysisDAO customerAnalysisDAO;

    public SetViewDataByYearAction(Model model) {
        customerAnalysisDAO = model.getCustomerAnalysisDAO();
    }

    public String getName() {
        return "view_data_year.do";
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

            DateFormat format = new SimpleDateFormat("yyyy");
            Date currTime;
            Date timeTo;
            try {
                String curr = request.getParameter("chooseYearFrom");
                String currTo = request.getParameter("chooseYearTo");

                if (curr == null && currTo == null) {

                    currTo = new SimpleDateFormat("yyyy")
                    .format(Calendar.getInstance().getTime());
                    
                    timeTo = format.parse(new SimpleDateFormat("yyyy")
                            .format(Calendar.getInstance().getTime()));
                    Calendar cal1 = Calendar.getInstance();
                    cal1.setTime(timeTo);
                    cal1.add(Calendar.YEAR, -5);
                    curr = new SimpleDateFormat("yyyy")
                    .format(cal1.getTime());
                     currTime = format.parse(new SimpleDateFormat("yyyy")
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
                DateFormat format1 = new SimpleDateFormat("yyyy");
                Calendar dateFrom = Calendar.getInstance();
                dateFrom.setTime(format1.parse(curr));
                Calendar dateTo  = Calendar.getInstance();
                dateTo.setTime(format1.parse(currTo));

                int diffYear = dateTo.get(Calendar.YEAR)
                        - dateFrom.get(Calendar.YEAR) + 1;
               
                cal.setTime(currTime);
                for (int i = 0; i < diffYear; i++) {
                    
                    for (CustomerAnalysisBean cus : customer) {
                        // System.out.print(cus.getTime().substring(0, 10));

                        if (cus.getTime()
                                .substring(0, 4)
                                .equals(new SimpleDateFormat("yyyy")
                                        .format(cal.getTime()))) {
                            MonthWalkIn = MonthWalkIn + cus.getAmount_enter();
                        }

                    }
                    cal.add(Calendar.YEAR, 1);

                }
                System.out.println(MonthWalkIn);
                int MonthWalkBy = 0;
                cal.setTime(currTime);
                for (int i = 0; i < diffYear; i++) {
                    
                    for (CustomerAnalysisBean cus : customer) {
                        // System.out.print(cus.getTime().substring(0, 10));

                        if (cus.getTime()
                                .substring(0, 4)
                                .equals(new SimpleDateFormat("yyyy")
                                        .format(cal.getTime()))) {
                            MonthWalkBy = MonthWalkBy + cus.getAmount_passby();
                        }

                    }
                    cal.add(Calendar.YEAR, 1);

                }

                System.out.println(MonthWalkBy);
                int[] visitTimebyMonth = new int[diffYear];
                int[] walkByTimebyMonth = new int[diffYear];
                int[] existTime = new int[diffYear];
                int[] existTime2 = new int[diffYear];
                for (int i = 0; i < diffYear; i++) {
                    visitTimebyMonth[i] = -1;
                    walkByTimebyMonth[i] = -1;
                }
                cal.setTime(currTime);
                for (int i = 0; i < diffYear; i++) {
                   
                    for (CustomerAnalysisBean cus : customer) {
                        System.out.print(cus.getTime().substring(0, 4));

                        if (cus.getTime()
                                .substring(0, 4)
                                .equals(new SimpleDateFormat("yyyy")
                                        .format(cal.getTime()))) {
                            existTime[diffYear - 1 - i] += cus
                                    .getAmount_enter();
                            existTime2[diffYear - 1 - i] += cus
                                    .getAmount_passby();

                        }
                    }
                    cal.add(Calendar.YEAR, 1);

                }

                cal.setTime(currTime);
                String timeMonth = new SimpleDateFormat("yyyy")
                        .format(currTime.getTime());
                for (int i = 1; i < diffYear; i++) {
                    cal.add(Calendar.YEAR, 1);
                    timeMonth = timeMonth
                            + ","
                            + new SimpleDateFormat("yyyy").format(cal
                                    .getTime());
                }
                for (int i = 0; i < diffYear; i++) {
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
                for (int i = 1; i < diffYear; i++) {
                    defaultWalkInView = defaultWalkInView + ","
                            + String.valueOf(visitTimebyMonth[i]);
                    defaultWalkByView = defaultWalkByView + ","
                            + String.valueOf(walkByTimebyMonth[i]);
                }
                String yearFrom = new SimpleDateFormat("yyyy").format(currTime);
                String yearTo = new SimpleDateFormat("yyyy").format(timeTo);
                try {
                    String defaultWalkInView1 = customerAnalysisDAO.getWalkinByYear(yearFrom, yearTo);
                    String defaultWalkByView1 = customerAnalysisDAO.getWalkbyByYear(yearFrom, yearTo);
                    int totalVisit = customerAnalysisDAO.getTotalVisit();
                    int totalWalkBy = customerAnalysisDAO.getTotalWalkBy(business.getBusiness_id());
                    int totalVisitThisWeek = customerAnalysisDAO.totalVisitThisWeek(business.getBusiness_id());
                    int newCustomerThisWeek = customerAnalysisDAO.getNewCustomer(business.getBusiness_id());
                    int repeatCustomerThisWeek = customerAnalysisDAO.getRepeatCustomer(business.getBusiness_id());
                    int walkbyThisWeek = customerAnalysisDAO.getWalkbyThisWeek(business.getBusiness_id());
                    String loyalCustomer = customerAnalysisDAO.getLoyalCustomer(a);
                    String[] result = getPercentege(loyalCustomer);
                    int total = getTotalCustomer(loyalCustomer);
                    
                    request.setAttribute("currTime",
                            new SimpleDateFormat("yyyy").format(currTime));
                    request.setAttribute("timeTo",
                            new SimpleDateFormat("yyyy").format(timeTo));
                    request.setAttribute("defaultWalkInView", defaultWalkInView1);
                    request.setAttribute("defaultWalkByView", defaultWalkByView1);
                    request.setAttribute("MonthWalkIn", MonthWalkIn);
                    request.setAttribute("MonthWalkBy", MonthWalkBy);
                    request.setAttribute("timeMonth", timeMonth);
                    request.setAttribute("totalVisit", totalVisit);
                    request.setAttribute("loyalCustomer", loyalCustomer);
                    //request.setAttribute("walkInByDay", walkIn);
//                    request.setAttribute("walkByDay", walkBy);
                    
                    request.setAttribute("totalWalkBy", totalWalkBy);
                    request.setAttribute("loyalPercentege", result[0]);
                    request.setAttribute("lukeWarmPercentege", result[1]);
                    request.setAttribute("firstTimePercentege", result[2]);
                    request.setAttribute("total", total);
                    request.setAttribute("totalVisitThisWeek", totalVisitThisWeek);
                    request.setAttribute("newCustomerThisWeek", newCustomerThisWeek);
                    request.setAttribute("repeatCustomerThisWeek", repeatCustomerThisWeek);
                    request.setAttribute("walkbyThisWeek", walkbyThisWeek);

                    System.out.println(defaultWalkInView1);
                    System.out.println(defaultWalkByView1);
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

        return "view_dataYear.jsp";
    }
}
