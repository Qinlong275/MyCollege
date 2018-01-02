package com.qinlong.sortTest;import javax.swing.text.html.HTMLDocument.HTMLReader.HiddenAction;

import sun.security.util.Length;

public class AllSorts {
	public static int length;
	public static Comparable[] aux;
	public  AllSorts(int n) {
		length=n;
		aux=new Comparable[n];
	}
	

	//插入排序
	public static void IS(Comparable[] a) {
		int N=a.length;
		for (int i = 1; i < N; i++) {
			//将a[i]插入到a[i-1],a[i-2],a[i-3]....中
			for (int j = i; j >0&&less(a[j],a[j-1]); j--) {
				exch(a, j, j-1);
			}
		}
	}
	
	//自顶向下归并排序
	
//	public static Comparable[] aux=new Comparable[300];
	
	public static void TDM(Comparable[] a,int lo,int hi) {
		if (hi<=lo) {
			return;
		}
		int mid=lo+(hi-lo)/2;
		TDM(a, lo, mid);
		TDM(a, mid+1, hi);
		merge(a, lo, mid, hi);
	}
	
	public static void merge(Comparable[] a,int lo,int mid,int hi) {
		int i=lo;
		int j=mid+1;
		for (int k = lo; k <=hi; k++) {
			aux[k]=a[k];
		}
		for (int k = lo; k <=hi; k++) {
			if (i>mid) {
				a[k]=aux[j++];
			}else if (j>hi) {
				a[k]=aux[i++];
			}else if (less(aux[j], aux[i])) {
				a[k]=aux[j++];
			}else {
				a[k]=aux[i++];
			}
		}
	}
	
	//自底向上归并排序
	public static void BUM(Comparable[]a) {
		for(int sz=1;sz<length;sz=sz+sz){
			for (int lo = 0; lo < length-sz; lo+=sz+sz) {
				merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, length-1));
			}
		}
	}
	
	//随机快速排序
	public static void RQ(Comparable[] a,int lo,int hi) {
		if (lo<hi) {
			return;
		}
		int j=partition(a, lo, hi);
		RQ(a, lo, j-1);
		RQ(a, j, hi);
	}
	
	public static int partition(Comparable[] a,int lo,int hi) {
		int i=lo;
		int j=hi+1;
		Comparable v=a[lo];
		while (true) {
			while (less(a[++i], v)) {
				if (i==hi) {
					break;
				}
			}
			while (less(v, a[--j])) {
				if (j==lo) {
					break;
				}
			}
			if (i>=j) {
				break;
			}
			exch(a, i, j);
		}
		exch(a, lo, j);
		return j;
	}
	
	//Dijkstra 3-路划分快速排序
	public static void QD3P(Comparable[] a,int lo,int hi) {
		if (hi<lo) {
			return;
		}
		int lt=lo;
		int i=lo+1;
		int gt=hi;
		Comparable v=a[lo];
		while (i<=gt) {
			int cmp=a[i].compareTo(v);
			if (cmp<0) {
				exch(a, lt++, i++);
			}else if (cmp>0) {
				exch(a, i, gt--);
			}else {
				i++;
			}
		}
		QD3P(a, lo, lt-1);
		QD3P(a, gt+1, hi);
	}
	
	public static boolean less(Comparable a,Comparable b) {
		return a.compareTo(b)<0;
	}
	
	public static void exch(Comparable[] a,int i,int j) {
		Comparable t=a[i];
		a[i]=a[j];
		a[j]=t;
	}
}
