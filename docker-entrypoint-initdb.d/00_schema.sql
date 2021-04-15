-- USER(S)
-- user(s)
-- ORM - H2 -> case insensitive

-- List<User> users
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    login TEXT UNIQUE NOT NULL, -- UNIQUE may be NULL
    password TEXT NOT NULL,
    roles TEXT[] NOT NULL DEFAULT '{ROLE_USER}',
    removed BOOLEAN DEFAULT FALSE,
    modified TIMESTAMP,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE tokens (
    user_id BIGINT NOT NULL REFERENCES users,
    token TEXT PRIMARY KEY,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);