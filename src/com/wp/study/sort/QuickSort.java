package com.wp.study.sort;

import java.util.concurrent.locks.ReentrantLock;

public class QuickSort {

	/*public static void print(Node head) {
		while(head!=null) {
			System.out.println(head.val);
			head = head.next;
		}
	}
	public static Node reverse(Node head) {
		Node next = null;
		Node pre = null;
		while(head!=null) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;
	}*/
	public static void quickSort(int[] arr,int l,int r) {
		if(l>=r) return;
		int lt = l;
		int rt = r;
		int pivot = arr[lt];
		while(lt<rt) {
			while(lt<rt && arr[rt]>=pivot) {
				rt--;
			}
			arr[lt] = arr[rt];
			while(lt<rt && arr[lt]<=pivot) {
				lt++;
			}
			arr[rt] = arr[lt];
		}
		arr[lt] = pivot;
		quickSort(arr,l,lt-1);
		quickSort(arr,lt+1,r);
	}
	public static void main(String[] args) {
		int[] arr = new int[] {100,88,77,-9,-888};
		for(int val : arr) {
			System.out.print(val+" ");
		}
		System.out.println();
		quickSort(arr,0,arr.length-1);
		for(int val : arr) {
			System.out.print(val+" ");
		}
		ReentrantLock c = new ReentrantLock();
		c.lock();
		c.tryLock();
	}

}

