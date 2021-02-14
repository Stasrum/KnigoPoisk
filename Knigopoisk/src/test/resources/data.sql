INSERT INTO roles (id, name) VALUES (1, 'ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'USER');

INSERT INTO users (id, username, password, birth_day, email, enabled, account_non_expired, credentials_non_expired, account_non_locked, first_name, last_name)
VALUES (1, 'testUser1', '$2a$10$5uECjAbKQ86L.cW8naqBRu2dIue7LiaN9AWxys/B7vmoKHBJDLu7O', '2000-01-01', 'email@mail.com', true, true, true, true, 'firstName', 'lastName');

INSERT INTO users (id, username, password, birth_day, email, enabled, account_non_expired, credentials_non_expired, account_non_locked, first_name, last_name)
VALUES (2, 'testUser2', '$2a$10$5uECjAbKQ86L.cW8naqBRu2dIue7LiaN9AWxys/B7vmoKHBJDLu7O', '2000-01-01', 'email@mail.com', true, true, true, true, 'firstName', 'lastName');

INSERT INTO users (id, username, password, birth_day, email, enabled, account_non_expired, credentials_non_expired, account_non_locked, first_name, last_name)
VALUES (3, 'testUser3', '$2a$10$5uECjAbKQ86L.cW8naqBRu2dIue7LiaN9AWxys/B7vmoKHBJDLu7O', '2000-01-01', 'email@mail.com', true, true, true, true, 'firstName', 'lastName');

INSERT INTO users_roles (user_id, role_id) VALUES (1, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);