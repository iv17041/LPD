FROM openjdk:18-ea-11-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} SpringBootApp.jar
ENTRYPOINT ["java","-jar","SpringBootApp.jar"]