# API Design

All REST APIs use a shared response envelope:

```json
{
  "success": true,
  "message": "OK",
  "data": {}
}
```

## Authentication

### POST `/api/auth/register`

Request:

```json
{
  "email": "worker@example.com",
  "password": "StrongPass123",
  "role": "WORKER"
}
```

Validation:

- `email`: valid email, unique.
- `password`: minimum 8 characters.
- `role`: `WORKER`, `EMPLOYER`, or `ADMIN` for seeded/internal use.

### POST `/api/auth/login`

Request:

```json
{
  "email": "worker@example.com",
  "password": "StrongPass123"
}
```

## Jobs

### GET `/api/jobs?page=0&size=10&city=Bengaluru&category=EVENT&minSalary=800&maxSalary=2500`

Returns paginated open jobs.

### POST `/api/jobs`

Employer-only.

Request:

```json
{
  "title": "Event Helper",
  "description": "Help manage entry desk and guest flow.",
  "salary": 1200,
  "categoryId": "uuid",
  "workersNeeded": 4,
  "location": "Bengaluru",
  "shiftDate": "2026-06-01",
  "shiftStartTime": "10:00",
  "shiftEndTime": "18:00"
}
```

Validation:

- `title`: 3-120 characters.
- `description`: 20-5000 characters.
- `salary`: positive.
- `workersNeeded`: 1-500.
- `shiftDate`: today or future.

### PATCH `/api/jobs/{id}`

Employer owns job or admin.

### DELETE `/api/jobs/{id}`

Soft deletes or closes the job depending on application state.

## Applications

- `POST /api/jobs/{jobId}/applications`: worker applies.
- `GET /api/workers/me/applications`: worker views application status.
- `GET /api/employers/me/jobs/{jobId}/applications`: employer views applicants.
- `PATCH /api/applications/{id}/accept`: employer accepts worker.
- `PATCH /api/applications/{id}/reject`: employer rejects worker.
- `PATCH /api/applications/{id}/complete`: employer marks completed.

## Chat

Chat is allowed only for accepted applications.

- `GET /api/chats`: list conversations.
- `GET /api/chats/{conversationId}/messages?cursor=...`: cursor-paginated messages.
- `POST /api/chats/{conversationId}/messages`: send message.
- `PATCH /api/chats/{conversationId}/read`: update last read message.

WebSocket endpoints:

- Connect: `/ws`
- Send: `/app/chats/{conversationId}/send`
- Subscribe: `/topic/chats/{conversationId}`
- User notifications: `/user/queue/notifications`

## Notifications

- `GET /api/notifications?read=false&page=0&size=20`
- `PATCH /api/notifications/{id}/read`
- `PATCH /api/notifications/read-all`

## Verification

- `POST /api/verifications/workers/documents`
- `POST /api/verifications/employers/documents`
- `GET /api/admin/verifications?status=PENDING`
- `PATCH /api/admin/verifications/{id}/approve`
- `PATCH /api/admin/verifications/{id}/reject`
- `PATCH /api/admin/verifications/{id}/request-reupload`

## Attendance

- `POST /api/jobs/{jobId}/attendance`
- `PATCH /api/attendance/{id}/present`
- `PATCH /api/attendance/{id}/absent`
- `PATCH /api/jobs/{jobId}/attendance/close`
- `GET /api/workers/me/attendance`

## Reports And Disputes

- `POST /api/reports`
- `POST /api/disputes`
- `GET /api/admin/reports?status=OPEN`
- `PATCH /api/admin/reports/{id}/resolve`
- `GET /api/admin/disputes?status=OPEN`
- `PATCH /api/admin/disputes/{id}/resolve`

## Admin Analytics

- `GET /api/admin/analytics/overview?from=2026-01-01&to=2026-05-09`
- `GET /api/admin/analytics/cities`
- `GET /api/admin/analytics/completion-rates`
- `GET /api/admin/analytics/fraud`
