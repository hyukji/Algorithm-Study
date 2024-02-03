import java.io.*;
import java.util.*;

public class Main {
	
	static int n,  m, k, answer = Integer.MAX_VALUE;
	static boolean[] visited;
	static int[] perm;
	static int[][] graph, rGraph;
	static int[] dr = {0, 1, 0, -1}; //좌하우상
	static int[] dc = {-1, 0, 1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		graph = new int[n][m];
		for(int r =0; r< n; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int c =0; c< m; c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		rGraph = new int[k][3]; // 회전 정보
		for(int r =0; r< k; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int c =0; c< 2; c++) {
				rGraph[r][c] = Integer.parseInt(st.nextToken())-1;
			}
			rGraph[r][2] = Integer.parseInt(st.nextToken());
		}

		perm = new int[k];
		visited = new boolean[k];
		permRotate(0); // 순열로 경우의 수 + 회전.
		
		System.out.println(answer);
	}
	
	private static void permRotate(int cnt) {
		if(cnt == k) {
			int[][] nGraph = copyGraph(); // 깊은 복사를 위해서 복제
			for(int i =0; i < k; i++)  rotate(nGraph, rGraph[perm[i]]);	 // 회전
			int value = getMinRow(nGraph);
			if(answer > value) answer = value; 
			return;
		}
		
		for(int i =0; i < k; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			perm[cnt] = i;
			permRotate(cnt+1);
			visited[i] = false;
		}
	}

	private static int getMinRow(int[][] nGraph) {
		int minValue = Integer.MAX_VALUE;
		for(int r =0; r< n; r++) {
			int row = 0;
			for(int c =0; c< m; c++)  row += nGraph[r][c];
			if(minValue > row) minValue = row;
		}
		return minValue;
	}

	private static void rotate(int[][] nGraph, int[] rotation) {
		int r = rotation[0];
		int c = rotation[1];
		for(int s = 1; s <= rotation[2]; s++) {
			int d = 0; 
			int nr = r - s;
			int nc = c + s;
			int temp = nGraph[nr][nc];
			//좌하우상
			while(d<4) {
				int nnr = nr+dr[d];
				int nnc = nc+dc[d];
				if(nnr < r -s || nnr> r+s || nnc < c -s|| nnc > c +s) {
					d++;
					continue;
				}
				nGraph[nr][nc] = nGraph[nnr][nnc];
				nr = nnr;
				nc = nnc;
			}
			 nGraph[r - s+1][c + s] = temp;
			
		}
		
	}

	private static int[][] copyGraph() {
		int[][] nGraph = new int[n][m];
		for(int r =0; r< n; r++) {
			for(int c =0; c< m; c++) {
				nGraph[r][c] = graph[r][c];
			}
		}
		return nGraph;
	}

}