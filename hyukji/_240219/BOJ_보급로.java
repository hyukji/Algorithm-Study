import java.io.*;
import java.util.*;

public class Solution {

	static final int[] dr = {0, 0, -1, 1};
	static final int[] dc = {-1, 1, 0, 0};
	
	static int n;
	static int[][] graph,visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			n = Integer.parseInt(br.readLine());
			graph = new int[n][n];
			visited = new int[n][n]; // 해당 위치까지의 최소시간 

			for(int r = 0; r < n; r++) {
				String row = br.readLine();
				for(int c = 0; c < n; c++) {
					graph[r][c] = row.charAt(c) -'0';
					visited[r][c] = Integer.MAX_VALUE; // 최소값을 구해야하므로 초기값으로 MAX_VALUE 
				}
			}
			
			Deque<int[]> q = new ArrayDeque<>(); // bfs 이용. 
			q.offer(new int[] {0 ,0 , graph[0][0]});
			
			while(!q.isEmpty()) { 
				int[] restore = q.poll();
				int r = restore[0];
				int c = restore[1];
				int time = restore[2];
				
				for(int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue; // 갈수 없는 범위인 경우 
					
					int nTime = time + graph[nr][nc];
					if(visited[nr][nc] <= nTime) continue; // 이전에 탐색했던 경로가 더 빠른 경우 
					
					visited[nr][nc] = nTime;
					q.offer(new int[] {nr, nc, nTime});
				}
			}
			
			sb.append("#").append(tc).append(" ").append(visited[n-1][n-1]).append("\n");
			
		}
		
		System.out.print(sb);
	}

}