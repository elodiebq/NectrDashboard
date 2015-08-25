
var data2 = [
            {
                value: parseInt(33),
                color:"#F7464A",
                highlight: "#FF5A5E",
                label: "Loyal Customer"
            },
            {
                value: parseInt(14),
                color: "#46BFBD",
                highlight: "#5AD3D1",
                label: "Lukewarm Customer"
            },
            {
                value: parseInt(15),
                color: "#FDB45C",
                highlight: "#FFC870",
                label: "First Time Customer"
            }
        ];


var pie2 = $("#pie2").get(0).getContext("2d");
new Chart(pie2).Doughnut(data2);


