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
			//TODO comment me out when you go live
//			CreateDB.main(null);
			//end comment out at launch
			
		String phone1 = request.getParameter("phone1");
		String phone2= request.getParameter("phone2");
		String phone3= request.getParameter("phone3");
		Customer c = BusinessObjectDAO.getInstance().create("Customer");
		c.setFirstName(request.getParameter("firstname"));
		c.setLastName(request.getParameter("lastname"));
		c.setPassword(request.getParameter("password"));
//		c.setPassword("test");
//		System.out.println("Password: " + request.getParameter("password"));
		c.setAddress(request.getParameter("address"));
		c.setEmail(request.getParameter("email"));
		c.setPhone(phone1 + phone2 + phone3);
//		System.out.println("PHone: " + request.getParameter("phone1"));
//		c.setPhone("Hobbits don't have phones you fool of a took");
		c.setState(request.getParameter("state"));
		c.setZip(request.getParameter("zip"));
		c.setVerified(false);
		String guid = GUID.generate();
		c.setValidation(guid);
		request.setAttribute("validationGUID", c.getValidation());
		request.setAttribute("customer", "Customer Name: " +c.getFirstName() + " " + c.getLastName());
		System.out.println("Customer Name: " +c.getFirstName() + " " + c.getLastName());
		c.save();
	} catch (Exception e){
		System.out.println("failure");
	}
		
		
		return "/accountcreated.jsp";

	}
}
