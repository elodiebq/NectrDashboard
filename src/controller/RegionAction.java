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
            System.out.println("I'm here");
            HashSet<String> list = new HashSet<String>();
            RegionBean[] regions = regionDAO.getRegionList();
            if (regions.length != 0) {

                for (int i = 0; i < regions.length; i++) {
                    list.add(regions[i].getRegionName());

                }
                String[] region = new String[regions.length];
                java.util.Iterator<String> iterator = list.iterator();
                int i = 0;
                while (iterator.hasNext()) {
                    region[i] = iterator.next();
                    i++;
                }

                for (int j = 0; j < region.length; j++) {
                    System.out.println(region[j]);
                }
                
                request.setAttribute("region", region);
            }
        } catch (RollbackException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        return "create_business.jsp";
    }
}
