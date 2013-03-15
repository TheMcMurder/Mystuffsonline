<jsp:directive.page import="java.util.*"/> 
<jsp:directive.page import="edu.byu.util.email.*"/> 
<jsp:directive.page import="edu.byu.util.email.*"/> 
<jsp:directive.page import="edu.byu.isys413.jmcmurdi.web.*"/>
<jsp:directive.page import = "edu.byu.isys414.jmcmurdi.IntexII.*"/>


<jsp:include page="/header.jsp">
  <jsp:param name="title" value="Account Validation" />
</jsp:include>


<form method="get" id = "validateform" action="edu.byu.isys413.jmcmurdi.actions.Valadate.action">
Validation code: <input class="input-medium" type="text" name="vcode"><br>
Email address: <input class = "input-medium" type="text" name="emailvcode"><br>
<input type="submit" id = "submit" name="mysubmitter" value="Create Account" class="btn btn-success">

</form>


<jsp:include page="/footer.jsp"/>