FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar first-application.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/first-application.jar"]