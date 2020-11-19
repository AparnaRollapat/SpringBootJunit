FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} apar.jar
ENTRYPOINT ["java","-jar","/apar.jar"]