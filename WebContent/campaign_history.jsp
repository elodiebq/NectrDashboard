<jsp:include page="templet.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <div class="container-fluid container-fullw  bg-white">
							<div class="row">
								<div class="col-lg-12">
									<h3 class="over-title margin-bottom-15"> <span class="text-bold">Campaign History</span></h3>

									<div class="tabbable tabs-left">
										<ul id="myTab4" class="nav nav-tabs">
										
											          <c:set var="index" value="0"/>
										<c:forEach var="c" items="${campaignList}">    
											          <c:set var="index" value="${index+1}"/>
											          <c:choose>
  											<c:when test="${index == 1}">
											<li class="active">
												<a href="#${index+1}" data-toggle="tab">
													
													     ${c.date_post }: ${c.title }
													     </a>
												
											</li>
											</c:when>
											 <c:otherwise>
											 <li>
												<a href="#${index+1}" data-toggle="tab">
													
													     ${c.date_post }: ${c.title }
													     </a>									
											</li>
											 </c:otherwise>
											 </c:choose>
											 </c:forEach>
											
										</ul>
										<div class="tab-content">
											          <c:set var="index" value="0"/>
										<c:forEach var="c" items="${campaignList}">    
          								<c:set var="index" value="${index+1}"/>
          								
											<div class="tab-pane fade in active" id="${index+1}">
											<section>
											      <div class ="row">
			     
												     <div class ="col-sm-4">     
												      <p>Date: ${c.date_post } to ${c.date_to} 
												      </p>
												      <p>Time: ${c.time_from } to ${c.time_to}
												      </p>
												        <p>
												         ${c.message }
												         </p>
												         
												          <br/>
												          <font size="4">Campaign Issued: <font color="blue">${c.total_amount }</font></font>
												          <br/>
												         <font size="4"> Campaign Used &nbsp;: <font color="red">${c.used_amount }</font></font>
									
									
												      </div>
												      <div class ="col-sm-4">
  <canvas id="cam${index}" width="300" height="200px"></canvas>
  <div id="barLegend" class="chart-legend"><ul class="bar-legend"><li>Issue<span style="background-color:rgba(92,184,92,0.5)"></span></li><li><span style="background-color:rgba(217,83,79,0.5)"></span>Use</li></ul></div>
												      </div>
												      <div class ="col-sm-4">
	<canvas id="pie${index}" width="300" height="200px"></canvas>
									<div id="doughnutLegend" class="chart-legend"><ul class="doughnut-legend"><li><span style="background-color:#F7464A"></span>Loyal Customer</li><li><span style="background-color:#46BFBD"></span>Lukewarm Customer</li><li><span style="background-color:#FDB45C"></span>First Time Customer</li></ul></div>
										      
												      </div>
												    
												      </div>
											      </section>
											</div>
											
											</c:forEach>
										</div>
									</div>
								</div>
								
							</div>
<jsp:include page="templetButton.jsp"/>