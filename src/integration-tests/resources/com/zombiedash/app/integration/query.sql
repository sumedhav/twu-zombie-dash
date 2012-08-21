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

INSERT INTO Conference(name,topic,description,venue,start_date,end_date,max_attendee)
             values('Java Conf','Java','complete description of java','Bangalore','2012-12-24','2012-12-24',200);



