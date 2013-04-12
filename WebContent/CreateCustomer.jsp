<jsp:directive.page import="java.util.*" />

<jsp:include page="/header.jsp">
	<jsp:param name="title" value="Create Your Account" />
</jsp:include>

<%
	if (request.getAttribute("message") != null) {
		out.println("<p>");
		out.println(request.getAttribute("message"));
		out.println("</p>");
	}//if
%>
<div class="container" style = "width:600px";>
	<div class="hero-unit">
		<form class="form-horizontal" method="post" id="signupform"
			action="edu.byu.isys413.jmcmurdi.actions.CreateCustomer.action">
			<div class="control-group">
				<label class="control-label"for "firstname">First Name:</label>
				<div class="controls">
					<input class="input-large" type="text" size="15" name="firstname">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"for "lastname">Last Name:</label>
				<div class="controls">
					<input class="input-large" type="text" size="15" name="lastname">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"for "email">Email:</label>
				<div class="controls">
					<input class="input-large" type="text" size="25" name="email">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"for "creditcard">Credit Card:</label>
				<div class="controls">
					<input class="input-large" type="text" size="25" name="creditcard">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"for "phonenumber">Phone:</label>
				<div class="controls">
					( <input class="input-mini" type="text" maxlength="3" size="3"
						name="phone1"> ) <input class="input-mini" type="text"
						maxlength="3" size="3" name="phone2"> - <input
						class="input-mini" type="text" maxlength="4" size="4"
						name="phone3">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"for "password1">Password:</label>
				<div class="controls">
					<input class="input-large" type="password" size="25" name="password1" id = "password1"">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"for "password2">Confirm
					Password:</label>
				<div class="controls">
					<input class="input-large" type="password" size="25" name="confirmpassword"
						id="password2">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"for "address">Address:</label>
				<div class="controls">
					<input class="input-large" type="text" size="50" name="address">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"for "state">State:</label>
				<div class="controls">
					<select name="state">

				<option value="Alabama">Alabama</option>
				<option value="Alaska">Alaska</option>
				<option value="Arizona">Arizona</option>
				<option value="Arkansas">Arkansas</option>
				<option value="California">California</option>
				<option value="Colorado">Colorado</option>
				<option value="Connecticut">Connecticut</option>
				<option value="Delaware">Delaware</option>
				<option value="District of Columbia">District of Columbia</option>
				<option value="Florida">Florida</option>
				<option value="Georgia">Georgia</option>
				<option value="Hawaii">Hawaii</option>
				<option value="Idaho">Idaho</option>
				<option value="Illinois">Illinois</option>
				<option value="Indiana">Indiana</option>
				<option value="Iowa">Iowa</option>
				<option value="Kansas">Kansas</option>
				<option value="Kentucky">Kentucky</option>
				<option value="Louisiana">Louisiana</option>
				<option value="Maine">Maine</option>
				<option value="Maryland">Maryland</option>
				<option value="Massachusetts">Massachusetts</option>
				<option value="Michigan">Michigan</option>
				<option value="Minnesota">Minnesota</option>
				<option value="Mississippi">Mississippi</option>
				<option value="Missouri">Missouri</option>
				<option value="Montana">Montana</option>
				<option value="Nebraska">Nebraska</option>
				<option value="Nevada">Nevada</option>
				<option value="New Hampshire">New Hampshire</option>
				<option value="New Jersey">New Jersey</option>
				<option value="New Mexico">New Mexico</option>
				<option value="New York">New York</option>
				<option value="North Carolina">North Carolina</option>
				<option value="North Dakota">North Dakota</option>
				<option value="Ohio">Ohio</option>
				<option value="Oklahoma">Oklahoma</option>
				<option value="Oregon">Oregon</option>
				<option value="Pennsylvania">Pennsylvania</option>
				<option value="Rhode Island">Rhode Island</option>
				<option value="South Carolina">South Carolina</option>
				<option value="South Dakota">South Dakota</option>
				<option value="Tennessee">Tennessee</option>
				<option value="Texas">Texas</option>
				<option value="Utah">Utah</option>
				<option value="Vermont">Vermont</option>
				<option value="Virginia">Virginia</option>
				<option value="Washington">Washington</option>
				<option value="West Virginia">West Virginia</option>
				<option value="Wisconsin">Wisconsin</option>
				<option value="Wyoming">Wyoming</option>
			</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"for "zip">Zip Code:</label>
				<div class="controls">
					<input class="input-large" type="text" size="50" name="zip">
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<input type="submit" id="submit" name="mysubmitter" value="Create Account" class="btn btn-success"  disabled="disabled">
				</div>
			</div>
			
			
		</form>
		<!-- <button type="button" class="btn btn-success">Success</button> -->
	</div>
	<script>
	
		$(function() {
			$("#password2").focusout(function() {
				var password1 = $("#password1").val();
				var password2 = $("#password2").val();
				//alert("password1: " + password1 + " password2: " + password2);
				if (password1 == "") {
					//alert("Made it to the empty password field check");
					//$("#"+div_id+" .widget-head sp#spTitle").text("new dialog title");
					//$("div#brieftext").text($(this).attr("data-text"));
					//$("#error").text($(this).attr("data-text"));
					$("#submit").prop("disabled", "disabled");

				} else if (password1 != password2) {
					//alert("Passwords must match");
					//$("#error" + ".widget-head sp#spanerror").text("passwords must match");
					//$("#error").text($(this).attr("data-text"));
					$("#submit").prop("disabled", "disabled");

				} else if (password1 == password2) {
					//alert("passwords match")
					$("#submit").removeAttr("disabled");
				} 

			})
		});
	</script>
	
	<script>
		$(function() {
			$("#password1").focusout(function() {
				var password1 = $("#password1").val();
				var password2 = $("#password2").val();
				//alert("password1: " + password1 + " password2: " + password2);
				if (password1 == "") {
					//alert("Made it to the empty password field check");
					//$("#"+div_id+" .widget-head sp#spTitle").text("new dialog title");
					//$("div#brieftext").text($(this).attr("data-text"));
					//$("#error").text($(this).attr("data-text"));
					$("#submit").prop("disabled", "disabled");

				} else if (password1 != password2) {
					//alert("Passwords must match");
					//$("#error" + ".widget-head sp#spanerror").text("passwords must match");
					//$("#error").text($(this).attr("data-text"));
					$("#submit").prop("disabled", "disabled");

				} else if (password1 == password2) {
					//alert("passwords match")
					$("#submit").removeAttr("disabled");
				}
			})

		});
	</script>
</div>
</div>
</div>


<jsp:include page="/footer.jsp" />
