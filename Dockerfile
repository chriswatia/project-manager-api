# Pull image
FROM openjdk:17-jdk-slim

# Set working DIR
WORKDIR /app

# Copy compiled Jar File
COPY target/*.jar app.jar

# Expose project port
EXPOSE 8080

# Run the api
CMD ["java", "-jar", "app.jar"]
