package edu.byu.isys413.jmcmurdi.actions;

import javax.servlet.ServletRequest;
import javax.servlet.http.*;

import com.google.gson.Gson;

import java.util.*;
import edu.byu.isys413.jmcmurdi.web.*;
import edu.byu.isys414.jmcmurdi.IntexII.*;

public class ProductSearch implements Action {
	/** Constructor */
	public ProductSearch() {
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
			//System.out.println(request.getParameter("store"));
			Store s1 = BusinessObjectDAO.getInstance().read(request.getParameter("store"));
			String searchtext = request.getParameter("text");
			//System.out.println("Searchtext: " + searchtext);
			
			
//			List<StoreProd> spList = BusinessObjectDAO.getInstance().searchForList("StoreProd", new SearchCriteria("storeid", s1.getId()));
//			List<PProduct> ppList = BusinessObjectDAO.getInstance().searchForList("PProduct", new SearchCriteria("storeid", s1.getId()));
			List<StoreProd> spList = new LinkedList <StoreProd>();
			List<PProduct> ppList = new LinkedList <PProduct>();
			List<Product> prodList = BusinessObjectDAO.getInstance().searchForList("Product", new SearchCriteria("name", searchtext+"%", 6));
			
			for (Product p: prodList){
				//System.out.println();
				if(p.getProdType().equals("pproduct")){
					PProduct temppprod = BusinessObjectDAO.getInstance().searchForBO("PProduct", new SearchCriteria("id", p.getId()), new SearchCriteria("storeid", s1.getId() ) );
//					try{System.out.println("product: " + p.getId() + " " + p.getName() + " " + p.getProdType() + " " + temppprod.getId());}
//					catch(Exception e){
//						System.out.println("FAIL!");
//					}
					if( temppprod != null){
						//System.out.println(temppprod.getId() + "");
						if(temppprod.getStatus().equals("available")){
							//System.out.println(temppprod.getStatus() + "");
							ppList.add(temppprod);
						}
					}
				}else if(p.getProdType().equals("cproduct")){
					
					
					StoreProd tempsprod = BusinessObjectDAO.getInstance().searchForBO("StoreProd", new SearchCriteria("cprodid", p.getId()), new SearchCriteria("storeid", s1.getId() ) );
//					try{System.out.println("product: " + p.getId() + " " + p.getName() + " " + p.getProdType() + " " + tempsprod.getId());}
//					catch(Exception e){
//						System.out.println("FAIL!");
//					}
					if(tempsprod != null){
						if(tempsprod.getQuantityleft() > 0){
							spList.add(tempsprod);
						}
						
					}
				}
			}
//				for (StoreProd s: spList){
//				System.out.println("Store prod: " + s.getCprodid());
//			}
//			for (PProduct s: ppList){
//				System.out.println("PProd: " + s.getId());
//			}
			
			ArrayList<HashMap<String, String>> jsonthingy = new ArrayList<HashMap<String, String>>();
			//to pu the storeprods (conceptual products) into the list
			for(StoreProd sp: spList){
				CProduct tempcp = (CProduct) BusinessObjectDAO.getInstance().read(sp.getCprodid());
				HashMap<String, String> tempHash = new HashMap<String, String>();
				tempHash.put("price", tempcp.getProdPrice() +"");
				tempHash.put("name", tempcp.getProdName() + "");
				tempHash.put("id", tempcp.getId() + "");
				jsonthingy.add(tempHash);
			}
			for(PProduct pp: ppList){
				HashMap<String, String> tempHash = new HashMap<String, String>();
				tempHash.put("price", pp.getProdPrice()+"");
				tempHash.put("name", pp.getPpname() +"");
				tempHash.put("id", pp.getId() + "");
				jsonthingy.add(tempHash);
			}
			
			Gson gson = new Gson();
			String json1 = gson.toJson(jsonthingy);
			request.setAttribute("prodList", json1);
			return "/product_ajax.jsp";
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		
		
		return null;
	}
}