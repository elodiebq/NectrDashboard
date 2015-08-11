package controller;

import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.HTMLDocument.Iterator;

import model.BusinessProfileDAO;
import model.RegionDAO;
import model.Model;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanFactory;

import databeans.BusinessProfileBean;
import databeans.RegionBean;
import formbeans.CreateBusinessProfileForm;

public class RegionAction extends Action {
    private FormBeanFactory<CreateBusinessProfileForm> formBeanFactory = FormBeanFactory
            .getInstance(CreateBusinessProfileForm.class);
    private BusinessProfileDAO customerDAO;
    private RegionDAO regionDAO;

    public RegionAction(Model model) {
        customerDAO = model.getBusinessProfileDAO();
        regionDAO = model.getRegionDAO();
    }

    public String getName() {
        return "get_region.do";
    }

    public String perform(HttpServletRequest request) {
        BusinessProfileBean[] businessList;
        RegionBean regionList;
        try {
            
            businessList = customerDAO.getBusinessList();
            HashSet<String> list = new HashSet<String>();
            if (businessList.length != 0) {

                for (int i = 0; i < businessList.length; i++) {
                    regionList = regionDAO.getAnalysis(businessList[i].getRegionId() - 1);
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

        return "create_business.jsp";
    }
}
