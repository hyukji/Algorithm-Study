package personal;

import java.io.*;
import java.util.*;

public class Solution_d9_2105_디저트카페_서울_20_곽지혁  {
	
	static final int[] dr = {1, 1, -1, -1};
	static final int[] dc = {-1, 1, 1, -1};
	
	static int n;
	static int answer;
	static int[][] graph;
	static boolean[] eaten;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc < T+1; tc++) {
			answer = -1;
			n = Integer.parseInt(br.readLine());
			
			graph = new int[n][n];
			eaten = new boolean[101];
			
			for(int  r =0; r <n; r++) {
				st = new StringTokenizer(br.readLine());
				for(int  c =0; c <n; c++) {
					graph[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			for(int r =0; r < n-1; r++) {
				for(int c =1; c < n-1; c++) {
					dfs(r, c, 0, new int[4], new boolean[101]); // r, c에서 깊이우선으로 가능한 경우를 체크 
				}
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append(" ").append("\n");
		}
		
		System.out.print(sb);
	}
	
	private static void dfs(int r, int c, int d, int[] moves, boolean[] eaten) {
		if(d == 4) { // 한바퀴를 돌았을 때 
			int depth = (moves[2] + moves[3]) * 2;
			if(answer < depth) answer = depth;
			return;
		}
		
		if(d > 1) { // 아래쪽을 탐색하고 돌아오는 루트 
			for(int i = 0; i < moves[d]; i++) {
				r += dr[2]; c += dc[d];
				if(r < 0 || r >= n || c < 0 || c >= n) return;

				int dessert = graph[r][c];
				if(eaten[dessert]) return;
				eaten[dessert] = true;
			}
			
			dfs(r, c, d+1, moves, Arrays.copyOf(eaten, 101));
			
		} else { // 가능한 경우를 찾기 위해 아래로 내려갈  때 
			int nr = r += dr[d];
			int nc = c += dc[d];
			
			while(nr >= 0 && nr < n && nc >= 0 && nc < n) {
				int dessert = graph[nr][nc];
				if(eaten[dessert]) return; // 같은 디저트를 먹은 적이 있다면 멈춤 
				eaten[dessert] = true;
				
				moves[d+2]++;  // moves에 d상황에 움직인 만큼 저장해둔다. 
				dfs(nr, nc, d+1, Arrays.copyOf(moves, 4), Arrays.copyOf(eaten, 101)); // 깊은 복사를 이용해 처리 
				
				nr += dr[d]; nc += dc[d];
			}
		}
	}
	

}