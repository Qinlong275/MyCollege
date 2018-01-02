/********************************************
学号：15030120044
姓名：秦龙
********************************************/

/********************************************
一、创建数据库
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
二、创建表
********************************************/

--创建供应商表S
CREATE TABLE S(
	SNO CHAR(2) UNIQUE,
	SNAME CHAR(6) ,
	STATUS CHAR(2) ,
	CITY CHAR(4)
);
GO

--创建零件表P
CREATE TABLE P(
	PNO CHAR(2) PRIMARY KEY,
	PNAME CHAR(6) ,
	COLOR CHAR(2) ,
	WEIGHT INT
);
GO

--创建工程项目表J
CREATE TABLE J(
	JNO CHAR(2) PRIMARY KEY,
	JNAME CHAR(6) ,
	CITY CHAR(4) 
);
GO

--创建供应情况表SPJ
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
三、数据更新
********************************************/

--给S表插入数据
INSERT INTO S VALUES('S1','精益','20','天津');
INSERT INTO S VALUES('S2','盛锡','10','北京');
INSERT INTO S VALUES('S3','东方红','30','北京');
INSERT INTO S VALUES('S4','丰泰盛','20','天津');
INSERT INTO S VALUES('S5','为民','30','上海');
--...........

--给P表插入数据
INSERT INTO P VALUES('P1','螺母','红',12);
INSERT INTO P VALUES('P2','螺栓','绿',17);
INSERT INTO P VALUES('P3','螺丝刀','蓝',14);
INSERT INTO P VALUES('P4','螺丝刀','红',14);
INSERT INTO P VALUES('P5','凸轮','蓝',40);
INSERT INTO P VALUES('P6','齿轮','红',30)
--...........

--修改J表的JNAME类型为CHAR(8)
ALTER TABLE J ALTER COLUMN JNAME CHAR(8);
--给J表插入数据
INSERT INTO J VALUES('J1','三建','北京');
INSERT INTO J VALUES('J2','一汽','长春');
INSERT INTO J VALUES('J3','弹簧厂','天津');
INSERT INTO J VALUES('J4','造船厂','天津');
INSERT INTO J VALUES('J5','机车厂','唐山');
INSERT INTO J VALUES('J6','无线电厂','常州');
INSERT INTO J VALUES('J7','半导体厂','南京');
--...........

--给SPJ表插入数据
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
四、查询
********************************************/

--第二章 第五题

--1：求供应工程J1零件的供应商号码SNO

	SELECT DISTINCT SNO
	FROM SPJ
	WHERE JNO='J1';

--2：求供应工程J1零件P1的供应商号码SNO

	SELECT SNO
	FROM SPJ
	WHERE JNO='J1' AND PNO='P1';
	
--3：求供应工程J1零件为红色的供应商号码SNO

	SELECT SNO
	FROM SPJ,P
	WHERE SPJ.PNO=P.PNO AND
		  SPJ.JNO='J1' AND
		  P.COLOR='红';
	
--4：求没有使用天津供应商生产的红色零件的工程号JNO

	SELECT JNO
	FROM J
	WHERE NOT EXISTS
		(SELECT *
		 FROM SPJ
		 WHERE EXISTS
			(SELECT *
			 FROM S
			 WHERE S.CITY='天津' AND EXISTS
				(SELECT *
				 FROM P
				 WHERE P.COLOR='红' AND 
					SPJ.SNO=S.SNO AND
					SPJ.PNO=P.PNO AND
					SPJ.JNO=J.JNO )));
					
	
--5：求至少用了供应商S1所供应的全部零件的工程号JNO

	SELECT DISTINCT JNO
	FROM SPJ X
	WHERE NOT EXISTS
		(SELECT *
		 FROM SPJ Y
		 WHERE Y.SNO='S1' AND NOT EXISTS 
			(SELECT *
			 FROM SPJ Z
			 WHERE Z.JNO=X.JNO AND Z.PNO=Y.PNO AND Z.SNO='S1'));

--第三章 第五题

--1: 找出所有供应商的姓名和所在城市

	SELECT SNAME, CITY
	FROM S;

--2: 找出所有零件的名称，颜色，重量

	SELECT PNAME ,COLOR ,WEIGHT
	FROM P;

--3：找出使用供应商S1所供应的零件的工程号码

	SELECT JNO
	FROM SPJ
	WHERE SNO='S1';

--4：找出工程项目J2使用的各种零件的名称及其数量

	SELECT PNAME ,QTY
	FROM P,SPJ
	WHERE SPJ.PNO=P.PNO AND SPJ.JNO='J2';

--5：找出上海厂商供应的所有零件号码

	SELECT DISTINCT PNO
	FROM S,SPJ
	WHERE SPJ.SNO=S.SNO AND S.CITY='上海';

--6：找出使用上海产的零件的工程名称

	SELECT JNAME 
	FROM J
	WHERE EXISTS 
		(SELECT *
		 FROM SPJ
		 WHERE EXISTS 
			(SELECT *
			 FROM S
			 WHERE S.CITY='上海' AND SPJ.SNO=S.SNO
				AND SPJ.JNO=J.JNO));

--7：找出没有使用天津产的零件的工程号码

	SELECT JNO
	FROM J
	WHERE NOT EXISTS
		(SELECT *
		 FROM SPJ
		 WHERE EXISTS 
			(SELECT *
			 FROM S
			 WHERE S.CITY='天津' AND SPJ.SNO=S.SNO
				AND SPJ.JNO=J.JNO));
				
/******************************************
五、创建供应商零件供应表SP表、订单表Orders表
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
六、向SP表中插入数据
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
七、创建销售存储过程
*******************************************/

/*
(一）创建过程	
	 --说明: sp表中的 balance属性必须有>=0约束。否则，销售仍可完成，但结果balance会出现负数。

--删除同名存储过程，方便开发中调试存储过程程序*/
IF OBJECT_ID ( 'PTransfer', 'P' ) IS NOT NULL 
    DROP PROCEDURE PTransfer;
GO

CREATE PROCEDURE PTransfer 
		@order_ono CHAR(4),	--订单号
		@order_sno CHAR(2),	--供应商号
		@order_pno CHAR(2),	--零件号
		@order_jno	CHAR(2),	--项目号
		@order_quantity int		--销售数量
AS
	BEGIN
			
		DECLARE @ErrorVar INT;	--声明用户变量,用于存储SQL语句的错误编号
		BEGIN TRANSACTION;
		
		--（1）**********向订单表插入订单记录***********
			
			--此供应商不供应此零件则错误
			IF NOT EXISTS (SELECT * 
			 FROM SP
			 WHERE Sno=@order_sno and Pno = @order_pno) 
			 RETURN -1;
			 
			 INSERT INTO Orders VALUES(@order_ono,@order_sno,@order_pno,@order_jno,getdate(),@order_quantity);
			--判定上一SQL语句的执行状态
			SELECT @ErrorVar = @@ERROR;	--系统变量，上一句SQL的执行状态，
							--会后被下一SQL语句重新赋值，故另存
			IF @ErrorVar != 0	--为0表示代码执行正确，非0值为系统定义的错误编号，
						--可在主调程序中查阅详细错误信息并处理
				/*BEGIN
					ROLLBACK;*/	--应该去掉，否则上一次成功的也会被回滚
					RETURN @ErrorVar;
			
		
		--（2）**********修改零件供应表中的库存量balance***********
			
			UPDATE SP
			SET balance = balance - @order_quantity
			WHERE Sno = @order_sno and Pno=@order_pno;
			--判定上一SQL语句的执行状态
			SELECT @ErrorVar = @@ERROR;	--系统变量，上一句SQL的执行状态，
							--会后被下一SQL语句重新赋值，故另存
			IF @ErrorVar != 0	--为0表示代码执行正确，非0值为系统定义的错误编号，
						--可在主调程序中查阅详细错误信息并处理
					--会重置@@ERROR，故前面用@ErrorVar另存
				BEGIN
					ROLLBACK;	--会重置@@ERROR，故前面用@ErrorVar另存
					RETURN @ErrorVar;
				END
			
			--（3）**********提交事务***********
			COMMIT;
			RETURN 0;
	END
GO

/****************************
（二）、调用该销售存储过程向数据库添加每一笔交易的信息
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
八、删除SPJ表，创建视图SPJ（结构同SPJ表）
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
--重新执行之前（四中）你写的所有与SPJ表相关的查询语句，验证这些查询语句是否能够正确执行，并说明原因。
--能正确执行，因为从用户角度，查询视图和查询基本标相同