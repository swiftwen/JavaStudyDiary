package com.wp.study.collections;

import java.util.LinkedHashMap;
import java.util.Map;
/**
 * @desc LinkedHashMap是HashMap的子类，但内部还有一个双向链表维护键值对的顺序，每个键值对既位于哈希表中，也位于这个双向链表中
 * LinkedHashMap支持两种顺序，一种是插入顺序，另外一种是访问顺序。
 * 插入顺序容易理解，先添加的在前面，后添加的在后面，修改操作不影响顺序。
 * 访问顺序是什么意思呢？所谓访问是指get/put操作，对一个键执行get/put操作后，其对应的键值对会移到链表末尾，
 * 所以，最末尾的是最近访问的，最开始的最久没被访问的，这种顺序就是访问顺序。
 * @param <K>
 * @param <V>
 * @Author wenpeng
 * @2018年4月18日 下午4:58:47
 */
public class LinkedHashMapCache<K,V> extends LinkedHashMap<K,V> {

	
	private static final long serialVersionUID = 1L;
	private int maxParties;
	
	public LinkedHashMapCache(int maxParties) {
		super(16, 0.75f, true); //true 按访问有序
		this.maxParties = maxParties;
	}

	@Override
	protected boolean removeEldestEntry(java.util.Map.Entry<K,V> eldest) {
		return this.size() > maxParties;
	}

	public static void main(String[] args) {

		LinkedHashMapCache<String,String> map = new LinkedHashMapCache<>(3);
		map.put("a", "1");
		map.put("b", "2");
		map.put("c", "3");
		map.put("a", "11");
		
		for(Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " = "+entry.getValue());
		}
		
	}
	
}
