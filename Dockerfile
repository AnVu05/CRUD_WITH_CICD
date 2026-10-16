# Stage 1: Build the Spring Boot application using Maven Wrapper
FROM eclipse-temurin:25-jdk AS build
WORKDIR /app

# Copy project files
COPY . .

# Grant execute permission and build JAR file
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

# Stage 2: Minimal runtime image
FROM eclipse-temurin:25-jre
WORKDIR /app

# Copy the built jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Render supplies $PORT environment variable dynamically
ENV PORT=8080
ENV SPRING_PROFILES_ACTIVE=prod
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
