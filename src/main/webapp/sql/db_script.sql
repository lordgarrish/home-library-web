DROP DATABASE IF EXISTS books_db ;

CREATE DATABASE books_db;

USE books_db;

CREATE TABLE authors (
    author_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,

    CONSTRAINT author_uc UNIQUE (first_name, last_name)
);

CREATE TABLE genres (
    genre_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    genre VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE books (
    book_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    author_id INT NOT NULL,
    genre_id INT NOT NULL,
    year INT NOT NULL,

    FOREIGN KEY (author_id) REFERENCES authors (author_id),
    FOREIGN KEY (genre_id) REFERENCES genres (genre_id)
);
