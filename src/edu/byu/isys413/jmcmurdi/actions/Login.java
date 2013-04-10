package edu.byu.isys413.jmcmurdi.actions;

import javax.servlet.http.*;

import com.google.gson.Gson;

import java.util.*;

import edu.byu.isys413.jmcmurdi.web.*;
import edu.byu.isys414.jmcmurdi.IntexII.*;

public class Login implements Action {
	/** Constructor */
	public Login() {
		// no op
	}

	/**
	 * Responds to an action call from the Controller.java file. This method should perform any work required, then return a new JSP page to call.
	 * 
	 * @param request
	 *            The HttpServletRequest object that represents information sent from the browser. The getParameter() method is particularly useful.
	 * 
	 * @returns A string giving the path of the JSP file to call after this action is performed. If you need this to be dynamic, use hidden form field to send a parameter giving the page to go to
	 *          after the action.
	 * 
	 *          If the path starts with "/", the path is absolute to the application context. If the path doesn't start with "/", it is relative to the current page (dangerous!).
	 */
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// ensure we have a number to guess for
		HttpSession session = request.getSession();
		System.out.println("This should print1");
		if (request.getParameter("ismobile") != null) {
			System.out.println("point 1");
			Gson gson = new Gson();
			HashMap<String, String> jHashmap = new HashMap<String, String>();
			String memail = (String) request.getParameter("username");
			String mpassword = (String) request.getParameter("password");
			System.out.println("point 2");
			// System.out.println("Email recieved from android" + email);
			// System.out.println("Password recieved from android" + password);
			Customer mc = BusinessObjectDAO.getInstance().searchForBO("Customer", new SearchCriteria("email", memail));
			System.out.println("point 3");
			System.out.println("This should print1");
			if (mc != null) {
				System.out.println("This should print2");
				if (mc.getPassword().equals(mpassword)) {
					// session.setAttribute("customer", mc);

					// Customer cust = (Customer)(session.getAttribute("customer"));
					// System.out.println(cust.getFirstName());
					if (mc.isVerified() == true) {
						System.out.println("Customer is verified and such");
						jHashmap.put("custFName", mc.getFirstName().toString());
						jHashmap.put("custLName", mc.getLastName().toString());
						jHashmap.put("custid", mc.getId().toString());
						ArrayList<HashMap<String, String>> picList = new ArrayList<HashMap<String, String>>();
						List templist = BusinessObjectDAO.getInstance().searchForList("picture", new SearchCriteria("custid", mc.getId()));	
//						for(Object bo: templist){
//							HashMap<String,String> singlePic = new HashMap <String, String>();
//							Picture temppic = (Picture) bo;
//							singlePic.put("id", temppic.getId());
//							singlePic.put("caption", temppic.getCaption());
//							singlePic.put("picname", temppic.getPicname());
//							//singlePic.put("pic", temppic.getPic());
//							
//							//Add temp hashmap to arraylist
//							picList.add(singlePic);
//							
//						}
//						String tempjson = gson.toJson(picList);
//
//						jHashmap.put("piclist", tempjson);
						
						//String picJson = 
					
					} // if msisverified();
						// for (Store s: stores){
						// System.out.println("Store: "+ s.getLocation());
						// }

					String test = "mcmurdiej@gmail.com";
					if (memail.equals(test)) {
						jHashmap.put("status", "like a sir");
						// System.out.println("success");
					} else {
						jHashmap.put("status", "poopy");
						// // System.out.println("updated failure 3");
						// // System.out.println("Email: " + email);
						// // System.out.println("Password: " +password);
						// // System.out.println("Email: " + email.length());
						// // System.out.println("test: " + test.length());
					} // if mc email matches test string
					String json = gson.toJson(jHashmap);
					request.setAttribute("mobiledata", json);

					return "/mobileReturn.jsp";
				}
			}
		} else {
			//System.out.println("This should print3");
			String email = (String) request.getParameter("inputEmail");
			String password = (String) request.getParameter("inputPassword");
			System.out.println("Email: " + email + " password: " + password);
			Customer c = BusinessObjectDAO.getInstance().searchForBO("Customer", new SearchCriteria("email", email));
			LinkedList<Store> stores = new LinkedList<Store>();
			stores = (LinkedList) BusinessObjectDAO.getInstance().searchForAll("Store");
			for (Store s : stores) {
				System.out.println("Store: " + s.getLocation());
			}
			session.setAttribute("storelist", stores);
			if (c != null) {
				if (c.getPassword().equals(password)) {
					session.setAttribute("customer", c);
					session.setAttribute("user", c.getFirstName());
					Customer cust = (Customer) (session.getAttribute("customer"));
					System.out.println(cust.getFirstName());
					if (c.isVerified() == true) {
						return "/products.jsp";
					}
					request.setAttribute("message", "incorrect username or password");
					return "/Login.jsp";
				} else {
					request.setAttribute("message", "incorrect username or password");
					return "/Login.jsp";
				}

			} else {
				request.setAttribute("message", "incorrect username or password");
				return "/Login.jsp";
			}

		}
		request.setAttribute("message", "incorrect username or password");
		return "/Login.jsp";

	}

}
