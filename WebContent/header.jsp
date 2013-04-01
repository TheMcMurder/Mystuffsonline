<jsp:directive.page import="java.util.*"/> 
<jsp:directive.page import="edu.byu.isys414.jmcmurdi.IntexII.*"/> 
<jsp:directive.page import="edu.byu.isys413.jmcmurdi.web.*"/>
<jsp:directive.page import = "javax.servlet.http.*"/>
<html>
<head>

<title><%=request.getParameter("title")%></title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<link rel="stylesheet" type="stylesheet" href="bootstrap/css/bootstrap-responsive.css"/>
<link rel="stylesheet" type="stylesheet" href="bootstrap/css/bootstrap.css"/>

<script src = "bootstrap/js/bootstrap.js"></script>
<!--script src = "bootsrtap/js/bootstrap-Collapse.js"></script-->
<style type="text/css"></style>

</head>


<div class="navbar">
  <div class="navbar-inner">
    <div class="container">
 
      <!-- .btn-navbar is used as the toggle for collapsed navbar content -->
      <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </a>
 
      <!-- Be sure to leave the brand out there if you want it shown -->
      <a class="brand" href="#">MyStuffsOnline</a>
 
      <!-- Everything you want hidden at 940px or less, place within here -->
      <div class="nav-collapse collapse">
<ul class="nav">
              <li class="">
                <a href="./index.html">Home</a>
              </li>
              <li class="">
                <a href="./getting-started.html">Get started</a>
              </li>
              <li class="">
                <a href="./scaffolding.html">Scaffolding</a>
              </li>
              <li class="">
                <a href="./base-css.html">Base CSS</a>
              </li>
              <li class="active">
                <a href="./components.html">Components</a>
              </li>
              <li class="">
                <a href="./javascript.html">JavaScript</a>
              </li>
              <li class="">
                <a href="./customize.html">Customize</a>
              </li>
            </ul>
        <!-- .nav, .navbar-search, .navbar-form, etc -->
      </div>
 
    </div>
  </div>
</div>





<body>
    