DROP TABLE IF EXISTS users;
CREATE TABLE users(username varchar(20) NOT NULL PRIMARY KEY,
                password varchar(40) NOT NULL,
                role number(1) NOT NULL);

INSERT INTO users VALUES('admin','Welcome1',0);