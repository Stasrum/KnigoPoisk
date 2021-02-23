
CREATE TABLE users_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL
    )
GO

ALTER TABLE IF EXISTS users_roles
    ADD CONSTRAINT FK2o0jvgh89lemvvo17cbqvdxaa
        FOREIGN KEY (user_id)
            REFERENCES users

GO

ALTER TABLE IF EXISTS users_roles
    ADD CONSTRAINT FKj6m8fwv7oqv74fcehir1a9ffy
        FOREIGN KEY (role_id)
            REFERENCES roles
GO

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1), (1, 2),(2, 2),(3, 2),(4, 1),(4, 2)

GO