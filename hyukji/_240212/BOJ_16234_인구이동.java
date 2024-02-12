package personal;

import java.io.*;
import java.util.*;

public class Main_bj_16234_인구이동_서울_20_곽지혁 {

	static final int[] dr = {1, 0, -1, 0};
	static final int[] dc = {0, 1, 0, -1};
	
	static int n, L, R;
	static int[][] graph;
	static boolean[][] visited;
	static ArrayDeque<int[]> opened;
	
	static int amount;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		graph = new int[n][n];
		
		for(int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < n; c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time = 0;
		while(move()) time++; // 이동 시도해 보고 가능여부를 boolean으로 
		System.out.println(time);
	}
	
	
	private static boolean move() { // 이동 가능한지. 가능하다면 이동까지! 
		visited = new boolean[n][n]; // 현재 시간 동안 방문! -> 같은 곳에 인구변화를 여러번 하면 안됨. 
		boolean isMoved = false;
		
		for(int r = 0; r < n; r++) {
			for(int c = 0; c < n; c++) {
				if(visited[r][c]) continue; // 방문했었다면 continue 

				// 총 인구수 / 나라 수 로 나누어서 업데이트해주어야 하기에, 탐색이 끝난 후에 방문했던 곳에 값을 바꿔주는 방법이 필요.
				// 방문했던 곳을 opened라는 곳을 통해 따로 저장.
				// 한번에 이어지는 나라들을 모아둔다. -> deque이기에 삽입 삭제 O(1)
				opened = new ArrayDeque<>();  
				amount = 0; // findGroup을 통해 찾은 인구수의 총합 
				
				int cnt = findGroup(r, c); // 이어지는 나라 찾기 -> 나라들을 opened에 넣고 방문 횟수를 cnt로 
				if(cnt == 1) continue;
				
				isMoved = true;
				int abs = amount / cnt; // 나라들 평균으로 업데이트 
				while(!opened.isEmpty()) {
					int[] loc = opened.poll();
					graph[loc[0]][loc[1]] = abs;
				}
			}
			
		}
		
		return isMoved;
	}


	private static int findGroup(int r, int c) {
		visited[r][c] = true;
		amount += graph[r][c];
		opened.offer(new int[] {r, c}); 
		int cnt = 1;
		
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
			if(visited[nr][nc]) continue;
			
			int diff = Math.abs(graph[r][c] - graph[nr][nc]); 
			if(diff < L || diff > R) continue;
			cnt += findGroup(nr, nc); // dfs로 계속해서 찾기. return 값을 더해서
		}
		
		return cnt;
	}


}