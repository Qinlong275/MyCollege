package com.qinlong.filetest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.io.*;
import java.util.Scanner;

public class Figure {

	static BufferedReader in = null;

	static void input(String s) {
		try {
			in = new BufferedReader(new FileReader(s));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static int Scount() {
		int count = 0;
		String s;
		try {
			while ((s = in.readLine()) != null) {
				for (int i = 0; i < s.length(); i++)
					if ((s.charAt(i) >= 'a' && s.charAt(i) <= 'z') || (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z'))
						count++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	static int Strcount() {
		String s;
		int count = 0;
		try {
			while ((s = in.readLine()) != null) {
				int count1 = 0; // 统计每行的字符串个数
				for (int i = 0; i < s.length(); i++) {
					if ((s.charAt(i) == ' '))
						count1++;
				}
				count = count + count1 + 1;

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}


	static void output(String s, int result) throws IOException {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String str = "result: " + String.valueOf(result);
		byte[] bytes = new byte[256];
		bytes = str.getBytes();
		int b = str.length();
		try {
			out.write(bytes, 0, b);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			out.close();
		}
		System.out.println("已将结果成功写入文件");
	}

}
