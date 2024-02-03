import java.io.*;
import java.util.*;

public class Main {

	static int n = 0;
	static int h = 0;
	static int[][] graph;
	
	static int answer = Integer.MAX_VALUE;
	static int count = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		
		graph = new int[h][n];
		for(int i =0; i < m; i++) { 
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken()) -1;
			int b = Integer.parseInt(st.nextToken()) -1;
			
			graph[a][b] = 1; // a번째 세로줄에 b, b+1 연결되어 있다.
		}
		
		dfs(0, 0, 0);
		
		if(answer == Integer.MAX_VALUE) answer = -1;
		System.out.println(answer);
	}

	private static void dfs(int r, int c, int start) {
		if(r == h) { // 맨 끝까지 갔을 때x
			if(start != c) return; // i번째는 i번째
			if(start == n-1) {
				answer = Math.min(answer, count);
				return;
			}
			dfs(0, start+1, start+1);
			return;
		}

		// 가로선이 이미 존재한다면
		if(c > 0 && graph[r][c-1] == 1) {
			dfs(r+1, c-1, start);
			return;
		}
		if(c < n-1 && graph[r][c] == 1) {
			dfs(r+1, c+1, start);
			return;
		}
		
		//가로선 추가x
		dfs(r+1, c, start);
		
		if(count == 3) return;

		//왼쪽으로 가로선	
		if (c == 1 || (c > 1 && graph[r][c-2] != 1)) {
			 graph[r][c-1] = 1;
			 count++;
			dfs(r+1, c-1, start);
			 graph[r][c-1] = 0;
			 count--;
		}
		
		//오른쪽으로 가로선 추가
		if(c < n-1 && graph[r][c+1] != 1) {
			 graph[r][c] = 1;
			 count++;
			 dfs(r+1, c+1, start);
			 graph[r][c] = 0;
			 count--;
		}
		
	}

}