package com.qinlong.filetest;

import java.io.IOException;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		System.out.println("ѡ���ܣ�\n1��ͳ���ַ�\n2��ͳ���ַ���\n0���˳�");
		System.out.println("��ѡ��");
		int select = in.nextInt();

		int result = 0;
		switch (select) {
		case 1: {
			System.out.println("�����ļ�·����");

			String temp=in.nextLine();//����Ҫ���ϣ���һ��""����
			String name = in.nextLine();
			Figure.input(name);
			result = Figure.Scount();
			break;
		}
		case 2: {
			System.out.println("�����ļ�·����");
			String temp=in.nextLine();//����Ҫ���ϣ���һ��""����
			String name = in.nextLine();
			Figure.input(name);
			result = Figure.Strcount();
			break;
		}
		default:
			break;
		}

		System.out.println("��ѡ������ļ�·��");
		
			String name2 = in.nextLine();
			Figure.output(name2, result);

		in.close();
	}
}
