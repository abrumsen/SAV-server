# Use a lightweight Java image
FROM openjdk:17-jdk-slim

# Set app directory
WORKDIR /app

# Copy the jar file
COPY sav/target/sav-0.0.1-SNAPSHOT.jar app.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
