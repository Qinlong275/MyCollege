package com.qinlong.algsTest1;

//��������ڵ��±궼��1��ʼ
public class Percolation {

	private int []id;	//����������
	private int []size;	//����������Ӧ�ķ�����С
	private int []isOp;	//�����ڵ��Ƿ�򿪣�0Ϊ��1Ϊ��
	private int row;	//����
	private int column;		//����
	private int count;	//��ͨ����������
	private int num;	//�Ѿ��򿪵Ľڵ���Ŀ
	
	// ��ʼ��
	public Percolation(int n,int y) {
		row=n;
		column=y;
		count=n*y;
		id=new int[count+1];
		for (int i = 1; i < count+1; i++) {
			id[i]=i;
		}
		
		size=new int[count+1];
		for (int i = 1; i < count+1; i++) {
			size[i]=1;
		}
		
		isOp=new int[count+1];
		for (int i = 1; i < count+1; i++) {
			isOp[i]=0;
		}
	}

	//�򿪽ڵ� (row i, column j)
	public void open(int i, int j) {
		if (!isOpen(i, j)) {
			isOp[(i-1)*column+j]=1;
			
			//����˽ڵ�����ڽڵ�Ҳ��������������
			if (i>1) {
				if (isOpen(i-1, j)) {
					union(i-1, j, i, j);
				}
			}
			
			//��ֹ�±߽�Խ��
			if (i<row) {
				if (isOpen(i+1, j)) {
					union(i+1, j, i, j);
				}
			}
			
			if (j>1) {
				if (isOpen(i, j-1)) {
					union(i, j-1, i, j);
				}
			}
			
			
			//��ֹ�ұ߽�Խ��
			if (j<column) {
				if (isOpen(i, j+1)) {
					union(i, j+1, i, j);
				}
			}
			
			num++;
			if (percolates()) {
				System.out.println("���� "+num+" ���ڵ�ʱϵͳ������͸ ");
				System.out.println("��͸��ֵΪ�� "+num*1.0/(row*column));
				System.exit(0);//��������
			}
		}	
	}

	// �жϽڵ� (row i, column j) �Ƿ��
	public boolean isOpen(int i,int j){
		if (isOp[(i-1)*column+j]==1) {
			return true;
		}
		return false;
	}

	public int count() {
		return count;
	}
	
	public boolean connected(int i,int j,int m,int n) {
		return find(i,j)==find(m,n);
	}
	
	public int find(int i,int j) {
		//���������ҵ����ڵ�
		int p=(i-1)*column+j;
		while (p!=id[(i-1)*column+j]) {
			if (id[(i-1)*column+j]%column==0) {//�ܷ�����Column�����ͬ
				i=id[(i-1)*column+j]/column;
				j=column;
			}else {
				int qi=id[(i-1)*column+j]/column+1;
				j=id[(i-1)*column+j]-(qi-1)*column;
				i=qi;
			}	
			p=(i-1)*column+j;
		}
		return p;
	}
	
	public void union(int i,int j,int m,int n) {
		int x=find(i,j);
		int y=find(m,n);
		if (x==y) {
			return;
		}
		
		//��С����������ӵ������������
		if (size[x]<size[y]) {
			id[x]=y;
			size[y]+=size[x];
		}else {
			id[y]=x;
			size[x]+=size[y];
		}
		count--;
	}
//	// �жϽڵ�ڵ� (row i, column j) �Ƿ��ˮ
//	public boolean isFull(int i,int j){
//		
//		return false;
//	}

	//�ж�ϵͳ�Ƿ������͸
	public boolean percolates() {
		for(int i=1;i<column+1;i++){
			for (int j = 1; j < column; j++) {
				if (connected(1, i, row, j)) {
					return true;
				}
			}
			
		}
		return false;
	}
}
