/********************************************
ѧ�ţ�15030120044
����������
********************************************/

/********************************************
һ���������ݿ�
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
����������
********************************************/

--������Student
CREATE TABLE Student(
	Sno CHAR(5) NOT NULL UNIQUE,
	Sname CHAR(20) UNIQUE,
	Sex CHAR(1) CHECK(Sex IN ('M','F')),
	Sage INT,
	Sdept CHAR(15)
);
GO

--������Course
CREATE TABLE Course(
	Cno CHAR(4) PRIMARY KEY,
	Cname CHAR(40) ,
	Cpno CHAR(4) ,
	Ccredit SMALLINT,
);
GO

--������SC
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
�������ݸ���
********************************************/

--��Student���������
INSERT INTO Student VALUES('95001','����','M',20,'CS');
INSERT INTO Student VALUES('95002','����','F',19,'IS');
INSERT INTO Student VALUES('95003','����','F',18,'MA');
INSERT INTO Student VALUES('95004','����','M',19,'IS');
--...........

--��Course���������
INSERT INTO Course VALUES('C1','���ݿ�','C5',4);
INSERT INTO Course VALUES('C2','��ѧ','',2);
INSERT INTO Course VALUES('C3','��Ϣϵͳ','C1',4);
INSERT INTO Course VALUES('C4','����ϵͳ','C6',3);
INSERT INTO Course VALUES('C5','���ݽṹ','C7',4);
INSERT INTO Course VALUES('C6','����ԭ��','',2);
INSERT INTO Course VALUES('C7','Pascal����','C6',4);
SELECT *
FROM COURSE;
--...........

--��SC���������
INSERT INTO SC VALUES('95001','C1',92);
INSERT INTO SC VALUES('95001','C2',65);
INSERT INTO SC VALUES('95001','C4',88);
INSERT INTO SC VALUES('95002','C2',90);
INSERT INTO SC VALUES('95002','C5',73);
--...........



/********************************************
�ġ���ѯ
********************************************/

--1����ѯѧ��������ϸ��¼

	SELECT *
	FROM Student;
	
	SELECT Sno,Sname,Sex,Sage,Sdept
	FROM Student;
	
--2����ѯ����������20�����µ�ѧ��������������

	SELECT Sname,Sage
	FROM Student
	WHERE Sage<20;
	
	SELECT Sname,Sage
	FROM Student
	WHERE NOT Sage>=20;
	
--3����ѯ��Ϣϵ����ѧϵ�ͼ����ϵѧ�����������Ա�

	SELECT Sname,Sex
	FROM Student
	WHERE Sdept IN ('IS','MA','CS');
	
	SELECT Sname,Sex
	FROM Student
	WHERE Sdept='IS' OR Sdept='MA' OR Sdept='CS';
	
--4����ѯÿ��ѧ������ѡ�޿ε����

	SELECT Student.*,SC.*
	FROM Student,SC
	WHERE Student.Sno=SC.Sno;
	
	SELECT Student.Sno,Sname,Sex,Sage,Sdept,SC.Sno,Cno,Grade
	FROM Student,SC
	WHERE Student.Sno=SC.Sno;
	
--5����ѯ������ͬѧ��һ��ϵ��ѧ��

	SELECT Sno,Sname,Sdept
	FROM Student
	WHERE Sdept=(
		SELECT Sdept
		FROM Student
		WHERE Sname='����'
	);
	
	SELECT S1.Sno,S1.Sname,S1.Sdept
	FROM Student S1,Student S2
	WHERE S1.Sdept=S2.Sdept AND S2.Sname='����';
