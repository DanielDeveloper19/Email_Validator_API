# Use an official OpenJDK image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy project files
COPY . .

# Build the project
RUN ./mvnw clean package -DskipTests

# Expose the app port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/API_VALIDATION-0.0.1-SNAPSHOT.jar"]