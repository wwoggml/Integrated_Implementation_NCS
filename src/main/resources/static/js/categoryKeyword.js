const urlParams = new URLSearchParams(window.location.search);
const parameterValue = urlParams.get("sid");

const categoryColors = ["#FFCE44", "#0288D1", "#43A047", "#FFCE44", "#0288D1"];
const categoryIds = ["sid_100", "sid_101", "sid_102", "sid_103", "sid_104"];

const index = parameterValue - 100;

if (index >= 0 && index <= 5) {
    const categoryElem = document.getElementById("category");
    categoryElem.style.backgroundColor = categoryColors[index];

    const sidElem = document.getElementById(categoryIds[index]);
    sidElem.style.borderBottom = "2px solid white";
}

let str = document.getElementById("jsonData").value
let data = JSON.parse(str);

const pie = document.getElementById('primary-outlined');
const bar = document.getElementById('warning-outlined');
const tag = document.getElementById('success-outlined');

chart = anychart.pie(data); // Create a new pie chart
chart.container("container");
chart.draw();

tag.addEventListener("click", () => {
    if(tag.checked) {
        document.getElementById('container').innerHTML = "";

        chart = anychart.tagCloud(data);
        chart.angles([0]);
        chart.container("container");
        chart.draw();

    }
});


pie.addEventListener("click", () => {


    if (pie.checked) {
        document.getElementById('container').innerHTML = ""

        chart.dispose(); // Clear the container
        chart = anychart.pie(data); // Create a new pie chart
        chart.container("container");
        chart.draw();
    }
});

bar.addEventListener("click", () => {
    if(bar.checked) {
        document.getElementById('container').innerHTML = ""

        chart.dispose();
        chart = anychart.bar(data);
        chart.container("container");
        chart.draw();
    }
});


