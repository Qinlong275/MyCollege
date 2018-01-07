package com.qinlong.mapRouting;

import java.util.Scanner;

import edu.princeton.cs.algs4.In;

public class TestRoute {

	public static void main(String[] args) {
		In in=new In("usa.txt");
		int a=in.readInt();//点数
		int b=in.readInt();//边数
		EdgeWeightedGraph mDigraph=new EdgeWeightedGraph(a);
		Point []mPoints=new Point[a];
		initPoint(in, mPoints);
		initGraph(in, b, mDigraph, mPoints);
		findRoute(mDigraph);
	}
	
	public static double getDistance(Point a,Point b) {
		double d=0;
		d=(a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y);
		return Math.sqrt(d);
	}
	
	public static void initPoint(In in,Point[] p) {
		for (int i = 0; i < p.length; i++) {
			int q=in.readInt();
			int x=in.readInt();
			int y=in.readInt();
			p[i]=new Point(x,y);
		}
	}
	
	public static void initGraph(In in,int b,EdgeWeightedGraph e,Point[] a) {
		for (int i = 0; i < b; i++) {
			int q=in.readInt();
			int l=in.readInt();
			double weight=getDistance(a[q], a[l]);
			Edge edge=new Edge(q, l, weight);
			e.addEdge(edge);
		}
	}
	
	public static void findRoute(EdgeWeightedGraph mDigraph) {
		System.out.println("请输入你要查询的路线的两个端点：");
		Scanner in=new Scanner(System.in);
		DijkstraUndirectedSP mSp=new DijkstraUndirectedSP(mDigraph);//优化算法，多次查询使用同一个对象
		while (true) {
			int from=in.nextInt();
			int to=in.nextInt();
			mSp.setFrom(from);//设置新的起始点
			if (mSp.hasPathTo(to)) {
				System.out.println("从顶点 "+from+" 到顶点 "+to+" 的最短路径长度为: "+mSp.distTo(to));
				System.out.println("沿途经过的路径结点：");
				for (Edge e : mSp.pathTo(to)) {
					System.out.print(e+"  ");
				}
				System.out.println();
			}else {
				System.out.println("对不起，不存在这样一条路径");
			}
			System.out.println();
			System.out.println("请输入你要查询的路线的两个端点：");
		}
	}

}
