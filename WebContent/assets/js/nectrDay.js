
var walkInByDay = $("#defaultWalkInView").html();
var walkByByDay = $("#defaultWalkByView").html();
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
        	 fillColor : "rgba(217,83,79,1)",
             strokeColor : "rgba(217,83,79,0.6)",
            data : [parseInt(walkInByDays[6]),
                    parseInt(walkInByDays[5]),
                    parseInt(walkInByDays[4]),
                    parseInt(walkInByDays[3]),
                    parseInt(walkInByDays[2]),
                    parseInt(walkInByDays[1]),
                    parseInt(walkInByDays[0]),
                  ]
        },
        {
        	fillColor : "rgba(92,184,92,1)",
            strokeColor : "rgba(92,184,92,0.6)",
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