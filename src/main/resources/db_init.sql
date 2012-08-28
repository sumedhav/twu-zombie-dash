DROP TABLE IF EXISTS zombie_question;
CREATE TABLE zombie_question (ID INT PRIMARY KEY,
                       Text varchar(50) NOT NULL);

insert into zombie_question values (15, 'Where is Red Fort');
insert into zombie_question values (2, 'Is it lunch time?');
DROP TABLE IF EXISTS zombie_option;
CREATE TABLE zombie_option(
            ID INT,
            QUESTION_ID int NOT NULL,
            Text varchar(50) NOT NULL,
            correct BOOLEAN,
            PRIMARY KEY (ID,QUESTION_ID),
            FOREIGN KEY(QUESTION_ID) REFERENCES zombie_question(ID));
insert into zombie_option values (1,15,  'Delhi', true);
insert into zombie_option values (2,15,  'Paris', false);
insert into zombie_option values (3,15,  'New York', false);
insert into zombie_option values (4, 2, 'I bet it is', true);
insert into zombie_option values (5, 2, 'No thanks, fasting at the moment', false);

DROP TABLE IF EXISTS zombie_conference;

CREATE TABLE zombie_conference(
    NAME VARCHAR(30) NOT NULL PRIMARY KEY,
    TOPIC VARCHAR(30) NOT NULL,
    DESCRIPTION VARCHAR(1000) NOT NULL,
    VENUE VARCHAR(20) NOT NULL,
    START_DATE VARCHAR(10) NOT NULL,
    END_DATE VARCHAR(10) NOT NULL,
    MAX_ATTENDEE INT NOT NULL);

DROP TABLE IF EXISTS zombie_users;

CREATE TABLE zombie_users(username varchar(20) NOT NULL PRIMARY KEY CHECK(username NOT LIKE ''),
                password varchar(40) NOT NULL,
                role integer NOT NULL,
                name varchar(30) NOT NULL,
                email varchar(100) NOT NULL);

INSERT INTO zombie_users VALUES('admin','b56e0b4ea4962283bee762525c2d490f',0,'Administrator','admin@zombie.com');




