<jsp:directive.page import="java.util.*"/> 
<jsp:directive.page import="edu.byu.isys414.jmcmurdi.IntexII.*"/> 
<jsp:directive.page import="edu.byu.isys413.jmcmurdi.web.*"/>
<jsp:directive.page import = "javax.servlet.http.*"/>


<jsp:include page="/header.jsp">
	<jsp:param name="title" value="MyStuffsOnlineStore" />
</jsp:include>
<div class="container" width=60%>
	
	<div class="hero-unit">
		<select>
			<% ArrayList <Store> stores = new ArrayList <Store>(); %>
			<% stores = (ArrayList)session.getAttribute("storelist"); %>
		</select>
		<input type="text" class="input-medium search-query">
		<button type="submit" class="btn">Search</button>
	
		<p></p>

	<div id=searchresults><p>temporary holding place</p></div>
	</div>
</div>

<script type = "text/javascript">
	$(function(){
		$('#searchbox').keyup(function(){
			var inputtext = $(this).val();
			var storeid = $('#storebox').val();
			
			$.ajax({
				type: "POST",
				url:"edu.byu.isys.isys413.jmcmurdi.actions.ProductSearch.action",
				data:{text:inputtext, store:storeid}
			}).done(function(data){
				console.log('success');
				console.log(data);
			}).fail(function(data){
				console.log('failure');
			});
		
		});
		});
	</script>


<jsp:include page="/footer.jsp" />
