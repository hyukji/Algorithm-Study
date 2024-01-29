package com.ssafy.algo.study.week1;

import java.util.*;
import java.io.*;

public class BOJ_17136_색종이붙이기 {
	
	static final int[][]  arr = new int[10][10];
	static int[] count= {5,5,5,5,5};
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int i=0; i<10; i++) {
			st=new StringTokenizer(br.readLine()," ");
			for(int j=0;j<10;j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0,0,0);
		
		if(answer==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(answer);
		br.close();
	}
	
	static void dfs(int x, int y, int cnt) {
		if(x>=9 && y>9) {
			answer = Math.min(answer, cnt);
			return;
		}
		
		if(cnt>=answer)return;
		
		if(y>9) {
			dfs(x+1,0,cnt);
			return;
		}
		
		if(arr[x][y]==1) {
			for(int i=5; i>0; i--) {
				if(count[i-1]>0 && possible(x,y,i)) {
					plus(x,y,i);
					count[i-1]-=1;
					dfs(x,y+1,cnt+1);
					minus(x,y,i);
					count[i-1]+=1;
				}
			}
		}else {
			dfs(x,y+1,cnt);
		}
	}
	
	static boolean possible(int x, int y, int size) {
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				if((x+i)<0 || (x+i)>9 || (y+j)<0 || (y+j)>9) return false;
				if(arr[x+i][y+j]!=1) return false;
			}
		}
		return true;
		 
	}
	
	static void plus(int x, int y, int size) {
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				arr[x+i][y+j]=2;
			}
		}
	}
	static void minus(int x, int y, int size) {
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				arr[x+i][y+j]=1;
			}
		}
	}
}
