
var cam2Data = {
		labels : ["12","13","14","15","16","17","18"],
		datasets : [
		    {
		    	fillColor : "rgba(92,184,92,0.5)",
		    	strokeColor : "rgba(92,184,92,0.6)",
                label: "Issue",
                data : [parseInt(45),
                        parseInt(38),
                        parseInt(31),
                        parseInt(12),
                        parseInt(13),
                        parseInt(29),
                        parseInt(42)
                      ]    	
		    },
		    {
                fillColor : "rgba(217,83,79,0.5)",
                strokeColor : "rgba(217,83,79,0.6)",
                label: "Use",
                data : [parseInt(17),
                        parseInt(13),
                        parseInt(12),
                        parseInt(3),
                        parseInt(1),
                        parseInt(3),
                        parseInt(13)
                      ]
            }	    
		    ]
};


var cam2 = $("#cam2").get(0).getContext("2d");
new Chart(cam2).Bar(cam2Data);

