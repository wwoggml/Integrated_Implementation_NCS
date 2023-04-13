function showContent(contentId) {
    const contents = document.querySelectorAll('.sid');
    for (const content of contents) {
        content.style.display = 'none';
    }
    const targetContent = document.getElementById(contentId);
    targetContent.style.display = 'block';
}

document.getElementById('sid_100_btn').addEventListener('click', () => {
    showContent('sid_100');
});

document.getElementById('sid_101_btn').addEventListener('click', () => {
    showContent('sid_101');
});

document.getElementById('sid_102_btn').addEventListener('click', () => {
    showContent('sid_102');
});

document.getElementById('sid_103_btn').addEventListener('click', () => {
    showContent('sid_103');
});

document.getElementById('sid_104_btn').addEventListener('click', () => {
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