//var bar = $("#bar").html();
var walkIn = $("#defaultWalkInView").html();
var walkBy = $("#defaultWalkByView").html();


var walkIns = String(walkIn).split(',');
var walkBys = String(walkBy).split(',');




var barHourData = {
        labels : ["00:00","02:00","04:00","06:00","08:00","10:00","12:00","14:00","16:00","18:00","20:00","22:00"],
        datasets : [
            {
                fillColor : "rgba(217,83,79,1)",
                strokeColor : "rgba(217,83,79,0.6)",
                label: "Walk in",
                data : [parseInt(walkIns[0]),
                        parseInt(walkIns[1]),
                        parseInt(walkIns[2]),
                        parseInt(walkIns[3]),
                        parseInt(walkIns[4]),
                        parseInt(walkIns[5]),
                        parseInt(walkIns[6]),
                        parseInt(walkIns[7]),
                        parseInt(walkIns[8]),
                        parseInt(walkIns[9]),
                        parseInt(walkIns[10]),
                        parseInt(walkIns[11])
                      ]
            },
            {
                fillColor : "rgba(92,184,92,1)",
                strokeColor : "rgba(92,184,92,0.6)",
                label: "Walk by",
                data : [parseInt(walkBys[0]),
                        parseInt(walkBys[1]),
                        parseInt(walkBys[2]),
                        parseInt(walkBys[3]),
                        parseInt(walkBys[4]),
                        parseInt(walkBys[5]),
                        parseInt(walkBys[6]),
                        parseInt(walkBys[7]),
                        parseInt(walkBys[8]),
                        parseInt(walkBys[9]),
                        parseInt(walkBys[10]),
                        parseInt(walkBys[11])
                      ]
            }
            
        ]
    };

var hour = $("#hour").get(0).getContext("2d");
new Chart(hour).Bar(barHourData);




