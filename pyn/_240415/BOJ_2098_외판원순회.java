package a0328;

import java.io.*;
import java.util.*;

public class Solution_bj_2098_외판원순회_서울_20반_박영남 {
	static int N;
	static int[][] map;
	static int[][] memo;
	static int value=1000000000;
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		N=Integer.parseInt(br.readLine());
		map=new int[N][N];
		memo=new int[N][1<<N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<(1<<N); j++) memo[i][j]=-1;
		}
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(tsp(0,1));
	}
	static int tsp(int n, int v) {
		if(v==(1<<N)-1) {
			if(map[n][0]==0) return value;
			return map[n][0];
		}
		if(memo[n][v]!=-1) return memo[n][v];
		int min=value;
		for(int i=0; i<N; i++) {
			// 현재가 n
			// 다음으로 갈 게 i(n과 연결된 곳)
			// 현재까지 방문 정보 v
			if((v & (1<<i))!=0) continue;
			if(map[n][i]!=0) min=Math.min(tsp(i,v|(1<<i))+map[n][i],min);
		}
		return memo[n][v]=min;
	}
}
