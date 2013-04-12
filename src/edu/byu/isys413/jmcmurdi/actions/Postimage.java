package edu.byu.isys413.jmcmurdi.actions;

import javax.servlet.http.*;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.*;

import edu.byu.isys413.jmcmurdi.web.*;
import edu.byu.isys414.jmcmurdi.IntexII.*;
import java.util.*;

public class Postimage implements Action {
	/** Constructor */
	public Postimage() {
		// no op
	}
	SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

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
		//System.out.println("PostImage Started");
		HttpSession session = request.getSession();
		if (request.getParameter("ismobile") != null) {
			//System.out.println("point 1");
			Gson gson = new Gson();
			HashMap<String, String> jHashmap = new HashMap<String, String>();
		//System.out.println("Session" + session.getId());
		String custid = request.getParameter("custid");
		//System.out.println("PostImage point 2");
		String caption = request.getParameter("caption");
		//System.out.println("PostImage point 3");

		String imagedata = request.getParameter("imagedata");
		//System.out.println("PostImage point 3");

		String picname = request.getParameter("picname");
		
		try{
		Picture p = BusinessObjectDAO.getInstance().create("Picture");
		
		p.setCaption(caption);
		p.setCustid(custid);
		p.setPic(imagedata);
		p.setPicname(picname);
		p.save();
		jHashmap.put("status", "successful");
		} catch (Exception e){
			jHashmap.put("status", "failure");
		}
		
		
		String json = gson.toJson(jHashmap);
		

		System.out.println("Json: " + json);
		
		request.setAttribute("UpMobilePic", json);

		}
		
	return "/picUpload.jsp";
	}
	
}
