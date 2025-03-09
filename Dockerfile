# Official OpenJDK 21 image
FROM openjdk:21-jdk-slim

# Working directory inside the container
WORKDIR /app

# Copy the built JAR file into the container
COPY target/managerShop-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Ensure the app waits for PostgreSQL before starting
CMD ["sh", "-c", "echo 'Waiting for PostgreSQL...'; sleep 10; java -jar app.jar"]
