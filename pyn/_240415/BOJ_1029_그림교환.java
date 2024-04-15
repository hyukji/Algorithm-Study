package study;

import java.io.*;
import java.util.*;

public class BOJ_1029_그림교환 {
	static int N, count=1, max=0;
	static int[][] people, memo;
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		N=Integer.parseInt(br.readLine());
		people=new int[N][N];
		memo=new int[N][1<<N];
		for(int i=0; i<N; i++) {
			String line=br.readLine();
			for(int j=0; j<N; j++) people[i][j]=line.charAt(j)-'0';
		}
		dp인척하는dfs(0, 1, 0);
		System.out.println(max);
	}
	static void dp인척하는dfs(int n, int v, int cur) { // 현재사람, 방문했는지, 현재가격
		if(max==N) return;
		max=Math.max(max, count);
		if(v==(1<<N)-1) return;
		for(int i=1; i<N; i++) {
			// 현재 사람 n
			// 다음 사람 i (n과 연결된 곳)
			if((v&(1<<i))!=0) continue; // 이미 방문했던 곳이면 continue
			if(people[n][i]>=cur) {
				// i번째 사람에 방문한 적이 있고, 구매하려는 가격이 전보다 더 비싸다면 continue
				if(memo[i][v|(1<<i)]!=0 && memo[i][v|(1<<i)]<=people[n][i]) continue;
				// 더 싸다면 memo에 값 기록
				memo[i][v|(1<<i)]=people[n][i];
				count++;
				dp인척하는dfs(i, v|(1<<i), people[n][i]);
				count--;
			}
		}
	}
}
/*
static void dp(int n, int v, int cur) {
		max=Math.max(max, count);
		if(v==(1<<N)-1) return;
		for(int i=1; i<N; i++) {
			// 현재 사람 n
			// 다음 사람 i (n과 연결된 곳)
			if((v&(1<<i))!=0) continue;
			if(people[n][i]>=cur) {
				count++;
				dp(i, v|(1<<i), people[n][i]);
				count--;
			}
		}
	}
*/