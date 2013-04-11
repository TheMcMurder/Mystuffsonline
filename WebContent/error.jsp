<jsp:directive.page import="edu.byu.isys413.jmcmurdi.web.*"/> 

<%
   // if we get to this page, we have a web exception in the request
   // (Tomcat puts it there for us per web.xml settings)
   // let's just make sure it is there
   WebException exc = (WebException)request.getAttribute("javax.servlet.error.exception");
   if (exc == null) {
     throw new Exception("error.jsp cannot be called directly!");
   }
%>

<jsp:include page="/header.jsp">
  <jsp:param name="title" value="Error" />
</jsp:include>

<div class="container" style = "width:600px";>
	<div class="hero-unit">
<h1>OH SNAP!!!</h1>
<p>
Something terribly wrong occurred... We're sending a team of ant-sized squirrels, if you see them tell them to check the Tachyon particle field for errors in the constriant matrix.
<p>
In the meantime relax and try again.  We're here to help you can always call us at (801)-555-5555
<p>
An error code just for good measure:
<p>
</p>
</div>
</div>


<div style="color:#FF0000" align="center">
    <%=exc.getMessage()%>
</div>    

<jsp:include page="/footer.jsp"/>
