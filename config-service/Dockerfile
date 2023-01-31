FROM openjdk:8-jre-slim

ADD target/config-server-*.jar config-service.jar


ENTRYPOINT java -jar -Dspring.profiles.active=${ACTIVE_PROFILE} config-service.jar