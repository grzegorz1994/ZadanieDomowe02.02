CREATE TABLE IF NOT EXISTS repo
(
    id BIGSERIAL PRIMARY KEY,
    owner VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL
);