# 구현 현황

**스포츠 카테고리 추가**

<br>

#### 1. elasticsearch, kibana, logstash 설치

* docker-compose.yml 파일이 있는 위치에서 `docker-compose up -d` 명령어 실행
* `docker ps` 로 elasticsearch, kibana, logstash가 모두 실행 중인지 확인한 후 프로젝트 RUN

<br>


<br>

#### 2. 데이터 추가하기
==> localhost:8080/news/search 

정치 : https://news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=100

경제 : https://news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=101

생활/문화 : https://news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=103

IT/과학 : https://news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=105

스포츠 
* https://sports.news.naver.com/kbaseball/index
* https://sports.news.naver.com/kfootball/index
* https://sports.news.naver.com/basketball/index
* https://sports.news.naver.com/volleyball/index
* https://sports.news.naver.com/golf/index
* https://sports.news.naver.com/general/index


<br>

<br>


#### 3. 메인 페이지 이동
==> http://localhost:8080

* 메인 페이지에서 카테고리로 이동 가능
* 키워드 검색 가능
* 카테고리 추기

