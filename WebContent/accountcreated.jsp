<jsp:directive.page import="java.util.*"/> 
<jsp:directive.page import="edu.byu.util.email.*"/> 
<jsp:directive.page import="edu.byu.util.email.*"/> 
<jsp:directive.page import="edu.byu.isys413.jmcmurdi.web.*"/>
<jsp:directive.page import = "edu.byu.isys414.jmcmurdi.IntexII.*"/>


<jsp:include page="/header.jsp">
  <jsp:param name="title" value="Create Your Account" />
</jsp:include>


<%
  if (request.getAttribute("message") != null) {
      out.println("<p>");
      out.println(request.getAttribute("message"));
      out.println("</p>");
  }//if
  
  //http://localhost:2020/MystuffWeb/edu.byu.isys413.jmcmurdi.actions.Valadate.action?vcode=0000013d6b3142ad57b11ba6001000fd00d100f6&emailvcode=mcmurdiej%40gmail.com&mysubmitter=Create+Account
  //System.out.println("test: " + test);
  //new SearchCriteria(columnName, searchfor)
  //Customer c = BusinessObjectDAO.getInstance().searchForBO("Customer", new SearchCriteria("validation", test));
  
/* 
  message = "This is your lucky day.  You've just signed up for MyStuffsOnline.com \nRemember to validate your account by going here "
		  + address; */
  

%>
<div class = container width = 60%>




	<div class = "hero-unit">
		<p> Your account has been created and we've sent you a confirmation email. <i>You lucky dog</i> </p>
		<p> Before you can login you'll need to vailidate your account <p>
		<a href=index.jsp class="btn btn-primary btn-large">
     		 Take me home!
    	</a>
	</div>

</div>

<jsp:include page="/footer.jsp"/>