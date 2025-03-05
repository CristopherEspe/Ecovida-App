FROM openjdk:17.0.2
LABEL maintainer="Cristopher Zambrano"

WORKDIR /app

COPY ./target/*.jar application.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "application.jar"]