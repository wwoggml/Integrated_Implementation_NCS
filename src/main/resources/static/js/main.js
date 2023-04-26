

function showContent(contentId) {
    const contents = document.querySelectorAll('.sid');
    for (const content of contents) {
        content.style.display = 'none';
    }
    const targetContent = document.getElementById(contentId);
    targetContent.style.display = 'block';
}

function colorReset() {
    const bcontents = document.querySelectorAll('b');
    const pathcontents = document.querySelectorAll('path');
    for (const content of bcontents) {
        content.style.color = 'black';
    }
    for (const content of pathcontents) {
        content.style.color = 'black';
    }
}

document.getElementById('sid_100_btn').addEventListener('click', () => {
    colorReset();
    this.document.querySelectorAll("b")[0].style.color="#0288D1"
    this.document.querySelectorAll("path")[0].style.color="#0288D1"
    showContent('sid_100');
});

document.getElementById('sid_101_btn').addEventListener('click', () => {
    colorReset();
    this.document.querySelectorAll("b")[1].style.color="#FFCE44"
    this.document.querySelectorAll("path")[1].style.color="#FFCE44"
    showContent('sid_101');
});

document.getElementById('sid_102_btn').addEventListener('click', () => {
    colorReset();
    this.document.querySelectorAll("b")[2].style.color="#43A047"
    this.document.querySelectorAll("path")[2].style.color="#43A047"
    showContent('sid_102');
});

document.getElementById('sid_103_btn').addEventListener('click', () => {
    colorReset();
    this.document.querySelectorAll("b")[3].style.color="#0288D1"
    this.document.querySelectorAll("path")[3].style.color="#0288D1"
    showContent('sid_103');
});

document.getElementById('sid_104_btn').addEventListener('click', () => {
    colorReset();
    this.document.querySelectorAll("b")[4].style.color="#FFCE44"
    this.document.querySelectorAll("path")[4].style.color="#FFCE44"
    showContent('sid_104');
});



let intervalId;

function startRankingRotation() {
    intervalId = setInterval(function() {
        const rows = document.querySelectorAll('.realtime-search .row');
        const top5 = Array.from(rows).slice(0, 5);
        const bottom5 = Array.from(rows).slice(5, 10);

        if (top5[0].style.display === 'none') {
            top5.forEach(row => row.style.display = 'flex');
            bottom5.forEach(row => row.style.display = 'none');
        } else {
            top5.forEach(row => row.style.display = 'none');
            bottom5.forEach(row => row.style.display = 'flex');
        }
    }, 5000);
}

function stopRankingRotation() {
    clearInterval(intervalId);
}

// 기본적으로 5초 간격으로 순위를 바꿔주는 기능을 실행합니다.
startRankingRotation();

const showTopButton = document.querySelector('#show-top');
showTopButton.addEventListener('click', function() {
    // 순위를 바꾸는 기능을 다시 실행합니다.
    startRankingRotation();
    document.querySelector('.realtime-search').style.height = "230px";

    const rows = document.querySelectorAll('.realtime-search .row');
    const top5 = Array.from(rows).slice(0, 5);
    const bottom5 = Array.from(rows).slice(5, 10);

    top5.forEach(row => row.style.display = 'flex');
    bottom5.forEach(row => row.style.display = 'none');

    document.querySelector('#show-all').style.display = 'flex';
    this.style.display = 'none';
});

const showAllButton = document.querySelector('#show-all');
showAllButton.addEventListener('click', function() {
    // 순위를 바꾸는 기능을 중지합니다.
    stopRankingRotation();

    document.querySelector('.realtime-search').style.height = "440px";
    const rows = document.querySelectorAll('.realtime-search .row');
    rows.forEach(row => row.style.display = 'flex');

    showTopButton.style.display = 'block';
    showTopButton.style.margin = "0 auto;"
    this.style.display = 'none';
});

// anychart.onDocumentReady(function () {
//     var data = [
//         {
//             "x": "IT",
//             "value": 590000000,
//             category: "Sino-Tibetan"
//         },
//         {
//             "x": "Python",
//             "value": 283000000,
//             category: "Indo-European"
//         },
//         {
//             "x": "소프트웨어",
//             "value": 544000000,
//             category: "Indo-European"
//         },
//         {
//             "x": "JAVA",
//             "value": 527000000,
//             category: "Indo-European"
//         }, {
//             "x": "C++",
//             "value": 422000000,
//             category: "Afro-Asiatic"
//         }, {
//             "x": "HTML",
//             "value": 620000000,
//             category: "Afro-Asiatic"
//         }
//     ];
//     var chart = anychart.tagCloud(data);
//     chart.angles([0]);
//     chart.container("container");
//     // chart.getCredits().setEnabled(false);
//     chart.draw();
//     chart.getCredits().setEnabled(false);
// });
// var data = [
//     {x: "learning", value: 80},
//     {x: "includes", value: 56},
//     {x: "lists", value: 44},
//     {x: "meaning", value: 40},
//     {x: "useful", value: 36},
//     {x: "different", value: 32}
// ];
//
// // create a chart and set the data
// chart = anychart.tagCloud(data);
//
// chart.mode("cards");
//
// // set the container id
// chart.container("container");
//
// // initiate drawing the chart
// chart.draw();

// anychart.onDocumentReady(function () {
//
//     // create data
//     var data = "Tyger, tyger, burning bright " +
//         "In the forests of the night, " +
//         "What immortal hand or eye " +
//         "Could frame thy fearful symmetry? " +
//         "In what distant deeps or skies " +
//         "Burnt the fire of thine eyes? " +
//         "On what wings dare he aspire? " +
//         "What the hand dare seize the fire? " +
//         "And what shoulder and what art " +
//         "Could twist the sinews of thy heart? " +
//         "And, when thy heart began to beat, " +
//         "What dread hand and what dread feet? " +
//         "What the hammer? what the chain? " +
//         "In what furnace was thy brain? " +
//         "What the anvil? what dread grasp " +
//         "Dare its deadly terrors clasp? " +
//         "When the stars threw down their spears, " +
//         "And watered heaven with their tears, " +
//         "Did He smile His work to see? " +
//         "Did He who made the lamb make thee? " +
//         "Tyger, tyger, burning bright " +
//         "In the forests of the night, " +
//         "What immortal hand or eye " +
//         "Dare frame thy fearful symmetry? ";
//
//     // create a chart
//     chart = anychart.tagCloud();
//
//     // configure angles
//     chart.angles([0]);
//
//     // set the parsing mode
//     chart.data(data, {mode: "by-word"});
//
//     // set the chart title
//     chart.listen("chartDraw", function () {
//         chart.title("Tag Cloud Chart: Mode = " +  chart.mode());
//     });
//
//     // set the container id
//     chart.container("container");
//
//     // initiate drawing the chart
//     chart.draw();
// });
//
// // set the mode of the tag cloud
// function tagCloudMode(mode) {
//     chart.mode(mode);
//}
