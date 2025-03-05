FROM openjdk:17.0.2
LABEL maintainer="Cristopher Zambrano"
WORKDIR /app
COPY ./target/*.jar application.jar
COPY ./default-realm.json /app/
RUN mkdir -p /target
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "application.jar"]