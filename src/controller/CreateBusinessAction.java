package controller;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;

import model.BusinessProfileDAO;
import model.Model;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.BusinessProfileBean;
import formbeans.CreateBusinessProfileForm;


public class CreateBusinessAction extends Action {
    private FormBeanFactory<CreateBusinessProfileForm> formBeanFactory = FormBeanFactory.getInstance(CreateBusinessProfileForm.class);
    private BusinessProfileDAO customerDAO;

    public CreateBusinessAction(Model model) {
        customerDAO = model.getBusinessProfileDAO();
    }

    public String getName() {
        return "create_business.do";
    }

    public String perform(HttpServletRequest request) {
        //BusinessProfileBean business = (BusinessProfileBean) request.getSession(false).getAttribute("business");
//        if (employ == null) {
//            return "login.jsp";
//        }
        try {

            List<String> errors = new ArrayList<String>();
            request.setAttribute("errors", errors);

            CreateBusinessProfileForm form = (CreateBusinessProfileForm) formBeanFactory.create(request);

            if (!form.isPresent()) return "create_business.jsp";

            errors.addAll(form.getValidationErrors());
            //if (errors.size() != 0) return "create_business.jsp";

//            BusinessProfileBean[] cb;
//            try {
//                cb = customerDAO.match(MatchArg.equals("email", form.getEmail()));
//                if (cb.length != 0) {
//                    errors.add("Email already exists.");
//                    return "employee_createcustomer.do";
//                }
//            } catch (RollbackException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            

            BusinessProfileBean customer = new BusinessProfileBean();
            customer.setName(form.getName());
            customer.setPhone(form.getPhone());
            customer.setDescription(form.getDescription());
            customer.setOperation_housrs(form.getOperation_hours());
            customer.setWebsite(form.getWebsite());
            customer.setUsername(form.getUsername());
            customer.setPassword(form.getPassword());
            customer.setInLat(form.getInLat());
            customer.setInLng(form.getInLnt());
            customer.setCategory(form.getCategory());
            
            

            try {
                customerDAO.create(customer);
            } catch (RollbackException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            request.setAttribute("msg", "Business account for " + customer.getName() + " " + " has been created");
//            SimpleEmail email = new SimpleEmail();
//
//            email.setTLS(true);
//            email.setSSL(true);
//            email.setHostName("smtp.gmail.com");
//            email.setSmtpPort(465);
//
//            email.setAuthentication("cfsteam5help@gmail.com", "helphelph");
//            int i = 8;
//            String pwd = "";
//            while (i-- > 0) {
//                int a = (int) (Math.random() * 26) + 'a';
//                char b = (char) a;
//                pwd += b;
//            }
//            customer.setPassword(pwd);
//            customerDAO.create(customer);
//            System.out.print(pwd);
//            
//                
//                try {
//                    email.addTo(customer.getEmail());
//                    email.setFrom("cfsteam5help@gmail.com");
//                    email.setSubject("Account Create Confirm");
//                    email.setCharset("utf-8");
//
//                    email.setContent(new MimeMultipart("text/html"));
//                    email.setMsg("Dear " + customer.getFirstName() + "," + "\n" + " Thanks for using Carnegie Financial Service! Your temp-password is:"
//                            + pwd + "." + "\n" + "You can change password through this link: http://54.173.57.219:8080/home%20page/");
//                    email.send();
//                } catch (EmailException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//                request.setAttribute("pwd", pwd);
//            
//        } catch (RollbackException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (FormBeanException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }

        

        
        }catch (FormBeanException e) {
//          // TODO Auto-generated catch block
          e.printStackTrace();
      }
      return "create_business.jsp";
    }
}