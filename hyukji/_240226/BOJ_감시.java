import java.io.*;
import java.util.*;

public class Main {

	private static final int[] dr = {-1, 0, 1, 0};
	private static final int[] dc = {0, 1, 0, -1};

	static int n, m, k, tot, answer = Integer.MAX_VALUE;
	static int[][] graph;
	static List<int[]> cctvs;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		graph = new int[n][m];
		cctvs = new ArrayList<>();

		for (int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < m; c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
				if(graph[r][c] == 0) tot++;
				if(graph[r][c] > 0 && graph[r][c] < 6) {
					cctvs.add(new int[] { r, c, graph[r][c] });
				}
			}
		}

		k = cctvs.size();
		visited = new boolean[k];
		dfs(0, 0);

		System.out.println(answer);

	}

	private static void dfs(int idx, int count) {
		if (idx == k) {
			answer = Math.min(answer, tot-count);
			return;
		}
	
		int[] cctv = cctvs.get(idx);
		int r = cctv[0];
		int c = cctv[1];
		int v = cctv[2];

		for (int d = 0; d < 4; d++) {
			int[] ds = getDirections(d, v);
			int plus = fillwithDirection(r, c, ds, 7);
			dfs(idx + 1, count + plus);
			fillwithDirection(r, c, ds, -7);
			
			if(v == 5 && d == 0) break;
			if(v == 2 && d == 1) break;
		}
			

	}
	
	private static int[] getDirections(int d, int v) {
			if (v == 5) return new int[] { 0, 1, 2, 3 };
			else if (v == 4) return new int[] { d, (d + 1) % 4, (d + 2) % 4 };
			else if (v == 3) return new int[] { d, (d + 1) % 4 };
			else if (v == 2) return new int[] { d, d + 2 };
			else if (v == 1) return new int[] { d };
			return new int[] {};
	}

	private static int fillwithDirection(int r, int c, int[] ds, int nv) {
		int changed = 0;
		
		for (int d : ds) {
			int nr = r;
			int nc = c;
			while (true) {
				nr += dr[d];
				nc += dc[d];
				if (nr < 0 || nr >= n || nc < 0 || nc >= m) break;

				int v = graph[nr][nc];
				if (v == 6) break;
				else if (v > 0 && v < 7) continue;

				if(graph[nr][nc] == 0) changed++;
				graph[nr][nc] += nv;
			}
		}
		return changed;
	}

}