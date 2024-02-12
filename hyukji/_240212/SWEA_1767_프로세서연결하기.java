package personal;

import java.io.*;
import java.util.*;

public class Solution_d9_1767_프로세서연결하기_서울_20_곽지혁  {
	
	static final int[] dr = {1, 0, -1, 0};
	static final int[] dc = {0, 1, 0, -1};
	
	static int n, totCnt, maxConnected, maxLines;
	static int[][] graph, cores;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc < T+1; tc++) {
			cores = new int[12][2];
			maxConnected = 0;
			maxLines = 0;
			totCnt = 0;
			
			n = Integer.parseInt(br.readLine());
			graph = new int[n][n];
			
			for(int  r =0; r <n; r++) {
				st = new StringTokenizer(br.readLine());
				for(int  c =0; c <n; c++) {
					graph[r][c] = Integer.parseInt(st.nextToken());
					if(graph[r][c] == 1) cores[totCnt++] = new int[] {r, c}; // 프로세서의 코어 따로 저장 
				}
			}

			dfs(0, 0, 0);
			
			sb.append("#").append(tc).append(" ").append(maxLines).append(" ").append("\n");
		}
		
		System.out.print(sb);
	}

	/*
	 * cnt : 현재까지 탐색한 core수 
	 * connected : 현재까지 연결한 core수
	 * lines : 현재까지 사용한 line 
	 */
	private static void dfs(int cnt, int connected, int lines) { 
		if(totCnt - cnt + 1 < maxConnected - connected) return; // 앞으로 모든 코어를 연결해도 최대가 아닐때 
		
		if(cnt == totCnt) { // 모든 코어에 대해 탐색이 끝난 후 
			if(connected > maxConnected || (connected == maxConnected && lines < maxLines)) {
				maxConnected = connected;
				maxLines = lines;
			}
			return;
		}

		int r = cores[cnt][0];
		int c = cores[cnt][1];
		if(r == 0 || c == 0 || r == n-1 || c == n-1) { // 맨 끝라인에 있다면 자동 연결 
			dfs(cnt+1, connected+1, lines);
			return;
		}
		
		for(int d = 0; d < 4; d++) { 
			// 처음에는 복구 시키는 과정없이 새로운 2차원 배열을 만들어서 처리 
			// 새로운 2차원만드는 과정자체가 n*n이므로 오랜시간이 걸리더라...
			// 코드짜기에는 빠르게 짤 수 있어서 좋았지만, 시간복잡도는 높더라 ... B형에서는 이렇게 하면 안될 듯 ! A형은 괜찮지 않을ㄲ ㅏ... 
			if(check(r, c, d)) { // 연결 가능하다면 
				int plusLines = connect(r, c, d); // 연결 
				dfs(cnt+1, connected+1, lines + plusLines);
				rollback(r, c, d); // 복구 
			};
		}

		dfs(cnt+1, connected, lines); // 연결하지 않은 경우 
	}


	private static void rollback(int r, int c, int d) {
		while(true) {
			r += dr[d]; c += dc[d];
			if(r < 0 || r >= n || c < 0 || c >= n) return;
			if(graph[r][c] != 2) return;
			
			graph[r][c] = 0;
		}
	}

	
	private static boolean check(int r, int c, int d) {
		while(true) {
			r += dr[d]; c += dc[d];
			if(r < 0 || r >= n || c < 0 || c >= n) return true;
			if(graph[r][c] != 0) return false;
		}
	}

	private static int connect(int r, int c, int d) { // 연결 가능한지 확인 후 가능하다면 필요한 선의 개수 
		int plusLines = 0;
		while(true) {
			r += dr[d]; c += dc[d];
			if(r < 0 || r >= n || c < 0 || c >= n) return plusLines;
			
			graph[r][c] = 2;
			plusLines++;
		}
	}
	

}