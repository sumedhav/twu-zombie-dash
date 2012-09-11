DROP TABLE IF EXISTS zombie_option;
DROP TABLE IF EXISTS zombie_question;
DROP TABLE IF EXISTS zombie_task;
DROP TABLE IF EXISTS zombie_conference;

CREATE TABLE zombie_conference(
    ID uuid PRIMARY KEY,
    NAME VARCHAR(200) NOT NULL,
    TOPIC VARCHAR(200) NOT NULL,
    DESCRIPTION VARCHAR(1000) NOT NULL,
    VENUE VARCHAR(400) NOT NULL,
    START_DATE VARCHAR(10) NOT NULL,
    END_DATE VARCHAR(10) NOT NULL,
    MAX_ATTENDEE INT NOT NULL);

CREATE TABLE zombie_task(name varchar(100) NOT NULL,
                         ID uuid NOT NULL,
                         Description varchar(1000) NOT NULL,
                         CONFERENCE_ID uuid NOT NULL,
                         PRIMARY KEY (ID),
                         FOREIGN KEY(CONFERENCE_ID) REFERENCES zombie_conference(ID));

CREATE TABLE zombie_question (ID uuid PRIMARY KEY,
                       Text varchar(50) NOT NULL,
                       TASK_ID uuid NOT NULL,
                       FOREIGN KEY(TASK_ID) REFERENCES zombie_task(ID));

CREATE TABLE zombie_option(
            ID uuid,
            Text varchar(50) NOT NULL,
            correct BOOLEAN,
            QUESTION_ID uuid NOT NULL,
            PRIMARY KEY (ID,QUESTION_ID),
            FOREIGN KEY(QUESTION_ID) REFERENCES zombie_question(ID)
            ON DELETE CASCADE);


DROP TABLE IF EXISTS zombie_attendee_info;
DROP TABLE IF EXISTS zombie_users;

CREATE TABLE zombie_users(username varchar(40)  PRIMARY KEY ,
                password varchar(128) NOT NULL,
                role integer NOT NULL,
                name varchar(40) NOT NULL,
                email varchar(100) NOT NULL);


CREATE TABLE zombie_attendee_info(
                username varchar(40) ,
                dob varchar(10) NOT NULL,
                country varchar(40) NOT NULL,
                phoneNo varchar(20),
                address varchar(100),
                zipcode varchar(20),
                conference_ID uuid NOT NULL,
                PRIMARY KEY (username,conference_ID),
                CONSTRAINT username_constraint FOREIGN KEY (username) REFERENCES zombie_users(username)
                ON DELETE CASCADE,
                CONSTRAINT conference_ID_constraint FOREIGN KEY (conference_ID) REFERENCES zombie_conference(ID)
                ON DELETE CASCADE);

INSERT INTO zombie_users VALUES('admin','da07c08a2c2ef3710e688bff476a8a09d52d6d34b6ee3c41a4b1f58f2949792ef20079565ca0d78e2758b33b50a13c9829c08bdf670dc802e627f289364d203a',0,'Administrator','admin@zombie.com');



