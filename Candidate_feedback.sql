drop table if exists Candidates;

create table Candidates (
    id          integer auto_increment primary key,
    name        varchar(255),
    speciality  varchar(255),
    rating      varchar(255),
    presentation varchar(255)
);

insert into Candidates values (1, 'john', 'Machine Learning', 'N/A', '10am on 2/20 in ET A227');
insert into Candidates values (2, 'jack', 'Computer Vision', 'N/A', '10am on 2/25 in ET A332');
insert into Candidates values (3, 'jane', 'Machine Learning', 'N/A', '3pm on 2/27 in ET A126');
insert into Candidates values (4, 'May', 'Computer Science Education', 'N/A', '3:30pm on 3/11 in FA 219');


drop table if exists Feedbacks;

create table Feedbacks (
    rate   integer,
    name_f varchar(255),
    comment varchar(255),
    date date,
    owner_id integer references Candidates(id)
);

insert into Feedbacks values (1,'john','good',now(),1);


drop table if exists User;

create table User (
    id          integer auto_increment primary key,
    name        varchar(255),
    password  varchar(255),
    admin      boolean 
);

insert into User values (1,'cysun','abcd',1);
insert into User values (2,'john','abcd',0);

