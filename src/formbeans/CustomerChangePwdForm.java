/*
Team 5
Task 7
Date: Jan. 28, 2015
Only for educational use
 */
package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CustomerChangePwdForm extends FormBean {
	private String confirmPassword;
	private String newPassword;
	private String action;
	
	public String getConfirmPassword() { return confirmPassword; }
	public String getNewPassword()     { return newPassword;     }
	public String getAction()          { return action;          }
	
	public void setConfirmPassword(String s) { confirmPassword = trimAndConvert(s,"<>\""); }
	public void setNewPassword(String s)     { newPassword     = trimAndConvert(s,"<>\""); }
	public void setAction(String s)          { action          = trimAndConvert(s,"<>\""); }

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (newPassword == null || newPassword.length() == 0) {
			errors.add("New Password is required");
		}
		
		if (confirmPassword == null || confirmPassword.length() == 0) {
			errors.add("Confirm Pwd is required");
		}
		
		
		if (errors.size() > 0) {
			return errors;
		}
		
		if (!newPassword.equals(confirmPassword)) {
			errors.add("Passwords do not match");
		}
		
		if (!action.equals("Change Password"))
			errors.add("Invalid button");

		return errors;
	}
}
