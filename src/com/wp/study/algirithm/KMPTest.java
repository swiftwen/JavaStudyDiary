package com.wp.study.algirithm;

import java.util.Scanner;
/**
 * @source hdoj:http://acm.hdu.edu.cn/showproblem.php?pid=1711
 * @desc kmp算法
 * @author swiftwen
 * @date 2018年9月6日 下午7:20:37
 */
public class KMPTest {

	final static int N = 1000010;
	public static int[] calNext(int[] pattern){
		int[] next = new int[pattern.length];
		int maxLen = 0;
		for(int i=1;i<pattern.length;++i){
			while(maxLen>0 && pattern[maxLen] != pattern[i]){
				maxLen = next[maxLen-1];
			}
			if(pattern[maxLen] == pattern[i]){
				maxLen++;
			}
			next[i] = maxLen;
		}
		return next;
	}
	public static void search(int[] text,int[] pattern){
	    int[] next = calNext(pattern);
		int idx = 0;
		int flag = -1;
		for(int i=0;i<text.length;++i){
			while(idx>0 && pattern[idx] != text[i]){
				idx = next[idx-1];
			}
			if(pattern[idx] == text[i]){
				idx++;
			}
			if(idx == pattern.length){
				flag = i-idx+2;
				break;
			}
		}
		System.out.println(flag);
	}
	
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int ca = in.nextInt();
		while(ca-->0){
			int n = in.nextInt();
			int m = in.nextInt();
			int[] text  = new int[n];
			int[] pattern = new int[m];
			for(int i=0;i<n;i++){
				text[i] = in.nextInt();
			}
			for(int i=0;i<m;i++){
				pattern[i] = in.nextInt();
			}
			search(text, pattern);
		}
		in.close();
		
	}
}
