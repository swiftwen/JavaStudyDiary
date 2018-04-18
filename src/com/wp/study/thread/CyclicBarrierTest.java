package com.wp.study.thread;

import java.util.concurrent.CyclicBarrier;

/**
 * @desc 字面意思回环栅栏，通过它可以实现让一组线程等待至某个状态之后再全部同时执行。
 * 叫做回环是因为当所有等待线程都被释放以后，CyclicBarrier可以被重用。
 * 我们暂且把这个状态就叫做barrier，当调用await()方法之后，线程就处于barrier了。
 * @Author wenpeng
 * @2018年4月18日 上午10:05:13
 */
public class CyclicBarrierTest {

	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
		WriteDataThread t1 = new WriteDataThread(cyclicBarrier,5);
		WriteDataThread t2 = new WriteDataThread(cyclicBarrier,4);
		WriteDataThread t3 = new WriteDataThread(cyclicBarrier,3);
		t1.start();
		t2.start();
		t3.start();
	}

}
class WriteDataThread extends Thread{
	private CyclicBarrier cyclicBarrier;
	private int num;
	public WriteDataThread(CyclicBarrier cyclicBarrier,int num) {
		this.cyclicBarrier = cyclicBarrier;
		this.num = num;
	}
	@Override
	public void run() {
		try {
			Thread.sleep(1000);
			System.out.println("write num:"+num);
			cyclicBarrier.await();
			System.out.println("begin again");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

