FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
RUN  chmod 777 -R / && \
    mvn clean install  && \
    mv "target/*.jar" "/apar.jar"
ENTRYPOINT ["java","-jar","/apar.jar"]
