# Use official OpenJDK 17 image
FROM openjdk:17-jdk-slim

# Set working directory
#WORKDIR /app

# Copy JAR file into container (make sure this matches your jar output)
ADD target/book-review-api.jar book-review-api.jar

# Expose port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/book-review-api.jar"]
