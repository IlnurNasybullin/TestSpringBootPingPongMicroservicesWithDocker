FROM openjdk:11.0.14-jdk-slim-buster as build
COPY . /opt/pong-service
WORKDIR /opt/pong-service
RUN ./gradlew clean bootJar

FROM openjdk:11.0.14-jre-slim-buster
COPY --from=build '/opt/pong-service/build/libs' '/opt/pong-service'
WORKDIR /opt/pong-service

EXPOSE 9005

CMD ["java", "-jar", "./pong-service-0.0.1.jar"]