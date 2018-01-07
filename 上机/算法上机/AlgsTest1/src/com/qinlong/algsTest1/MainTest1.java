package com.qinlong.algsTest1;

import java.util.Random;
import java.util.Scanner;

public class MainTest1 {

	public static void main(String[] args) {
		Random random=new Random();
		Scanner in=new Scanner(System.in);
		System.out.println("��������������ͼ ���� ����");
		int row=in.nextInt();
		int column=in.nextInt();
		while (true) {
			System.out.println();
			System.out.println("��ѡ����Ҫʹ�õ��㷨��quick-find ����1����Ȩquick-union ����2");
			int chose=in.nextInt();
			System.out.println("��������Ե�ʵ���ظ�����");
			int sum=in.nextInt();
			double[] ql=new double[sum];
			for (int w = 0; w < sum; w++) {
				if (chose==1) {
					System.out.println("-----------------------------------------------------------");
					System.out.println("������quick-find�㷨�����н��");
					PercolationQf pQf=new PercolationQf(row, column);
					int q=0;
					int num=0;//�򿪵�����
					while (q!=-1) {
						int i=random.nextInt(row)+1;
						int j=random.nextInt(column)+1;
						if (!pQf.isOpen(i, j)) {
							q=pQf.open(i, j);
							num++;
						}
					}
					ql[w]=num * 1.0 / (row * column);
					System.out.println("���� " + num + " ���ڵ�ʱϵͳ������͸ ");
					System.out.println("��͸��ֵΪ�� " + num * 1.0 / (row * column));
				}else {
					System.out.println("-----------------------------------------------------------");
					System.out.println("������quick-union�㷨�����н��");
					Percolation p=new Percolation(row, column);
					int q=0;
					int num=0;//�򿪵�����
					while (q!=-1) {
						int i=random.nextInt(row)+1;
						int j=random.nextInt(column)+1;
						if (!p.isOpen(i, j)) {
							q=p.open(i, j);
							num++;
						}
					}
					ql[w]=num * 1.0 / (row * column);
					System.out.println("���� "+num+" ���ڵ�ʱϵͳ������͸ ");
					System.out.println("��͸��ֵΪ�� "+num*1.0/(row*column));
				}
				
			}
			double a=MathUtil.getAverage(ql);//ƽ��ֵ
			double b=MathUtil.getStandardDiviation(ql);//��׼��
			System.out.println(sum+"�����е�ƽ����ֵΪ��"+a);
			System.out.println("��׼��Ϊ"+b);
			double x=1.96*b/(Math.sqrt(sum));
			System.out.println("��������Ϊ��");
			System.out.println("["+(a-x)+","+(a+x)+"]");
			
		}
		
	}
	

}
