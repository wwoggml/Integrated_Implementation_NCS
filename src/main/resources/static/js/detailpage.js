let datetime = document.querySelector('.datetime');

let dateChangeStr;
dateChangeStr = datetime.innerText.split('T')[0].replaceAll('-','.') + " ";
if(Number(datetime.innerText.split('T')[1].split('+')[0].split(':')[0]) > 12) {
    dateChangeStr += " 오후  " + (Number(datetime.innerText.split('T')[1].split('+')[0].split(':')[0]) - 12) + '시' + datetime.innerText.split('T')[1].split('+')[0].split(':')[1] + '분'
} else if(Number(datetime.innerText.split('T')[1].split('+')[0].split(':')[0]) == 12) {
    dateChangeStr += " 오후  " + (Number(datetime.innerText.split('T')[1].split('+')[0].split(':')[0])) + '시' + datetime.innerText.split('T')[1].split('+')[0].split(':')[1] + '분'
} else {
    dateChangeStr += " 오전  " + (Number(datetime.innerText.split('T')[1].split('+')[0].split(':')[0])) + '시' + datetime.innerText.split('T')[1].split('+')[0].split(':')[1] + '분'
}

datetime.innerText = dateChangeStr;

let news_text = document.querySelector('.newsMain');
let news_textStr;
let news_textSize = news_text.innerText.split('.').length;
news_text.innerText.split('.')
news_textStr = news_text.innerText.split('.')[0] + "<br><br>"  + news_text.innerText.split('.').slice(1,news_textSize/4) + "<br><br>" + news_text.innerText.split('.').slice(news_textSize/4+1,news_textSize/2) + "<br><br>" + news_text.innerText.split('.').slice(news_textSize/2+1,news_textSize - 1)
news_text.innerHTML = news_textStr;

