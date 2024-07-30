FROM openjdk:17-alpine3.14 as builder 
WORKDIR /opt/app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean package -DskipTests

FROM openjdk:17-alpine3.14
WORKDIR /opt/app
COPY --from=builder /opt/app/target/*.jar /opt/app/app.jar
CMD ["java","-jar","/opt/app/app.jar"]