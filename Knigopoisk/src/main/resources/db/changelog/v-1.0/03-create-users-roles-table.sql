
CREATE TABLE users_roles (
    user_id int8 not null,
    role_id int8 not null
    )

GO

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1), (1, 2)

GO