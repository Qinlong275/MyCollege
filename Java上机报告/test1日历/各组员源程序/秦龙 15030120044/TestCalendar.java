package com.ql.calendar;

import java.util.Scanner;

public class TestCalendar {

	public static void main(String[] args) {
		System.out.println("请输入确定的年月日：例如 2017 5 6");
		Scanner in=new Scanner(System.in);
		int year=in.nextInt();
		int month=in.nextInt();
		int day=in.nextInt();
		boolean isRen=false;
		int days=365;
		if (year%4==0&&year%100!=0||year%400==0) {
			isRen=true;
			days=366;
		}
		int firstDay=(year-1+(year-1)/4-(year-1)/100+(year-1)/400+1)%7;
		Calendar myCalendar=new Calendar(year);
		myCalendar.setRenYear(isRen);
		myCalendar.setFirstDay(firstDay);
		myCalendar.printCalendar();
		myCalendar.printWeek(month, day);

	}

}
