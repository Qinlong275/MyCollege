package com.qinlong.filetest;

import java.io.IOException;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		System.out.println("选择功能：\n1、统计字符\n2、统计字符串\n0、退出");
		System.out.println("请选择：");
		int select = in.nextInt();

		int result = 0;
		switch (select) {
		case 1: {
			System.out.println("输入文件路径：");

			String temp=in.nextLine();//必须要加上，有一个""空行
			String name = in.nextLine();
			Figure.input(name);
			result = Figure.Scount();
			break;
		}
		case 2: {
			System.out.println("输入文件路径：");
			String temp=in.nextLine();//必须要加上，有一个""空行
			String name = in.nextLine();
			Figure.input(name);
			result = Figure.Strcount();
			break;
		}
		default:
			break;
		}

		System.out.println("请选择输出文件路径");
		
			String name2 = in.nextLine();
			Figure.output(name2, result);

		in.close();
	}
}
