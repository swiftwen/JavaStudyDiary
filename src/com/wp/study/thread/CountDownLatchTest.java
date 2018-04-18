package com.wp.study.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @desc CountDownLatch类位于java.util.concurrent包下，利用它可以实现类似计数器的功能
 * 比如有一个任务A，它要等待其他4个任务执行完毕之后才能执行，此时就可以利用CountDownLatch来实现这种功能了
 * 主要方法：
 * public void await() throws InterruptedException { };  //调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
 * public boolean await(long timeout, TimeUnit unit) throws InterruptedException { };  
 *                                //和await()类似，只不过等待一定的时间后count值还没变为0的话就会继续执行
 * public void countDown() { };  //将count值减1
 * @Author wenpeng
 * @2018年4月18日 上午9:49:50
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
