insert into users(first_name,last_name,username,password,enabled)
values('John','Doe','admin','$2y$10$ZJ7RqJbaAdxNCttloSuGzOpl1muG8G09C1WACiIxU7hyuBqis6MTm', 1) ,
('Allan','Graves','student@faks.hr','$2y$12$fcb0ckD33FH550uFWcxvp.Z56JgFCK0AEw1/113RQUYGD0JlP.NjC', 1),
('Ivan','Keller','student1@faks.hr','$2y$12$fcb0ckD33FH550uFWcxvp.Z56JgFCK0AEw1/113RQUYGD0JlP.NjC', 1),
('Jeff','Holland','student2@faks.hr','$2y$12$fcb0ckD33FH550uFWcxvp.Z56JgFCK0AEw1/113RQUYGD0JlP.NjC', 1),
('Peter','Nunez','student3@faks.hr','$2y$12$fcb0ckD33FH550uFWcxvp.Z56JgFCK0AEw1/113RQUYGD0JlP.NjC', 1),
('Jennie','Pope','student4@faks.hr','$2y$12$fcb0ckD33FH550uFWcxvp.Z56JgFCK0AEw1/113RQUYGD0JlP.NjC', 1),
('Rosa','Collier','student5@faks.hr','$2y$12$fcb0ckD33FH550uFWcxvp.Z56JgFCK0AEw1/113RQUYGD0JlP.NjC', 1),
('Patrick','Braum','student6@faks.hr','$2y$12$fcb0ckD33FH550uFWcxvp.Z56JgFCK0AEw1/113RQUYGD0JlP.NjC', 1);
insert into roles(id_user,role)
values(1,'ROLE_ADMIN'), (2,'ROLE_STUDENT'), (3,'ROLE_STUDENT'), (4,'ROLE_STUDENT'), (5,'ROLE_STUDENT'), (6,'ROLE_STUDENT'), (7,'ROLE_STUDENT');

insert into rooms(name)
values '1A', '1B', '1C', '1D', '1E', '1F', '1G';


INSERT INTO ANNOUNCEMENTS 
	(id, id_user,  title, body, creation_date, expiration_date) 
VALUES (1, 1, 'Join our esport team', 'WESA is the result of joint efforts between industry-leading professional esports teams and ESL, the world’s largest esports company. Based on similar traditional sports associations, WESA is an open and inclusive organization that will further professionalize esports by introducing elements of player representation, standardized regulations, and revenue shares for teams. WESA will seek to create predictable schedules for fans, players, organizers and broadcasters, and for the first time bring all stakeholders to the discussion table.', CURDATE(), CURDATE() + 2);


INSERT INTO ANNOUNCEMENTS 
	(id, id_user,  title, body, creation_date, expiration_date) 
VALUES (2, 1, 'Building new garden', 'Tomorrow (4.4.2020) we are going to build garden. Come and help us', CURDATE(), CURDATE() + 2);


--Allan Graves
INSERT INTO APPLICATIONS 
(ID, ID_USER, GENDER, BIRTH_DATE, ADDRESS, JMBAG, UNIVERSITY, CREATION_DATE, APPROVED) 
VALUES 
(1, 2, 'MALE', '2000-06-05', 'Zagreb', 6312990345, 'TVZ', CURDATE(), 1);

INSERT INTO TENANTS
(ID, ID_APPLICATION, ID_ROOM)
VALUES
(1,1,1);

UPDATE ROLES SET role = 'ROLE_TENANT' WHERE ID_USER = 2;

--Ivan Keller
INSERT INTO APPLICATIONS 
(ID, ID_USER, GENDER, BIRTH_DATE, ADDRESS, JMBAG, UNIVERSITY, CREATION_DATE, APPROVED) 
VALUES 
(2, 3, 'MALE', '2002-05-01', 'Rijeka', 1113397345, 'FER', CURDATE(), 1);

INSERT INTO TENANTS
(ID, ID_APPLICATION, ID_ROOM)
VALUES
(2,2,1);

UPDATE ROLES SET role = 'ROLE_TENANT' WHERE ID_USER = 3;

--Jeff Holland
INSERT INTO APPLICATIONS 
(ID, ID_USER, GENDER, BIRTH_DATE, ADDRESS, JMBAG, UNIVERSITY, CREATION_DATE, APPROVED) 
VALUES 
(3, 4, 'MALE', '1998-05-01', 'Pula', 4315916686, 'FER', CURDATE(), 1);

INSERT INTO TENANTS
(ID, ID_APPLICATION, ID_ROOM)
VALUES
(3,3,2);

UPDATE ROLES SET role = 'ROLE_TENANT' WHERE ID_USER = 4;

--Peter Nunez
INSERT INTO APPLICATIONS 
(ID, ID_USER, GENDER, BIRTH_DATE, ADDRESS, JMBAG, UNIVERSITY, CREATION_DATE, APPROVED) 
VALUES 
(4, 5, 'MALE', '2001-05-01', 'Kasina', 4333176536, 'PMF', CURDATE(), 1);

INSERT INTO TENANTS
(ID, ID_APPLICATION, ID_ROOM)
VALUES
(4,4,2);

UPDATE ROLES SET role = 'ROLE_TENANT' WHERE ID_USER = 5;

--Jennie Pope
INSERT INTO APPLICATIONS 
(ID, ID_USER, GENDER, BIRTH_DATE, ADDRESS, JMBAG, UNIVERSITY, CREATION_DATE, APPROVED) 
VALUES 
(5, 6, 'FEMALE', '2000-12-05', 'Vukovar', 2275897412, 'PMF', CURDATE(), 1);

INSERT INTO TENANTS
(ID, ID_APPLICATION, ID_ROOM)
VALUES
(5,5,3);

UPDATE ROLES SET role = 'ROLE_TENANT' WHERE ID_USER = 6;

--Rosa Collier
INSERT INTO APPLICATIONS 
(ID, ID_USER, GENDER, BIRTH_DATE, ADDRESS, JMBAG, UNIVERSITY, CREATION_DATE, APPROVED) 
VALUES 
(6, 7, 'FEMALE', '2001-09-23', 'Makarska', 2275897412, 'Uciteljski fakultet', CURDATE(), 1);

INSERT INTO TENANTS
(ID, ID_APPLICATION, ID_ROOM)
VALUES
(6,6,3);

UPDATE ROLES SET role = 'ROLE_TENANT' WHERE ID_USER = 7;

--Patrick Braum
INSERT INTO APPLICATIONS 
(ID, ID_USER, GENDER, BIRTH_DATE, ADDRESS, JMBAG, UNIVERSITY, CREATION_DATE, APPROVED) 
VALUES 
(7, 8, 'MALE', '1996-11-30', 'Osijek', 1174814562, 'TVZ', CURDATE(), 1);

INSERT INTO TENANTS
(ID, ID_APPLICATION, ID_ROOM)
VALUES
(7,7,4);

UPDATE ROLES SET role = 'ROLE_TENANT' WHERE ID_USER = 8;

--Complaints
INSERT INTO COMPLAINTS (ID, ID_USER, BODY, CREATION_DATE) VALUES 
(1, 1, 'Sink doesnt work in kitchen', '2020-02-22');

INSERT INTO COMPLAINTS (ID, ID_USER, BODY, CREATION_DATE) VALUES 
(2, 5, 'No electricity in my room', '2020-03-01');

INSERT INTO COMPLAINTS (ID, ID_USER, BODY, CREATION_DATE) VALUES 
(3, 4, 'Roomate is to noisy', '2020-04-02');

INSERT INTO COMPLAINTS (ID, ID_USER, BODY, CREATION_DATE) VALUES 
(4, 2, 'Kitchen has no light', '2020-04-20');

INSERT INTO COMPLAINTS (ID, ID_USER, BODY, CREATION_DATE) VALUES 
(5, 1, 'We had no water yesterday', '2020-04-12');

INSERT INTO COMPLAINTS (ID, ID_USER, BODY, CREATION_DATE) VALUES 
(6, 6, 'Sink doesnt work in kitchen', '2020-02-12');

INSERT INTO COMPLAINTS (ID, ID_USER, BODY, CREATION_DATE) VALUES 
(7, 7, 'Sink doesnt work in kitchen', '2020-07-11');

INSERT INTO COMPLAINTS (ID, ID_USER, BODY, CREATION_DATE) VALUES 
(8, 4, 'Sink doesnt work in kitchen', '2020-09-17');

INSERT INTO COMPLAINTS (ID, ID_USER, BODY, CREATION_DATE) VALUES 
(9, 5, 'Sink doesnt work in kitchen', '2020-08-14');

INSERT INTO COMPLAINTS (ID, ID_USER, BODY, CREATION_DATE) VALUES 
(10, 5, 'Sink doesnt work in kitchen', '2020-12-18');

INSERT INTO COMPLAINTS (ID, ID_USER, BODY, CREATION_DATE) VALUES 
(11, 5, 'Sink doesnt work in kitchen', '2020-12-18');

INSERT INTO COMPLAINTS (ID, ID_USER, BODY, CREATION_DATE) VALUES 
(12, 3, 'Sink doesnt work in kitchen', '2020-12-28');

INSERT INTO COMPLAINTS (ID, ID_USER, BODY, CREATION_DATE) VALUES 
(13, 2, 'Sink doesnt work in kitchen', '2020-10-25');

INSERT INTO COMPLAINTS (ID, ID_USER, BODY, CREATION_DATE) VALUES 
(14, 2, 'Sink doesnt work in kitchen', '2020-11-12');

INSERT INTO COMPLAINTS (ID, ID_USER, BODY, CREATION_DATE) VALUES 
(15, 6, 'Sink doesnt work in kitchen', '2020-09-10');

INSERT INTO COMPLAINTS (ID, ID_USER, BODY, CREATION_DATE) VALUES 
(16, 6, 'Sink doesnt work in kitchen', '2020-02-16');

INSERT INTO COMPLAINTS (ID, ID_USER, BODY, CREATION_DATE) VALUES 
(17, 1, 'Sink doesnt work in kitchen', '2020-01-19');

INSERT INTO COMPLAINTS (ID, ID_USER, BODY, CREATION_DATE) VALUES 
(18, 2, 'Sink doesnt work in kitchen', '2020-06-22');

INSERT INTO COMPLAINTS (ID, ID_USER, BODY, CREATION_DATE) VALUES 
(19, 3, 'Sink doesnt work in kitchen', '2020-06-04');

INSERT INTO COMPLAINTS (ID, ID_USER, BODY, CREATION_DATE) VALUES 
(20, 4, 'Sink doesnt work in kitchen', '2020-07-08');

INSERT INTO COMPLAINTS (ID, ID_USER, BODY, CREATION_DATE) VALUES 
(21, 4, 'Sink doesnt work in kitchen', '2020-07-15');

INSERT INTO COMPLAINTS (ID, ID_USER, BODY, CREATION_DATE) VALUES 
(22, 6, 'Sink doesnt work in kitchen', '2020-08-27');

INSERT INTO COMPLAINTS (ID, ID_USER, BODY, CREATION_DATE) VALUES 
(23, 7, 'Sink doesnt work in kitchen', '2020-05-16');

INSERT INTO COMPLAINTS (ID, ID_USER, BODY, CREATION_DATE) VALUES 
(24, 2, 'Sink doesnt work in kitchen', '2020-05-04');

INSERT INTO COMPLAINTS (ID, ID_USER, BODY, CREATION_DATE) VALUES 
(25, 3, 'Sink doesnt work in kitchen', '2020-04-03');

INSERT INTO COMPLAINTS (ID, ID_USER, BODY, CREATION_DATE) VALUES 
(26, 3, 'Sink doesnt work in kitchen', '2020-09-03');

INSERT INTO COMPLAINTS (ID, ID_USER, BODY, CREATION_DATE) VALUES 
(27, 5, 'Sink doesnt work in kitchen', '2020-01-22');

INSERT INTO COMPLAINTS (ID, ID_USER, BODY, CREATION_DATE) VALUES 
(28, 5, 'Sink doesnt work in kitchen', '2020-01-02');

INSERT INTO COMPLAINTS (ID, ID_USER, BODY, CREATION_DATE) VALUES 
(29, 5, 'Sink doesnt work in kitchen', '2020-02-01');

INSERT INTO COMPLAINTS (ID, ID_USER, BODY, CREATION_DATE) VALUES 
(30, 6, 'Sink doesnt work in kitchen', '2020-02-15');

INSERT INTO COMPLAINTS (ID, ID_USER, BODY, CREATION_DATE) VALUES 
(31, 4, 'Sink doesnt work in kitchen', '2020-08-14');







