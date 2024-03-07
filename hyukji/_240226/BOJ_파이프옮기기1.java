import java.io.*;
import java.util.*;

public class Main {

	static final int[] dr = {0, 0, 1, 1};
	static final int[] dc = {0, 1, 0, 1};
	static final int[][] directions= {{}, {1, 3}, {2, 3}, {1, 2, 3}};
	
	static int[][] graph;
	static int[][][] visited;
	static int n;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		
		graph = new int[n][n];
		visited = new int[n][n][4];
		for(int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < n; c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(dfs(0, 1, 1));

	}

	private static int dfs(int r, int c, int d) {
		if(r == n-1 && c == n-1) {
			return 1;
		}
		
		int result = 0;
		for(int nd : directions[d]) {
			int nr = r + dr[nd];
			int nc = c + dc[nd];
			if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
			if(graph[nr][nc] == 1) continue;
			if(nd == 3) {
				if(graph[nr-1][nc] == 1 || graph[nr][nc-1] == 1) continue;
			}
			if(visited[nr][nc][nd] != 0) {
				result += visited[nr][nc][nd] - 1;
				continue;
			}
			
			int v = dfs(nr, nc, nd);
			visited[nr][nc][nd] = v+1;
			result += v;
		}
		
		return result;
	}

}