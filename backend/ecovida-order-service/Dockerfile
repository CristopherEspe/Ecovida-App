FROM openjdk:17.0.2
LABEL maintainer="Cristopher Zambrano"

WORKDIR /app

COPY ./target/ecovida-order-service-0.0.1-SNAPSHOT.jar application.jar

EXPOSE 8084

ENTRYPOINT ["java", "-jar", "application.jar"]
