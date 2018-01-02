package com.ql.calendar;

public class Calendar {
	private int year;
	private boolean isRenYear;
	private int firstDay;
	private int monthFirstDay;
	
	public Calendar(int year) {
		super();
		this.year = year;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	public void setRenYear(boolean isRenYear) {
		this.isRenYear = isRenYear;
	}
	
	public int getFirstDay() {
		return firstDay;
	}
	public void setFirstDay(int firstDay) {
		this.firstDay = firstDay;
	}
	public boolean isRenYear() {
		return isRenYear;
	}
	public void printCalendar() {
		for (int i=1;i<=12;i++){
			
			if (i==1) {
				monthFirstDay=firstDay;
			}
			int monthDays;
			if (i==1||i==3||i==5||i==7||i==8||i==10||i==12) {
				monthDays=31;
			}else if (i==4||i==6||i==9||i==11) {
				monthDays=30;
			}else {
				if (isRenYear) {
					monthDays=29;
				}else {
					monthDays=28;
				}
			}
			
			System.out.println(i+" 月   "+year+" 年");
			System.out.println("日\t一\t二\t三\t四\t五\t六\t");
			for (int j = 0; j < monthFirstDay; j++) {
				System.out.print(" \t");
			}
			int q=1;
			int temp=monthFirstDay+1;
			for (int j2 = 0; j2 <monthDays; j2++) {		
				System.out.print(q+"\t");
				if (temp%7==0) {
					temp=0;
					System.out.println();
				}
				q++;
				temp++;
			}
			monthFirstDay=temp-1;
			System.out.println();
			System.out.println();
		}
	}
	
	public void printWeek(int month,int day) {
		int sum=0;
		for (int i = 1; i < month; i++) {
			if (i==1||i==3||i==5||i==7||i==8||i==10||i==12) {
				sum+=31;
			}else if (i==4||i==6||i==9||i==11) {
				sum+=30;
			}else {
				if (isRenYear) {
					sum+=29;
				}else {
					sum+=28;
				}
			}
		}
		sum+=day;
		int result=(sum-1)%7; //一定要减1
		int myDay=firstDay;
		for (int i = 0; i < result; i++) {
			myDay++;
			myDay=myDay%7;
		}
		System.out.print("今天是： ");
		switch (myDay) {
		case 0:
			System.out.println("星期日");
			break;
		case 1:
			System.out.println("星期一");
			break;
		case 2:
			System.out.println("星期二");
			break;
		case 3:
			System.out.println("星期三");
			break;
		case 4:
			System.out.println("星期四");
			break;
		case 5:
			System.out.println("星期五");
			break;
		case 6:
			System.out.println("星期六");
		default:
			break;
		}
	}
}
