FROM openjdk:8-jdk-alpine

ADD maven/*jar docker-example.jar

ENTRYPOINT ["java", "-jar", "docker-example.jar"]