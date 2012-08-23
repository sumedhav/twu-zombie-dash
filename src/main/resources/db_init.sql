DROP TABLE IF EXISTS Question;
CREATE TABLE Question (ID INT PRIMARY KEY,
                       Text varchar(50) NOT NULL,
);
DROP TABLE IF EXISTS Option;
CREATE TABLE Option(
            ID INT,
            QUESTION_ID int NOT NULL,
            Text varchar(50) NOT NULL,
            correct BOOLEAN,
            PRIMARY KEY (ID,QUESTION_ID),
            FOREIGN KEY(QUESTION_ID) REFERENCES Question(ID)
);

DROP TABLE IF EXISTS Conference;

CREATE TABLE Conference(
    NAME VARCHAR(30) NOT NULL PRIMARY KEY,
    TOPIC VARCHAR(30) NOT NULL,
    DESCRIPTION VARCHAR(100) NOT NULL,
    VENUE VARCHAR(20) NOT NULL,
    START_DATE DATE NOT NULL,
    END_DATE DATE NOT NULL,
    MAX_ATTENDEE INT NOT NULL,
    ORGANISER_NAME VARCHAR(30) NOT NULL,
    ORGANISER_CONTACT_NO VARCHAR(10) NOT NULL,
    ORGANISER_EMAIL VARCHAR(30) NOT NULL
);

DROP TABLE IF EXISTS users;
CREATE TABLE users(username varchar(20) NOT NULL PRIMARY KEY,
                password varchar(40) NOT NULL,
                role number(1) NOT NULL,
                name varchar(30) NOT NULL,
                email varchar(100) NOT NULL);

INSERT INTO users VALUES('admin','Welcome1',0,'Administrator','admin@zombie.com');




