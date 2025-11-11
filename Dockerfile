# Usa un'immagine base con Java
FROM openjdk:17-jdk-slim

# Imposta la directory di lavoro
WORKDIR /app

# Copia il file pom.xml e scarica le dipendenze
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
RUN ./mvnw dependency:go-offline -B

# Copia tutto il resto del progetto
COPY . .

# Compila il progetto
RUN ./mvnw clean package -DskipTests

# Avvia l'applicazione
CMD ["java", "-jar", "target/portfolio-0.0.1-SNAPSHOT.jar"]
