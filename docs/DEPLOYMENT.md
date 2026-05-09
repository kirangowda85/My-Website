# Deployment

## Frontend: Vercel

Build command:

```bash
npm run build
```

Root directory:

```text
frontend
```

Environment variables:

- `NEXT_PUBLIC_API_BASE_URL`
- `NEXT_PUBLIC_WS_URL`

## Backend: Railway Or Render

Build command:

```bash
mvn clean package -DskipTests
```

Start command:

```bash
java -jar target/jobboard-0.0.1-SNAPSHOT.jar
```

Environment variables:

- `PORT`
- `DATABASE_URL`
- `DATABASE_USERNAME`
- `DATABASE_PASSWORD`
- `JWT_SECRET`
- `CLOUDINARY_CLOUD_NAME`
- `CLOUDINARY_API_KEY`
- `CLOUDINARY_API_SECRET`
- `REDIS_HOST`
- `REDIS_PORT`
- `REDIS_PASSWORD`
- `FRONTEND_URL`

## Database: Neon PostgreSQL

Run `docs/database/schema.sql` as the baseline schema for managed migrations. In production, replace `spring.jpa.hibernate.ddl-auto=update` with migration-managed schema changes using Flyway or Liquibase.

## CORS

Development may use broad origins. Production should restrict CORS to the Vercel domain.
