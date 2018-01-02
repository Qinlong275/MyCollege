/********************************************
学号：15030120044
姓名：秦龙
********************************************/

/********************************************
一、创建数据库
********************************************/
USE master;
GO

CREATE DATABASE stu_name 
ON
PRIMARY  
    (NAME = stud,
    FILENAME = 'F:\SQLData\Studdat1.mdf',
    SIZE = 100MB,
    MAXSIZE = 200,
    FILEGROWTH = 20)
    
LOG ON 
   (NAME = Studlog,
    FILENAME = 'F:\SQLData\Studlog1.ldf',
    SIZE = 100MB,
    MAXSIZE = 200,
    FILEGROWTH = 20)
    ;
GO

USE stu_name;
GO

/********************************************
二、创建表
********************************************/

--创建表Student
CREATE TABLE Student(
	Sno CHAR(5) NOT NULL UNIQUE,
	Sname CHAR(20) UNIQUE,
	Sex CHAR(1) CHECK(Sex IN ('M','F')),
	Sage INT,
	Sdept CHAR(15)
);
GO

--创建表Course
CREATE TABLE Course(
	Cno CHAR(4) PRIMARY KEY,
	Cname CHAR(40) ,
	Cpno CHAR(4) ,
	Ccredit SMALLINT,
);
GO

--创建表SC
CREATE TABLE SC(
	Sno CHAR(5),
	Cno CHAR(4) ,
	Grade INT CHECK(Grade>=0 AND Grade<=100) ,
	PRIMARY KEY (Sno ,Cno),
	FOREIGN KEY (Sno) REFERENCES Student(Sno),
	FOREIGN KEY (Cno) REFERENCES Course(Cno) 
);
GO

--...........

/********************************************
三、数据更新
********************************************/

--给Student表插入数据
INSERT INTO Student VALUES('95001','李勇','M',20,'CS');
INSERT INTO Student VALUES('95002','刘晨','F',19,'IS');
INSERT INTO Student VALUES('95003','王敏','F',18,'MA');
INSERT INTO Student VALUES('95004','张立','M',19,'IS');
--...........

--给Course表插入数据
INSERT INTO Course VALUES('C1','数据库','C5',4);
INSERT INTO Course VALUES('C2','数学','',2);
INSERT INTO Course VALUES('C3','信息系统','C1',4);
INSERT INTO Course VALUES('C4','操作系统','C6',3);
INSERT INTO Course VALUES('C5','数据结构','C7',4);
INSERT INTO Course VALUES('C6','编译原理','',2);
INSERT INTO Course VALUES('C7','Pascal语言','C6',4);
SELECT *
FROM COURSE;
--...........

--给SC表插入数据
INSERT INTO SC VALUES('95001','C1',92);
INSERT INTO SC VALUES('95001','C2',65);
INSERT INTO SC VALUES('95001','C4',88);
INSERT INTO SC VALUES('95002','C2',90);
INSERT INTO SC VALUES('95002','C5',73);
--...........



/********************************************
四、查询
********************************************/

--1：查询学生所有详细记录

	SELECT *
	FROM Student;
	
	SELECT Sno,Sname,Sex,Sage,Sdept
	FROM Student;
	
--2：查询所有年龄在20岁以下的学生姓名及其年龄

	SELECT Sname,Sage
	FROM Student
	WHERE Sage<20;
	
	SELECT Sname,Sage
	FROM Student
	WHERE NOT Sage>=20;
	
--3：查询信息系，数学系和计算机系学生的姓名和性别

	SELECT Sname,Sex
	FROM Student
	WHERE Sdept IN ('IS','MA','CS');
	
	SELECT Sname,Sex
	FROM Student
	WHERE Sdept='IS' OR Sdept='MA' OR Sdept='CS';
	
--4：查询每个学生及其选修课的情况

	SELECT Student.*,SC.*
	FROM Student,SC
	WHERE Student.Sno=SC.Sno;
	
	SELECT Student.Sno,Sname,Sex,Sage,Sdept,SC.Sno,Cno,Grade
	FROM Student,SC
	WHERE Student.Sno=SC.Sno;
	
--5：查询与刘晨同学在一个系的学生

	SELECT Sno,Sname,Sdept
	FROM Student
	WHERE Sdept=(
		SELECT Sdept
		FROM Student
		WHERE Sname='刘晨'
	);
	
	SELECT S1.Sno,S1.Sname,S1.Sdept
	FROM Student S1,Student S2
	WHERE S1.Sdept=S2.Sdept AND S2.Sname='刘晨';
