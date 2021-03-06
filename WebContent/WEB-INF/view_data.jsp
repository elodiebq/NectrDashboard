<!DOCTYPE html>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="javax.servlet.*,java.text.*"%>
<!-- Template Name: Clip-Two - Responsive Admin Template build with Twitter Bootstrap 3.x | Author: ClipTheme -->
<!--[if IE 8]><html class="ie8" lang="en"><![endif]-->
<!--[if IE 9]><html class="ie9" lang="en"><![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- start: HEAD -->
<head>
<title>Nectr DashBoard</title>
<!-- start: META -->
<!--[if IE]><meta http-equiv='X-UA-Compatible' content="IE=edge,IE=9,IE=8,chrome=1" /><![endif]-->
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta content="" name="description" />
<meta content="" name="author" />
<!-- end: META -->
<!-- start: GOOGLE FONTS -->
<link
	href="http://fonts.googleapis.com/css?family=Lato:300,400,400italic,600,700|Raleway:300,400,500,600,700|Crete+Round:400italic"
	rel="stylesheet" type="text/css" />
<!-- end: GOOGLE FONTS -->
<!-- start: MAIN CSS -->
<link rel="stylesheet" href="styles/kendo.common.min.css" />
<link rel="stylesheet" href="styles/kendo.default.min.css" />
<link rel="stylesheet" href="vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="vendor/fontawesome/css/font-awesome.min.css">
<link rel="stylesheet" href="vendor/themify-icons/themify-icons.min.css">
<link href="vendor/animate.css/animate.min.css" rel="stylesheet"
	media="screen">
<link href="vendor/perfect-scrollbar/perfect-scrollbar.min.css"
	rel="stylesheet" media="screen">
<link href="vendor/switchery/switchery.min.css" rel="stylesheet"
	media="screen">
<!-- end: MAIN CSS -->
<!-- start: CLIP-TWO CSS -->
<link rel="stylesheet" href="assets/css/styles.css">
<link rel="stylesheet" href="assets/css/plugins.css">
<link rel="stylesheet" href="assets/css/themes/theme-1.css"
	id="skin_color" />
<!-- end: CLIP-TWO CSS -->
<!-- start: CSS REQUIRED FOR THIS PAGE ONLY -->
<link type="text/css" href="assets/css/bootstrap.min.css" />
<link type="text/css" href="assets/css/bootstrap-timepicker.min.css" />
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript" src="assets/js/bootstrap-2.2.2.min.js"></script>
<script type="text/javascript"
	src="assets/js/bootstrap-timepicker.min.js"></script>
<script language="javascript">
	var today = new Date();
	document.getElementById('time').innerHTML = today;
</script>
<script src="js/jquery.min.js"></script>
<script src="js/kendo.all.min.js"></script>
<script src='assets/js/Chart.js'></script>
<script src='assets/js/nectr.js'></script>
<script src='assets/js/nectr1.js'></script>
<!-- end: CSS REQUIRED FOR THIS PAGE ONLY -->
</head>
<!-- end: HEAD -->
<body>
	<div id="app">
		<!-- sidebar -->
		<div class="sidebar app-aside" id="sidebar">
			<div class="sidebar-container perfect-scrollbar">
				<nav>
					<!-- start: SEARCH FORM -->
					<div class="search-form">
						<a class="s-open" href="#"> <i class="ti-search"></i>
						</a>
						<form class="navbar-form" role="search">
							<a class="s-remove" href="#" target=".navbar-form"> <i
								class="ti-close"></i>
							</a>
							<div class="form-group">
								<input type="text" class="form-control" placeholder="Search...">
								<button class="btn search-button" type="submit">
									<i class="ti-search"></i>
								</button>
							</div>
						</form>
					</div>
					<!-- end: SEARCH FORM -->
					<!-- start: MAIN NAVIGATION MENU -->
					<div class="navbar-title">
						<span>Main Navigation</span>
					</div>
					<ul class="main-navigation-menu">
						<li><a href="create_campaign.jsp">
								<div class="item-content">
									<div class="item-media">
										<span class="fa-stack"> <i
											class="fa fa-square fa-stack-2x"></i> <i
											class="fa fa-terminal fa-stack-1x fa-inverse"></i>
										</span>
									</div>
									<div class="item-inner">
										<span class="title">Create Campaign </span>
									</div>
								</div>
						</a></li>
					</ul>
					<!-- end: MAIN NAVIGATION MENU -->
					<!-- start: CORE FEATURES -->
					<div class="navbar-title">
						<span>Core Features</span>
					</div>
					<ul class="folders">
						<li><a href="view_history.do">
								<div class="item-content">
									<div class="item-media">
										<span class="fa-stack"> <i
											class="fa fa-square fa-stack-2x"></i> <i
											class="fa fa-terminal fa-stack-1x fa-inverse"></i>
										</span>
									</div>
									<div class="item-inner">
										<span class="title">History Campaign </span>
									</div>
								</div>
						</a></li>
						<li><a href="view_data.do">
								<div class="item-content">
									<div class="item-media">
										<span class="fa-stack"> <i
											class="fa fa-square fa-stack-2x"></i> <i
											class="fa fa-folder-open-o fa-stack-1x fa-inverse"></i>
										</span>
									</div>
									<div class="item-inner">
										<span class="title"> Business Performance</span>
									</div>
								</div>
						</a></li>
					</ul>
					<!-- end: CORE FEATURES -->
					<!-- start: DOCUMENTATION BUTTON -->
					<div class="wrapper">
						<a href="documentation.html" class="button-o"> <i
							class="ti-help"></i> <span>Documentation</span>
						</a>
					</div>
					<!-- end: DOCUMENTATION BUTTON -->
				</nav>
			</div>
		</div>
		<!-- / sidebar -->
		<div class="app-content">
			<!-- start: TOP NAVBAR -->
			<header class="navbar navbar-default navbar-static-top">
				<!-- start: NAVBAR HEADER -->
				<div class="navbar-header">
					<a href="#"
						class="sidebar-mobile-toggler pull-left hidden-md hidden-lg"
						class="btn btn-navbar sidebar-toggle"
						data-toggle-class="app-slide-off" data-toggle-target="#app"
						data-toggle-click-outside="#sidebar"> <i
						class="ti-align-justify"></i>
					</a> <a class="navbar-brand" href="business_main.jsp">
						<h2>Nectr</h2>
					</a> <a href="#"
						class="sidebar-toggler pull-right visible-md visible-lg"
						data-toggle-class="app-sidebar-closed" data-toggle-target="#app">
						<i class="ti-align-justify"></i>
					</a> <a class="pull-right menu-toggler visible-xs-block"
						id="menu-toggler" data-toggle="collapse" href=".navbar-collapse">
						<span class="sr-only">Toggle navigation</span> <i
						class="ti-view-grid"></i>
					</a>
				</div>
				<!-- end: NAVBAR HEADER -->
				<!-- start: NAVBAR COLLAPSE -->
				<div class="navbar-collapse collapse">
					<ul class="nav navbar-right">
						<!-- start: ACTIVITIES DROPDOWN -->
						<li class="dropdown"><a href class="dropdown-toggle"
							data-toggle="dropdown"> <i class="ti-check-box"></i> <span>ACTIVITIES</span>
						</a>
							<ul
								class="dropdown-menu dropdown-light dropdown-messages dropdown-large">
								<li><span class="dropdown-header"> You have new
										notifications</span></li>
								<li>
									<div class="drop-down-wrapper ps-container">
										<div class="list-group no-margin">
											<a class="media list-group-item" href=""> <img
												class="img-circle" alt="..."
												src="assets/images/avatar-1.jpg"> <span
												class="media-body block no-margin"> Use awesome
													animate.css <small class="block text-grey">10
														minutes ago</small>
											</span>
											</a> <a class="media list-group-item" href=""> <span
												class="media-body block no-margin"> 1.0 initial
													released <small class="block text-grey">1 hour ago</small>
											</span>
											</a>
										</div>
									</div>
								</li>
								<li class="view-all"><a href="#"> See All </a></li>
							</ul></li>
						<!-- end: ACTIVITIES DROPDOWN -->
						<!-- start: LANGUAGE SWITCHER -->
						<li class="dropdown"><a href class="dropdown-toggle"
							data-toggle="dropdown"> <i class="ti-world"></i> English
						</a>
							<ul role="menu"
								class="dropdown-menu dropdown-light fadeInUpShort">
								<li><a href="#" class="menu-toggler"> Deutsch </a></li>
								<li><a href="#" class="menu-toggler"> English </a></li>
								<li><a href="#" class="menu-toggler"> Italiano </a></li>
							</ul></li>
						<!-- start: LANGUAGE SWITCHER -->
						<!-- start: USER OPTIONS DROPDOWN -->
						<li class="dropdown current-user"><a href
							class="dropdown-toggle" data-toggle="dropdown"> <img
								src="assets/images/avatar-1.jpg" alt="Peter"> <span
								class="username">Peter <i class="ti-angle-down"></i></i></span>
						</a>
							<ul class="dropdown-menu dropdown-dark">

								<li><a href="logout.do"> Log Out </a></li>
							</ul></li>
						<!-- end: USER OPTIONS DROPDOWN -->
					</ul>
					<!-- start: MENU TOGGLER FOR MOBILE DEVICES -->
					<div class="close-handle visible-xs-block menu-toggler"
						data-toggle="collapse" href=".navbar-collapse">
						<div class="arrow-left"></div>
						<div class="arrow-right"></div>
					</div>
					<!-- end: MENU TOGGLER FOR MOBILE DEVICES -->
				</div>
				<a class="dropdown-off-sidebar"
					data-toggle-class="app-offsidebar-open" data-toggle-target="#app"
					data-toggle-click-outside="#off-sidebar"> &nbsp; </a>
				<!-- end: NAVBAR COLLAPSE -->
			</header>
			<!-- end: TOP NAVBAR -->
			<div class="main-content">
				<div class="wrap-content container" id="container">
					<!-- start: DASHBOARD TITLE -->
					<section id="page-title" class="padding-top-15 padding-bottom-15">
						<div class="row">
							<div class="col-sm-7">
								<h1 class="mainTitle">Business Performance</h1>
								<span class="mainDescription">for all time record </span> <span
									id="time"></span>
							</div>
							<div class="col-sm-5">
								<!-- start: MINI STATS WITH SPARKLINE -->
								<ul class="mini-stats pull-right">
									<li>
										<div class="sparkline-1">
											<span></span>
										</div>
										<div class="values">
											<strong class="text-dark">18304</strong>
											<p class="text-small no-margin">Follower</p>
										</div>
									</li>
								</ul>
								<!-- end: MINI STATS WITH SPARKLINE -->
							</div>
						</div>
					</section>
					<div class="viewData" style="margin-up: -100px;">
						<%-- <p id="bar" style="display: none">${walkIn},${walkBy},${totalVisit},${defaultWalkByView}</p> --%>
						<p id="defaultWalkInView" style="display: none">${defaultWalkInView}</p>
						<p id="defaultWalkByView" style="display: none">${defaultWalkByView}</p>
						<p id="loyalCustomer" style="display: none">${loyalCustomer}</p>
						<div class="col-md-8">
							<!-- bar chart canvas element -->
							<!--  <ul class="pagination"> -->
							<div>
							<table>
								<tr>
									<td>
									    <form action="view_data.do">
                                            <button class="btn btn-success" type="submit">Hour&nbsp</button>
                                        </form>
										
									</td>
									<td>
										<form action="view_data_day.do">
											<button class="btn btn-success" type="submit">Day&nbsp</button>
										</form>
									</td>
									<td>
									</td>
									<td>
										<form action="view_data_month.do">
											<button class="btn btn-success" type="submit">Month&nbsp</button>
										</form>
									</td>
									<td>
										<form action="view_data_year.do">
											<button class="btn btn-success" type="submit">Year&nbsp</button>
										</form>
									</td>
								</tr>
							</table>
							</div>
							<h3 align=center>Current date is ${currTime}</h3>

							<form action="view_data.do">
								
									Change date to: <input type="date" name="chooseday"> <input
                                        class="btn btn-warning" type="submit">
								
							</form>
							<center>
								<span class="glyphicon glyphicon-user" aria-hidden="true">Walkin
									vs. Walkby </span>
							</center>
							
							<canvas id="hour" width="600" height="400"></canvas>
							<br/>
							<br/>
							<canvas id="hourPie" width="600" height="400"></canvas>

						</div>
						<div class="col-md-4">
							<table>
								<tr>
									<br />
								</tr>
								<tr>
									<h2>
										Walk in &nbsp&nbsp&nbsp&nbsp<span class="label label-success">13${walkInByDay}</span>
									</h2>
								</tr>
								<tr>
									<br />
								</tr>
								<tr>
									<h2>
										Walk by &nbsp&nbsp&nbsp<span class="label label-warning">28${walkByDay}</span>
									</h2>
								</tr>
								<tr>
									<br />
								</tr>
								<tr>
									<h2>
										Total Visit&nbsp&nbsp<span class="label label-warning">${totalVisit}</span>
									</h2>
								</tr>
							</table>
							<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
							                         <div id="barLegend" class="chart-legend"><ul class="bar-legend"><li><span style="background-color:rgba(220,220,220,0.5)"></span>Walk in</li><li><span style="background-color:rgba(151,187,205,0.5)"></span>Walk by</li></ul></div>
							<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
							<div id="doughnutLegend" class="chart-legend"><ul class="doughnut-legend"><li><span style="background-color:#F7464A"></span>Loyal Customer</li><li><span style="background-color:#46BFBD"></span>Lukewarm Customer</li><li><span style="background-color:#FDB45C"></span>First Time Customer</li></ul></div>
						</div>
					</div>
				</div>
				<!-- end: DASHBOARD TITLE -->
				<!-- start: data -->

			</div>
			<!-- end: data -->

			<!-- start: MAIN JAVASCRIPTS -->
			<script>
				/*  // bar chart data
				      var barHourData = {
				          labels : ["00:00","02:00","04:00","06:00","08:00","10:00","12:00","14:00","16:00","18:00","20:00","22:00"],
				          datasets : [
				              {
				                  fillColor : "rgba(73,188,170,0.4)",
				                  strokeColor : "rgba(72,174,209,0.4)",
				                  data : [36,50,60,40,34,32,10,2,4,6,12,20]
				              }
				          ]
				      }
				  // get bar chart canvas
				  var hour = document.getElementById("hour").getContext("2d");
				  // draw bar chart
				  new Chart(hour).Bar(barHourData); */
			</script>
			<script src="vendor/jquery/jquery.min.js"></script>
			<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
			<script src="vendor/modernizr/modernizr.js"></script>
			<script src="vendor/jquery-cookie/jquery.cookie.js"></script>
			<script src="vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
			<script src="vendor/switchery/switchery.min.js"></script>
			<!-- end: MAIN JAVASCRIPTS -->
			<!-- start: JAVASCRIPTS REQUIRED FOR THIS PAGE ONLY -->
			<script src="vendor/Chart.js/Chart.min.js"></script>
			<script src="vendor/jquery.sparkline/jquery.sparkline.min.js"></script>
			<!-- end: JAVASCRIPTS REQUIRED FOR THIS PAGE ONLY -->
			<!-- start: CLIP-TWO JAVASCRIPTS -->
			<script src="assets/js/main.js"></script>
			<script src="assets/js/Chart.js"></script>
			<script src='assets/js/nectr.js'></script>
			<script src='assets/js/nectr1.js'></script>
			<!-- <script src="assets/js/angular-chart.js"></script> -->
			<!-- start: JavaScript Event Handlers for this page -->
			<!-- <script src="assets/js/index.js"></script> -->
			<script>
				jQuery(document).ready(function() {
					Main.init();
					Index.init();
				});
			</script>
			<!-- end: JavaScript Event Handlers for this page -->
			<!-- end: CLIP-TWO JAVASCRIPTS -->
</body>
</html>
