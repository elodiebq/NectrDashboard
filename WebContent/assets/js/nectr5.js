
var data = [
            {
                value: parseInt(17),
                color:"#F7464A",
                highlight: "#FF5A5E",
                label: "Loyal Customer"
            },
            {
                value: parseInt(8),
                color: "#46BFBD",
                highlight: "#5AD3D1",
                label: "Lukewarm Customer"
            },
            {
                value: parseInt(10),
                color: "#FDB45C",
                highlight: "#FFC870",
                label: "First Time Customer"
            }
        ];


var pie1 = $("#pie1").get(0).getContext("2d");
new Chart(pie1).Doughnut(data);


