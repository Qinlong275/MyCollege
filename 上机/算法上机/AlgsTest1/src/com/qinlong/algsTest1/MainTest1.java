package com.qinlong.algsTest1;

import java.util.Random;
import java.util.Scanner;

public class MainTest1 {

	public static void main(String[] args) {
		Random random=new Random();
		Scanner in=new Scanner(System.in);
		System.out.println("请输入您的网格图 行数 列数");
		int row=in.nextInt();
		int column=in.nextInt();
		while (true) {
			System.out.println();
			System.out.println("请选择你要使用的算法：quick-find 输入1，加权quick-union 输入2");
			int chose=in.nextInt();
			System.out.println("请输入测试的实验重复次数");
			int sum=in.nextInt();
			double[] ql=new double[sum];
			for (int w = 0; w < sum; w++) {
				if (chose==1) {
					System.out.println("-----------------------------------------------------------");
					System.out.println("下面是quick-find算法的运行结果");
					PercolationQf pQf=new PercolationQf(row, column);
					int q=0;
					int num=0;//打开的数量
					while (q!=-1) {
						int i=random.nextInt(row)+1;
						int j=random.nextInt(column)+1;
						if (!pQf.isOpen(i, j)) {
							q=pQf.open(i, j);
							num++;
						}
					}
					ql[w]=num * 1.0 / (row * column);
					System.out.println("打开了 " + num + " 个节点时系统可以渗透 ");
					System.out.println("渗透阈值为： " + num * 1.0 / (row * column));
				}else {
					System.out.println("-----------------------------------------------------------");
					System.out.println("下面是quick-union算法的运行结果");
					Percolation p=new Percolation(row, column);
					int q=0;
					int num=0;//打开的数量
					while (q!=-1) {
						int i=random.nextInt(row)+1;
						int j=random.nextInt(column)+1;
						if (!p.isOpen(i, j)) {
							q=p.open(i, j);
							num++;
						}
					}
					ql[w]=num * 1.0 / (row * column);
					System.out.println("打开了 "+num+" 个节点时系统可以渗透 ");
					System.out.println("渗透阈值为： "+num*1.0/(row*column));
				}
				
			}
			double a=MathUtil.getAverage(ql);//平均值
			double b=MathUtil.getStandardDiviation(ql);//标准差
			System.out.println(sum+"次运行的平均阈值为："+a);
			System.out.println("标准差为"+b);
			double x=1.96*b/(Math.sqrt(sum));
			System.out.println("置信区间为：");
			System.out.println("["+(a-x)+","+(a+x)+"]");
			
		}
		
	}
	

}
