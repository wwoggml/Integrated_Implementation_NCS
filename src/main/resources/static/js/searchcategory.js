let datetime = document.querySelectorAll(".datetime");
let summary = document.querySelectorAll(".summary");

for(let i = 0; i<datetime.length; i++) {
    let diff = new Date().getTime() - new Date(Date.parse(datetime[i].innerText)).getTime()


    let minute = 1000 * 60;
    let hour = minute * 60;
    let day = hour * 24;

    if (diff < minute) {
        datetime[i].innerText = "방금 전";
    } else if (diff < hour) {
        datetime[i].innerText = Math.floor(diff / minute) + '분 전';
    } else if (diff < day) {
        datetime[i].innerText = Math.floor(diff / hour) + '시간 전'
    } else {
        datetime[i].innerText = Math.floor(diff / day) + '일 전'
    }

    summary[i].innerText = summary[i].innerText.substring(0, 155) + "..."

    const urlParams = new URLSearchParams(window.location.search);
    const parameterValue = urlParams.get("sid");
    const parameterSortValue = urlParams.get("sort");

    const categoryColors = ["#FFCE44", "#0288D1", "#43A047", "#FFCE44", "#0288D1"];
    const categoryIds = ["sid_100", "sid_101", "sid_102", "sid_103", "sid_104"];

    const index = parameterValue - 100;

    if (index >= 0 && index <= 5) {
        const categoryElem = document.getElementById("category");
        categoryElem.style.backgroundColor = categoryColors[index];

        const sidElem = document.getElementById(categoryIds[index]);
        sidElem.style.borderBottom = "2px solid white";
    }


    if (parameterSortValue == 1) {
        document.getElementById("link1").style.color = "black";
    } else if(parameterSortValue == 2) {
        document.getElementById("link2").style.color = "black";
    }
}