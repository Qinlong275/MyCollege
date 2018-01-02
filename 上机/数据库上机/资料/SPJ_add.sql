/******************************************
�塢������Ӧ�������Ӧ��SP��������Orders��
*******************************************/
CREATE TABLE SP(
	Sno CHAR(4),	--�������������S���Sno���Ȳ�ͬ���뽫�˴��޸ĳ��㶨��ĳ��ȣ���ͬ��
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
�����������۴洢����
*******************************************/


/******************************************
�ߡ�ɾ��SPJ��������ͼSPJ���ṹͬSPJ��
*******************************************/


--����ִ��֮ǰ�����У���д��������SPJ����صĲ�ѯ��䣬��֤��Щ��ѯ����Ƿ��ܹ���ȷִ�У���˵��ԭ��
