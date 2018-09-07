package com.wp.study.algirithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

/**
 * @source hdoj： http://acm.hdu.edu.cn/showproblem.php?pid=2586
 * @desc 最近公共祖先lca
 * @author swiftwen
 * @date 2018年9月7日 下午3:21:23
 */
public class LCATest {
	final static int N = 40010;
	final static int INF = 999999999;
	static int[] dep = new int[N];
	static int[] fa = new int[N];
	
	static Map<Integer,Vector<Node>> vectorMap = new HashMap<>();
	public static void init(){
		for(int i=0;i<N;i++){
			dep[i] = -1;
			fa[i] = -1;
		}
	}
	public static int lca(int a,int b){
		if(dep[a]<dep[b]){
			a=a^b;
			b=a^b;
			a=a^b;
		}
		//ensure dep[a]>dep[b] ,a is under b
		//可以用二进制来优化
		int subDep = dep[a] - dep[b];
		while(dep[a]!=dep[b]){
			a = fa[a];
		}
		if(a==b){
			return a;
		}
		while(a!=b){
			a = fa[a];
			b = fa[b];
		}
		return a;
		
	}
	public static int getLength(int from,int to){
		int ans = 0;
		if(dep[from] < dep[to]){
			from = from^to;
			to = from^to;
			from = from^to;
		}
		while(from != to){
			Vector<Node> vector = vectorMap.get(from);
			for(int i=0;i<vector.size();++i){
				if(dep[vector.get(i).to] < dep[from]){
					ans+=vector.get(i).len;
					from = fa[from];
				}
			}
		}
		return ans;
	}
	public static void dfs(int root,int d){
		dep[root] = d;
		Vector<Node> vector = vectorMap.get(root);
		for(int i=0;i<vector.size();i++){
			int to = vector.get(i).to;
			if(dep[to] < 0){
				fa[to] = root;
				dfs(to,d+1);
			}
		}
	}
	public static void addEdge(int from,int to,int len){
		Vector<Node> vector = vectorMap.get(from);
		if(null == vector){
			vector = new Vector<Node>();
			vectorMap.put(from, vector);
		}
		vector.add(new Node(to,len));
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int ca = in.nextInt();
		while(ca-->0){
			int n,m;
			n = in.nextInt();
			m = in.nextInt();
			init();
			vectorMap = new HashMap<>();
			for(int i=1;i<n;i++){
				int from = in.nextInt();
				int to = in.nextInt();
				int len = in.nextInt();
				addEdge(from, to, len);
				addEdge(to,from,len);
			}
			dfs(1,1);
			/*for(int i=1;i<=4;i++){
				System.out.println(dep[i]+" "+fa[i]);
			}*/
			for(int i=0;i<m;i++){
				int from = in.nextInt();
				int to = in.nextInt();
				int lcaNode = lca(from,to);
				System.out.println(getLength(lcaNode,from)+getLength(lcaNode, to));
			}
		}
		in.close();
	}
}
class Node{
	int to;
	int len;
	public Node(int to, int len) {
		this.to = to;
		this.len = len;
	}
	
}