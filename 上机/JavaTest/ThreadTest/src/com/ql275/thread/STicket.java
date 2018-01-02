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
			System.out.println(Thread.currentThread().getName()+" 卖出一张票,还剩下 "+Tickets+"张票");
			return true;
		}
		System.out.println("Sorry,票已经售完了");
		return false;
	}

	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		System.out.println("请输入票数总数");
		int tickets=in.nextInt();
		STicket sTicket=new STicket();
		sTicket.setTickets(tickets);
		
		Thread thread1=new Thread(sTicket, "窗口1");
		Thread thread2=new Thread(sTicket,"窗口2");
		Thread thread3=new Thread(sTicket, "窗口3");
		
		thread1.start();
		thread2.start();
		thread3.start();

	}

}
