<jsp:include page="/header.jsp">
	<jsp:param name="title" value="MyStuffsonline" />
</jsp:include>
<div class="container" style = "width:600px";>
	<div class="page-header">
		<center>
			<h1>
				MyStuffsOnline<small>(beta)</small>
			</h1>
		</center>
	</div>
	<div class="hero-unit">



		<form class="form-horizontal" method="post" id="login" action="edu.byu.isys413.jmcmurdi.actions.Login.action">
			<ledgend>Login</ledgend>
			<div class="control-group">
				<label class="control-label">Email</label>
				<div class="controls">
					<input type="text" name="inputEmail" placeholder="Email">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" >Password</label>
				<div class="controls">
					<input type="password" name="inputPassword" placeholder="Password">
				</div>
			</div>
			<div class="control-group">

				<div class="controls">
					<input type="submit" id="submit" name="login" value="Let me in!!"
						class="btn btn-success">
				</div>
		</form>
		<div></div>
		<% if(request.getAttribute("message") != null){
			System.out.println("Message : " + request.getAttribute("message"));
			out.println("<div id = \"fail\" Style = \"Color:Red;\"; >");
			out.println("UserName or Password is incorrect - don't give up!");
			out.println("</div>");	
			
		}%>
		
	</div>
</div>
</div>
<jsp:include page="/footer.jsp" />

</div>;