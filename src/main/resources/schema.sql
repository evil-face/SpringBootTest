DROP ALL OBJECTS;

-- PERSON TABLE
CREATE TABLE person (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    age INT NOT NULL CHECK (age >= 0),
    email VARCHAR(255) NOT NULL UNIQUE
);

-- PASSPORT TABLE
CREATE TABLE passport (
    id INT AUTO_INCREMENT PRIMARY KEY,
    number VARCHAR(20) NOT NULL UNIQUE,
    person_id INT UNIQUE,
    CONSTRAINT fk_passport_person FOREIGN KEY (person_id) REFERENCES person(id)
);

-- BOOK TABLE
CREATE TABLE book (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    person_id INT,
    CONSTRAINT fk_book_person FOREIGN KEY (person_id) REFERENCES person(id)
);

-- HOBBY TABLE
CREATE TABLE hobby (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

-- JOIN TABLE PERSON-HOBBY:
CREATE TABLE person_hobby (
    person_id INT NOT NULL,
    hobby_id INT NOT NULL,
    PRIMARY KEY (person_id, hobby_id),
    CONSTRAINT fk_person_hobby_person FOREIGN KEY (person_id) REFERENCES person(id),
    CONSTRAINT fk_person_hobby_hobby FOREIGN KEY (hobby_id) REFERENCES hobby(id)
);
