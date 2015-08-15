package controller;

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
                int OneDayWalkIn = 0;
                for (int i = 0; i < 7; i++) {
                    for (CustomerAnalysisBean cus : customer) {
                        // System.out.print(cus.getTime().substring(0, 10));
                        if (cus.getTime()
                                .substring(0, 10)
                                .equals(new SimpleDateFormat("yyyy-MM-dd")
                                        .format(currTime.getTime() - i * 24
                                                * 3600 * 1000))) {
                            OneDayWalkIn = OneDayWalkIn + cus.getAmount_enter();
                        }
                        System.out.println(new SimpleDateFormat("yyyy-MM-dd")
                                .format(currTime.getTime() - i * 24 * 3600
                                        * 1000));
                    }

                }
                int OneDayWalkBy = 0;
                for (int i = 0; i < 7; i++) {
                    for (CustomerAnalysisBean cus : customer) {
                        // System.out.print(cus.getTime().substring(0, 10));
                        if (cus.getTime()
                                .substring(0, 10)
                                .equals(new SimpleDateFormat("yyyy-MM-dd")
                                        .format(currTime.getTime() - i * 24
                                                * 3600 * 1000))) {
                            OneDayWalkBy = OneDayWalkBy
                                    + cus.getAmount_passby();
                        }
                        System.out.println(new SimpleDateFormat("yyyy-MM-dd")
                                .format(currTime.getTime() - i * 24 * 3600
                                        * 1000));
                    }

                }

                int[] visitTimebyDay = new int[7];
                int[] walkByTimebyDay = new int[7];
                int[] existTime = { -1, -1, -1, -1, -1, -1, -1 };
                int[] existTime2 = { -1, -1, -1, -1, -1, -1, -1 };
                for (int i = 0; i < 7; i++) {
                    for (CustomerAnalysisBean cus : customer) {
                        // System.out.print(cus.getTime().substring(0, 10));
                        if (cus.getTime()
                                .substring(0, 10)
                                .equals(new SimpleDateFormat("yyyy-MM-dd")
                                        .format(currTime.getTime() - i * 24
                                                * 3600 * 1000))) {
                            existTime[6 - i] += cus.getAmount_enter();
                            existTime2[6 - i] += cus.getAmount_passby();

                        }
                    }

                }

                String time = new SimpleDateFormat("yyyy-MM-dd")
                        .format(currTime.getTime());
                for (int i = 1; i < 7; i++) {
                    time = time
                            + ","
                            + new SimpleDateFormat("yyyy-MM-dd")
                                    .format(currTime.getTime() - i * 24 * 3600
                                            * 1000);
                }
                for (int i = 0; i < 7; i++) {
                    if (existTime[i] == -1) {
                        visitTimebyDay[i] = 0;
                    } else {
                        visitTimebyDay[i] = existTime[i];
                    }

                    if (existTime2[i] == -1) {
                        walkByTimebyDay[i] = 0;
                    } else {
                        walkByTimebyDay[i] = existTime2[i];
                    }
                }

                String defaultWalkInView = String.valueOf(visitTimebyDay[0]);
                String defaultWalkByView = String.valueOf(walkByTimebyDay[0]);
                for (int i = 1; i < 7; i++) {
                    defaultWalkInView = defaultWalkInView + ","
                            + String.valueOf(visitTimebyDay[i]);
                    defaultWalkByView = defaultWalkByView + ","
                            + String.valueOf(walkByTimebyDay[i]);
                }
                int totalVisit = customerAnalysisDAO.getAnalysis(a).getTotal_visit();
                request.setAttribute("currTime",  new SimpleDateFormat("yyyy-MM-dd")
                .format(currTime));
                request.setAttribute("defaultWalkInView", defaultWalkInView);
                request.setAttribute("defaultWalkByView", defaultWalkByView);
                request.setAttribute("OneDayWalkIn", OneDayWalkIn);
                request.setAttribute("OneDayWalkBy", OneDayWalkBy);
                request.setAttribute("timeByDay", time);
                request.setAttribute("totalVisit", totalVisit);

                System.out.println(defaultWalkInView);
                System.out.println(defaultWalkByView);
                System.out.println(OneDayWalkIn);
                System.out.println(OneDayWalkBy);
                System.out.println(time);
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
