# Stage 1: build (Maven + JDK 21)
FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn -B clean package -DskipTests

# Stage 2: runtime (JDK 21)
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app

ARG JAR_FILE=/app/target/*.jar
COPY --from=builder ${JAR_FILE} app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]