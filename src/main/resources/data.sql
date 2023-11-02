DROP TABLE IF EXISTS todo_class;

CREATE TABLE todo_class (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) ,
    description VARCHAR(255) ,
    targetdate DATE,
    completed BOOLEAN
);

