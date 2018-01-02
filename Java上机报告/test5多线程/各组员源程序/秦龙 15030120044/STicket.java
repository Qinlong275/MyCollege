package com.ql275.thread;

import java.util.Scanner;

public class STicket implements Runnable{
	
	private int Tickets;
	
	public void setTickets(int tickets) {
		Tickets = tickets;
	}
	
	@Override
	public void run() {
		boolean flag=sellTicket();
		while(flag){
			flag=sellTicket();
		}	
	}
	
	public synchronized boolean sellTicket() {
		if (Tickets>0) {
			Tickets--;
			System.out.println(Thread.currentThread().getName()+" ����һ��Ʊ,��ʣ�� "+Tickets+"��Ʊ");
			return true;
		}
		System.out.println("Sorry,Ʊ�Ѿ�������");
		return false;
	}

	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		System.out.println("������Ʊ������");
		int tickets=in.nextInt();
		STicket sTicket=new STicket();
		sTicket.setTickets(tickets);
		
		Thread thread1=new Thread(sTicket, "����1");
		Thread thread2=new Thread(sTicket,"����2");
		Thread thread3=new Thread(sTicket, "����3");
		
		thread1.start();
		thread2.start();
		thread3.start();

	}

}
