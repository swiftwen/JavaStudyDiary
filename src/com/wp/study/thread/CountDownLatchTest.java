package com.wp.study.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @desc CountDownLatch类位于java.util.concurrent包下，利用它可以实现类似计数器的功能
 * 比如有一个任务A，它要等待其他4个任务执行完毕之后才能执行，此时就可以利用CountDownLatch来实现这种功能了
 * @Author wenpeng
 * @2018年4月18日 上午9:49:50
 */
public class CountDownLatchTest {

	public static void main(String[] args)throws Exception{
		CountDownLatch countDownLatch = new CountDownLatch(3);
		MyThread t1 = new MyThread(countDownLatch, 5);
		MyThread t2 = new MyThread(countDownLatch, 4);
		MyThread t3 = new MyThread(countDownLatch, 3);
		t1.start();
		t2.start();
		t3.start();
		countDownLatch.await();
		int sum = t1.getSum()+t2.getSum()+t3.getSum();
		System.out.println("all threads are end,the sum is "+sum);
	}
}
class MyThread extends Thread{
	private CountDownLatch countDownLatch;
	private int num;
	private int sum;
	public int getSum() {
		return sum;
	}
	public MyThread(CountDownLatch countDownLatch,int num) {
		this.countDownLatch = countDownLatch;
		this.num = num;
	}
	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {
			
		}
		System.out.println("num = "+num);
		for(int i=1;i<=num;i++) {
			sum+=i;
		}
		countDownLatch.countDown();
	}
}
