package com.qinlong.mapRouting;

import java.util.Scanner;

import edu.princeton.cs.algs4.In;

public class TestRoute {

	public static void main(String[] args) {
		In in=new In("usa.txt");
		int a=in.readInt();//����
		int b=in.readInt();//����
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
		System.out.println("��������Ҫ��ѯ��·�ߵ������˵㣺");
		Scanner in=new Scanner(System.in);
		DijkstraUndirectedSP mSp=new DijkstraUndirectedSP(mDigraph);//�Ż��㷨����β�ѯʹ��ͬһ������
		while (true) {
			int from=in.nextInt();
			int to=in.nextInt();
			mSp.setFrom(from);//�����µ���ʼ��
			if (mSp.hasPathTo(to)) {
				System.out.println("�Ӷ��� "+from+" ������ "+to+" �����·������Ϊ: "+mSp.distTo(to));
				System.out.println("��;������·����㣺");
				for (Edge e : mSp.pathTo(to)) {
					System.out.print(e+"  ");
				}
				System.out.println();
			}else {
				System.out.println("�Բ��𣬲���������һ��·��");
			}
			System.out.println();
			System.out.println("��������Ҫ��ѯ��·�ߵ������˵㣺");
		}
	}

}
