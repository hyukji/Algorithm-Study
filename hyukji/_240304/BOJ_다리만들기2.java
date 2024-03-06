import java.io.*;
import java.util.*;

public class BOJ_다리만들기2 {
	
	static int[] dr = {0, 0, -1, 0, 1};
	static int[] dc = {0, 1, 0, -1, 0};
	
	static PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
	static int[][] graph, borders;
	static boolean[][] visited;
	static int[] p;
	static int n, m;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		graph = new int[n][m];
		borders = new int[n][m];
		for (int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < m; c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int num = 2;
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				if(graph[r][c] == 1) checkNum(r, c, num++); // 섬 번호 부여 및 경계선과 방향 파악
			}
		}

		getDist(); // 섬 사이의 거리 구해서 pq에 넣기
		
		p = new int[num];
		for(int i = 0; i < num; i++) p[i] = i;
		System.out.println(kruskal()); // 크루스칼을 이용한 값 구하기
	}
	
	private static int kruskal() {
		int result = 0;
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int from = cur[0];
			int to = cur[1];
			int dist = cur[2];
			
			if(union(from, to)) result += dist;
 		}

		for(int i = 2; i < p.length; i++) find(i);
		for(int i = 3; i < p.length; i++) if(p[i-1] != p[i]) return -1; // 하나라도 연결 안된 곳이 있다
		return result;
	}

	private static boolean union(int a, int b) {
		int ra = find(a);
		int rb = find(b);
		if(ra == rb) return false;
		p[ra] = rb;
		return true;
	}

	private static int find(int a) {
		if(p[a] == a) return a;
		return p[a] = find(p[a]);
	}

	// 섬 경계선과 바다방향 가져와 acrossSea 호출
	private static void getDist() {
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				int v = borders[r][c];
				if(v == 0) continue;
				for(int d = 1; d < 5; d++) 
					if((v & (1 << d)) > 0) 
						acrrossSea(r, c, d, graph[r][c]);
			}
		}
	}
	
	// 섬 경계선에서 특정 방향으로 바다를 건널때
	private static void acrrossSea(int r, int c, int d, int num) {
		int dist = 0;
		while(true) {
			r += dr[d];
			c += dc[d];
			if(r < 0 || r >= n || c < 0 || c >= m) return;
			if(graph[r][c] != 0) {
				if(dist != 1) {
					int nNum = graph[r][c];
					pq.add(new int[] {num, nNum, dist});
				}
				return;
			}
			
			dist++;
		}
	}

	// 섬 번호 부여 및 경계선 파악
	private static void checkNum(int r, int c, int num) {
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		graph[r][c] = num;
		dq.offer(new int[] {r, c});
		
		while(!dq.isEmpty()) {
			int[] cur = dq.poll();
			for(int d = 1; d < 5; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;

				int v = graph[nr][nc];
				if(v == 0) {
					borders[cur[0]][cur[1]] |= (1 << d); // 바다로 나가는 방향 저장 
				} else if(v == 1) {
					graph[nr][nc] = num;
					dq.offer(new int[] {nr, nc});
				}
			}
		}
	}
	

}