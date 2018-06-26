package com.wp.study.hdoj;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @desc 哈夫曼编码  http://acm.hdu.edu.cn/showproblem.php?pid=2527
 * @Author wenpeng
 * @2018年6月26日 下午2:47:26
 */
public class T2527 {

	private static final int N = 26;
	private static int[] L = new int[N];
	public static void init() {
		for(int i=0;i<L.length;i++) {
			L[i] = 0;
		}
	}
	public static int huffman() {
		int ans  = 0;
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		for(int i=0;i<L.length;++i) {
			if(L[i]>0)
			queue.add(L[i]);
		}
		if(queue.size()==1) {
			ans = queue.peek();
		}
		while(queue.size()>1) {
			int v1 = queue.poll();
			int v2 = queue.poll();
			ans+=v1+v2;
			//System.out.println(v1+"--"+v2);
			queue.add(v1+v2);
		}
		return ans;
	}
	public static void main(String[] args)throws Exception {

		Scanner in = new Scanner(System.in);
		int ca;
		int pivot;
		String s;
		ca = in.nextInt();
		while(ca-->0) {
			init();
			pivot = in.nextInt();
			in.nextLine();
			s = in.nextLine();
			for(int i =0;i<s.length();++i) {
				L[s.charAt(i)-'a']++;
			}
			int ans = huffman();
			//System.out.println(ans);
			System.out.println(ans>pivot?"no":"yes");
		}
		in.close();
	}

}
