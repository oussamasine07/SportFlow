CREATE DATABASE sportflow;
USE sportflow;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(255) not null,
    lastName VARCHAR(255) not null,
    email VARCHAR(255) not null,
    password VARCHAR(255) not null,
    role ENUM("trainer", "member") NOT NULL,
    isAdmin BOOLEAN DEFAULT FALSE
);

CREATE TABLE members (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id int not null,
    FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE NULL
);

CREATE TABLE trainers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id int not null,
    specialty VARCHAR(255) not null,
    FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE NULL
);


























