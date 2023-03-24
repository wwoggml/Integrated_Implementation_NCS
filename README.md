
## 크롤링 구현 샘플 코드 업로드


#### 1. elasticsearch 설치
1. 파일 다운로드 후 zip 압축 풀기
2. es.yml 파일이 있는 위치에서 docker-compose -f es.yml up -d 명령어 실행
3. docker ps로 kibana와 es가 설치되어 있는 지 확인 
4. 설치가 되었다면 10분 정도 기다린 후 Intellij에서 프로젝트 RUN 

-> Run -> localhost:8080 접속

<br>

#### 2. 샘플 코드 링크 이동
==> http://localhost:8080/ 


<br>

#### 3. https://news.naver.com/main/list.naver?mode=LS2D&mid=shm&sid1=105&sid2=731 에 있는 뉴스기사 크롤링한 예제
==> localhost:8080/news/search -> localhost:8080/news/add -> localhost:8080/news/result  

<br>

#### 4. Naver Api 구현 샘플 코드 (키워드 입력)
==> http://localhost:8080/api


<br>

#### 5. 텍스트 마이닝 구현 샘플 코드 
==> http://localhost:8080/mining


<br>


#### 6. elasticsearch 데이터 샘플 코드
==> Member.http, Member.json 파일 참고




<br>

com.ncs
* config
* controller : GET, POST 매핑 설정하는 패키지
* dto
* entity : JPA, ElasticSearch 설정하는 패키지
* impl : interface 구현하는 패키지
* repository
* service



<br>


