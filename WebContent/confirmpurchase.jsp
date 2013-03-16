<jsp:directive.page import="java.util.*"/> 
<jsp:directive.page import="edu.byu.isys414.jmcmurdi.IntexII.*"/> 
<jsp:directive.page import="edu.byu.isys413.jmcmurdi.web.*"/>
<jsp:directive.page import = "javax.servlet.http.*"/> 


<jsp:include page="/header.jsp">
  <jsp:param name="title" value="Create Your Account" />
</jsp:include>

<%
  if (request.getAttribute("message") != null) {
      out.println("<p>");
      out.println(request.getAttribute("message"));
      out.println("</p>");
  }//if
	Product p = (Product) request.getAttribute("product");
	Store s = (Store) request.getAttribute("store");
	Customer c = (Customer) session.getAttribute("customer");
	Membership m = BusinessObjectDAO.getInstance().searchForBO("membership", new SearchCriteria("custid", c.getId()) );
	Double tax = s.getSalestaxrate() * p.getProdPrice();
	
	request.setAttribute("product", p);
	request.setAttribute("store", s);
	request.setAttribute("membership", m);

%>

	<form method="post" id = "finalize" action="edu.byu.isys413.jmcmurdi.actions.MakeMoney.action">
	Confirm Shipping address:<input class="input-medium" type="text" size="15" name="address" value = <%out.print(c.getAddress());%>><br>
	Confirm Credit Card:<input class="input-medium" type="text" size="20" name="creditcard" value = <%out.print(m.getCreditCard()); %>><br>	
	Product Price: <%out.println(p.getProdPrice()); %><br>
	Tax: <%out.print(tax) ;%>
	Total Cost: <%out.print(p.getProdPrice() + tax);%>
	
    <input type="submit" id = "submit" name="mysubmitter" value="Checkout" class="btn btn-success">
	</form> 
	<!-- <button type="button" class="btn btn-success">Success</button> -->
	




<jsp:include page="/footer.jsp"/>
