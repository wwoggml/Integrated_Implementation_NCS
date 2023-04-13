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
}

const urlParams = new URLSearchParams(window.location.search);
const parameterValue = urlParams.get("sort");

if (parameterValue == 1) {
    document.getElementById("link1").style.color = "black";
} else if(parameterValue == 2) {
    document.getElementById("link2").style.color = "black";
} else if(parameterValue == 3) {
    document.getElementById("link3").style.color = "black";
} else {
    document.getElementById("link1").style.color = "black";
}