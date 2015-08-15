
var walkInByDay = $("#walkInByDay").html();
var walkByByDay = $("#walkByByDay").html();
var timeByDay = $("#timeByDay").html();

var walkInByDays = String(walkInByDay).split(',');
var walkByByDays = String(walkByByDay).split(',');
var timeByDays = String(timeByDay).split(',');

var barDayData = {
    labels : [timeByDays[6],
              timeByDays[5],
              timeByDays[4],
              timeByDays[3],
              timeByDays[2],
              timeByDays[1],
              timeByDays[0],
              ],
    datasets : [
        {
            fillColor : "rgba(73,188,170,0.4)",
            strokeColor : "rgba(72,174,209,0.4)",
            data : [parseInt(walkInByDays[0]),
                    parseInt(walkInByDays[1]),
                    parseInt(walkInByDays[2]),
                    parseInt(walkInByDays[3]),
                    parseInt(walkInByDays[4]),
                    parseInt(walkInByDays[5]),
                    parseInt(walkInByDays[6]),
                  ]
        },
        {
            fillColor : "rgba(151,187,205,0.5)",
            strokeColor : "rgba(151,187,205,0.8)",
            data : [parseInt(walkByByDays[0]),
                    parseInt(walkByByDays[1]),
                    parseInt(walkByByDays[2]),
                    parseInt(walkByByDays[3]),
                    parseInt(walkByByDays[4]),
                    parseInt(walkByByDays[5]),
                    parseInt(walkByByDays[6]),
                  ]
        }
        
    ]
};

var day = $("#day").get(0).getContext("2d");
new Chart(day).Bar(barDayData);