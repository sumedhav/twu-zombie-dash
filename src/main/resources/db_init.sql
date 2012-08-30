DROP TABLE IF EXISTS zombie_option;
DROP TABLE IF EXISTS zombie_question;
CREATE TABLE zombie_question (ID INT PRIMARY KEY,
                       Text varchar(50) NOT NULL);

CREATE TABLE zombie_option(
            ID INT,
            QUESTION_ID int NOT NULL,
            Text varchar(50) NOT NULL,
            correct BOOLEAN,
            PRIMARY KEY (ID,QUESTION_ID),
            FOREIGN KEY(QUESTION_ID) REFERENCES zombie_question(ID));

DROP TABLE IF EXISTS zombie_conference;

CREATE TABLE zombie_conference(
    ID INT PRIMARY KEY,
    NAME VARCHAR(100) NOT NULL,
    TOPIC VARCHAR(100) NOT NULL,
    DESCRIPTION VARCHAR(500) NOT NULL,
    VENUE VARCHAR(200) NOT NULL,
    START_DATE VARCHAR(10) NOT NULL,
    END_DATE VARCHAR(10) NOT NULL,
    MAX_ATTENDEE INT NOT NULL);

DROP TABLE IF EXISTS zombie_users;

CREATE TABLE zombie_users(username varchar(40) NOT NULL PRIMARY KEY CHECK(username NOT LIKE ''),
                password varchar(40) NOT NULL,
                role integer NOT NULL,
                name varchar(40) NOT NULL,
                email varchar(100) NOT NULL);

INSERT INTO zombie_users VALUES('admin','b56e0b4ea4962283bee762525c2d490f',0,'Administrator','admin@zombie.com');




