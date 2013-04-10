package edu.byu.isys413.jmcmurdi.actions;

import javax.servlet.http.*;
import java.util.*;
import edu.byu.isys413.jmcmurdi.web.*;
import edu.byu.isys414.jmcmurdi.IntexII.*;

public class Checkout implements Action {
	/** Constructor */
	public Checkout() {
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
		
		HttpSession session = request.getSession();
		String storeid = request.getParameter("storeid");
		String prodid = request.getParameter("prodid");
		Customer c = (Customer) session.getAttribute("customer");
		Store s = null;
		Product p = null;
//		System.out.println("Checkout page reads: Store id: " + storeid + " prodid: " + prodid);
		if(storeid != null){
			s = BusinessObjectDAO.getInstance().searchForBO("store",  new SearchCriteria("id", storeid));
			request.setAttribute("store", s);

		}
		if (prodid != null){
			p = BusinessObjectDAO.getInstance().searchForBO("product",  new SearchCriteria("id", prodid));
			//System.out.println("Checkout (object) page reads: Store id: " + s.getId() + " prodid: " + p.getId() + " customerid: " + c.getId());
			request.setAttribute("product", p);
		}
			
		return "/confirmpurchase.jsp";
	}
	
}
