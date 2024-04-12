package study;

import java.io.*;
import java.util.*;

public class BOJ_2263_트리의순회 {
	static int n, cnt;
	static int[] inorder, postorder, preorder;
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		StringTokenizer st;
		
		n=Integer.parseInt(br.readLine());
		preorder=new int[n];
		inorder=new int[n];
		postorder=new int[n];
		st=new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<n; i++) inorder[i]=Integer.parseInt(st.nextToken());
		st=new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<n; i++) postorder[i]=Integer.parseInt(st.nextToken());
		
		cnt=0;
		makePre(0, n-1, 0, n-1);
		
		for(int i=0; i<n; i++) sb.append(preorder[i]).append(" "); 
			
		System.out.println(sb);
	}
	static int findI(int root, int inS, int inE) {
		for(int i=inS; i<inE+1; i++) if(inorder[i]==root) return i;
		return n;
	}
	static void makePre(int postS, int postE, int inS, int inE) {
		
		int a=postE;
		
		if(postE-postS<0) return;
		//System.out.println("Pre "+cnt+"에 "+postorder[a]+"넣을게");
		preorder[cnt++]=postorder[postE];
		if(postE-postS<=0) return;
		
		int nextI=findI(postorder[postE], inS, inE);
		//System.out.println(nextI+"찾았어!");
		
		// 루트노드 왼쪽
		//System.out.println("왼쪽 IN: ("+inS+","+(nextI-1)+")");
		//System.out.println("왼쪽 POST: ("+postS+","+(postS+nextI-1-inS)+")");
		makePre(postS, postS+(nextI-1-inS), inS, nextI-1);
		
		// 루트노드 오른쪽
		//System.out.println("오른쪽 IN: ("+(nextI+1)+","+inE+")");
		//System.out.println("오른쪽 POST: ("+(postS+nextI-inS)+","+(a-1)+")");
		makePre(postS+(nextI-inS), a-1, nextI+1, inE);
		
	}
}
/*
static void makePre(int[] post, int[] in) {
		
		int a=post.length-1;
		
		if(a<0) return;
		//System.out.println("Pre "+cnt+"에 "+post[a]+"넣을게");
		preorder[cnt++]=post[a];
		if(a<=0) return;
		
		int next=find(post[a], in);
		//System.out.println(next+"찾았어!");
		
		
		// 루트노드 왼쪽
		//System.out.println("왼쪽 IN: "+Arrays.toString(Arrays.copyOf(in, next)));
		//System.out.println("왼쪽 POST: "+Arrays.toString(Arrays.copyOf(post, next)));
		makePre(Arrays.copyOf(post, next), Arrays.copyOf(in, next));
		
		// 루트노드 오른쪽
		//System.out.println("오른쪽 IN: "+Arrays.toString(Arrays.copyOfRange(in, next+1, a+1)));
		//System.out.println("오른쪽 POST: "+Arrays.toString(Arrays.copyOfRange(post, next, a)));
		makePre(Arrays.copyOfRange(post, next, a), Arrays.copyOfRange(in, next+1, a+1));
		
}*/
