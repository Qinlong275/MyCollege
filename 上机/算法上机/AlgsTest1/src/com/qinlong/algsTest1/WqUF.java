package com.qinlong.algsTest1;

public class WqUF {
	
	private int []id;	//父链接数组
	private int []size;	//各个根结点对应的分量大小
	private int count;	//连通分量的数量
	
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
		//跟随连接找到根节点
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
		
		//将小树根结点连接到大树根结点上
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
