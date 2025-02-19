FROM maven:3.8.6-amazoncorretto-17 AS build
COPY pom.xml /build/
WORKDIR /build/
RUN mvn dependency:go-offline
COPY src /build/src
RUN mvn package -DskipTests

#Run stage
FROM openjdk:17-jdk-alpine
ARG JAR_FILE=/build/target/*.jar
COPY --from=build $JAR_FILE /opt/sensors.jar
ENTRYPOINT ["java","-jar","/opt/sensors.jar"]