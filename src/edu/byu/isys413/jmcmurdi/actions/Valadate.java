package edu.byu.isys413.jmcmurdi.actions;

import edu.byu.isys413.jmcmurdi.web.*;

import javax.servlet.http.*;

import java.util.*;
import edu.byu.isys413.jmcmurdi.web.*;
import edu.byu.isys414.jmcmurdi.IntexII.*;

public class Valadate implements Action {
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String emailaddress = request.getParameter("emailvcode");
		String code = request.getParameter("vcode");
		Customer c1 = BusinessObjectDAO.getInstance().searchForBO("Customer", new SearchCriteria("validation", code));
		if (c1 != null) {
			if (c1.getValidation().equals(request.getParameter("vcode")));{
				c1.setVerified(true);
				c1.save();
			}
			
		}
		else{
			return "/error.jsp";
		}
		//Customer c = BusinessObjectDAO.getInstance().searchForBO("Customer", new SearchCriteria("validation", test));

		return "congratulations.jsp";
	}
}
