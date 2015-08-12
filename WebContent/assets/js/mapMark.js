var curr = new google.maps.LatLng(40.4436664, -79.943622)
var marker;
var marker2;
var lat;
var lng;
var lat2;
var lng2;
var rad;
var name;
var name2;
var currPosition;
var currPosition2;
function initialize() {

	var mapProp = {
		center : curr,
		zoom : 14,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};

	var map = new google.maps.Map(document.getElementById("googleMap"), mapProp);

	var lonLan = $("#addList").html();
	var regions = $("#addList2").html();

	var centers = String(lonLan).split(';');
	var centerpoint = String(regions).split(';');
	// var currMarker = new google.maps.Marker({
	// position:new
	// google.maps.LatLng((parseInt($("#addList").html()),parseInt($("#addList").html()))),
	// animation:google.maps.Animation.BOUNCE
	// });
	// currMarker.setMap(map);
	var marker2, i, center;
	for (i = 0; i < centerpoint.length; i++) {
		lat = String(centerpoint[i]).split(',')[0];
		lng = String(centerpoint[i]).split(',')[1];
		rad = String(centerpoint[i]).split(',')[2];
		name = String(centerpoint[i]).split(',')[3];
		var a = parseFloat(lat);
		var b = parseFloat(lng);
		var rad = parseFloat(rad);
		currPosition = new google.maps.LatLng(a, b);

		var circle = new google.maps.Circle({
            center: new google.maps.LatLng(a, b),
            map: map,
            radius: rad,          // IN METERS.
            fillColor: '#FF6600',
            fillOpacity: 0.3,
            strokeColor: "#FFF",
            strokeWeight: 0.2         // DON'T SHOW CIRCLE BORDER.
        });
		
		attachInfoWindow(map, circle, name);
	}
	
	for (i = 0; i < centers.length; i++) {
		lat = String(centers[i]).split(',')[0];
		lng = String(centers[i]).split(',')[1];
		name2 = String(centers[i]).split(',')[2];
		var a = parseFloat(lat);
		var b = parseFloat(lng);
		currPosition = new google.maps.LatLng(a, b);
		marker = new google.maps.Marker({
			position : currPosition,
			animation : google.maps.Animation
		});
		marker.setMap(map);
		attachInfoWindow2(map, marker, name2);
	}
}

function attachInfoWindow(map, circle, info){
	  var infowindow = new google.maps.InfoWindow({
	    content: info
	  });  
	  google.maps.event.addListener(circle, 'click', function(ev) {
	    infowindow.setPosition(circle.getCenter());
	    infowindow.open(map);
	  });
}

function attachInfoWindow2(map, marker, info){
	  var infowindow = new google.maps.InfoWindow({
	    content: info
	  });  
	  google.maps.event.addListener(marker, 'click', function(ev) {
	    infowindow.setPosition(marker.getPosition());
	    infowindow.open(map);
	  });
}


google.maps.event.addDomListener(window, 'load', initialize);
