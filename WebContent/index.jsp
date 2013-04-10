<jsp:include page="/header.jsp">
	<jsp:param name="title" value="My Stuffs Online" />
</jsp:include>
<!-- Carousel
    ================================================== -->
<div id="myCarousel" class="carousel slide">
	<div class="carousel-inner">
		<div class="item active">
			<img src="files/docs/assets/img/examples/slide-01.jpg" alt="">
			<div class="container">
				<div class="carousel-caption">
					<h1>Create an Account Today</h1>
					<p class="lead">Creating an account is safe and simple. It will
						take less than a minute.</p>
					<a class="btn btn-large btn-primary" href="/MystuffWeb/CreateCustomer.jsp">Sign
						up today</a>
				</div>
			</div>
		</div>
		<div class="item">
			<img src="files/docs/assets/img/examples/slide-02.jpg" alt="">
			<div class="container">
				<div class="carousel-caption">
					<h1>New Product Weekly</h1>
					<p class="lead">Check our newly listed products.  We guarantee the highest quality and the lowest prices</p>
					<a class="btn btn-large btn-primary" href="/Login.jsp">Shop Now</a>
				</div>
			</div>
		</div>
		<div class="item">
			<img src="files/docs/assets/img/examples/slide-03.jpg" alt="">
			<div class="container">
				<div class="carousel-caption">
					<h1>Ready To Get Shopping?</h1>
					<p class="lead">Shopping online can be just as fun as in the store.  Login now to get started.</p>
					<a class="btn btn-large btn-primary" href="/MystuffWeb/Login.jsp">Login Now!</a>
				</div>
			</div>
		</div>
	</div>
	<a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
	<a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
</div>
<!-- /.carousel -->


<jsp:include page="/footer.jsp" />
