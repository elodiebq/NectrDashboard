var curr = new google.maps.LatLng(40.4436664, -79.943622)
var marker;
var lat;
var lng;
var currPosition;
function initialize() {

	var mapProp = {
		center : curr,
		zoom : 14,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};

	var map = new google.maps.Map(document.getElementById("googleMap"), mapProp);

	var lonLan = $("#addList").html();

	var centers = String(lonLan).split(' ');
	// var currMarker = new google.maps.Marker({
	// position:new
	// google.maps.LatLng((parseInt($("#addList").html()),parseInt($("#addList").html()))),
	// animation:google.maps.Animation.BOUNCE
	// });
	// currMarker.setMap(map);
	for (i = 0; i < centers.length; i++) {
		lat = String(centers[i]).split(',')[0];
		lng = String(centers[i]).split(',')[1];
		var a = parseFloat(lat);
		var b = parseFloat(lng);
		currPosition = new google.maps.LatLng(a, b);
		marker = new google.maps.Marker({
			position : currPosition,
			animation : google.maps.Animation
		});
		marker.setMap(map);

	}

}

google.maps.event.addDomListener(window, 'load', initialize);
