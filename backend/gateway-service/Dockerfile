FROM openjdk:17-jdk-slim
LABEL maintainer="Cristopher Zambrano"

WORKDIR /app
COPY target/gateway-service-0.0.1-SNAPSHOT.jar gateway-service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "gateway-service.jar"]