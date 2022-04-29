
let sevenDay, valueOfTotal
$.ajax({
    url: '/api/web_shop/admin/revenue',
    type: 'GET',
    dataType: "json",
    success: displayAll
});

const options = {
    chart: {
        type: "line",
        height: "280px",
        fontFamily: "Nunito",
        toolbar: {
            show: true,
        },
    },
    stroke: {
        curve: "straight",
        width: 6,
        colors: ["#6E00FF"],
    },
    markers: {
        size: 7,
        colors: ["#6E00FF"],
    },
    grid: {
        strokeDashArray: 10,
    },
    series: [
        {
            name: "Doanh thu",
            data: [8,12,56,21,43,56,12],
        },
    ],
    xaxis: {
        categories: ['28-04','27-04','26-04','25-04','24-04','23-04','22-04'],
    },
};
// Radial chart

var optionsRadial = {
    series: [44, 55, 67, 83],
    chart: {
        height: 350,
        type: "radialBar",
    },
    plotOptions: {
        radialBar: {
            dataLabels: {
                name: {
                    fontSize: "22px",
                },
                value: {
                    fontSize: "16px",
                },
                total: {
                    show: true,
                    label: "Tổng",
                    formatter: function (w) {
                        // By default this function returns the average of all series. The below is just an example to show the use of custom formatter function
                        return 249;
                    },
                },
            },
        },
    },
    labels: ["Đang xử lý", "Đã tiếp nhận", "Đang giao", "Đã giao"],
};
const chartRadial = new ApexCharts(document.querySelector("#emailChart"), optionsRadial);
chartRadial.render();

function displayAll(data){
    // alert(data.date)
    // console.log(data)
    sevenDay = data.date
    valueOfTotal = data.value
}

function statisFunction(){
    const series = [
        {
            name: "Doanh thu",
            data: valueOfTotal,
        },
    ]
    options.xaxis.categories = sevenDay;
    options.series = series;
    const chart = new ApexCharts(document.querySelector("#chart"), options);
    chart.render();
}