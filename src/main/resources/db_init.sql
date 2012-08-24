DROP TABLE IF EXISTS zombie_question;
CREATE TABLE zombie_question (ID INT PRIMARY KEY,
                       Text varchar(50) NOT NULL);

DROP TABLE IF EXISTS zombie_option;
CREATE TABLE zombie_option(
            ID INT,
            QUESTION_ID int NOT NULL,
            Text varchar(50) NOT NULL,
            correct BOOLEAN,
            PRIMARY KEY (ID,QUESTION_ID),
            FOREIGN KEY(QUESTION_ID) REFERENCES zombie_question(ID));

DROP TABLE IF EXISTS zombie_conference;

CREATE TABLE zombie_conference(
    NAME VARCHAR(30) NOT NULL PRIMARY KEY,
    TOPIC VARCHAR(30) NOT NULL,
    DESCRIPTION VARCHAR(1000) NOT NULL,
    VENUE VARCHAR(20) NOT NULL,
    START_DATE DATE NOT NULL,
    END_DATE DATE NOT NULL,
    MAX_ATTENDEE INT NOT NULL);

DROP TABLE IF EXISTS zombie_users;

CREATE TABLE zombie_users(username varchar(20) NOT NULL PRIMARY KEY CHECK(username NOT LIKE ''),
                password varchar(40) NOT NULL,
                role number(1) NOT NULL,
                name varchar(30) NOT NULL,
                email varchar(100) NOT NULL);

INSERT INTO zombie_users VALUES('admin','Welcome1',0,'Administrator','admin@zombie.com');




