package com.qinlong.sortTest;

import java.util.Random;
import java.util.Scanner;

public class TestSort {

	public static void main(String[] args) {
		System.out.println("请输入您要排序的集合大小");
		Scanner in=new Scanner(System.in);
		int size=in.nextInt();
		Random random=new Random();
		Comparable[] a=new Comparable[size];
		for (int i = 0; i < a.length; i++) {
			a[i]=random.nextInt(size);
		}
		AllSorts all=new AllSorts(size);
		
		getSortTime(a,"IS",all,size);
		getSortTime(a, "TDM", all, size);
		getSortTime(a, "BUM", all, size);
		getSortTime(a, "RQ", all, size);
		getSortTime(a, "QD3P", all, size);
	}
	
	public static void getSortTime(Comparable[]a ,String name,AllSorts all,int size) {
		long first=System.nanoTime();
		long start=System.nanoTime();
		System.out.println(name+" 排序消耗时间情况如下单位纳秒");
		for(int i=0;i<10;i++){
			switch (name) {
			case "IS":
				all.IS(a.clone());
				break;
			case "TDM":
				all.TDM(a.clone(), 0, size-1);
				break;
			case "BUM":
				all.BUM(a.clone());
				break;
			case "RQ":
				all.RQ(a.clone(), 0, size-1);
				break;
			case "QD3P":
				all.QD3P(a.clone(), 0, size-1);
				break;
			default:
				break;
			}
			long end=System.nanoTime();
			System.out.print(end-start+"   ");
			start=end;
		}
		long fin=System.nanoTime();
		System.out.println("十次的平均时间为： "+(fin-first)/10);
		System.out.println("---------------------------------------------------------------");
	}

}
