/********************************************
ѧ�ţ�15030120044
����������
********************************************/

/********************************************
һ���������ݿ�
********************************************/
USE master;
GO

CREATE DATABASE spj_db 
ON
PRIMARY  
    (NAME = stud,
    FILENAME = 'F:\SQLData\Spjdat1.mdf',
    SIZE = 100MB,
    MAXSIZE = 200,
    FILEGROWTH = 20)
    
LOG ON 
   (NAME = Studlog,
    FILENAME = 'F:\SQLData\Spjlog1.ldf',
    SIZE = 100MB,
    MAXSIZE = 200,
    FILEGROWTH = 20)
    ;
GO

USE spj_db;
GO

/********************************************
����������
********************************************/

--������Ӧ�̱�S
CREATE TABLE S(
	SNO CHAR(2) UNIQUE,
	SNAME CHAR(6) ,
	STATUS CHAR(2) ,
	CITY CHAR(4)
);
GO

--���������P
CREATE TABLE P(
	PNO CHAR(2) PRIMARY KEY,
	PNAME CHAR(6) ,
	COLOR CHAR(2) ,
	WEIGHT INT
);
GO

--����������Ŀ��J
CREATE TABLE J(
	JNO CHAR(2) PRIMARY KEY,
	JNAME CHAR(6) ,
	CITY CHAR(4) 
);
GO

--������Ӧ�����SPJ
CREATE TABLE SPJ(
	SNO CHAR(2),
	PNO CHAR(2) ,
	JNO CHAR(2) ,
	QTY INT ,
	PRIMARY KEY (SNO ,PNO ,JNO),
	FOREIGN KEY (SNO) REFERENCES S(SNO),
	FOREIGN KEY (PNO) REFERENCES P(PNO), 
	FOREIGN KEY (JNO) REFERENCES J(JNO)
);
GO

--...........

/********************************************
�������ݸ���
********************************************/

--��S���������
INSERT INTO S VALUES('S1','����','20','���');
INSERT INTO S VALUES('S2','ʢ��','10','����');
INSERT INTO S VALUES('S3','������','30','����');
INSERT INTO S VALUES('S4','��̩ʢ','20','���');
INSERT INTO S VALUES('S5','Ϊ��','30','�Ϻ�');
--...........

--��P���������
INSERT INTO P VALUES('P1','��ĸ','��',12);
INSERT INTO P VALUES('P2','��˨','��',17);
INSERT INTO P VALUES('P3','��˿��','��',14);
INSERT INTO P VALUES('P4','��˿��','��',14);
INSERT INTO P VALUES('P5','͹��','��',40);
INSERT INTO P VALUES('P6','����','��',30)
--...........

--�޸�J���JNAME����ΪCHAR(8)
ALTER TABLE J ALTER COLUMN JNAME CHAR(8);
--��J���������
INSERT INTO J VALUES('J1','����','����');
INSERT INTO J VALUES('J2','һ��','����');
INSERT INTO J VALUES('J3','���ɳ�','���');
INSERT INTO J VALUES('J4','�촬��','���');
INSERT INTO J VALUES('J5','������','��ɽ');
INSERT INTO J VALUES('J6','���ߵ糧','����');
INSERT INTO J VALUES('J7','�뵼�峧','�Ͼ�');
--...........

--��SPJ���������
INSERT INTO SPJ VALUES('S1','P1','J1',200);
INSERT INTO SPJ VALUES('S1','P1','J3',100);
INSERT INTO SPJ VALUES('S1','P1','J4',700);
INSERT INTO SPJ VALUES('S1','P2','J2',100);
INSERT INTO SPJ VALUES('S2','P3','J1',400);
INSERT INTO SPJ VALUES('S2','P3','J2',200);
INSERT INTO SPJ VALUES('S2','P3','J4',500);
INSERT INTO SPJ VALUES('S2','P3','J5',400);
INSERT INTO SPJ VALUES('S2','P5','J1',400);
INSERT INTO SPJ VALUES('S2','P5','J2',100);
INSERT INTO SPJ VALUES('S3','P1','J1',200);
INSERT INTO SPJ VALUES('S3','P3','J1',200);
INSERT INTO SPJ VALUES('S4','P5','J1',100);
INSERT INTO SPJ VALUES('S4','P6','J3',300);
INSERT INTO SPJ VALUES('S4','P6','J4',200);
INSERT INTO SPJ VALUES('S5','P2','J4',100);
INSERT INTO SPJ VALUES('S5','P3','J1',200);
INSERT INTO SPJ VALUES('S5','P6','J2',200);
INSERT INTO SPJ VALUES('S5','P6','J4',500);
--...........

/********************************************
�ġ���ѯ
********************************************/

--�ڶ��� ������

--1����Ӧ����J1����Ĺ�Ӧ�̺���SNO

	SELECT DISTINCT SNO
	FROM SPJ
	WHERE JNO='J1';

--2����Ӧ����J1���P1�Ĺ�Ӧ�̺���SNO

	SELECT SNO
	FROM SPJ
	WHERE JNO='J1' AND PNO='P1';
	
--3����Ӧ����J1���Ϊ��ɫ�Ĺ�Ӧ�̺���SNO

	SELECT SNO
	FROM SPJ,P
	WHERE SPJ.PNO=P.PNO AND
		  SPJ.JNO='J1' AND
		  P.COLOR='��';
	
--4����û��ʹ�����Ӧ�������ĺ�ɫ����Ĺ��̺�JNO

	SELECT JNO
	FROM J
	WHERE NOT EXISTS
		(SELECT *
		 FROM SPJ
		 WHERE EXISTS
			(SELECT *
			 FROM S
			 WHERE S.CITY='���' AND EXISTS
				(SELECT *
				 FROM P
				 WHERE P.COLOR='��' AND 
					SPJ.SNO=S.SNO AND
					SPJ.PNO=P.PNO AND
					SPJ.JNO=J.JNO )));
					
	
--5�����������˹�Ӧ��S1����Ӧ��ȫ������Ĺ��̺�JNO

	SELECT DISTINCT JNO
	FROM SPJ X
	WHERE NOT EXISTS
		(SELECT *
		 FROM SPJ Y
		 WHERE Y.SNO='S1' AND NOT EXISTS 
			(SELECT *
			 FROM SPJ Z
			 WHERE Z.JNO=X.JNO AND Z.PNO=Y.PNO AND Z.SNO='S1'));

--������ ������

--1: �ҳ����й�Ӧ�̵����������ڳ���

	SELECT SNAME, CITY
	FROM S;

--2: �ҳ�������������ƣ���ɫ������

	SELECT PNAME ,COLOR ,WEIGHT
	FROM P;

--3���ҳ�ʹ�ù�Ӧ��S1����Ӧ������Ĺ��̺���

	SELECT JNO
	FROM SPJ
	WHERE SNO='S1';

--4���ҳ�������ĿJ2ʹ�õĸ�����������Ƽ�������

	SELECT PNAME ,QTY
	FROM P,SPJ
	WHERE SPJ.PNO=P.PNO AND SPJ.JNO='J2';

--5���ҳ��Ϻ����̹�Ӧ�������������

	SELECT DISTINCT PNO
	FROM S,SPJ
	WHERE SPJ.SNO=S.SNO AND S.CITY='�Ϻ�';

--6���ҳ�ʹ���Ϻ���������Ĺ�������

	SELECT JNAME 
	FROM J
	WHERE EXISTS 
		(SELECT *
		 FROM SPJ
		 WHERE EXISTS 
			(SELECT *
			 FROM S
			 WHERE S.CITY='�Ϻ�' AND SPJ.SNO=S.SNO
				AND SPJ.JNO=J.JNO));

--7���ҳ�û��ʹ������������Ĺ��̺���

	SELECT JNO
	FROM J
	WHERE NOT EXISTS
		(SELECT *
		 FROM SPJ
		 WHERE EXISTS 
			(SELECT *
			 FROM S
			 WHERE S.CITY='���' AND SPJ.SNO=S.SNO
				AND SPJ.JNO=J.JNO));
				
/******************************************
�塢������Ӧ�������Ӧ��SP��������Orders��
*******************************************/
CREATE TABLE SP(
	Sno CHAR(2),	
	Pno CHAR(2),
	balance int CHECK(balance >= 0),
	PRIMARY KEY (Sno,Pno),
	FOREIGN KEY (Sno) REFERENCES S(Sno),
	FOREIGN KEY (Pno) REFERENCES P(Pno),
);

CREATE TABLE Orders(
	Ono CHAR(4),
	Sno CHAR(2),
	Pno CHAR(2),
	Jno CHAR(2),
	Otime DATETIME,  
	quantity int CHECK(quantity >= 0),
	PRIMARY KEY (Ono),
	FOREIGN KEY (Sno) REFERENCES S(Sno),
	FOREIGN KEY (Pno) REFERENCES P(Pno),
	FOREIGN KEY (Jno) REFERENCES J(Jno),
);

/******************************************
������SP���в�������
*******************************************/
INSERT INTO SP VALUES('S1','P1',200);
INSERT INTO SP VALUES('S1','P2',100);
INSERT INTO SP VALUES('S1','P3',700);
INSERT INTO SP VALUES('S2','P2',100);
INSERT INTO SP VALUES('S2','P5',400);
INSERT INTO SP VALUES('S3','P3',200);
INSERT INTO SP VALUES('S3','P4',500);
INSERT INTO SP VALUES('S4','P5',400);
INSERT INTO SP VALUES('S4','P6',200);
INSERT INTO SP VALUES('S5','P1',500);
INSERT INTO SP VALUES('S5','P4',400);

Go

/******************************************
�ߡ��������۴洢����
*******************************************/

/*
(һ����������	
	 --˵��: sp���е� balance���Ա�����>=0Լ�������������Կ���ɣ������balance����ָ�����

--ɾ��ͬ���洢���̣����㿪���е��Դ洢���̳���*/
IF OBJECT_ID ( 'PTransfer', 'P' ) IS NOT NULL 
    DROP PROCEDURE PTransfer;
GO

CREATE PROCEDURE PTransfer 
		@order_ono CHAR(4),	--������
		@order_sno CHAR(2),	--��Ӧ�̺�
		@order_pno CHAR(2),	--�����
		@order_jno	CHAR(2),	--��Ŀ��
		@order_quantity int		--��������
AS
	BEGIN
			
		DECLARE @ErrorVar INT;	--�����û�����,���ڴ洢SQL���Ĵ�����
		BEGIN TRANSACTION;
		
		--��1��**********�򶩵�����붩����¼***********
			
			--�˹�Ӧ�̲���Ӧ����������
			IF NOT EXISTS (SELECT * 
			 FROM SP
			 WHERE Sno=@order_sno and Pno = @order_pno) 
			 RETURN -1;
			 
			 INSERT INTO Orders VALUES(@order_ono,@order_sno,@order_pno,@order_jno,getdate(),@order_quantity);
			--�ж���һSQL����ִ��״̬
			SELECT @ErrorVar = @@ERROR;	--ϵͳ��������һ��SQL��ִ��״̬��
							--�����һSQL������¸�ֵ�������
			IF @ErrorVar != 0	--Ϊ0��ʾ����ִ����ȷ����0ֵΪϵͳ����Ĵ����ţ�
						--�������������в�����ϸ������Ϣ������
				/*BEGIN
					ROLLBACK;*/	--Ӧ��ȥ����������һ�γɹ���Ҳ�ᱻ�ع�
					RETURN @ErrorVar;
			
		
		--��2��**********�޸������Ӧ���еĿ����balance***********
			
			UPDATE SP
			SET balance = balance - @order_quantity
			WHERE Sno = @order_sno and Pno=@order_pno;
			--�ж���һSQL����ִ��״̬
			SELECT @ErrorVar = @@ERROR;	--ϵͳ��������һ��SQL��ִ��״̬��
							--�����һSQL������¸�ֵ�������
			IF @ErrorVar != 0	--Ϊ0��ʾ����ִ����ȷ����0ֵΪϵͳ����Ĵ����ţ�
						--�������������в�����ϸ������Ϣ������
					--������@@ERROR����ǰ����@ErrorVar���
				BEGIN
					ROLLBACK;	--������@@ERROR����ǰ����@ErrorVar���
					RETURN @ErrorVar;
				END
			
			--��3��**********�ύ����***********
			COMMIT;
			RETURN 0;
	END
GO

/****************************
�����������ø����۴洢���������ݿ����ÿһ�ʽ��׵���Ϣ
*****************************/

EXECUTE PTransfer '1001', 'S1', 'P5', 'J1',20

EXECUTE PTransfer '1002', 'S1', 'P1', 'J1',20

EXECUTE PTransfer '1003', 'S1', 'P2', 'J2',30

EXECUTE PTransfer '1004', 'S1', 'P4', 'J5',50

EXECUTE PTransfer '1005', 'S1', 'P3', 'J6',20

EXECUTE PTransfer '1006', 'S1', 'P1', 'J1',50


EXECUTE PTransfer '1007', 'S2', 'P1', 'J3',20

EXECUTE PTransfer '1008', 'S2', 'P3', 'J4',50

EXECUTE PTransfer '1009', 'S2', 'P2', 'J1',15

EXECUTE PTransfer '1010', 'S2', 'P6', 'J6',30

EXECUTE PTransfer '1011', 'S2', 'P1', 'J4',20

EXECUTE PTransfer '1012', 'S2', 'P4', 'J1',45


EXECUTE PTransfer '1013', 'S3', 'P1', 'J1',20

EXECUTE PTransfer '1014', 'S3', 'P4', 'J2',24

EXECUTE PTransfer '1015', 'S3', 'P5', 'J2',22

EXECUTE PTransfer '1016', 'S3', 'P5', 'J4',35

EXECUTE PTransfer '1017', 'S3', 'P6', 'J5',21

EXECUTE PTransfer '1018', 'S3', 'P2', 'J6',55


EXECUTE PTransfer '1019', 'S4', 'P1', 'J2',24

EXECUTE PTransfer '1020', 'S4', 'P4', 'J1',32

EXECUTE PTransfer '1021', 'S4', 'P5', 'J4',20

EXECUTE PTransfer '1022', 'S4', 'P2', 'J3',26

EXECUTE PTransfer '1023', 'S4', 'P3', 'J5',12

EXECUTE PTransfer '1024', 'S4', 'P1', 'J1',26


EXECUTE PTransfer '1025', 'S5', 'P1', 'J1',20

EXECUTE PTransfer '1026', 'S5', 'P3', 'J5',23

EXECUTE PTransfer '1027', 'S5', 'P5', 'J4',11

EXECUTE PTransfer '1028', 'S5', 'P4', 'J3',2

EXECUTE PTransfer '1029', 'S5', 'P2', 'J1',22

EXECUTE PTransfer '1030', 'S5', 'P3', 'J2',25

SELECT *
FROM SP;

SELECT *
FROM Orders;


GO
/******************************************
�ˡ�ɾ��SPJ��������ͼSPJ���ṹͬSPJ��
*******************************************/

DROP TABLE SPJ;

IF OBJECT_ID ('SPJ', 'V') IS NOT NULL
    DROP VIEW SPJ ;
GO

CREATE VIEW SPJ(SNO,PNO,JNO,QTY)
AS
	SELECT Sno,Pno,Jno,SUM(quantity)
	FROM Orders
	GROUP BY Sno,Pno,Jno;
GO

SELECT * 
FROM SPJ;
--����ִ��֮ǰ�����У���д��������SPJ����صĲ�ѯ��䣬��֤��Щ��ѯ����Ƿ��ܹ���ȷִ�У���˵��ԭ��
--����ȷִ�У���Ϊ���û��Ƕȣ���ѯ��ͼ�Ͳ�ѯ��������ͬ