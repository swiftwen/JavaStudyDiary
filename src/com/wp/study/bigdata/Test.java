package com.wp.study.bigdata;
/**
 * @author swiftwen
 * @date 2018年8月31日 下午3:42:39
 */
public class Test {

	public static void main(String[] args) {

		int[] arr = new int[]{2,3};
		byte[] bufs = new byte[4];
		for(int i=0;i<arr.length;++i){
			bufs[arr[i]/8]+=1<<(arr[i]-arr[i]/8*8-1);
		}
        for(int i=0;i<bufs.length;++i){
        	int flag = 1;
        	while(bufs[i]>0){
        		if((bufs[i]&1)>0){
        			System.out.print((flag+i*8)+" ");
        		}
        		bufs[i]>>=1;
        		flag++;
        	}
        }
        
	}
}
