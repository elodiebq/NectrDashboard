package formbeans;

import org.mybeans.form.FormBean;

public class ChangeDayForm extends FormBean {
    private String chooseday;
    

    public void setDayTime(String s) { chooseday = s.trim(); }
   
    
    public String getDayTime() {   return chooseday;    }
}
   
