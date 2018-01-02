/*****************************************************************************************************
说明：本程序包含两部分功能
	  1. 创建存储过程（应单独执行，且执行一次即可！），请参考联机文档自学存储过程相关知识
	  2. 调用存储过程
		 在系统开发过程中，存储过程是被客户端或Web服务器端的其它高级语言程序所调用的。
		 本例为方便学习，仍使用服务器端调用，可理解为对存储过程的测试。
******************************************************************************************************/
USE bank
GO

/****************************
0附：创建账户account表及插入数据
*****************************/
CREATE TABLE account(
	account_number CHAR(10),	--账号
	branch_name CHAR(15)  NOT NULL,	--开户支行
	balance NUMERIC(12,2) CHECK (balance >= 0),	--账户余额
	PRIMARY KEY (account_number)
);
GO

INSERT INTO account VALUES ('A-101','Downtown','505');
INSERT INTO account VALUES ('A-102','Perryridge','400');
INSERT INTO account VALUES ('A-201','Brighton','900');
INSERT INTO account VALUES ('A-215','Mianus','700');
INSERT INTO account VALUES ('A-217','Brighton','750');
INSERT INTO account VALUES ('A-222','Redwood','700');
INSERT INTO account VALUES ('A-305','Round','350');
INSERT INTO account VALUES ('A-213','Perryridge','250');

INSERT INTO account VALUES ('A-214','Perryridge',NULL);
GO

/****************************
一、创建 转账存储过程
*****************************/
--说明: account表中的 balance属性必须有>=0约束。否则，转账仍可完成，但结果balance会出现负数。

--删除同名存储过程，方便开发中调试存储过程程序
IF OBJECT_ID ( 'PTransfer', 'P' ) IS NOT NULL 
    DROP PROCEDURE PTransfer;
GO

CREATE PROCEDURE PTransfer 
		@account_no_x varchar(30),	--x账号
		@account_no_y varchar(30),	--y账号
		@amount_k NUMERIC(8,2)		--转账金额
AS
	BEGIN
		--问题1：注释掉下句代码的存储过程，分别用空记录(不存在的账号参与转账)和balance为空值的记录进行转账测试，会发现什么问题？
		--问题2：转账过程中的两个账户不能出现空记录或余额为空值。为什么？
		/*IF  (SELECT balance 
			 FROM account 
			 WHERE account_number = @account_no_x) IS NULL
			OR
			(SELECT balance 
			 FROM account 
			 WHERE account_number = @account_no_y) IS NULL
			RETURN -1;
		*/
			
		DECLARE @ErrorVar INT;	--声明用户变量,用于存储SQL语句的错误编号
		BEGIN TRANSACTION;
			--为方便观察数据，把教材的两步次序互换，减x时出错回滚，先前已修改的y账号值会还原。
			--即使第一句出错，对数据库未作修改，为保持程序规范性，也要求回滚！！！

			--（2）**********y账号加上k元***********
			UPDATE account
			SET balance = balance + @amount_k
			WHERE account_number = @account_no_y;
			--判定上一SQL语句的执行状态
			SELECT @ErrorVar = @@ERROR;	--系统变量，上一句SQL的执行状态，
							--会后被下一SQL语句重新赋值，故另存
			IF @ErrorVar != 0	--为0表示代码执行正确，非0值为系统定义的错误编号，
						--可在主调程序中查阅详细错误信息并处理
				BEGIN
					ROLLBACK;	--会重置@@ERROR，故前面用@ErrorVar另存
					RETURN @ErrorVar;
				END
			
			--（1）**********X账号减去k元***********
			UPDATE account
			SET balance = balance - @amount_k
			WHERE account_number = @account_no_x;
			--判定上一SQL语句的执行状态
			SELECT @ErrorVar = @@ERROR;	
			IF @ErrorVar != 0	
				BEGIN
					ROLLBACK;	
					RETURN @ErrorVar;
				END
			    
			--（3）**********提交事务***********
			COMMIT;
			RETURN 0;
	END
GO


/****************************
二、测试 转账存储过程
*****************************/

--测试1. *******转账成功*******
--1）先记录下转账前余额

--2）调用存储过程，实现转账
DECLARE @retstat int; --执行状态
EXECUTE @retstat = PTransfer 'A-101', 'A-201', 100
SELECT @retstat	--显示存储过程执行的返回值，用于调试程序，可注释掉
IF @retstat = 0
	SELECT '转账成功。'
ELSE 
	SELECT '转账失败！'
--注释：主调程序（可以是其它高级语言）获取存储过程的出错代码，可据此查找具体的出错原因。
	
--3）对比转账前数据，观察数据是否正确


--2. *******转账失败(违反“余额大于0”约束)，事务回滚，数据库仍保持一致性*******
--可多次执行转账调用或修改转账金额，使存储过程违反约束，以测试程序的执行状态。


--<完>

