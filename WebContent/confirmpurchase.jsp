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
	
	//request.setAttribute("fproduct", p.getId());
	//request.setAttribute("fstore", s.getId());
	//request.setAttribute("fmembership", m.getId());
	
	System.out.println("Product id from confirm page " + p.getId());
	System.out.println("Store id from confirm page " + s.getId());

	System.out.println("Customer id from confirm page " + c.getId());

	System.out.println("member id from confirm page " + m.getId());


%>
<div class="container" style = "width:600px";>
	<div class = "hero-unit">
		<form class = "form-horizontal" method="post" id="finalize"	action="edu.byu.isys413.jmcmurdi.actions.MakeMoney.action">
			
			
			<div class = "control-group">
				<label class = "control-label" for "cfrmshipping">Confirm Address:</label>
				<div class = "controls">
					<input class="input-medium" type="text"	size="15" name="address" value=<%out.print(c.getAddress());%>>
				</div>
			</div>
			<div class = "control-group">
				<label class = "control-label" for "cnfrmcc">Confirm Credit Card:</label>
				<div class = "controls">
					<input class="input-medium" type="text" size="20" name="creditcard" value=<%out.print(m.getCreditCard());%>>
				</div>
			</div>
			<div class = "control-group">
				<label class = "control-label" for "prodprice">Product Price:</label>
				<div class = "controls">
					<% out.println(p.getProdPrice());%>
				</div>
			</div>
			<div class = "control-group">
				<label class = "control-label" for "tax">Tax:</label>
				<div class = "controls">
					<%out.print(tax);	%>
				</div>
			</div>
			<div class = "control-group">
				<label class = "control-label" for "Total">Total:</label>
				<div class = "controls">
					<% out.print(p.getProdPrice() + tax);%>
				</div>
			</div>
			<div class = "control-group">
				<div class = "controls">
					<input type="submit" id="submit" name="mysubmitter" value="Checkout"
				class="btn btn-success">
				</div>
			</div>
			<div class = "control-group" style = "visibility:hidden;">
				<div class = "controls">
					<input class="input-medium" type="text"	size="15" name="prodid" id = "prodid" value=<%out.print(p.getId());%>>
				</div>
			</div>
			<div class = "control-group" style = "visibility:hidden;">
				<div class = "controls">
					<input class="input-medium" type="text"	size="15" name="storeid" id = "storeid" value=<%out.print(s.getId());%>>
				</div>
			</div>
			<div class = "control-group" style = "visibility:hidden;">
				<div class = "controls">
					<input class="input-medium" type="text"	size="15" name="tax" id = "tax" value=<%out.print(tax);%>>
				</div>
			</div>
			<div class = "control-group" style = "visibility:hidden;">
				<div class = "controls">
					<input class="input-medium" type="text"	size="15" name="total" id = "total" value=<%out.print(p.getProdPrice() + tax);%>>
				</div>
			</div>
			
			
		</form>
		<!-- <button type="button" class="btn btn-success">Success</button> -->
	</div>
</div>




<jsp:include page="/footer.jsp"/>
