package a0219;

import java.io.*;
import java.util.*;

public class SWEA_1249_보급로 {
	
	static int n,ans;
	static int[][] map;
	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	static boolean[][] v;
	
	public static void main(String[] args) throws Exception{
		// dfs 완탐 백트래킹 -> 시간초과
		// 그냥 bfs -> 틀림
		// 우선순위 큐 사용해야함 -> 최솟값찾기
		System.setIn(new FileInputStream("res/input_d4_1249.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<T+1; tc++) {
			sb = new StringBuilder();
			n = Integer.parseInt(br.readLine());
			
			map = new int[n][n];
			
			for(int i=0; i<n; i++) {
				String s = br.readLine();
				for(int j=0; j<n; j++) {
					map[i][j] = s.charAt(j)-'0';
				}
			}
			v = new boolean[n][n];
			ans = Integer.MAX_VALUE;
			
			bfs(0,0,0);
			
			
			sb.append("#").append(tc).append(" ").append(ans);
			System.out.println(sb.toString());
		}
		
		
		br.close();
	}

	static void bfs(int i, int j, int cnt) {
		PriorityQueue<int[]> q = new PriorityQueue<>((o1,o2)->{return o1[2]-o2[2];});
		v[0][0]=true;
		q.offer(new int[] {i,j,cnt});
		
		while(!q.isEmpty()) {
			int[] ij = q.poll();
			if(ij[0]==n-1 && ij[1]==n-1) {
				ans = Math.min(ans, ij[2]);
			}
			
			for(int d=0; d<4; d++) {
				int nx = ij[0] + di[d];
				int ny = ij[1] + dj[d];
				
				if(nx>=0 && nx<n && ny>=0 && ny<n && !v[nx][ny]) {
					v[nx][ny] = true;
					q.offer(new int[] {nx,ny,ij[2]+map[nx][ny]});
				}
				
			}
		}
		
		
	}

}
