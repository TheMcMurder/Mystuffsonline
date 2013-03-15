<jsp:include page="/header.jsp">
	<jsp:param name="title" value="MyStuffsonline" />
</jsp:include>
<div class="container" style = "width:600px";>
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
		<% 
		System.out.println(request.getAttribute("message"));
		if(request.getAttribute("message") != null){
			System.out.println("Message : " + request.getAttribute("message"));
			out.println("<div id = \"fail\" Style = \"Color:Red;\"; >");
			out.println("<p></p><center>Something didn't work but don't give up!<br>Maybe Your username or password was incorrect<br>or maybe your account hasn't been validated</center>");
			out.println("</div>");	
			
		}%>
		
	</div>
</div>
</div>
<jsp:include page="/footer.jsp" />

</div>;