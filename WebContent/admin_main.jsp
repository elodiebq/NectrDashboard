<!DOCTYPE html>
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
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta content="" name="description" />
        <meta content="" name="author" />
        <!-- end: META -->
        <!-- start: GOOGLE FONTS -->
        <link href="http://fonts.googleapis.com/css?family=Lato:300,400,400italic,600,700|Raleway:300,400,500,600,700|Crete+Round:400italic" rel="stylesheet" type="text/css" />
        <!-- end: GOOGLE FONTS -->
        <!-- start: MAIN CSS -->
        <link rel="stylesheet" href="vendor/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="vendor/fontawesome/css/font-awesome.min.css">
        <link rel="stylesheet" href="vendor/themify-icons/themify-icons.min.css">
        <link href="vendor/animate.css/animate.min.css" rel="stylesheet" media="screen">
        <link href="vendor/perfect-scrollbar/perfect-scrollbar.min.css" rel="stylesheet" media="screen">
        <link href="vendor/switchery/switchery.min.css" rel="stylesheet" media="screen">
        <!-- end: MAIN CSS -->
        <!-- start: CLIP-TWO CSS -->
        <link rel="stylesheet" href="assets/css/styles.css">
        <link rel="stylesheet" href="assets/css/plugins.css">
        <link rel="stylesheet" href="assets/css/themes/theme-1.css" id="skin_color" />
        <!-- end: CLIP-TWO CSS -->
        <!-- start: CSS REQUIRED FOR THIS PAGE ONLY -->
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
                            <a class="s-open" href="#">
                                <i class="ti-search"></i>
                            </a>
                            <form class="navbar-form" role="search">
                                <a class="s-remove" href="#" target=".navbar-form">
                                    <i class="ti-close"></i>
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
                            <li class="active open">
                                <a href="create_business.jsp">
                                    <div class="item-content">
                                        <div class="item-media">
                                            <i class="ti-home"></i>
                                        </div>
                                        <div class="item-inner">
                                            <span class="title"> Create Business </span>
                                        </div>
                                    </div>
                                </a>
                            </li>
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
                            <a href="documentation.html" class="button-o">
                                <i class="ti-help"></i>
                                <span>Documentation</span>
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
                        <a href="#" class="sidebar-mobile-toggler pull-left hidden-md hidden-lg" class="btn btn-navbar sidebar-toggle" data-toggle-class="app-slide-off" data-toggle-target="#app" data-toggle-click-outside="#sidebar">
                            <i class="ti-align-justify"></i>
                        </a>
                        <a class="navbar-brand" href="#">
                            <h2> Nectr</h2>
                        </a>
                        <a href="#" class="sidebar-toggler pull-right visible-md visible-lg" data-toggle-class="app-sidebar-closed" data-toggle-target="#app">
                            <i class="ti-align-justify"></i>
                        </a>
                        <a class="pull-right menu-toggler visible-xs-block" id="menu-toggler" data-toggle="collapse" href=".navbar-collapse">
                            <span class="sr-only">Toggle navigation</span>
                            <i class="ti-view-grid"></i>
                        </a>
                    </div>
                    <!-- end: NAVBAR HEADER -->
                    <!-- start: NAVBAR COLLAPSE -->
                    <div class="navbar-collapse collapse">
                        <ul class="nav navbar-right">
                            
                            <!-- start: USER OPTIONS DROPDOWN -->
                            <li class="dropdown current-user">
                                <a href class="dropdown-toggle" data-toggle="dropdown">
                                    <img src="assets/images/avatar-1.jpg" alt="Peter"> <span class="username">Peter <i class="ti-angle-down"></i></i></span>
                                </a>
                                <ul class="dropdown-menu dropdown-dark">
                                   
                                    <li>
                                         <a href="logout.do">
                                            Log Out
                                        </a>
                                    </li>
                                </ul>
                            </li>
                            <!-- end: USER OPTIONS DROPDOWN -->
                        </ul>
                        <!-- start: MENU TOGGLER FOR MOBILE DEVICES -->
                        <div class="close-handle visible-xs-block menu-toggler" data-toggle="collapse" href=".navbar-collapse">
                            <div class="arrow-left"></div>
                            <div class="arrow-right"></div>
                        </div>
                        <!-- end: MENU TOGGLER FOR MOBILE DEVICES -->
                    </div>
                   
                    <!-- end: NAVBAR COLLAPSE -->
                </header>
                <!-- end: TOP NAVBAR -->
                <div class="main-content" >
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
                                                <span ></span>
                                            </div>
                                            <div class="values">
                                                <strong class="text-dark">18304</strong>
                                                <p class="text-small no-margin">
                                                    Sales
                                                </p>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="sparkline-2">
                                                <span ></span>
                                            </div>
                                            <div class="values">
                                                <strong class="text-dark">&#36;3,833</strong>
                                                <p class="text-small no-margin">
                                                    Earnings
                                                </p>
                                            </div>
                                        </li>
                                    </ul>
                                    <!-- end: MINI STATS WITH SPARKLINE -->
                                </div>
                            </div>
                        </section>
                        <!-- end: DASHBOARD TITLE -->
                        <!-- start: FEATURED BOX LINKS -->
                        <div class="container-fluid container-fullw bg-white">
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="panel panel-white no-radius text-center">
                                        <div class="panel-body">
                                            <span class="fa-stack fa-2x"> <i class="fa fa-square fa-stack-2x text-primary"></i> <i class="fa fa-smile-o fa-stack-1x fa-inverse"></i> </span>
                                            <h2 class="StepTitle">Manage Users</h2>
                                            <p class="text-small">
                                                To add users, you need to be signed in as the super user.
                                            </p>
                                            <p class="links cl-effect-1">
                                                <a href="list.do">
                                                    view more
                                                </a>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="panel panel-white no-radius text-center">
                                        <div class="panel-body">
                                            <span class="fa-stack fa-2x"> <i class="fa fa-square fa-stack-2x text-primary"></i> <i class="fa fa-paperclip fa-stack-1x fa-inverse"></i> </span>
                                            <h2 class="StepTitle">Create Company</h2>
                                            <p class="text-small">
                                                The Manage Orders tool provides a view of all your orders.
                                            </p>
                                            <p class="cl-effect-1">
                                                <a href="get_region.do">
                                                    view more
                                                </a>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- end: FEATURED BOX LINKS -->
                        
                        
                       
                    </div>
                </div>
            </div>
            <!-- start: FOOTER -->
            <footer>
                <div class="footer-inner">
                    <div class="pull-left">
                        &copy; <span class="current-year"></span><span class="text-bold text-uppercase">Nectr</span>. <span>All rights reserved</span>
                    </div>
                    <div class="pull-right">
                        <span class="go-top"><i class="ti-angle-up"></i></span>
                    </div>
                </div>
            </footer>
            <!-- end: FOOTER -->
           
         
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
        <script src="assets/js/index.js"></script>
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
