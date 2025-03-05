FROM openjdk:17-jdk-slim
LABEL maintainer="Cristopher Zambrano"

WORKDIR /app

COPY ./target/*.jar application.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "application.jar"]