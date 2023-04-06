## git

git pull origin main - 다운받는 명령어

[올리는 명령어]

git add .

git commit -m "수정한 내용 간략하게 적기"

git push origin main : 업로드

[merge 떴을 경우]

git add .

git commit -m "aaaa"

git pull origin main

## 도커

[실행]

docker-compose.yml 파일에서만 실행하기

docker-compose up -d : 최초만 사용 한번 적용한 뒤로는 필요 없음.

docker ps : 도커 컨테이너 실행상태 확인

docker ps -a : all 모든 상태 보여줌.

docker restart 컨테이너 이름 : 컨테이너 이름에 해당하는 걸 다시 시작함.

docker logs -f 컨테이너 이름 : 컨테이너 로그 실행 명령어.

docker-compose down : 컨테이너 다 내릴 때 사용하는 명령어.