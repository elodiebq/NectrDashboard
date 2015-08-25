//var bar = $("#bar").html();

var loyalCustomer = $("#loyalCustomer").html();


var loyalCustomers = String(loyalCustomer).split(',');

var data = [
            {
                value: parseInt(loyalCustomers[0]),
                color:"#F7464A",
                highlight: "#FF5A5E",
                label: "Loyal Customer"
            },
            {
                value: parseInt(loyalCustomers[1]),
                color: "#46BFBD",
                highlight: "#5AD3D1",
                label: "Lukewarm Customer"
            },
            {
                value: parseInt(loyalCustomers[2]),
                color: "#FDB45C",
                highlight: "#FFC870",
                label: "First Time Customer"
            }
        ];


var hourPie = $("#hourPie").get(0).getContext("2d");
new Chart(hourPie).Doughnut(data);


