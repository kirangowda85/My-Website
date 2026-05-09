# WorkShift Part-Time Job Platform

Production-oriented V1 MVP scaffold with a V2/V3 enterprise roadmap for a part-time job platform connecting employers with temporary workers.

## Stack

- Frontend: Next.js App Router, React, Tailwind CSS
- Backend: Java Spring Boot, Spring Security, JWT, JPA/Hibernate
- Database: PostgreSQL, designed for Neon
- Storage: Cloudinary
- Realtime readiness: Spring WebSocket plus optional Redis
- Deployment targets: Vercel frontend, Railway/Render backend, Neon PostgreSQL

## Current Scope

- Modular Spring Boot backend for auth, users, jobs, applications, chat, notifications, moderation, audit, and shared core utilities.
- Next.js frontend with home, auth, jobs, worker dashboard, employer dashboard, and admin dashboard pages.
- Architecture, API, database schema, deployment, and phased implementation documents under `docs/`.
- Docker-ready backend and CI workflow foundation.

## Local Development

Backend:

```bash
cd backend
mvn spring-boot:run
```

Frontend:

```bash
cd frontend
npm install
npm run dev
```

## Environment Variables

Copy the examples and fill in production values:

- `backend/.env.example`
- `frontend/.env.example`

## Documentation

- [Architecture](docs/ARCHITECTURE.md)
- [Database Schema](docs/database/schema.sql)
- [API Design](docs/API.md)
- [Development Phases](docs/PHASES.md)
- [Deployment](docs/DEPLOYMENT.md)
