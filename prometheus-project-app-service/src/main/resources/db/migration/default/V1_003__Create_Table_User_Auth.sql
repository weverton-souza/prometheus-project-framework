CREATE TABLE IF NOT EXISTS user_auth (
    id varchar(36) NOT NULL,
    tenant_id varchar(36) NOT NULL,
    username varchar(255) NOT NULL UNIQUE,
    nickname varchar(255) NULL,
    "password" varchar(255) NULL,
    is_account_non_expired bool NOT NULL,
    is_account_non_locked bool NOT NULL,
    is_credentials_non_expired bool NOT NULL,
    is_enabled bool NOT NULL,

    CONSTRAINT PKd0d8013e2c8941c6bc2c0c956d07dfd0 PRIMARY KEY (id),
    CONSTRAINT PKcf33e1083fd14830a704a5aec5f2d92e UNIQUE (username),
    CONSTRAINT FK3aa6bf1c34ba4e5e8801b0e534548a21 FOREIGN KEY (tenant_id) REFERENCES tenant(id)
);
