# Development Phases

## Phase 1: V1 Foundation

- Auth with JWT, BCrypt, role-based authorization.
- Worker and employer profiles.
- Job posting and public job browsing.
- Worker applications and employer accept/reject flow.
- Basic admin moderation.
- Ratings after completed jobs.
- PostgreSQL schema, indexes, and deployment configuration.

## Phase 2: V2 Operations

- Private accepted-application chat.
- Notifications.
- Worker and employer verification.
- Saved jobs and advanced filters.
- Attendance closure.
- Reports and disputes.
- Multi-admin roles and permissions.
- Admin settings and category management.
- Chart-ready admin analytics APIs.

## Phase 3: V3 Enterprise

- Redis-backed realtime scale-out.
- Matching score engine.
- Wallet, ledger, payouts, refunds, and payment disputes.
- QR attendance tokens and location validation.
- Worker availability and direct invites.
- Company hierarchy and branch-level permissions.
- Full-text search and Elasticsearch readiness.
- CMS, subscriptions, boosted jobs, and trust scoring.
- Regional admin support and mobile API hardening.

## Phase 4: Hardening

- Integration and contract tests.
- Load testing for jobs, applications, chat, and notifications.
- Observability with structured logs, metrics, and tracing.
- Security review for uploads, JWT lifecycle, RBAC, and payment flows.
- Data retention, archival, and backup restore drills.
