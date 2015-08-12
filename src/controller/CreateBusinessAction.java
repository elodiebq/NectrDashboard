package controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;

import model.BusinessProfileDAO;
import model.Model;
import model.RegionDAO;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.BusinessProfileBean;
import databeans.RegionBean;
import formbeans.CreateBusinessProfileForm;

public class CreateBusinessAction extends Action {
    private FormBeanFactory<CreateBusinessProfileForm> formBeanFactory = FormBeanFactory
            .getInstance(CreateBusinessProfileForm.class);
    private BusinessProfileDAO customerDAO;
    private RegionDAO regionDAO;

    public CreateBusinessAction(Model model) {
        customerDAO = model.getBusinessProfileDAO();
        regionDAO = model.getRegionDAO();
    }

    public String getName() {
        return "create_business.do";
    }

    public String perform(HttpServletRequest request) {
        // BusinessProfileBean business = (BusinessProfileBean)
        // request.getSession(false).getAttribute("business");
        // if (employ == null) {
        // return "login.jsp";
        // }
        try {

            List<String> errors = new ArrayList<String>();
            request.setAttribute("errors", errors);

            CreateBusinessProfileForm form = (CreateBusinessProfileForm) formBeanFactory
                    .create(request);

            if (!form.isPresent())
                return "create_business.jsp";

            errors.addAll(form.getValidationErrors());
            BusinessProfileBean[] businessList;

            try {
                System.out.print("I'm here");
                businessList = customerDAO.getBusinessList();
                HashSet<String> list = new HashSet<String>();
                if (businessList.length != 0) {

                    for (int i = 0; i < businessList.length; i++) {
                        System.out.print(businessList[i].getRegionId());
                        RegionBean regionList = regionDAO
                                .getAnalysis(businessList[i].getRegionId());
                        list.add(regionList.getRegionName());

                    }
                    String[] region = new String[list.size()];
                    java.util.Iterator<String> iterator = list.iterator();
                    int i = 0;
                    while (iterator.hasNext()) {
                        region[i] = iterator.next();
                        i++;
                    }

                    System.out.println(region);
                    request.setAttribute("region", region);
                }
            } catch (RollbackException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            BusinessProfileBean customer = new BusinessProfileBean();
            RegionBean region1 = new RegionBean();
            try {
                region1 = regionDAO.getRegion(form.getRegion());
                System.out.print(region1.getRegionName());
            } catch (RollbackException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            customer.setRegionId(region1.getRegionId());
            customer.setName(form.getName());
            customer.setPhone(form.getPhone());
            customer.setDescription(form.getDescription());
            customer.setOperation_housrs(form.getOperation_hours());
            customer.setWebsite(form.getWebsite());
            customer.setUsername(form.getUsername());
            customer.setPassword(form.getPassword());
            customer.setAddress(form.getAddress());
            customer.setCity(form.getCity());
            customer.setInLat(form.getInLat());
            customer.setInLng(form.getInLnt());

            customer.setCategory(form.getCategory());

            try {
                customerDAO.create(customer);
                request.setAttribute("msg",
                        "Business account for " + customer.getName() + " "
                                + " has been created");
            } catch (RollbackException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            // SimpleEmail email = new SimpleEmail();
            //
            // email.setTLS(true);
            // email.setSSL(true);
            // email.setHostName("smtp.gmail.com");
            // email.setSmtpPort(465);
            //
            // email.setAuthentication("cfsteam5help@gmail.com", "helphelph");
            // int i = 8;
            // String pwd = "";
            // while (i-- > 0) {
            // int a = (int) (Math.random() * 26) + 'a';
            // char b = (char) a;
            // pwd += b;
            // }
            // customer.setPassword(pwd);
            // customerDAO.create(customer);
            // System.out.print(pwd);
            //
            //
            // try {
            // email.addTo(customer.getEmail());
            // email.setFrom("cfsteam5help@gmail.com");
            // email.setSubject("Account Create Confirm");
            // email.setCharset("utf-8");
            //
            // email.setContent(new MimeMultipart("text/html"));
            // email.setMsg("Dear " + customer.getFirstName() + "," + "\n" +
            // " Thanks for using Carnegie Financial Service! Your temp-password is:"
            // + pwd + "." + "\n" +
            // "You can change password through this link: http://54.173.57.219:8080/home%20page/");
            // email.send();
            // } catch (EmailException e) {
            // // TODO Auto-generated catch block
            // e.printStackTrace();
            // }
            // request.setAttribute("pwd", pwd);
            //
            // } catch (RollbackException e) {
            // // TODO Auto-generated catch block
            // e.printStackTrace();
            // } catch (FormBeanException e) {
            // // TODO Auto-generated catch block
            // e.printStackTrace();
            // }

        } catch (FormBeanException e) {
            // // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "create_business.jsp";
    }
}