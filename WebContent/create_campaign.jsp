<jsp:include page="templet.jsp" />
<!-- start: REGISTER BOX -->
<div class="box-register">
	<div class="col-sm-10">
		<h4 style="color: red">${msg}</h4>
		<form class="form-register" method="POST" action="create_campaign.do">
			<fieldset>
				<legend> Campaign Info </legend>
				<p>Enter new business campaign info below:</p>
				<div class="form-group">
					<input type="text" class="form-control" name="title"
						placeholder="Campaign Title">
				</div>
				<div class="radio clip-radio radio-primary radio-inline">
					Campaingn Type: <input type="radio" id="radio7" name="type"
						value="0" checked=""><label for="radio7">&nbsp
						Coupon</label> <input type="radio" id="radio6" name="type" value="1"
						checked="checked"><label for="radio6">&nbsp
						Message</label>

				</div>
				
					
				
						<div class="form-group">
                                <span>Send this campaign to: </span>
								
							
								<select >
									<option value="" disabled="" selected="">Select a
										type of customers</option>
									<option value="france">First Time Customer</option>
									<option value="brazil">Lukewarm Customer</option>
									<option value="argentina">Loyal Customer</option>
									<option value="south-africa">Send it to all</option>
								</select>
						
						</div>
				
			
				<div class="form-group">
					Date from:&nbsp; <input type="date" name="date_from"
						value="date_from" required> To:&nbsp;<input type="date"
						name="date_to" value="date_to" required> <br>
				</div>
				<div>
					Time from:&nbsp; <input type="time" name="time_from"
						value="time_from" required> To:&nbsp;<input type="time"
						name="time_to" value="time_to" required>
				</div>
				<br>
				<br>
				<div class="checkbox clip-check check-primary checkbox-inline ">
					Repeat on: <input type="checkbox" id="checkbox10" name="repeats_on"
						value="7"><label for="checkbox10">S</label> <input
						type="checkbox" id="checkbox1" name="repeats_on" value="1"><label
						for="checkbox1">M</label> <input type="checkbox" id="checkbox2"
						name="repeats_on" value="2"><label for="checkbox2">T</label>
					<input type="checkbox" id="checkbox3" name="repeats_on" value="3"><label
						for="checkbox3">W</label> <input type="checkbox" id="checkbox4"
						name="repeats_on" value="4"><label for="checkbox4">T</label>
					<input type="checkbox" id="checkbox5" name="repeats_on" value="5"><label
						for="checkbox5">F</label> <input type="checkbox" id="checkbox6"
						name="repeats_on" value="6"><label for="checkbox6">S</label>
					<br>

				</div>
				<div class="form-group">
					<p>Campaign Message:</p>
					<textarea class="form-control" name="message" rows="5"
						id="description"></textarea>
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
	<div class="col-sm-2"></div>
	<!-- end: COPYRIGHT -->
</div>
<!-- end: REGISTER BOX -->
<jsp:include page="templetButton.jsp" />