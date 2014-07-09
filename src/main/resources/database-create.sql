create table user (
	username varchar(50) primary key,
	fullname varchar(255),
	password char(60)
);

create table role (
	rolename varchar(50) primary key
);

create table user_role (
	id IDENTITY AUTO_INCREMENT,
	username varchar(50),
	rolename varchar(50),
	FOREIGN KEY (username) REFERENCES  user(username),
	FOREIGN KEY (rolename) REFERENCES  role(rolename),
);