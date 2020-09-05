FROM openjdk:8

COPY target/crypto-progress-tracking-1.0-SNAPSHOT.jar /apps/application.jar

WORKDIR /apps

CMD java -jar application.jar


