
var data3 = [
            {
                value: parseInt(10),
                color:"#F7464A",
                highlight: "#FF5A5E",
                label: "Loyal Customer"
            },
            {
                value: parseInt(11),
                color: "#46BFBD",
                highlight: "#5AD3D1",
                label: "Lukewarm Customer"
            },
            {
                value: parseInt(8),
                color: "#FDB45C",
                highlight: "#FFC870",
                label: "First Time Customer"
            }
        ];


var pie3 = $("#pie3").get(0).getContext("2d");
new Chart(pie3).Doughnut(data3);


