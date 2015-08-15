
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
            fillColor : "rgba(73,188,170,0.4)",
            strokeColor : "rgba(72,174,209,0.4)",
            data : n.map(function(i){return parseInt(walkInByMonths[walkInByMonths.length - i - 1]);})
        },
        {
            fillColor : "rgba(151,187,205,0.5)",
            strokeColor : "rgba(151,187,205,0.8)",
            data : n.map(function(i){return parseInt(walkByByMonths[walkInByMonths.length - i - 1]);})
        }
        
    ]
};

var month = $("#month").get(0).getContext("2d");
new Chart(month).Bar(barDayMonth);