package com.wp.study.queue.delayQueue;

import java.util.concurrent.DelayQueue;

public class Test {
	public static void main(String[] args) throws Exception {
		DelayQueue<DelayEvent> queue = new DelayQueue<DelayEvent>();
		DelayEvent element1 = new DelayEvent(10000, "zlx");
		DelayEvent element2 = new DelayEvent(50000, "gh");
		queue.put(element1);
		queue.put(element2);
		element1 = queue.take();
		System.out.println(element1);
	}
}