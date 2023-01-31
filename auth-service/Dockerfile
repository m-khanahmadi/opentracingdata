FROM openjdk:8-jre-slim

ADD target/*.jar auth-server.jar


ENTRYPOINT java -jar -Dspring.profiles.active=${ACTIVE_PROFILE} auth-service.jar