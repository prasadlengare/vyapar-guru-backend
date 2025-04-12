# Use an OpenJDK base image
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/vyapar-guru-app.jar /app/vyapar-guru-app.jar

# Expose the default application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/vyapar-guru-app.jar"]