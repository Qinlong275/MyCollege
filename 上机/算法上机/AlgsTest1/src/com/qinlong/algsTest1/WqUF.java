package com.qinlong.algsTest1;

public class WqUF {
	
	private int []id;	//����������
	private int []size;	//����������Ӧ�ķ�����С
	private int count;	//��ͨ����������
	
	public WqUF(int n) {
		count=n;
		id=new int[n];
		for (int i = 0; i < n; i++) {
			id[i]=i;
		}
		
		size=new int[n];
		for (int i = 0; i < n; i++) {
			size[i]=1;
		}
	}
	
	public int count() {
		return count;
	}
	
	public boolean connected(int p,int q) {
		return find(p)==find(q);
	}
	
	public int find(int p) {
		//���������ҵ����ڵ�
		while (p!=id[p]) {
			p=id[p];		
		}
		return p;
	}
	
	public void union(int p,int q) {
		int i=find(p);
		int j=find(q);
		if (i==j) {
			return;
		}
		
		//��С����������ӵ������������
		if (size[i]<size[j]) {
			id[i]=j;
			size[j]+=size[i];
		}else {
			id[j]=i;
			size[i]+=size[j];
		}
		count--;
	}

}
