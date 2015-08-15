var bar = $("#bar").html();
var walkIn = $("#walkIn").html();
var walkBy = $("#walkBy").html();

var walkIns = String(walkIn).split(',');
var walkBys = String(walkBy).split(',');



var barHourData = {
        labels : ["00:00","02:00","04:00","06:00","08:00","10:00","12:00","14:00","16:00","18:00","20:00","22:00"],
        datasets : [
            {
                fillColor : "rgba(73,188,170,0.4)",
                strokeColor : "rgba(72,174,209,0.4)",
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
                fillColor : "rgba(151,187,205,0.5)",
                strokeColor : "rgba(151,187,205,0.8)",
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
