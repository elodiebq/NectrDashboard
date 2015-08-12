package controller;

import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;

import model.BusinessProfileDAO;
import model.RegionDAO;
import model.Model;

import org.genericdao.RollbackException;

import databeans.BusinessProfileBean;
import databeans.RegionBean;

public class RegionAction extends Action {
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
        
        try {
            System.out.print("I'm here");
            businessList = customerDAO.getBusinessList();
            HashSet<String> list = new HashSet<String>();
            if (businessList.length != 0) {

                for (int i = 0; i < businessList.length; i++) {
                    System.out.print(businessList[i].getRegionId());
                    RegionBean regionList = regionDAO.getAnalysis(businessList[i].getRegionId());
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
