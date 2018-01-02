/*****************************************************************************************************
˵������������������ֹ���
	  1. �����洢���̣�Ӧ����ִ�У���ִ��һ�μ��ɣ�������ο������ĵ���ѧ�洢�������֪ʶ
	  2. ���ô洢����
		 ��ϵͳ���������У��洢�����Ǳ��ͻ��˻�Web�������˵������߼����Գ��������õġ�
		 ����Ϊ����ѧϰ����ʹ�÷������˵��ã������Ϊ�Դ洢���̵Ĳ��ԡ�
******************************************************************************************************/
USE bank
GO

/****************************
0���������˻�account����������
*****************************/
CREATE TABLE account(
	account_number CHAR(10),	--�˺�
	branch_name CHAR(15)  NOT NULL,	--����֧��
	balance NUMERIC(12,2) CHECK (balance >= 0),	--�˻����
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
һ������ ת�˴洢����
*****************************/
--˵��: account���е� balance���Ա�����>=0Լ��������ת���Կ���ɣ������balance����ָ�����

--ɾ��ͬ���洢���̣����㿪���е��Դ洢���̳���
IF OBJECT_ID ( 'PTransfer', 'P' ) IS NOT NULL 
    DROP PROCEDURE PTransfer;
GO

CREATE PROCEDURE PTransfer 
		@account_no_x varchar(30),	--x�˺�
		@account_no_y varchar(30),	--y�˺�
		@amount_k NUMERIC(8,2)		--ת�˽��
AS
	BEGIN
		--����1��ע�͵��¾����Ĵ洢���̣��ֱ��ÿռ�¼(�����ڵ��˺Ų���ת��)��balanceΪ��ֵ�ļ�¼����ת�˲��ԣ��ᷢ��ʲô���⣿
		--����2��ת�˹����е������˻����ܳ��ֿռ�¼�����Ϊ��ֵ��Ϊʲô��
		/*IF  (SELECT balance 
			 FROM account 
			 WHERE account_number = @account_no_x) IS NULL
			OR
			(SELECT balance 
			 FROM account 
			 WHERE account_number = @account_no_y) IS NULL
			RETURN -1;
		*/
			
		DECLARE @ErrorVar INT;	--�����û�����,���ڴ洢SQL���Ĵ�����
		BEGIN TRANSACTION;
			--Ϊ����۲����ݣ��ѽ̲ĵ��������򻥻�����xʱ����ع�����ǰ���޸ĵ�y�˺�ֵ�ỹԭ��
			--��ʹ��һ����������ݿ�δ���޸ģ�Ϊ���ֳ���淶�ԣ�ҲҪ��ع�������

			--��2��**********y�˺ż���kԪ***********
			UPDATE account
			SET balance = balance + @amount_k
			WHERE account_number = @account_no_y;
			--�ж���һSQL����ִ��״̬
			SELECT @ErrorVar = @@ERROR;	--ϵͳ��������һ��SQL��ִ��״̬��
							--�����һSQL������¸�ֵ�������
			IF @ErrorVar != 0	--Ϊ0��ʾ����ִ����ȷ����0ֵΪϵͳ����Ĵ����ţ�
						--�������������в�����ϸ������Ϣ������
				BEGIN
					ROLLBACK;	--������@@ERROR����ǰ����@ErrorVar���
					RETURN @ErrorVar;
				END
			
			--��1��**********X�˺ż�ȥkԪ***********
			UPDATE account
			SET balance = balance - @amount_k
			WHERE account_number = @account_no_x;
			--�ж���һSQL����ִ��״̬
			SELECT @ErrorVar = @@ERROR;	
			IF @ErrorVar != 0	
				BEGIN
					ROLLBACK;	
					RETURN @ErrorVar;
				END
			    
			--��3��**********�ύ����***********
			COMMIT;
			RETURN 0;
	END
GO


/****************************
�������� ת�˴洢����
*****************************/

--����1. *******ת�˳ɹ�*******
--1���ȼ�¼��ת��ǰ���

--2�����ô洢���̣�ʵ��ת��
DECLARE @retstat int; --ִ��״̬
EXECUTE @retstat = PTransfer 'A-101', 'A-201', 100
SELECT @retstat	--��ʾ�洢����ִ�еķ���ֵ�����ڵ��Գ��򣬿�ע�͵�
IF @retstat = 0
	SELECT 'ת�˳ɹ���'
ELSE 
	SELECT 'ת��ʧ�ܣ�'
--ע�ͣ��������򣨿����������߼����ԣ���ȡ�洢���̵ĳ�����룬�ɾݴ˲��Ҿ���ĳ���ԭ��
	
--3���Ա�ת��ǰ���ݣ��۲������Ƿ���ȷ


--2. *******ת��ʧ��(Υ����������0��Լ��)������ع������ݿ��Ա���һ����*******
--�ɶ��ִ��ת�˵��û��޸�ת�˽�ʹ�洢����Υ��Լ�����Բ��Գ����ִ��״̬��


--<��>

