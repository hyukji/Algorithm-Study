package _240129;

import java.io.*;
import java.util.*;

public class BOJ_14503_로봇청소기 {

	// 북동남서
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		int[][] graph = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < m; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 청소된 부분 = 2
		int answer = 0;
		while(true) {
			if(graph[r][c] == 0) { // 1번
				graph[r][c] = 2;
				answer++;
			}
			
			boolean hasBlank = false;
			for(int i = 0; i < 4; i++) { 
				d = (d+3) % 4; // 반시계방향으로 체크
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(graph[nr][nc] == 0) { // 비어있다면 이동!
					r = nr; c = nc;
					hasBlank = true;
					break;
				}
			}
			
			if(!hasBlank) { // 4방향 확인 후 청소할 부부니 없다면, 후진!
				r -= dr[d]; c -= dc[d]; 
				if(graph[r][c] == 1) { // 후진 후 벽인것 체크
					System.out.println(answer);
					return;
				}
			}
		}
	}
}
