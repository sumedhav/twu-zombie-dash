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
    NAME VARCHAR(200) NOT NULL,
    TOPIC VARCHAR(200) NOT NULL,
    DESCRIPTION VARCHAR(1000) NOT NULL,
    VENUE VARCHAR(400) NOT NULL,
    START_DATE VARCHAR(10) NOT NULL,
    END_DATE VARCHAR(10) NOT NULL,
    MAX_ATTENDEE INT NOT NULL);

DROP TABLE IF EXISTS zombie_users;

CREATE TABLE zombie_users(username varchar(40) NOT NULL PRIMARY KEY CHECK(username NOT LIKE ''),
                password varchar(128) NOT NULL,
                role integer NOT NULL,
                name varchar(40) NOT NULL,
                email varchar(100) NOT NULL);

INSERT INTO zombie_users VALUES('admin','da07c08a2c2ef3710e688bff476a8a09d52d6d34b6ee3c41a4b1f58f2949792ef20079565ca0d78e2758b33b50a13c9829c08bdf670dc802e627f289364d203a',0,'Administrator','admin@zombie.com');




