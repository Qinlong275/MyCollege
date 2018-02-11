package com.qinlong.algsTest1;

//加权quick-union算法
//所有数组节点下标都从1开始
public class Percolation {

	private int[] isOp; // 各个节点是否打开，0为否，1为是
	private WeightedQuickUnionUF qUf;
	private int row; // 行数
	private int column; // 列数
	private int count; // 连通分量的数量
	private int num; // 已经打开的节点数目
	
	// 初始化
	public Percolation(int v,int e) {
		row = v;
		column = e;
		count = v * e;
		qUf = new WeightedQuickUnionUF(v * e + 2);
		isOp = new int[v * e + 2];
		isOp[0]=1;	//将0点假设为顶层的水入口
		isOp[v*e+1]=1;//最后一个点假设为水的出口
	}

	//打开节点 (row i, column j)
	public int open(int i, int j) {
		if (!isOpen(i, j)) {
			isOp[(i-1)*column+j]=1;
			//如果是第一行的结点就和入水口连通
			if (i==1) {
				qUf.union(0, (i - 1) * column + j);
			}
			//如果是最后一行的，就和出水口连通
			if (i==row) {
				qUf.union((i - 1) * column + j,row*column+1 );
			}
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
			//判断当前矩阵是否可以渗漏
			if (percolates()) {
				return -1;
			}
		}
		return 0;
	}

	// 判断节点 (row i, column j) 是否打开
		public boolean isOpen(int i, int j) {
			if (isOp[(i - 1) * column + j] == 1) {
				return true;
			}
			return false;
		}

		public void union(int i, int j, int m, int n) {
			qUf.union((i - 1) * column + j, (m - 1) * column + n);
		}
		
		//判断当前是否可以渗漏，只要入口和出口连通即可
		public boolean percolates() {
			if (qUf.connected(0, row*column+1)) {
				return true;
			}
			return false;
		}
}
