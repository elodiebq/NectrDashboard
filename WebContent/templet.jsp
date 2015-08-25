<!DOCTYPE html>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="javax.servlet.*,java.text.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link rel="icon" href="assets/images/nectr.png">
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,800,600,700' rel='stylesheet' type='text/css'>
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

<link rel="stylesheet" href="assets/css/index copy.css">
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
<script src='assets/js/nectr2.js'></script>
<script src='assets/js/nectr5.js'></script>
<script src='assets/js/nectr6.js'></script>
<script src='assets/js/nectr7.js'></script>
<script src='assets/js/nectrDay.js'></script>
<script src='assets/js/nectrMonth.js'></script>
<script src='assets/js/nectrYear.js'></script>
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
                                            class="fa fa-square fa-stack-2x text-primary"></i> <i
                                            class="fa fa-smile-o fa-stack-1x fa-inverse"></i>
                                        </span>
                                    </div>
                                    <div class="item-inner">
                                        <span class="title">Create Campaign </span>
                                    </div>
                                </div>
                        </a></li>
                        <li><a href="view_history.do">
                                <div class="item-content">
                                    <div class="item-media">
                                        <span class="fa-stack"> <i
                                            class="fa fa-square fa-stack-2x text-primary"></i> <i
                                            class="fa fa-pie-chart fa-stack-1x fa-inverse"></i>
                                        </span>
                                    </div>
                                    <div class="item-inner">
                                        <span class="title"> Campaign Analysis </span>
                                    </div>
                                </div>
                        </a></li>
                        <li><a href="view_data.do">
                                <div class="item-content">
                                    <div class="item-media">
                                        <span class="fa-stack"> <i
                                            class="fa fa-square fa-stack-2x text-primary"></i> <i
                                            class="fa fa-line-chart fa-stack-1x fa-inverse"></i>
                                        </span>
                                    </div>
                                    <div class="item-inner">
                                        <span class="title">Foot Traffic</span>
                                    </div>
                                </div>
                        </a></li>
                    </ul>
                    <!-- end: MAIN NAVIGATION MENU -->

                    <!-- start: DOCUMENTATION BUTTON -->
                    <div class="wrapper">
                        <a href="documentation.html" class="button-o"> <span>Privacy
                                Policy</span>
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
                    </a> <a class="navbar-brand" href="view_data.do"> <img
                        src="assets/images/logo.png" alt="Nectr">
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

                        <!-- end: ACTIVITIES DROPDOWN -->
                        <!-- start: LANGUAGE SWITCHER -->

                        <!-- start: LANGUAGE SWITCHER -->
                        <!-- start: USER OPTIONS DROPDOWN -->
                        <li class="dropdown current-user"><a href
                            class="dropdown-toggle" data-toggle="dropdown"> <img
                                src="assets/images/xu.jpg" alt="Xu"> <span
                                class="username">Xu Zhao <i class="ti-angle-down"></i></i></span>
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
                                <h1 class="mainTitle">Dashboard</h1>
                                <span class="mainDescription">overview &amp; stats </span>
                            </div>
                            <div class="col-sm-5">
                                <!-- start: MINI STATS WITH SPARKLINE -->
                                <ul class="mini-stats pull-right">
                                    <li>
                                        <div class="sparkline-1">
                                            <span><canvas width="41" height="24"
                                                    style="display: inline-block; vertical-align: top; width: 41px; height: 24px;"></canvas></span>
                                        </div>
                                        <div class="values">
                                            <strong class="text-dark">108</strong>
                                            <p class="text-small no-margin">Followers</p>
                                        </div>
                                    </li>

                                </ul>
                                <!-- end: MINI STATS WITH SPARKLINE -->
                            </div>
                        </div>
                    </section>
                    <div class="container-fluid container-fullw bg-white">
                        <div class="row">
                            <div class="col-sm-4">
                                <div class="panel panel-white no-radius text-center">
                                    <div class="panel-body">
                                        <span class="fa-stack fa-2x"> <i
                                            class="fa fa-square fa-stack-2x text-primary"></i> <i
                                            class="fa fa-smile-o fa-stack-1x fa-inverse"></i>
                                        </span>
                                        <h2 class="StepTitle">Create Campaign</h2>
                                        <p class="text-small">You can create campaigns at right
                                            time to attract more customers.</p>
                                        <p class="links cl-effect-1">
                                            <a href="create_campaign.jsp"> view more </a>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="panel panel-white no-radius text-center">
                                    <div class="panel-body">
                                        <span class="fa-stack fa-2x"> <i
                                            class="fa fa-square fa-stack-2x text-primary"></i> <i
                                            class="fa fa-pie-chart fa-stack-1x fa-inverse"></i>
                                        </span>
                                        <h2 class="StepTitle">Campaign Analysis</h2>
                                        <p class="text-small">The Campaign tool provides a view of
                                            all your history campaigns.</p>
                                        <p class="cl-effect-1">
                                            <a href="view_history.do"> view more </a>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="panel panel-white no-radius text-center">
                                    <div class="panel-body">
                                        <span class="fa-stack fa-2x"> <i
                                            class="fa fa-square fa-stack-2x text-primary"></i><i
                                            class="fa fa-line-chart fa-stack-1x fa-inverse"></i>
                                        </span>
                                        <h2 class="StepTitle">Foot Traffic</h2>
                                        <p class="text-small">Store, modify, and extract
                                            visulization information from your business performance.</p>
                                        <p class="cl-effect-1">
                                            <a href="view_data.do"> view more </a>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>