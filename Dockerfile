# Use the official OpenJDK 11 base image
FROM openjdk:11

# Set the working directory to the root of the container
WORKDIR /

# Copy the compiled JAR file from the target directory to the container
COPY target/*.jar /app.jar

# Specify the command to run your application
CMD ["java", "-jar", "app.jar"]
