FROM openjdk:11.0.14-jdk-slim-buster as build
COPY . /opt/ping-service
WORKDIR /opt/ping-service
RUN ./gradlew clean bootJar

FROM openjdk:11.0.14-jre-slim-buster
COPY --from=build '/opt/ping-service/build/libs' '/opt/ping-service'
WORKDIR /opt/ping-service

EXPOSE 9005

CMD ["java", "-jar", "./ping-service-0.0.1.jar"]