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