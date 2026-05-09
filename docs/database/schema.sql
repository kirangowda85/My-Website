CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(40) NOT NULL CHECK (role IN ('WORKER', 'EMPLOYER', 'ADMIN')),
    status VARCHAR(40) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    deleted_at TIMESTAMPTZ
);

CREATE TABLE worker_profiles (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id UUID NOT NULL UNIQUE REFERENCES users(id),
    name VARCHAR(150),
    phone VARCHAR(30),
    city VARCHAR(120),
    skills TEXT,
    profile_photo_url TEXT,
    rating NUMERIC(3,2) NOT NULL DEFAULT 0,
    verification_status VARCHAR(40) NOT NULL DEFAULT 'PENDING',
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE employer_profiles (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id UUID NOT NULL UNIQUE REFERENCES users(id),
    business_name VARCHAR(180),
    owner_name VARCHAR(150),
    phone VARCHAR(30),
    location VARCHAR(180),
    verification_status VARCHAR(40) NOT NULL DEFAULT 'PENDING',
    trust_score INTEGER NOT NULL DEFAULT 0,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE job_categories (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(120) NOT NULL UNIQUE,
    slug VARCHAR(140) NOT NULL UNIQUE,
    is_active BOOLEAN NOT NULL DEFAULT true,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE jobs (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    employer_id UUID NOT NULL REFERENCES employer_profiles(id),
    category_id UUID REFERENCES job_categories(id),
    title VARCHAR(140) NOT NULL,
    description TEXT NOT NULL,
    salary NUMERIC(10,2) NOT NULL CHECK (salary > 0),
    workers_needed INTEGER NOT NULL CHECK (workers_needed > 0),
    location VARCHAR(180) NOT NULL,
    city VARCHAR(120),
    latitude NUMERIC(10,7),
    longitude NUMERIC(10,7),
    shift_date DATE NOT NULL,
    shift_start_time TIME,
    shift_end_time TIME,
    status VARCHAR(40) NOT NULL DEFAULT 'OPEN',
    is_urgent BOOLEAN NOT NULL DEFAULT false,
    verification_required BOOLEAN NOT NULL DEFAULT false,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    deleted_at TIMESTAMPTZ
);

CREATE TABLE job_applications (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    job_id UUID NOT NULL REFERENCES jobs(id),
    worker_id UUID NOT NULL REFERENCES worker_profiles(id),
    status VARCHAR(40) NOT NULL DEFAULT 'APPLIED',
    applied_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    UNIQUE (job_id, worker_id)
);

CREATE TABLE saved_jobs (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    worker_id UUID NOT NULL REFERENCES worker_profiles(id),
    job_id UUID NOT NULL REFERENCES jobs(id),
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    UNIQUE (worker_id, job_id)
);

CREATE TABLE ratings (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    application_id UUID NOT NULL REFERENCES job_applications(id),
    rater_user_id UUID NOT NULL REFERENCES users(id),
    rated_user_id UUID NOT NULL REFERENCES users(id),
    score INTEGER NOT NULL CHECK (score BETWEEN 1 AND 5),
    comment TEXT,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE chat_conversations (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    job_id UUID NOT NULL REFERENCES jobs(id),
    application_id UUID NOT NULL UNIQUE REFERENCES job_applications(id),
    employer_id UUID NOT NULL REFERENCES employer_profiles(id),
    worker_id UUID NOT NULL REFERENCES worker_profiles(id),
    status VARCHAR(40) NOT NULL DEFAULT 'ACTIVE',
    last_message_at TIMESTAMPTZ,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE chat_messages (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    conversation_id UUID NOT NULL REFERENCES chat_conversations(id),
    sender_user_id UUID NOT NULL REFERENCES users(id),
    message_type VARCHAR(40) NOT NULL DEFAULT 'TEXT',
    body TEXT,
    media_url TEXT,
    read_at TIMESTAMPTZ,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE notifications (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id UUID NOT NULL REFERENCES users(id),
    title VARCHAR(180) NOT NULL,
    message TEXT NOT NULL,
    notification_type VARCHAR(80) NOT NULL,
    payload JSONB,
    is_read BOOLEAN NOT NULL DEFAULT false,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE verification_documents (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id UUID NOT NULL REFERENCES users(id),
    document_type VARCHAR(80) NOT NULL,
    document_url TEXT NOT NULL,
    status VARCHAR(40) NOT NULL DEFAULT 'PENDING',
    rejection_reason TEXT,
    reviewed_by UUID REFERENCES users(id),
    reviewed_at TIMESTAMPTZ,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE attendance_records (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    job_id UUID NOT NULL REFERENCES jobs(id),
    application_id UUID NOT NULL REFERENCES job_applications(id),
    worker_id UUID NOT NULL REFERENCES worker_profiles(id),
    status VARCHAR(40) NOT NULL DEFAULT 'PENDING',
    marked_by UUID REFERENCES users(id),
    marked_at TIMESTAMPTZ,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    UNIQUE (job_id, worker_id)
);

CREATE TABLE reports (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    reporter_user_id UUID NOT NULL REFERENCES users(id),
    target_user_id UUID REFERENCES users(id),
    job_id UUID REFERENCES jobs(id),
    reason VARCHAR(180) NOT NULL,
    details TEXT,
    status VARCHAR(40) NOT NULL DEFAULT 'OPEN',
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    resolved_at TIMESTAMPTZ
);

CREATE TABLE disputes (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    application_id UUID REFERENCES job_applications(id),
    opened_by UUID NOT NULL REFERENCES users(id),
    reason VARCHAR(180) NOT NULL,
    details TEXT,
    status VARCHAR(40) NOT NULL DEFAULT 'OPEN',
    assigned_admin_id UUID REFERENCES users(id),
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    resolved_at TIMESTAMPTZ
);

CREATE TABLE admin_audit_logs (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    admin_user_id UUID NOT NULL REFERENCES users(id),
    action VARCHAR(120) NOT NULL,
    target_type VARCHAR(80),
    target_id UUID,
    metadata JSONB,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE INDEX idx_users_role_status ON users(role, status);
CREATE INDEX idx_jobs_status_date ON jobs(status, shift_date);
CREATE INDEX idx_jobs_city_category ON jobs(city, category_id);
CREATE INDEX idx_jobs_salary ON jobs(salary);
CREATE INDEX idx_applications_worker_status ON job_applications(worker_id, status);
CREATE INDEX idx_applications_job_status ON job_applications(job_id, status);
CREATE INDEX idx_chat_messages_conversation_created ON chat_messages(conversation_id, created_at DESC);
CREATE INDEX idx_notifications_user_read_created ON notifications(user_id, is_read, created_at DESC);
CREATE INDEX idx_verifications_status_created ON verification_documents(status, created_at);
CREATE INDEX idx_reports_status_created ON reports(status, created_at);
CREATE INDEX idx_disputes_status_created ON disputes(status, created_at);
