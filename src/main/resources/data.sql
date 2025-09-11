INSERT INTO person (name, age, email) VALUES
('Alice Johnson', 25, 'alice@example.com'),
('Bob Smith', 32, 'bob@example.com'),
('Charlie Brown', 40, 'charlie@example.com'),
('Diana Prince', 28, 'diana@example.com'),
('Ethan Hunt', 35, 'ethan@example.com'),
('Fiona Clark', 22, 'fiona@example.com'),
('George White', 30, 'george@example.com'),
('Hannah Green', 27, 'hannah@example.com'),
('Ivan Petrov', 33, 'ivan@example.com'),
('Julia Adams', 29, 'julia@example.com');

INSERT INTO passport (number, person_id) VALUES
('P123456789', 1),
('P987654321', 2),
('P111222333', 3),
('P444555666', 5),
('P777888999', 7);

INSERT INTO book (title, person_id) VALUES
('Effective Java', 1),
('Spring in Action', 1),
('Clean Code', 2),
('Hibernate Tips', 3),
('Java Concurrency in Practice', 3),
('Domain-Driven Design', 5),
('Design Patterns', 7),
('Head First Java', 8),
('Refactoring', 9),
('Microservices Patterns', 10);

INSERT INTO hobby (name) VALUES
('Reading'),
('Traveling'),
('Sports'),
('Cooking'),
('Gaming'),
('Music'),
('Photography');

INSERT INTO person_hobby (person_id, hobby_id) VALUES
(1, 1), (1, 2),
(2, 3), (2, 4),
(3, 1), (3, 5),
(4, 2), (4, 6),
(5, 3), (5, 7),
(6, 5),
(7, 1), (7, 6),
(8, 2), (8, 3), (8, 4),
(9, 7),
(10, 6), (10, 1);
