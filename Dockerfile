# Etapa 1: Construcción
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Contenedor final
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar reserva.jar
ENTRYPOINT ["java", "-jar", "reserva.jar"]
