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
let flexbool = false;

function startRankingRotation() {
    intervalId = setInterval(function() {
        const rows = document.querySelectorAll('.realtime-search .row');
        const top5 = Array.from(rows).slice(0, 5);
        const bottom5 = Array.from(rows).slice(5, 10);
        if (!flexbool) {
            if (top5[0].style.display === 'none') {
                top5.forEach(row => row.style.display = 'flex');
                bottom5.forEach(row => row.style.display = 'none');
            } else {
                top5.forEach(row => row.style.display = 'none');
                bottom5.forEach(row => row.style.display = 'flex');
            }
        }
    }, 5000);
}

function stopRankingRotation() {
    clearInterval(intervalId);
}


const showTopButton = document.querySelector('#show-top');
showTopButton.addEventListener('click', function() {
    flexbool = false;

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

    flexbool = true;

    stopRankingRotation();
    document.querySelector('.realtime-search').style.height = "440px";
    const rows = document.querySelectorAll('.realtime-search .row');
    rows.forEach(row => row.style.display = 'flex');

    showTopButton.style.display = 'block';
    showTopButton.style.margin = "0 auto;"
    this.style.display = 'none';
});

$(document).ready(function() {
    loadTopKeywords();
    startRankingRotation();
    setInterval(loadTopKeywords, 10000);
});

function loadTopKeywords() {
    $.ajax({
        url: "/topkeywords",
        type: "GET",
        dataType: "json",
        success: function (data) {
            $("#topkeywords-container").empty();
            $.each(data, function (index, keyword) {
                var row = "<div class='row mt-3'>" +
                    "<div class='col-1'>" + (index + 1) + "</div>" +
                    "<div class='col-10'><small><a href='/search?sort=1&keyword=" + encodeURIComponent(keyword.keyword) + "'>" + keyword.keyword + "</a></small></div>"
                "</div>";
                $("#topkeywords-container").append(row);
            });
            if (flexbool) {
                const rows = document.querySelectorAll('.realtime-search .row');
                rows.forEach(row => row.style.display = 'flex');
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Error loading top keywords: " + errorThrown);
        }
    });


}