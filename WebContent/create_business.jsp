<!DOCTYPE html>
<!-- Template Name: Clip-Two - Responsive Admin Template build with Twitter Bootstrap 3.x | Author: ClipTheme -->
<!--[if IE 8]><html class="ie8" lang="en"><![endif]-->
<!--[if IE 9]><html class="ie9" lang="en"><![endif]-->
<!--[if !IE]><!-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link type="text/css" href="css/bootstrap.min.css" />
<link type="text/css" href="css/bootstrap-timepicker.min.css" />
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap-2.2.2.min.js"></script>
<script type="text/javascript" src="js/bootstrap-timepicker.min.js"></script>
<script src="assets/js/mapMark.js"></script>
<script
	src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAAG_4i2swR3KOd-nGYrlrt8RTkyS8SRe_kYPTAbwTumvAqao01PRRUcCtCzTBnNH2kRURGR8RhQQoZ3w&language=us"
	type="text/javascript"></script>
<script type="text/javascript">
                
                var geocoder = new google.maps.Geocoder();
                var myMap;
                var myMarker
                function load() {
                    if (GBrowserIsCompatible()) {
                        myMap = new GMap2(document.getElementById("my_map"));
                        var myLatLng = new GLatLng(40.444597, -79.945033);
                        myMap.setCenter(myLatLng, 15);
                        myMap.addControl(new GLargeMapControl());
                        
                        document.getElementById('inLat').value = myLatLng.lat();
                        document.getElementById('inLng').value = myLatLng.lng();        
                        
                        myMarker = new GMarker( myLatLng );
                        myMap.addOverlay( myMarker );
                    }
                }
                
                function extractFromAdress(components, type){  //
                    for (var i=0; i<components.length; i++)
                        for (var j=0; j<components[i].types.length; j++)
                            if (components[i].types[j]==type) return components[i].long_name;
                    return "";
                }
                
                function addressGps() {
                    var myGeocoder = new GClientGeocoder();
                    var address = document.getElementById('address').value;
                    myGeocoder.getLatLng(address, function getRequest( point ){
                                            if(!point){
                                                alert('No such addess�');
                                            }else{
                                                //移動地圖中心點
                                                myMap.panTo( point );
                                                //設定標註座標
                                                myMarker.setLatLng(point);
                                                
                                                document.getElementById('inLat').value = point.lat();
                                                document.getElementById('inLng').value = point.lng();
                                            }
                                        });
                    geocoder.geocode( { 'address': address, "region": "US"}, function(results, status) {
                        if (status == google.maps.GeocoderStatus.OK) {

                          var postCode = extractFromAdress(results[0].address_components, "neighborhood");
                          
                          document.getElementById("region").innerHTML = "Region:" + postCode;
                          document.getElementById('inReg').value = postCode;
                          /* var marker = new google.maps.Marker({
                              map: map,
                              position: results[0].geometry.location
                          }); */
                        } else {
                          alert('Geocode was not successful for the following reason: ' + status);
                        }
                      });
                }
                
                //]]>
                </script>
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
						<li class="active open"><a href="create_business.jsp">
								<div class="item-content">
									<div class="item-media">
										<i class="ti-home"></i>
									</div>
									<div class="item-inner">
										<span class="title"> Create Business </span>
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
                            <li>
                                <a href="list.do">
                                    <div class="item-content">
                                        <div class="item-media">
                                            <span class="fa-stack"> <i class="fa fa-square fa-stack-2x"></i> <i class="fa fa-terminal fa-stack-1x fa-inverse"></i> </span>
                                        </div>
                                        <div class="item-inner">
                                            <span class="title"> Manage Users</span>
                                        </div>
                                    </div>
                                </a>
                            </li>
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
					</a> <a class="navbar-brand" href="admin_main.jsp">
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
								<h1 class="mainTitle">Create Company</h1>
								<span class="mainDescription">for new business owner </span>
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
											<p class="text-small no-margin">Sales</p>
										</div>
									</li>
									<li>
										<div class="sparkline-2">
											<span></span>
										</div>
										<div class="values">
											<strong class="text-dark">&#36;3,833</strong>
											<p class="text-small no-margin">Earnings</p>
										</div>
									</li>
								</ul>
								<!-- end: MINI STATS WITH SPARKLINE -->
							</div>
						</div>
					</section>
					<!-- end: DASHBOARD TITLE -->
					<!-- start: REGISTER BOX -->
					<div class="box-register">
						<div class="col-sm-9">
							<h4 style="color: red">${msg}</h4>
							<filedset>
							<legend> Beacon Info </legend>
							<form class="form-register" method="POST"
								action="create_beacon.do"  enctype="multipart/form-data">
								
									
                                    
                                    
                                        <input type="text" name="major_value" placeholder="Major Value">
                                        <input type="text" name="minor_value" placeholder="Minor Value">
                                        <input type="text" name="udid" placeholder="Udid">
                                        
                                   	<button type="submit" name="action" value="Add">Add </button>
                                   	
								</form>
								</filedset>
							<form class="form-register" method="POST"
								action="create_business.do"  enctype="multipart/form-data">
								<fieldset>
									<legend> Business Info </legend>
									<p>Enter new business owner's info below:</p>
									<div class="form-group">
										<input type="text" name="name" class="form-control"
											placeholder="Company Name">
									</div>
									<div class="form-group">
										<input type="text" name="username" placeholder="Username">
									</div>
									<div class="form-group">
										<input type="text" name="password" placeholder="Password">
									</div>
							
   									                                     
											
									
									<div class="form-group">
										<input id="address" name="address"
											type="text" size="40" placeholder="Address" />
									</div>
									<div class="form-group">
										<input type="text" name="city" placeholder="City">
									</div>
									<p name="region" id="region"></p>


									<body onload="load()" onunload="GUnload()">

										<input name="button" type="button" value="Check"
											onclick="javascript:addressGps();" />
										<br>

										<input id="inLat" name="inLat" type="hidden" size="20"
											value="" />
										<input id="inLng" name="inLng" type="hidden" size="20"
											value="" />
										<input id="inReg" name="inReg" type="hidden" size="20"
											value="" />
										<p>
										<div id="my_map" style="width: 400px; height: 300px"></div>
									</body>
									<div class="form-group">
										<label class="block"> Region </label>
										<div>
											<c:forEach var="c" items="${region}">
												
												
												<input type="radio"  name="region"
                                                value= "${c}" ><label>
                                                 ${c} </label>
                                                
											</c:forEach>
										</div>
									</div>
									<div class="form-group">
										<label class="block"> Categories </label>
										<div class="clip-radio radio-primary">
											<input type="radio" id="rg-restaurant" name="category"
												value="restaurant"> <label for="rg-restaurant">
												Restaurants </label> <input type="radio" id="rg-bar" name="category"
												value="bar"> <label for="rg-bar"> Bars </label> <input
												type="radio" id="rg-ct" name="category" value="ct">
											<label for="rg-ct"> Coffee & Tea </label>
										</div>
									</div>

									<div>

										<div class="form-group">
											<label class="control-label col-sm-2" for="phone">Phone:</label>
											<div class="col-sm-10">
												<input type="phone" name="phone" class="form-control"
													id="phone" placeholder="Enter phone number">
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-sm-2" for="website">Website:</label>
											<div class="col-sm-10">
												<input type="website" class="form-control" name="website"
													placeholder="Enter website address">
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-sm-2" for="email">Email:</label>
											<div class="col-sm-10">
												<input type="email" class="form-control" name="email"
													placeholder="Enter email address">
											</div>
										</div>
										<div class="input-append bootstrap-timepicker">
										Operating Hours <br></br>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										Mon&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										Tue&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										Wed&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										Thu&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp
										Fri&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp
										Sat&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp
										Sun&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp
										<br> 
										From:&nbsp;&nbsp;<select name="monday_from" >
												  <option selected="selected">closed</option>
												  <option value="12am">12:00am</option>
												  <option value="1am">1:00am</option>
												  <option value="2am">2:00am</option>
												  <option value="3am">3:00am</option>
												  <option value="4am">4:00am</option>
												  <option value="5am">5:00am</option>
												  <option value="6am">6:00am</option>
												  <option value="7am">7:00am</option>
												  <option value="8am">8:00am</option>
												  <option value="9am">9:00am</option>
												  <option value="10am">10:00am</option>
												  <option value="11am">11:00am</option>
												  <option value="12pm">12:00pm</option>
												  <option value="1pm">1:00pm</option>
												  <option value="2pm">2:00pm</option>
												  <option value="3pm">3:00pm</option>
												  <option value="4pm">4:00pm</option>
												  <option value="5pm">5:00pm</option>
												  <option value="6pm">6:00pm</option>
												  <option value="7pm">7:00pm</option>
												  <option value="8pm">8:00pm</option>
												  <option value="9pm">9:00pm</option>
												  <option value="10pm">10:00pm</option>
												  <option value="11pm">11:00pm</option>
												</select>
												&nbsp;&nbsp;
												<select name="tuesday_from" >
												<option selected="selected">closed</option>
												  <option value="12am">12:00am</option>
												  <option value="1am">1:00am</option>
												  <option value="2am">2:00am</option>
												  <option value="3am">3:00am</option>
												  <option value="4am">4:00am</option>
												  <option value="5am">5:00am</option>
												  <option value="6am">6:00am</option>
												  <option value="7am">7:00am</option>
												  <option value="8am">8:00am</option>
												  <option value="9am">9:00am</option>
												  <option value="10am">10:00am</option>
												  <option value="11am">11:00am</option>
												  <option value="12pm">12:00pm</option>
												  <option value="1pm">1:00pm</option>
												  <option value="2pm">2:00pm</option>
												  <option value="3pm">3:00pm</option>
												  <option value="4pm">4:00pm</option>
												  <option value="5pm">5:00pm</option>
												  <option value="6pm">6:00pm</option>
												  <option value="7pm">7:00pm</option>
												  <option value="8pm">8:00pm</option>
												  <option value="9pm">9:00pm</option>
												  <option value="10pm">10:00pm</option>
												  <option value="11pm">11:00pm</option>
												</select>
												&nbsp;&nbsp;
												<select name="wednesday_from" >
												<option selected="selected">closed</option>
												  <option value="12am">12:00am</option>
												  <option value="1am">1:00am</option>
												  <option value="2am">2:00am</option>
												  <option value="3am">3:00am</option>
												  <option value="4am">4:00am</option>
												  <option value="5am">5:00am</option>
												  <option value="6am">6:00am</option>
												  <option value="7am">7:00am</option>
												  <option value="8am">8:00am</option>
												  <option value="9am">9:00am</option>
												  <option value="10am">10:00am</option>
												  <option value="11am">11:00am</option>
												  <option value="12pm">12:00pm</option>
												  <option value="1pm">1:00pm</option>
												  <option value="2pm">2:00pm</option>
												  <option value="3pm">3:00pm</option>
												  <option value="4pm">4:00pm</option>
												  <option value="5pm">5:00pm</option>
												  <option value="6pm">6:00pm</option>
												  <option value="7pm">7:00pm</option>
												  <option value="8pm">8:00pm</option>
												  <option value="9pm">9:00pm</option>
												  <option value="10pm">10:00pm</option>
												  <option value="11pm">11:00pm</option>
												</select>
												&nbsp;&nbsp;
												<select name="thursday_from" >
												<option selected="selected">closed</option>
												  <option value="12am">12:00am</option>
												  <option value="1am">1:00am</option>
												  <option value="2am">2:00am</option>
												  <option value="3am">3:00am</option>
												  <option value="4am">4:00am</option>
												  <option value="5am">5:00am</option>
												  <option value="6am">6:00am</option>
												  <option value="7am">7:00am</option>
												  <option value="8am">8:00am</option>
												  <option value="9am">9:00am</option>
												  <option value="10am">10:00am</option>
												  <option value="11am">11:00am</option>
												  <option value="12pm">12:00pm</option>
												  <option value="1pm">1:00pm</option>
												  <option value="2pm">2:00pm</option>
												  <option value="3pm">3:00pm</option>
												  <option value="4pm">4:00pm</option>
												  <option value="5pm">5:00pm</option>
												  <option value="6pm">6:00pm</option>
												  <option value="7pm">7:00pm</option>
												  <option value="8pm">8:00pm</option>
												  <option value="9pm">9:00pm</option>
												  <option value="10pm">10:00pm</option>
												  <option value="11pm">11:00pm</option>
												</select>
												&nbsp;&nbsp;
												<select name="friday_from" >
												<option selected="selected">closed</option>
												  <option value="12am">12:00am</option>
												  <option value="1am">1:00am</option>
												  <option value="2am">2:00am</option>
												  <option value="3am">3:00am</option>
												  <option value="4am">4:00am</option>
												  <option value="5am">5:00am</option>
												  <option value="6am">6:00am</option>
												  <option value="7am">7:00am</option>
												  <option value="8am">8:00am</option>
												  <option value="9am">9:00am</option>
												  <option value="10am">10:00am</option>
												  <option value="11am">11:00am</option>
												  <option value="12pm">12:00pm</option>
												  <option value="1pm">1:00pm</option>
												  <option value="2pm">2:00pm</option>
												  <option value="3pm">3:00pm</option>
												  <option value="4pm">4:00pm</option>
												  <option value="5pm">5:00pm</option>
												  <option value="6pm">6:00pm</option>
												  <option value="7pm">7:00pm</option>
												  <option value="8pm">8:00pm</option>
												  <option value="9pm">9:00pm</option>
												  <option value="10pm">10:00pm</option>
												  <option value="11pm">11:00pm</option>
												</select>
												&nbsp;&nbsp;
												<select name="saturday_from" >
												<option selected="selected">closed</option>
												  <option value="12am">12:00am</option>
												  <option value="1am">1:00am</option>
												  <option value="2am">2:00am</option>
												  <option value="3am">3:00am</option>
												  <option value="4am">4:00am</option>
												  <option value="5am">5:00am</option>
												  <option value="6am">6:00am</option>
												  <option value="7am">7:00am</option>
												  <option value="8am">8:00am</option>
												  <option value="9am">9:00am</option>
												  <option value="10am">10:00am</option>
												  <option value="11am">11:00am</option>
												  <option value="12pm">12:00pm</option>
												  <option value="1pm">1:00pm</option>
												  <option value="2pm">2:00pm</option>
												  <option value="3pm">3:00pm</option>
												  <option value="4pm">4:00pm</option>
												  <option value="5pm">5:00pm</option>
												  <option value="6pm">6:00pm</option>
												  <option value="7pm">7:00pm</option>
												  <option value="8pm">8:00pm</option>
												  <option value="9pm">9:00pm</option>
												  <option value="10pm">10:00pm</option>
												  <option value="11pm">11:00pm</option>
												</select>
												&nbsp;&nbsp;
												<select name="sunday_from" >
												<option selected="selected">closed</option>
												  <option value="12am">12:00am</option>
												  <option value="1am">1:00am</option>
												  <option value="2am">2:00am</option>
												  <option value="3am">3:00am</option>
												  <option value="4am">4:00am</option>
												  <option value="5am">5:00am</option>
												  <option value="6am">6:00am</option>
												  <option value="7am">7:00am</option>
												  <option value="8am">8:00am</option>
												  <option value="9am">9:00am</option>
												  <option value="10am">10:00am</option>
												  <option value="11am">11:00am</option>
												  <option value="12pm">12:00pm</option>
												  <option value="1pm">1:00pm</option>
												  <option value="2pm">2:00pm</option>
												  <option value="3pm">3:00pm</option>
												  <option value="4pm">4:00pm</option>
												  <option value="5pm">5:00pm</option>
												  <option value="6pm">6:00pm</option>
												  <option value="7pm">7:00pm</option>
												  <option value="8pm">8:00pm</option>
												  <option value="9pm">9:00pm</option>
												  <option value="10pm">10:00pm</option>
												  <option value="11pm">11:00pm</option>
												</select>
												&nbsp;&nbsp;
												<br>
												<br>
										To:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select name="monday_to" >
										<option selected="selected">closed</option>
												  <option value="12am">12:00am</option>
												  <option value="1am">1:00am</option>
												  <option value="2am">2:00am</option>
												  <option value="3am">3:00am</option>
												  <option value="4am">4:00am</option>
												  <option value="5am">5:00am</option>
												  <option value="6am">6:00am</option>
												  <option value="7am">7:00am</option>
												  <option value="8am">8:00am</option>
												  <option value="9am">9:00am</option>
												  <option value="10am">10:00am</option>
												  <option value="11am">11:00am</option>
												  <option value="12pm">12:00pm</option>
												  <option value="1pm">1:00pm</option>
												  <option value="2pm">2:00pm</option>
												  <option value="3pm">3:00pm</option>
												  <option value="4pm">4:00pm</option>
												  <option value="5pm">5:00pm</option>
												  <option value="6pm">6:00pm</option>
												  <option value="7pm">7:00pm</option>
												  <option value="8pm">8:00pm</option>
												  <option value="9pm">9:00pm</option>
												  <option value="10pm">10:00pm</option>
												  <option value="11pm">11:00pm</option>
												</select>
												&nbsp;&nbsp;
												<select name="tuesday_to" >
												<option selected="selected">closed</option>
												  <option value="12am">12:00am</option>
												  <option value="1am">1:00am</option>
												  <option value="2am">2:00am</option>
												  <option value="3am">3:00am</option>
												  <option value="4am">4:00am</option>
												  <option value="5am">5:00am</option>
												  <option value="6am">6:00am</option>
												  <option value="7am">7:00am</option>
												  <option value="8am">8:00am</option>
												  <option value="9am">9:00am</option>
												  <option value="10am">10:00am</option>
												  <option value="11am">11:00am</option>
												  <option value="12pm">12:00pm</option>
												  <option value="1pm">1:00pm</option>
												  <option value="2pm">2:00pm</option>
												  <option value="3pm">3:00pm</option>
												  <option value="4pm">4:00pm</option>
												  <option value="5pm">5:00pm</option>
												  <option value="6pm">6:00pm</option>
												  <option value="7pm">7:00pm</option>
												  <option value="8pm">8:00pm</option>
												  <option value="9pm">9:00pm</option>
												  <option value="10pm">10:00pm</option>
												  <option value="11pm">11:00pm</option>
												</select>
												&nbsp;&nbsp;
												<select name="wednesday_to" >
												<option selected="selected">closed</option>
												  <option value="12am">12:00am</option>
												  <option value="1am">1:00am</option>
												  <option value="2am">2:00am</option>
												  <option value="3am">3:00am</option>
												  <option value="4am">4:00am</option>
												  <option value="5am">5:00am</option>
												  <option value="6am">6:00am</option>
												  <option value="7am">7:00am</option>
												  <option value="8am">8:00am</option>
												  <option value="9am">9:00am</option>
												  <option value="10am">10:00am</option>
												  <option value="11am">11:00am</option>
												  <option value="12pm">12:00pm</option>
												  <option value="1pm">1:00pm</option>
												  <option value="2pm">2:00pm</option>
												  <option value="3pm">3:00pm</option>
												  <option value="4pm">4:00pm</option>
												  <option value="5pm">5:00pm</option>
												  <option value="6pm">6:00pm</option>
												  <option value="7pm">7:00pm</option>
												  <option value="8pm">8:00pm</option>
												  <option value="9pm">9:00pm</option>
												  <option value="10pm">10:00pm</option>
												  <option value="11pm">11:00pm</option>
												</select>
												&nbsp;&nbsp;
												<select name="thursday_to" >
												<option selected="selected">closed</option>
												  <option value="12am">12:00am</option>
												  <option value="1am">1:00am</option>
												  <option value="2am">2:00am</option>
												  <option value="3am">3:00am</option>
												  <option value="4am">4:00am</option>
												  <option value="5am">5:00am</option>
												  <option value="6am">6:00am</option>
												  <option value="7am">7:00am</option>
												  <option value="8am">8:00am</option>
												  <option value="9am">9:00am</option>
												  <option value="10am">10:00am</option>
												  <option value="11am">11:00am</option>
												  <option value="12pm">12:00pm</option>
												  <option value="1pm">1:00pm</option>
												  <option value="2pm">2:00pm</option>
												  <option value="3pm">3:00pm</option>
												  <option value="4pm">4:00pm</option>
												  <option value="5pm">5:00pm</option>
												  <option value="6pm">6:00pm</option>
												  <option value="7pm">7:00pm</option>
												  <option value="8pm">8:00pm</option>
												  <option value="9pm">9:00pm</option>
												  <option value="10pm">10:00pm</option>
												  <option value="11pm">11:00pm</option>
												</select>
												&nbsp;&nbsp;
												<select name="friday_to" >
												<option selected="selected">closed</option>
												  <option value="12am">12:00am</option>
												  <option value="1am">1:00am</option>
												  <option value="2am">2:00am</option>
												  <option value="3am">3:00am</option>
												  <option value="4am">4:00am</option>
												  <option value="5am">5:00am</option>
												  <option value="6am">6:00am</option>
												  <option value="7am">7:00am</option>
												  <option value="8am">8:00am</option>
												  <option value="9am">9:00am</option>
												  <option value="10am">10:00am</option>
												  <option value="11am">11:00am</option>
												  <option value="12pm">12:00pm</option>
												  <option value="1pm">1:00pm</option>
												  <option value="2pm">2:00pm</option>
												  <option value="3pm">3:00pm</option>
												  <option value="4pm">4:00pm</option>
												  <option value="5pm">5:00pm</option>
												  <option value="6pm">6:00pm</option>
												  <option value="7pm">7:00pm</option>
												  <option value="8pm">8:00pm</option>
												  <option value="9pm">9:00pm</option>
												  <option value="10pm">10:00pm</option>
												  <option value="11pm">11:00pm</option>
												</select>
												&nbsp;&nbsp;
												<select name="saturday_to" >
												<option selected="selected">closed</option>
												  <option value="12am">12:00am</option>
												  <option value="1am">1:00am</option>
												  <option value="2am">2:00am</option>
												  <option value="3am">3:00am</option>
												  <option value="4am">4:00am</option>
												  <option value="5am">5:00am</option>
												  <option value="6am">6:00am</option>
												  <option value="7am">7:00am</option>
												  <option value="8am">8:00am</option>
												  <option value="9am">9:00am</option>
												  <option value="10am">10:00am</option>
												  <option value="11am">11:00am</option>
												  <option value="12pm">12:00pm</option>
												  <option value="1pm">1:00pm</option>
												  <option value="2pm">2:00pm</option>
												  <option value="3pm">3:00pm</option>
												  <option value="4pm">4:00pm</option>
												  <option value="5pm">5:00pm</option>
												  <option value="6pm">6:00pm</option>
												  <option value="7pm">7:00pm</option>
												  <option value="8pm">8:00pm</option>
												  <option value="9pm">9:00pm</option>
												  <option value="10pm">10:00pm</option>
												  <option value="11pm">11:00pm</option>
												</select>
												&nbsp;&nbsp;
												<select name="sunday_to" >
												<option selected="selected">closed</option>
												  <option value="12am">12:00am</option>
												  <option value="1am">1:00am</option>
												  <option value="2am">2:00am</option>
												  <option value="3am">3:00am</option>
												  <option value="4am">4:00am</option>
												  <option value="5am">5:00am</option>
												  <option value="6am">6:00am</option>
												  <option value="7am">7:00am</option>
												  <option value="8am">8:00am</option>
												  <option value="9am">9:00am</option>
												  <option value="10am">10:00am</option>
												  <option value="11am">11:00am</option>
												  <option value="12pm">12:00pm</option>
												  <option value="1pm">1:00pm</option>
												  <option value="2pm">2:00pm</option>
												  <option value="3pm">3:00pm</option>
												  <option value="4pm">4:00pm</option>
												  <option value="5pm">5:00pm</option>
												  <option value="6pm">6:00pm</option>
												  <option value="7pm">7:00pm</option>
												  <option value="8pm">8:00pm</option>
												  <option value="9pm">9:00pm</option>
												  <option value="10pm">10:00pm</option>
												  <option value="11pm">11:00pm</option>
												</select>
												&nbsp;&nbsp;
										</div>
										<br>
										<label class="control-label col-sm-2" for="image">Image:</label>
										<input type="file" name="image">
										<br></br>
									</div>
									<div class="form-group">
										<p>Description:</p>
										<textarea class="form-control" rows="5" name="description"></textarea>
									</div>
									<div class="form-actions">
										<button type="submit" name="action"
											class="btn btn-primary pull-right">
											Submit <i class="fa fa-arrow-circle-right"></i>
										</button>
									</div>
								</fieldset>
							</form>
							<!-- start: COPYRIGHT -->
							<div class="copyright">
								&copy; <span class="current-year"></span><span
									class="text-bold text-uppercase"> Nectr</span>. <span>All
									rights reserved</span>
							</div>
						</div>
						<div class="col-sm-6"></div>
						<!-- end: COPYRIGHT -->
					</div>
					<!-- end: REGISTER BOX -->
				
				</div>
				<!-- start: MAIN JAVASCRIPTS -->
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
				<!-- start: JavaScript Event Handlers for this page -->
				<script src="assets/js/mapMark.js"></script>
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