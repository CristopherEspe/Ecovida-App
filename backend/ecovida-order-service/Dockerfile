FROM openjdk:17.0.2
LABEL maintainer="Cristopher Zambrano"

WORKDIR /app

COPY ./target/ecovida-order-service-0.0.1-SNAPSHOT.jar application.jar

EXPOSE 8084

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/orders_db
ENV SPRING_DATASOURCE_USERNAME=ecovida-postgres
ENV SPRING_DATASOURCE_PASSWORD=A2QjQxdSIs9rNJ8YbXewmraEcqMz45BDDSzVFjWzkw5XjxP9Q1

ENTRYPOINT ["java", "-jar", "application.jar"]
