package edu.byu.isys413.jmcmurdi.actions;

import javax.servlet.http.*;

import com.google.gson.Gson;

import java.util.*;

import edu.byu.isys413.jmcmurdi.web.*;
import edu.byu.isys414.jmcmurdi.IntexII.*;

public class GetPic implements Action {
	/** Constructor */
	public GetPic() {
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
	public String process(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (request.getParameter("ismobile") != null) {
			// System.out.println("point 1");
			Gson gson = new Gson();
			
			
			return "/mobileReturn.jsp";
			
		} else {
			
			return "/confirmpurchase.jsp";
		}

	}
}
