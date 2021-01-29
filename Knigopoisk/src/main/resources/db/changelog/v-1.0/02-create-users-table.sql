
CREATE TABLE users (
    id BIGSERIAL,
    username varchar(255) UNIQUE NOT NULL,
    password varchar(255) NOT NULL,
    age int,
    email varchar (255),
    enabled boolean,
    account_non_expired boolean,
    credentials_non_expired boolean,
    account_non_locked boolean,
    first_name varchar(255),
    last_name varchar(255),
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id))

GO

INSERT INTO users (username, password, age, email, enabled, account_non_expired, credentials_non_expired, account_non_locked, first_name , last_name) VALUES ('admin', '$2a$10$5uECjAbKQ86L.cW8naqBRu2dIue7LiaN9AWxys/B7vmoKHBJDLu7O',30,'admin@adm.co', true, true, true, true, 'alex','alex')

GO