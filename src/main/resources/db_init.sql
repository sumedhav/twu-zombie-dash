create table Question (ID INT PRIMARY KEY,
                       Text varchar(50) NOT NULL,
);
create table Option(
            ID INT PRIMARY KEY,
            QUESTION_ID int NOT NULL,
            Text varchar(50) NOT NULL,
            correct BOOLEAN,
            FOREIGN KEY(QUESTION_ID) REFERENCES Question(ID)
);

CREATE TABLE Conference(
    ID INT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(30) NOT NULL,
    TOPIC VARCHAR(30) NOT NULL,
    DESCRIPTION VARCHAR(100) NOT NULL,
    VENUE VARCHAR(20) NOT NULL,
    START_DATE DATE NOT NULL,
    END_DATE DATE NOT NULL,
    MAX_ATTENDEE INT NOT NULL,
    PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS users;
CREATE TABLE users(username varchar(20) NOT NULL PRIMARY KEY,
                password varchar(40) NOT NULL,
                role number(1) NOT NULL);

INSERT INTO users VALUES('admin','Welcome1',0);

INSERT INTO Conference(name,topic,description,venue,start_date,end_date,max_attendee)
             values('Java Conf','Java','complete description of java','Bangalore','2012-12-24','2012-12-24',200);
