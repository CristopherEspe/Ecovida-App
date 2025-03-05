FROM openjdk:17.0.2
LABEL maintainer="Cristopher Zambrano"
WORKDIR /app
COPY ./target/*.jar application.jar
COPY ./default-realm.json /app/
RUN mkdir -p /target
EXPOSE 8090
ENV spring.datasource.url=jdbc:postgresql://localhost:5432/msvc-auth
ENV keycloak.connectionsJpa.url=jdbc:postgresql://localhost:5432/msvc-auth
ENV KEYCLOAK_ADMIN=ecovida_supp
ENV KEYCLOAK_ADMIN_PASSWORD=O8r3tP8wi4f2
ENV KEYCLOAK_IMPORT_FILE=/app/default-realm.json
ENTRYPOINT ["java", "-jar", "application.jar"]