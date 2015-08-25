<jsp:include page="templet.jsp"/>
					<div class="container-fluid container-fullw padding-bottom-10">
						<div class="row">

							<p id="defaultWalkInView" style="display: none">${defaultWalkInView}</p>
							<p id="defaultWalkByView" style="display: none">${defaultWalkByView}</p>
							<p id="loyalCustomer" style="display: none">${loyalCustomer}</p>
							<h5 class="over-title">
								<span class="text-bold">Today is ${currTime}</span>
							</h5>
							<div class="col-md-8">
								<div class="panel panel-white no-radius" id="visits">
									<div class="panel-heading border-light">

										<form action="view_data.do">
											<div class="col-sm-10 col-lg-7 input-group">

												<input type="date" id="form-field-mask-1"
													class="form-control input-mask-date"> <span
													class="input-group-btn">
													<button type="submit" class="btn btn-primary">
														<i class="fa fa-calendar"></i> Go!
													</button>
												</span>

											</div>
										</form>

										<ul class="panel-heading-tabs border-light">
											<li>
												<div class="pull-right">
													<div class="btn-group">
														<a class="padding-10" data-toggle="dropdown"
															aria-expanded="false"> <i class="ti-more-alt "></i>
														</a>
														<ul class="dropdown-menu dropdown-light" role="menu">
															<li><a href="view_data.do">View by Hour</a></li>
															<li><a href="view_data_day.do"> View by Day </a></li>
															<li><a href="view_data_month.do"> View by Month
															</a></li>
															<li><a href="view_data_year.do"> View by Year </a></li>
														</ul>
													</div>
												</div>
											</li>

											<li class="panel-tools"><a data-original-title="Refresh"
												data-toggle="tooltip" data-placement="top"
												class="btn btn-transparent btn-sm panel-refresh" href="#"><i
													class="ti-reload"></i></a></li>
										</ul>
									</div>
									<div collapse="visits" class="panel-wrapper">
										<div class="panel-body">
											<div class="height-350">

												<canvas id="hour" class="full-width" width="1176"
													height="700" style="width: 588px; height: 350px;"></canvas>
												<div class="margin-top-20">
													<div class="inline pull-left">
														<div id="chart1Legend" class="chart-legend">
															<ul class="tc-chart-js-legend">
																<li><span
																	style="background-color: rgba(181, 54, 49, 1)"></span>Walk
																	In</li>
																<li><span
																	style="background-color: rgba(70, 184, 218, 1)"></span>Walk
																	By</li>
															</ul>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>

							<div class="col-md-4">
    <div class="row">
                                        <div class="col-md-12">
                                            <div class="panel panel-white no-radius">
                                                <div class="panel-body padding-20 text-center">
                                                    <div class="space10">
                                                        <h5 class="text-dark no-margin">Today</h5>
                                                        <h2 class="no-margin"><small>$</small>1,450</h2>
                                                        <span class="badge badge-success margin-top-10">253 Sales</span>
                                                    </div>
                                                    <div class="sparkline-4 space10">
                                                        <span><canvas width="203" height="47" style="display: inline-block; vertical-align: top; width: 203px; height: 47px;"></canvas></span>
                                                    </div>
                                                    <span class="text-white-transparent"><i class="fa fa-clock-o"></i> 1 hour ago</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="panel panel-white no-radius">
                                                <div class="panel-body padding-20 text-center">
                                                    <div class="space10">
                                                        <h5 class="text-dark no-margin">Today</h5>
                                                        <h2 class="no-margin"><small>$</small>1,450</h2>
                                                        <span class="badge badge-danger margin-top-10">253 Sales</span>
                                                    </div>
                                                    <div class="sparkline-5 space10">
                                                        <span><canvas width="203" height="47" style="display: inline-block; vertical-align: top; width: 203px; height: 47px;"></canvas></span>
                                                    </div>
                                                    <span class="text-white-transparent"><i class="fa fa-clock-o"></i> 1 hour ago</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
								<%--  <h2>
                                        Walk in &nbsp&nbsp&nbsp&nbsp<span class="label label-success">13${walkInByDay}</span>
                                    </h2> --%>

								<%--  <h2>
                                        Walk by &nbsp&nbsp&nbsp<span class="label label-warning">28${walkByDay}</span>
                                    </h2> --%>

								<%-- <h2>
                                        Total Visit&nbsp&nbsp<span class="label label-warning">${totalVisit}</span>
                                    </h2> --%>
							

							</div>
						</div>
					</div>

					<div class="container-fluid container-fullw bg-white">
					<div class="col-xs-12 col-sm-4">
                                    <div class="panel panel-white no-radius">
                                    <div class="panel-heading border-light">
                                        <h4 class="panel-title">Visit Times</h4>
                                    </div>
                                    <div class="panel-body">
                                        <h3 class="inline-block no-margin">26</h3>
                                        visitors in this week
                                        <div class="progress progress-xs no-radius">
                                            <div class="progress-bar progress-bar-success"
                                                role="progressbar" aria-valuenow="40" aria-valuemin="0"
                                                aria-valuemax="100" style="width: 40%;">
                                                <span class="sr-only"> 40% Complete</span>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-4">
                                                <h4 class="no-margin">15</h4>
                                                <div class="progress progress-xs no-radius no-margin">
                                                    <div class="progress-bar progress-bar-danger"
                                                        role="progressbar" aria-valuenow="80" aria-valuemin="0"
                                                        aria-valuemax="100" style="width: 80%;">
                                                        <span class="sr-only"> 80% Complete</span>
                                                    </div>
                                                </div>
                                                visitors in this month
                                            </div>
                                            <div class="col-sm-4">
                                                <h4 class="no-margin">7</h4>
                                                <div class="progress progress-xs no-radius no-margin">
                                                    <div class="progress-bar progress-bar-info"
                                                        role="progressbar" aria-valuenow="60" aria-valuemin="0"
                                                        aria-valuemax="100" style="width: 60%;">
                                                        <span class="sr-only"> 60% Complete</span>
                                                    </div>
                                                </div>
                                                visitors in this year
                                            </div>
                                            <div class="col-sm-4">
                                                <h4 class="no-margin">4</h4>
                                                <div class="progress progress-xs no-radius no-margin">
                                                    <div class="progress-bar progress-bar-warning"
                                                        role="progressbar" aria-valuenow="40" aria-valuemin="0"
                                                        aria-valuemax="100" style="width: 40%;">
                                                        <span class="sr-only"> 40% Complete</span>
                                                    </div>
                                                </div>
                                                potential visitors in this year
                                            </div>
                                        </div>
                                        <div class="row margin-top-30">
                                            <div class="col-xs-4 text-center">
                                                <div class="rate">
                                                    <i class="fa fa-caret-up text-green"></i><span
                                                        class="value">26</span><span class="percentage">%</span>
                                                </div>
                                                Mac OS X
                                            </div>
                                            <div class="col-xs-4 text-center">
                                                <div class="rate">
                                                    <i class="fa fa-caret-up text-green"></i><span
                                                        class="value">62</span><span class="percentage">%</span>
                                                </div>
                                                Windows
                                            </div>
                                            <div class="col-xs-4 text-center">
                                                <div class="rate">
                                                    <i class="fa fa-caret-down text-red"></i><span
                                                        class="value">12</span><span class="percentage">%</span>
                                                </div>
                                                Other OS
                                            </div>
                                        </div>
                                        <div class="margin-top-10"></div>
                                    </div>
                                </div>
                                </div>
                                <div class="col-xs-12 col-sm-8">
						<div class="panel panel-white no-radius">
							<div class="panel-heading border-bottom">
								<h4 class="panel-title">Repeated Customer</h4>
							</div>
							<div class="panel-body">
								<div class="text-center">
									<span class="mini-pie">
										<canvas id="hourPie" width="600" height="400"></canvas> <span>${totalVisit}</span>
									</span> <span class="inline text-large no-wrap">Total</span>
								</div>
								<div class="margin-top-20 text-center legend-xs inline">
									<div id="chart3Legend" class="chart-legend">
										<ul class="tc-chart-js-legend">
											<li><span style="background-color: #F7464A"></span>Loyal
												Customer</li>
											<li><span style="background-color: #46BFBD"></span>Lukewarm
												Customer</li>
											<li><span style="background-color: #FDB45C"></span>First
												Time Customer</li>
										</ul>
									</div>
								</div>
							</div>
							<div class="panel-footer">
								<div class="clearfix padding-5 space5">
									<div class="col-xs-4 text-center no-padding">
										<div class="border-right border-dark">
											<span class="text-bold block text-extra-large">90%</span> <span
												class="text-light">Loyal Customer</span>
										</div>
									</div>
									<div class="col-xs-4 text-center no-padding">
										<div class="border-right border-dark">
											<span class="text-bold block text-extra-large">2%</span> <span
												class="text-light">Lukewarm Customer</span>
										</div>
									</div>
									<div class="col-xs-4 text-center no-padding">
										<span class="text-bold block text-extra-large">8%</span> <span
											class="text-light">First Time Customer</span>
									</div>
								</div>
							</div>
						</div>
					</div>
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

<jsp:include page="templetButton.jsp"/>