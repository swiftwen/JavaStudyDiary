package com.wp.study.thread;

import java.util.concurrent.Semaphore;

/**
 * @desc Semaphore�����������˼Ϊ �ź�����Semaphore���Կ�ͬʱ���ʵ��̸߳�����
 * ͨ�� acquire() ��ȡһ����ɣ����û�о͵ȴ����� release() �ͷ�һ����ɡ�
 * @Author wenpeng
 * @2018��4��18�� ����1:48:06
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
			System.out.println("工人"+num+"开始工作");
			Thread.sleep(1000);
			System.out.println("工人"+num+"结束工作");
			semaphore.release();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}