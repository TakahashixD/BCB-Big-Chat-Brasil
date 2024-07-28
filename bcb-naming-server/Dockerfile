FROM openjdk:17-alpine3.14
ARG  JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
CMD ["java","-jar","/app.jar"]