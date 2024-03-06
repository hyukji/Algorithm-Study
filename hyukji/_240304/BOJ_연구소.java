import java.io.*;
import java.util.*;

public class BOJ_연구소 {

	static final int[] dr = {0, 0, -1, 1};
	static final int[] dc = {-1, 1, 0, 0};
	
	static int n, m, tot = 0, minVirus = Integer.MAX_VALUE;
	static int[][] graph;
	static List<int[]> virus;
	
	// n의 범위 64 - > (60 * 59 * 58 / 6) * 64 = 2백만
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		graph = new int[n][m];
		virus = new ArrayList<>();
		
		for(int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < m; c++)  {
				int v = Integer.parseInt(st.nextToken());
				if(v == 2) virus.add(new int[] {r, c});
				else if(v == 0) tot++; // 안전지역 개수
				graph[r][c] = v;
			}
		}

		comb(0, 0);
		System.out.println(tot - minVirus - 3); // 안전 지역 개수 - 퍼진 바이러스 최소 - 벽3
	}
	
	private static void comb(int idx, int cnt) {
		if(cnt == 3) {
			minVirus = Integer.min(minVirus, countVirus());
			return;
		}
	
		for(int i = idx; i < n * m; i++) { // 조합
			int r = i / m;
			int c = i % m;
			if(graph[r][c] == 0)  { 
				graph[r][c] = 1; // MAP에 벽세우기
				comb(idx+1, cnt + 1);
				graph[r][c] = 0;
			}
		}
	}

	private static int countVirus() {
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		for(int[] cur : virus) dq.add(cur);
		boolean[][] visited = new boolean[n][m];
		
		int cnt = 0;
		while(!dq.isEmpty()) {
			int[] cur = dq.poll();
			
			for(int d = 0; d < 4; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				if(nr < 0 || nr >= n || nc < 0 || nc>= m) continue;
				
				if(graph[nr][nc] != 0 || visited[nr][nc]) continue;
				visited[nr][nc] = true;
				cnt++; // 퍼진 개수
				dq.offer(new int[] {nr, nc});
			}
		}
		
		return cnt;
	}

}