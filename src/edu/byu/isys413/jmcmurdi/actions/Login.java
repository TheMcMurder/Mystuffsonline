package edu.byu.isys413.jmcmurdi.actions;

import javax.servlet.http.*;
import java.util.*;
import edu.byu.isys413.jmcmurdi.web.*;
import edu.byu.isys414.jmcmurdi.IntexII.*;

public class Login implements Action {
	/** Constructor */
	public Login() {
		// no op
	}

	/**
	 * Responds to an action call from the Controller.java file. This method
	 * should perform any work required, then return a new JSP page to call.
	 * 
	 * @param request
	 *            The HttpServletRequest object that represents information sent
	 *            from the browser. The getParameter() method is particularly
	 *            useful.
	 * 
	 * @returns A string giving the path of the JSP file to call after this
	 *          action is performed. If you need this to be dynamic, use hidden
	 *          form field to send a parameter giving the page to go to after
	 *          the action.
	 * 
	 *          If the path starts with "/", the path is absolute to the
	 *          application context. If the path doesn't start with "/", it is
	 *          relative to the current page (dangerous!).
	 */
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// ensure we have a number to guess for
		HttpSession session = request.getSession();
		
		String email = (String) request.getParameter("inputEmail");
		String password = (String) request.getParameter("inputPassword");
		//System.out.println("Email: " + email + " password: " + password);
		Customer c = BusinessObjectDAO.getInstance().searchForBO("Customer", new SearchCriteria("email", email));
		if(c != null){
			if (c.getPassword().equals(password)){
				//session.setAttribute("customer", c);
				//Customer cust = (Customer)(session.getAttribute("customer"));
				//System.out.println(cust.getFirstName());
				return "/index.jsp";
			}
			else{
				request.setAttribute("message", "incorrect username or password");
				return "/Login.jsp";
			}
			
		}else{
			request.setAttribute("message", "incorrect username or password");
			return "/Login.jsp";
		}

	}
}
