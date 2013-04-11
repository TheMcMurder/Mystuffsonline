package edu.byu.isys413.jmcmurdi.actions;

import javax.servlet.http.*;

import java.text.SimpleDateFormat;
import java.util.*;
import edu.byu.isys413.jmcmurdi.web.*;
import edu.byu.isys414.jmcmurdi.IntexII.*;
import java.util.*;

public class MakeMoney implements Action {
	/** Constructor */
	public MakeMoney() {
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
		System.out.println("Make Money Started");
		HttpSession session = request.getSession();
		System.out.println("Session" + session.getId());
		
		Customer c = (Customer) session.getAttribute("customer");
		String pid = request.getParameter("prodid");
		String sid = request.getParameter("storeid");
		String tempchargeprice = (request.getParameter("total"));
		String prodprice = request.getParameter("prodprice");
		
		System.out.println ("String chargeprice: " + tempchargeprice);
		System.out.println ("String prodprice: " + prodprice);
		
		double chargeprice = Double.parseDouble(tempchargeprice);
		
		System.out.println("double chargeprice: " + chargeprice);
		
		double tax = Double.parseDouble(request.getParameter("tax"));
		
		System.out.println("Double tax: " + tax);
		
		
		Product tp = BusinessObjectDAO.getInstance().searchForBO("product", new SearchCriteria("id", pid));
		//System.out.println("Product type " + p.getProdType());
		
		Store s = BusinessObjectDAO.getInstance().searchForBO("store", new SearchCriteria("id", sid));
		//System.out.println("Store id: " + s.getId());


		
		//TODO save transaction object with all the datas
		// Uncomment email line
		
		
		Transaction t = BusinessObjectDAO.getInstance().create("Transaction");
		System.out.println("Transaction");
		//String Tid = t.getId();
		// System.out.println(t.getCustomerid());
		//Customer c = BusinessObjectDAO.getInstance().searchForBO("Customer", new SearchCriteria("id", t.getCustomerid()));
		//PProduct p = BusinessObjectDAO.getInstance().searchForBO("PProduct", new SearchCriteria("id", temprental.getForRentid()));
		//Store s = BusinessObjectDAO.getInstance().searchForBO("Store", new SearchCriteria("id", p.getStoreid()));
		//ConceptualRental cr = BusinessObjectDAO.getInstance().searchForBO("ConceptualRental", new SearchCriteria("id", p.getCprodid()));
		String custEmail = c.getEmail();
		String message = "Congratulations on your purchase from mystuffsonline.com - a myer Photography company. " +
				" <br>Your order: \"" + tp.getName() + "\" is waiting for you, come pick it up at " + s.getLocation();
		String subject = "Order Confirmation";
		String from = "DoNotReply@mystuffsonline.com";
		String fromname = "Robot";
		
		//BatchEmail.send(from, fromname, custEmail, subject, message);
		
		//System.out.println( "prod type" + p.getProdType());
		
		if (tp.getProdType().equals("pproduct")){
			System.out.println("boom baby!" + tp.getProdType());
			PProduct p = BusinessObjectDAO.getInstance().searchForBO("product", new SearchCriteria("id", tp.getId()));
			p.setStatus("sold");
			p.save();
		}else{
			System.out.println("oh noes " + tp.getProdType());
			StoreProd p = BusinessObjectDAO.getInstance().searchForBO("storeprod", new SearchCriteria("cprodid", tp.getId()));
			if( p == null){
				System.out.println(tp.getId());
				System.out.println("Oh shit");
			}
			System.out.println("Store prod id: " + p.getId());
			int quantityleft = p.getQuantityleft();
			System.out.println("Starting at " + quantityleft + " left");
			p.setQuantityleft(p.getQuantityleft() - 1);
			
			System.out.println("Currently " + quantityleft + " left");
			p.save();
			System.out.println("step4");
		}
		
		
		
		//Transaction here
		
		Transaction trans = BusinessObjectDAO.getInstance().create("Transaction");
		Sale sale = BusinessObjectDAO.getInstance().create("Sale");
		sale.setChargeamount(chargeprice);
		sale.setProdid(tp.getId());
		sale.setQuantity(1);
		sale.setRevtype("Sale");
		sale.setStoreid(s.getId());
		sale.setTransid(trans.getId());
		
		sale.save();
		
		Payment pay = BusinessObjectDAO.getInstance().create("Payment");
		pay.setPayamount(chargeprice);
		pay.setPaychange(0);
		pay.setType("Sale");
		
		pay.save();
		
		//Commission
		//JournalEntry
		
		Date today = new Date();
		String tempDate;
		tempDate = SDF.format(today);
		today = SDF.parse(tempDate);
		
		JournalEntry je = BusinessObjectDAO.getInstance().create("JournalEntry");
		je.setTransdate(today);

		trans.setPaymentid(pay.getId());
		trans.setCustomerid(c.getId());
		
		trans.setSubtotal(chargeprice-tax);
		trans.setTax(tax);
		trans.setTotal(pay.getPayamount());
		trans.setTransdate(today);
		
		je.save();
		
		for (int i = 0; i < 6; i++) {
			DebitCredit dc = BusinessObjectDAO.getInstance().create("DebitCredit");
			if (i == 0) {
				dc.setAmount(trans.getTotal());
				dc.setGlName("Cash");
				dc.setIsDebit(true);
				dc.setJournalEntryid(je.getId());
				dc.save();
			} else if (i == 1) {
				double salestotal = 0;
				double renttotal = 0;

				for (RevSource rs3 : trans.getRevsources()) {
					if (rs3.getRevtype().equals("Sale")) {
						salestotal += rs3.getChargeamount();
					} else {
						renttotal += rs3.getChargeamount();
					}
				}
				if (salestotal > 0) {
					dc.setAmount(salestotal);
					dc.setGlName("Sales Revenue");
					dc.setIsDebit(false);
					dc.setJournalEntryid(je.getId());
					dc.save();
				}
				if (renttotal > 0) {
					DebitCredit dc1 = BusinessObjectDAO.getInstance().create("DebitCredit");
					dc1.setAmount(salestotal);
					dc1.setGlName("Rental Revenue");
					dc1.setIsDebit(false);
					dc1.setJournalEntryid(je.getId());
					dc1.save();
				}

			} else if (i == 2) {
				dc.setAmount(trans.getTax());
				dc.setGlName("Sales Tax");
				dc.setIsDebit(false);
				dc.setJournalEntryid(je.getId());
				dc.save();
			} else if (i == 3) {
				dc.setAmount(trans.getComtotal());
				dc.setGlName("Commission Expense");
				dc.setIsDebit(true);
				dc.setJournalEntryid(je.getId());
				dc.save();
			} else if (i == 4) {
				dc.setAmount(trans.getComtotal());
				dc.setGlName("Commission Payable");
				dc.setIsDebit(false);
				dc.setJournalEntryid(je.getId());
				dc.save();
			} else if (i == 5) {
				dc.setAmount(trans.getSubtotal());
				dc.setGlName("Inventory");
				dc.setIsDebit((false));
				dc.setJournalEntryid(je.getId());
				dc.save();
			}
		}
		trans.setJournalentryid(je.getId());
		trans.save();
		
	return "/ThumbsUp.jsp";
	}
	
}
