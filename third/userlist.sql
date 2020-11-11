show databases;
create database userlist;
use userlist;

CREATE table userlist (
    name char(20) NOT NULL,
    birthday char(20) NOT NULL,
    email	char(30) NOT NULL,
    userid char(20) NOT NULL,
    password char(20) NOT NULL,
	primary key(userid)
);

show tables;
desc userlist;