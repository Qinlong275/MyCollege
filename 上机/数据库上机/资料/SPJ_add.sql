/******************************************
五、创建供应商零件供应表SP表、订单表Orders表
*******************************************/
CREATE TABLE SP(
	Sno CHAR(4),	--若与你所定义的S表的Sno长度不同，请将此处修改成你定义的长度，下同。
	Pno CHAR(4),
	balance int CHECK(balance >= 0),
	PRIMARY KEY (Sno,Pno),
	FOREIGN KEY (Sno) REFERENCES S(Sno),
	FOREIGN KEY (Pno) REFERENCES P(Pno),
);

CREATE TABLE Orders(
	Ono CHAR(4),
	Sno CHAR(4),
	Pno CHAR(4),
	Jno CHAR(4),
	Otime DATETIME,  
	quantity int CHECK(quantity >= 0),
	PRIMARY KEY (Ono),
	FOREIGN KEY (Sno) REFERENCES S(Sno),
	FOREIGN KEY (Pno) REFERENCES P(Pno),
	FOREIGN KEY (Jno) REFERENCES J(Jno),
);

/******************************************
六、创建销售存储过程
*******************************************/


/******************************************
七、删除SPJ表，创建视图SPJ（结构同SPJ表）
*******************************************/


--重新执行之前（四中）你写的所有与SPJ表相关的查询语句，验证这些查询语句是否能够正确执行，并说明原因。
