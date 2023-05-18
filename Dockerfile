#ARG ELK_VERSIONg
#FROM docker.elastic.co/elasticsearch/elasticsearch:${ELK_VERSION}
#RUN elasticsearch-plugin install analysis-nori

FROM openjdk:11-jdk-slim
ADD build/libs/ncs-0.0.1-SNAPSHOT.jar /app.jar
ENV SPTRING_PROFIELS_ACTIVE=$SPRING_PROFILES_ACTIVE
ARG DEBIAN_FRONTEND=noninteractive
ENV TZ=Asia/Seoul

ENTRYPOINT ["java","-jar","-Dspring-boot.run.profiles=kimjaehee","/app.jar"]

