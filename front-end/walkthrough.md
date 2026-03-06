# Walkthrough: Full-Stack Connectivity

The DevBoard application is now a fully connected full-stack application! The frontend now fetches and renders data dynamically from the Spring Boot backend.

## 1. Backend API Layer
I have implemented a clean, best-practice REST API:
- **Endpoints**: 
  - `GET /api/tasks`: Fetches all tasks.
  - `GET /api/dashboard/stats`: Fetches live dashboard numbers (Total, Done, etc.).
- **Architecture**: Separated into `Controller`, `Service`, and `Model` layers for maintainability.

## 2. Dynamic Frontend
The frontend no longer uses hardcoded HTML for tasks:
- **`script.js`**: Now includes an `async` fetch engine that pulls data from the backend on load.
- **Dynamic Rendering**: Task cards are generated on-the-fly, supporting different priorities (High/Med/Low) and statuses (To Do/In Progress/Done).
- **Live Stats**: The top-bar statistics are now live and reflect the actual data in the backend.

## 3. How to Verify
1.  **Start the Backend**:
    ```bash
    cd backend
    ./mvnw spring-boot:run
    ```
    *(Note: Use `mvn` if you have it installed globally, or the wrapper if provided).*
2.  **Open the Frontend**:
    - Open `front-end/index.html` in your browser.
3.  **Observe**:
    - You should see the 4 mock tasks I created in the backend service.
    - The stats bar should show: **Total: 4**, **Done: 1**, **Active: 3**, **Urgent: 2**.

## Next Steps
- We can now implement **Real-time Updates** (using polling or WebSockets).
- Start the **Firebase Integration** to persist these tasks to a real database!
