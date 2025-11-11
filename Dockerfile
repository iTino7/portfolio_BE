# Stage 1: build (usa Maven + JDK 17)
FROM maven:3.9.9-eclipse-temurin-17 AS builder
WORKDIR /app

# Copia solo i descriptor per sfruttare la cache
COPY pom.xml .
COPY src ./src

# Compila l'app (salta i test se vuoi velocizzare)
RUN mvn -B clean package -DskipTests

# Stage 2: runtime (JDK19 leggero per eseguire, senza tool di build)
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# Copia il JAR prodotto dal builder (modifica il path se necessario)
# Se Maven genera più jar, imposta JAR_FILE con il nome corretto
ARG JAR_FILE=/app/target/*.jar
COPY --from=builder ${JAR_FILE} app.jar

# Espone la porta della tua app (aggiorna se usi una porta diversa)
EXPOSE 8080

# Comando di avvio
ENTRYPOINT ["java", "-jar", "app.jar"]