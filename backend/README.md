# DevBoard Backend

This is the backend service for the DevBoard Task Management application, built with Spring Boot 3.x and Java 17.

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven (or an IDE like IntelliJ IDEA / VS Code with Java extension)

### Running the Application
From the `backend` directory, you can run the application using:
```bash
mvn spring-boot:run
```

## Project Structure
- `src/main/java/com/devboard`: Java source files.
- `src/main/resources`: Configuration and static resources.
- `pom.xml`: Maven project configuration.

## Deployment to Render 🚀

This project is configured for deployment on **Render** using Docker.

### Render Configuration Settings:
When creating a new **Web Service** on Render, use these settings:

1.  **Repository**: Connect your GitHub repository.
2.  **Root Directory**: `backend`
    - *This ensures Render only watches the backend folder for changes.*
3.  **Runtime**: `Docker`
4.  **Dockerfile Path**: `Dockerfile` 
    - *Since the Root Directory is set to `backend`, Render looks for the Dockerfile inside it.*
5.  **Environment Variables**:
    - `PORT`: `8080` (Render maps this automatically, but good to have).

### Manual Build (Local Testing)
To test the Docker build locally:
```bash
cd backend
docker build -t devboard-backend .
docker run -p 8080:8080 devboard-backend
```
