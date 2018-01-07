package com.qinlong.algsTest1;

//quick-find�㷨
public class PercolationQf {

	private int[] isOp; // �����ڵ��Ƿ�򿪣�0Ϊ��1Ϊ��
	private QuickFindUF qUf;
	private int row; // ����
	private int column; // ����
	private int count; // ��ͨ����������
	private int num; // �Ѿ��򿪵Ľڵ���Ŀ

	public PercolationQf(int v, int e) {
		row = v;
		column = e;
		count = v * e;
		qUf = new QuickFindUF(v * e + 2);
		isOp = new int[v * e + 2];
		isOp[0]=1;	//��0�����Ϊ�����ˮ���
		isOp[v*e+1]=1;//���һ�������Ϊˮ�ĳ���
	}

	// �򿪽ڵ� (row i, column j)
	public int open(int i, int j) {
		if (!isOpen(i, j)) {
			isOp[(i - 1) * column + j] = 1;
			//����ǵ�һ�еĽ��ͺ���ˮ����ͨ
			if (i==1) {
				qUf.union(0, (i - 1) * column + j);
			}
			
			//��������һ�еģ��ͺͳ�ˮ����ͨ
			if (i==row) {
				qUf.union((i - 1) * column + j,row*column+1 );
			}

			// ����˽ڵ�����ڽڵ�Ҳ��������������
			if (i > 1) {
				if (isOpen(i - 1, j)) {
					union(i - 1, j, i, j);
				}
			}

			// ��ֹ�±߽�Խ��
			if (i < row) {
				if (isOpen(i + 1, j)) {
					union(i + 1, j, i, j);
				}
			}

			if (j > 1) {
				if (isOpen(i, j - 1)) {
					union(i, j - 1, i, j);
				}
			}

			// ��ֹ�ұ߽�Խ��
			if (j < column) {
				if (isOpen(i, j + 1)) {
					union(i, j + 1, i, j);
				}
			}

			num++;
			if (percolates()) {
				return -1;
			}
		}
		return 0;
	}

	// �жϽڵ� (row i, column j) �Ƿ��
	public boolean isOpen(int i, int j) {
		if (isOp[(i - 1) * column + j] == 1) {
			return true;
		}
		return false;
	}

	public void union(int i, int j, int m, int n) {
		qUf.union((i - 1) * column + j, (m - 1) * column + n);
	}
	
	//ֻҪ��ںͳ�����ͨ����
	public boolean percolates() {
		if (qUf.connected(0, row*column+1)) {
			return true;
		}
		return false;
	}
}
