<jsp:directive.page import="java.util.*" />
<jsp:directive.page import="edu.byu.isys414.jmcmurdi.IntexII.*" />
<jsp:directive.page import="edu.byu.isys413.jmcmurdi.web.*" />
<jsp:directive.page import="javax.servlet.http.*" />


<jsp:include page="/header.jsp">
	<jsp:param name="title" value="MyStuffsOnlineStore" />
</jsp:include>
<div class="container" width=60%>

	<div class="hero-unit">
		<select id="storebox">
			<%
				LinkedList <Store> stores = new LinkedList <Store>();
			%>
			<%
				stores = (LinkedList)session.getAttribute("storelist");
			%>
			<%
				for(Store s:stores){
					out.println("<option value = " + s.getId() + " id = "+ s.getId() + ">" + s.getLocation() + "</option>");
					//System.out.println("Store: " + s.getLocation());
				}
			%>
		</select> </select> <input type="text" id="searchbox" class="input-medium search-query">
		<button type="submit" class="btn">Search</button>

		<p></p>
		<form id ="purchase_form" type = "GET" action = "edu.byu.isys413.jmcmurdi.actions.Checkout.action">
		<div id=searchresults>
			<p></p>
		</div>
		</form>
	</div>
</div>

<script type="text/javascript">
	$(function(){
		$('#searchbox').keyup(function(){
			var inputtext = $(this).val();
			if(inputtext != ""){
			var storeid = $('#storebox').val();
			console.log(storeid);
			$.ajax({
				type: "POST",
				url:"edu.byu.isys413.jmcmurdi.actions.ProductSearch.action",
				data:{text:inputtext, store:storeid}
			}).done(function(data){
				//console.log('success');
				//console.log(data);
				var htmlstring = "";
				var jsonlength = $.parseJSON(data).length;
				//console.log(jsonlength);
				var parsedData = JSON.parse(data);
				
				for(var j = 0; j <jsonlength; j++){
					//console.log("loop");
					//console.log(parsedData[j].name);
					htmlstring += '<div class ="product"> Product Name: ' + parsedData[j].name + '<br>Product Price: $'+parsedData[j].price+'<br><button class = "btn btn-success" id ="'+parsedData[j].id+'">Purchase!</button </div>';
				}
				$('#searchresults').html(htmlstring);
			}).fail(function(data){
				console.log('failure');
			});
			
			}
		});
		
		$('body').delegate('.purchasebutton','click',function(){
			var prodid = event.target.id;
			// console.log(prodid);
			var hidden = '<input type "hidden" name = "prodid" value = '+prodid+'>';
			hidden += '<input type"hidden" name= "storeid" value = "'+$('#storebox').val()+'">';
			// console.log(hidden);
			$('#searchresults').append(hidden);
			$('#purchase_form').submit();
		});
		});
	
		
		
	
	</script>


<jsp:include page="/footer.jsp" />
