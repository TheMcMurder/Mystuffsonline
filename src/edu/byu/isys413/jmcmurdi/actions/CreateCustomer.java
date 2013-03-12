package edu.byu.isys413.jmcmurdi.actions;

import javax.servlet.http.*;
import java.util.*;
import edu.byu.isys413.jmcmurdi.web.*;
import edu.byu.isys414.jmcmurdi.IntexII.*;

public class CreateCustomer implements Action {
	/** Constructor */
	public CreateCustomer() {
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
		try{
			
		
		Customer c = BusinessObjectDAO.getInstance().create("Customer");
		c.setFirstName(request.getParameter("firstname"));
		c.setLastName(request.getParameter("lastname"));
		c.setPassword(request.getParameter("password"));
		c.setAddress(request.getParameter("address"));
		c.setEmail(request.getParameter("email"));
		c.setPhone(request.getParameter("phone1"+"phone2" + "phone3"));
		c.setState(request.getParameter("state"));
		c.setZip(request.getParameter("zip"));
		request.setAttribute("customer", "Customer Name: " +c.getFirstName() + " " + c.getLastName());
		System.out.println("Customer Name: " +c.getFirstName() + " " + c.getLastName());
		c.save();
	} catch (Exception e){
		System.out.println("failure");
	}
		
		
		return "index.jsp";

	}
}
