package study;

import java.io.*;
import java.util.*;

public class BOJ_2617_구슬찾기 {
	static int N, M;
	static int[][] weigh, countW;
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine(), " ");
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		weigh = new int[N][N];
		
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine(), " ");
			int heavy=Integer.parseInt(st.nextToken())-1;
			int light=Integer.parseInt(st.nextToken())-1;
			
			weigh[light][heavy]=1;
		}
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<N; j++) System.out.print(weigh[i][j]+" ");
//			System.out.println();
//		}
		countW = new int[N][2];
		
		// 기준(i)보다 가벼운 것 찾기
		for(int i=0; i<N; i++) {
			visited=new boolean[N];
			dfsL(i, i);
		}
		// 기준(i)보다 무거운 것 찾기
		for(int i=0; i<N; i++) {
			visited=new boolean[N];
			dfsH(i,i);
		}
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<2; j++) System.out.print(countW[i][j]+" ");
//			System.out.println();
//		}
		
		int ans=0;
		for(int i=0; i<N; i++) if(countW[i][0]>N/2 || countW[i][1]>N/2) ans++;
		System.out.println(ans);
		br.close();
	}
	static void dfsL(int start, int num) {
		visited[num]=true;
		for(int i=0; i<N; i++) {
			if(!visited[i] && weigh[i][num]==1) {
				countW[start][0]++;
				dfsL(start, i);
			}
		}
	}
	static void dfsH(int start, int num) {
		visited[num]=true;
		for(int i=0; i<N; i++) {
			if(!visited[i] && weigh[num][i]==1) {
				countW[start][1]++;
				dfsH(start, i);
			}
		}
	}
//	static int bfsL(int num) {
//		ArrayDeque<Integer> q=new ArrayDeque<>();
//		int count=0;
//		
//		q.offer(num);
//		while(!q.isEmpty()) {
//			int standard=q.poll();
//			for(int i=0; i<N; i++) {
//				if(weigh[standard][i]==1) {
//					q.offer(i);
//					count++;
//				}
//			}
//		}
//		return count;
//	}
//	static int bfsH(int num) {
//		ArrayDeque<Integer> q=new ArrayDeque<>();
//		int count=0;
//		
//		q.offer(num);
//		while(!q.isEmpty()) {
//			int standard=q.poll();
//			for(int i=0; i<N; i++) {
//				if(weigh[i][standard]==1) {
//					q.offer(i);
//					count++;
//				}
//			}
//		}
//		return count;
//	}
}
