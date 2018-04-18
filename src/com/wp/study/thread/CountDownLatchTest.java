package com.wp.study.thread;

import java.util.concurrent.CountDownLatch;

/**
 * 
 * @desc 
 * @Author wenpeng
 * @2018年4月18日 下午4:58:35
 */
public class CountDownLatchTest {

	public static void main(String[] args)throws Exception{
		CountDownLatch countDownLatch = new CountDownLatch(3);
		CalSumThread t1 = new CalSumThread(countDownLatch, 5);
		CalSumThread t2 = new CalSumThread(countDownLatch, 4);
		CalSumThread t3 = new CalSumThread(countDownLatch, 3);
		t1.start();
		t2.start();
		t3.start();
		countDownLatch.await();
		//countDownLatch.await(10,TimeUnit.SECONDS);
		int sum = t1.getSum()+t2.getSum()+t3.getSum();
		System.out.println("all threads are end,the sum is "+sum);
	}
}
class CalSumThread extends Thread{
	private CountDownLatch countDownLatch;
	private int num;
	private int sum;
	public int getSum() {
		return sum;
	}
	public CalSumThread(CountDownLatch countDownLatch,int num) {
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
