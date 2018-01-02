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
		Percolation p=new Percolation(row, column);
		while (true) {
			int i=random.nextInt(row)+1;
			int j=random.nextInt(column)+1;
			System.out.println(i+"   "+j);
			p.open(i, j);
		}

	}

}
