# DevBoard Architecture Overview

Visualizing the flow between the modern UI, the Spring Boot API, and the Firebase ecosystem.

```mermaid
graph TD
    subgraph "Frontend (GitHub Pages)"
        User((User))
        UI["Modern UI (HTML/CSS/JS)"]
        FB_Client["Firebase Client SDK (Auth)"]
    end

    subgraph "Backend (Spring Boot)"
        API["REST Controllers"]
        Security["Spring Security (JWT Filter)"]
        FB_Admin["Firebase Admin SDK"]
    end

    subgraph "Firebase Cloud"
        Auth["Firebase Authentication"]
        DB["Cloud Firestore"]
    end

    User --> UI
    UI --> FB_Client
    FB_Client -- "ID Token" --> Auth
    UI -- "Bearer JWT" --> Security
    Security -- "Verify" --> FB_Admin
    FB_Admin -- "Fetch Data" --> DB
    API -- "CRUD" --> DB
```

### Tech Stack Details
- **Frontend**: Vanilla HTML/CSS/JS (Lightweight & Portable).
- **Backend API**: **Spring Boot 3.x** (Java 17+).
- **Security**: **JWT-based** authentication using Firebase ID Tokens.
- **Database**: **Cloud Firestore** (NoSQL, Serverless).
- **Deployment**: GitHub Pages (Frontend) + Containerized Host (Backend).
