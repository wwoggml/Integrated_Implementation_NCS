# 통합구현_NCS

<br>

```
💡 RNSE(Real time News Search Engine) 이란?
   사용자로부터 입력받은 키워드를 기반으로 뉴스를 보여주고, 실시간 검색 순위를 제공하는 실시간 뉴스 검색 엔진이다.
```

<br>

### 완성 사이트
==> http://rnse.developlog.site:13380


<br>


<br>


### 설치하는 방법

#### 1. PC에 Mysql, Docker 가 설치되어 있어야 한다.
* 데이터베이스명과, 사용자 아이디, 비밀번호는 appplication.yml에서 수정할 수 있다.

<br>


#### 2. elasticsearch, kibana, logstash 설치

* docker-compose.yml 파일이 있는 위치에서 `docker-compose up -d` 명령어 실행
* `docker ps` 로 elasticsearch, kibana, logstash가 모두 실행 중인지 확인한 후 프로젝트 RUN

<br>


<br>

#### 3. 데이터 추가하기
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


#### 4. 메인 페이지 이동
==> http://localhost:8080


