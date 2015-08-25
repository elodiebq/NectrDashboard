
var defaultWalkInView = $("#defaultWalkInView").html();
var defaultWalkByView = $("#defaultWalkByView").html();
var timeMonth = $("#timeMonth").html();

var walkInByMonths = String(defaultWalkInView).split(',');
var walkByByMonths = String(defaultWalkByView).split(',');
var timeByMonths = String(timeMonth).split(',');
var n = new Array(walkInByMonths.length);
for (i = 0; i < walkInByMonths.length; i++) {
	n[i] = i;
}

var barDayMonth = {
    labels : n.map(function(i){return timeByMonths[i];}),
    datasets : [
        {
        	fillColor : "rgba(217,83,79,1)",
            strokeColor : "rgba(217,83,79,0.6)",
            data : n.map(function(i){return parseInt(walkInByMonths[i]);})
        },
        {
        	fillColor : "rgba(92,184,92,1)",
            strokeColor : "rgba(92,184,92,0.6)",
            data : n.map(function(i){return parseInt(walkByByMonths[i]);})
        }
        
    ]
};

var month = $("#month").get(0).getContext("2d");
new Chart(month).Bar(barDayMonth);