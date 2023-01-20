FROM openjdk:17-jre-slim

LABEL maintainer="transfervalue.com"

ADD target/transfer-0.0.1-SNAPSHOT.jar springboot-transfer-value.jar

ENTRYPOINT ["java", "-jar", "springboot-transfer-value.jar"]

EXPOSE 8080
