CREATE TABLE user
(
    id bigint AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    role_id bigint NOT NULL,

    UNIQUE KEY username_uindex (username),
    UNIQUE KEY email_uindex (email),
    FOREIGN KEY (role_id) REFERENCES role (id)
);
