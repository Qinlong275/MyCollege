package com.qinlong.algsTest1;

//所有数组节点下标都从1开始
public class Percolation {

	private int []id;	//父链接数组
	private int []size;	//各个根结点对应的分量大小
	private int []isOp;	//各个节点是否打开，0为否，1为是
	private int row;	//行数
	private int column;		//列数
	private int count;	//连通分量的数量
	private int num;	//已经打开的节点数目
	
	// 初始化
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

	//打开节点 (row i, column j)
	public void open(int i, int j) {
		if (!isOpen(i, j)) {
			isOp[(i-1)*column+j]=1;
			
			//如果此节点的相邻节点也打开了则连接它们
			if (i>1) {
				if (isOpen(i-1, j)) {
					union(i-1, j, i, j);
				}
			}
			
			//防止下边界越界
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
			
			
			//防止右边界越界
			if (j<column) {
				if (isOpen(i, j+1)) {
					union(i, j+1, i, j);
				}
			}
			
			num++;
			if (percolates()) {
				System.out.println("打开了 "+num+" 个节点时系统可以渗透 ");
				System.out.println("渗透阈值为： "+num*1.0/(row*column));
				System.exit(0);//结束程序
			}
		}	
	}

	// 判断节点 (row i, column j) 是否打开
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
		//跟随连接找到根节点
		int p=(i-1)*column+j;
		while (p!=id[(i-1)*column+j]) {
			if (id[(i-1)*column+j]%column==0) {//能否整除Column情况不同
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
		
		//将小树根结点连接到大树根结点上
		if (size[x]<size[y]) {
			id[x]=y;
			size[y]+=size[x];
		}else {
			id[y]=x;
			size[x]+=size[y];
		}
		count--;
	}
//	// 判断节点节点 (row i, column j) 是否充水
//	public boolean isFull(int i,int j){
//		
//		return false;
//	}

	//判断系统是否可以渗透
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
