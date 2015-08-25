
var cam3Data = {
		labels : ["14","15","16","17","18"],
		datasets : [
		    {
		    	fillColor : "rgba(92,184,92,0.5)",
		    	strokeColor : "rgba(92,184,92,0.6)",
                label: "Issue",
                data : [parseInt(6),
                        parseInt(10),
                        parseInt(15),
                        parseInt(21),
                        parseInt(33)
                      ]    	
		    },
		    {
                fillColor : "rgba(217,83,79,0.5)",
                strokeColor : "rgba(217,83,79,0.6)",
                label: "Use",
                data : [parseInt(0),
                        parseInt(2),
                        parseInt(5),
                        parseInt(7),
                        parseInt(15)
                      ]
            }	    
		    ]
};


var cam3 = $("#cam3").get(0).getContext("2d");
new Chart(cam3).Bar(cam3Data);

