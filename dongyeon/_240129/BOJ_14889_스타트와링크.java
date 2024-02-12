package com.ssafy.algo.study.week1;

import java.io.*;
import java.util.*;

public class BOJ_14889_스타트와링크{
	
	static int N;
	static int[][] arr;
	static boolean[] visited;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 N = Integer.parseInt(br.readLine());
		 StringTokenizer st;
		 
		 arr = new int[N][N];
		 visited = new boolean[N];
		 
//		 System.out.println(Arrays.toString(visited)); // 초기값 false로 들어가 있음.
		 
		 for(int i=0; i<N; i++) {
			 st = new StringTokenizer(br.readLine()," ");
			 for(int j=0; j<N; j++) {
				 arr[i][j]=Integer.parseInt(st.nextToken());
			 }
		 }
		 
		 combi(0,0);
		 
		 System.out.println(min);
		 br.close();
	}
	
	static void combi(int depth, int num) {
		if(depth==(N/2)) {
			diff();
			return;
		}
		
		for(int i=num; i<N; i++) {
			if(!visited[i]) {
				visited[i]=true;
				combi(depth+1,i+1);
				visited[i]=false;
			}
		}
	}
	
	static void diff() {
		int start = 0;
		int link = 0;
		
//		System.out.println(Arrays.toString(visited));
		for(int i=0; i<N-1;i++) {
			for(int j=i+1;j<N; j++) {
				if(visited[i]==true && visited[j]==true) {
					start+=arr[i][j];
					start+=arr[j][i];
				}else if(visited[i]==false && visited[j]==false) {
					link+=arr[i][j];
					link+=arr[j][i];
				}
			}
		}
		
		int result = Math.abs(start-link);
		
		min = Math.min(result, min);
	
	}

}

