FROM openjdk:17-alpine

EXPOSE 8283

COPY ./target/authentication-service-*.jar authentication-service.jar

ENTRYPOINT ["java", "-jar", "authentication-service.jar"]