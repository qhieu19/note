# DevBoard Project Roadmap

```mermaid
gantt
    title Project Timeline
    dateFormat  YYYY-MM-DD
    section Phase 1: Foundation
    Spring Boot Setup           :active, p1, 2026-03-06, 3d
    Firebase Admin SDK         : p2, after p1, 2d
    section Phase 2: Auth
    Security & JWT Filter      : p3, after p2, 5d
    Frontend Login Integration : p4, after p3, 3d
    section Phase 3: Core API
    Firestore Repository       : p5, after p4, 5d
    REST Controllers           : p6, after p5, 5d
    section Phase 4: UI Expansion
    Real-time Updates          : p7, after p6, 7d
    section Phase 5: Deployment
    CI/CD Interconnection      : p8, after p7, 3d
```

This roadmap outlines the path from the current static blueprint to a full-stack application with a Spring Boot backend and Firebase integration.

## Phase 1: Foundation & Backend Setup
- [ ] **Spring Boot Initializer**: Set up a Maven/Gradle project in the `/backend` folder.
- [ ] **Dependencies**: Include `spring-boot-starter-web`, `spring-boot-starter-security`, and `google-cloud-firestore`.
- [ ] **Firebase Admin SDK**: Generate and securely store the service account key.

## Phase 2: Authentication & Security
- [ ] **Firebase Auth**: Implement token verification middleware in Spring Security.
- [ ] **User Context**: Link Firebase UID to application user profiles.
- [ ] **Frontend Login**: Connect the UI login flow to Firebase Client SDK.

## Phase 3: Core API (CRUD)
- [ ] **Firestore Service**: Create a generic repository for task and project persistence.
- [ ] **REST Controllers**: Implement endpoints for `GET /tasks`, `POST /tasks`, `PUT /tasks`, etc.
- [ ] **Frontend Sync**: Replace static JS data with `fetch()` calls to the backend.

## Phase 4: UI Expansion & Real-time
- [ ] **Advanced Filtering**: Implement server-side search and filtering.
- [ ] **Real-time Updates**: Use Firestore listeners or WebSockets for instant UI updates.
- [ ] **Personalization**: User settings, dark mode persistence, and profile management.

## Phase 5: Deployment & CI/CD
- [ ] **Production Build**: Dockerize the Spring Boot application.
- [ ] **Render/Heroku/Railway**: Deploy the backend.
- [ ] **Interconnected CI/CD**: Ensure the frontend deployment links to the live API.
