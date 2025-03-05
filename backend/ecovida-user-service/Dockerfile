FROM openjdk:17.0.2
LABEL maintainer="Cristopher Zambrano"

WORKDIR /app

COPY ./target/*.jar application.jar

EXPOSE 8081


# Comando para ejecutar el microservicio
ENTRYPOINT ["java", "-jar", "application.jar"]
