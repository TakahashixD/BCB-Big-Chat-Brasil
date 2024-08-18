FROM openjdk:17-alpine3.14
WORKDIR /opt/app
COPY --from=builder /opt/app/target/*.jar /opt/app/app.jar
CMD ["java","-jar","/opt/app/app.jar"]