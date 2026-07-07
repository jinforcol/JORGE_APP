# Stage 1: Build
FROM maven:3.9-eclipse-temurin-17 AS builder

WORKDIR /app

# Copiar archivos de configuración de Maven
COPY mvnw mvnw
COPY mvnw.cmd mvnw.cmd
COPY .mvn .mvn
COPY pom.xml pom.xml

# Copiar código fuente
COPY src src

# Compilar la aplicación
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copiar el JAR compilado del stage anterior
COPY --from=builder /app/target/aseguradora-0.0.1-SNAPSHOT.jar app.jar

# Exponer puerto
EXPOSE 9090

# Comando para ejecutar
ENTRYPOINT ["java", "-jar", "app.jar"]
