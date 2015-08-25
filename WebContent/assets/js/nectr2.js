
var cam1Data = {
		labels : ["12","13","14","15","16"],
		datasets : [
		    {
		    	fillColor : "rgba(92,184,92,0.5)",
		    	strokeColor : "rgba(92,184,92,0.6)",
                label: "Issue",
                data : [parseInt(31),
                        parseInt(42),
                        parseInt(27),
                        parseInt(14),
                        parseInt(6)
                      ]    	
		    },
		    {
                fillColor : "rgba(217,83,79,0.5)",
                strokeColor : "rgba(217,83,79,0.6)",
                label: "Use",
                data : [parseInt(10),
                        parseInt(15),
                        parseInt(6),
                        parseInt(2),
                        parseInt(2)
                      ]
            }	    
		    ]
};


var cam1 = $("#cam1").get(0).getContext("2d");
new Chart(cam1).Bar(cam1Data);

