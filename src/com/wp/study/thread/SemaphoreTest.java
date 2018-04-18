package com.wp.study.thread;

import java.util.concurrent.Semaphore;

/**
 * @desc Semaphore翻译成字面意思为 信号量，Semaphore可以控同时访问的线程个数，
 * 通过 acquire() 获取一个许可，如果没有就等待，而 release() 释放一个许可。
 * @Author wenpeng
 * @2018年4月18日 下午1:48:06
 */
public class SemaphoreTest {

	static final int N = 8;
	
	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(2);
		for(int i=0;i<N;i++) {
			new WorkerThread(semaphore, i+1).start();
		}
	}
}
class WorkerThread extends Thread{
	private Semaphore semaphore;
	private int num;
	public WorkerThread(Semaphore semaphore,int num) {
		this.semaphore = semaphore;
		this.num = num;
	}
	@Override
	public void run() {
		try {
			semaphore.acquire();
			System.out.println("工人"+num+"开始生产");
			Thread.sleep(1000);
			System.out.println("工人"+num+"结束生产");
			semaphore.release();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}