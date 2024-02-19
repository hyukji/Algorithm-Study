import java.io.*;
import java.util.*;

public class Solution {
	
	static final int[] dr = {-1, 0, 1, 0};
	static final int[] dc = {0, 1, 0, -1};
	
	static int[][] graph;
	static int answer = Integer.MIN_VALUE, n;
	static int[][] wormHole;
	static int[][] blocks ;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		
		blocks = new int[6][4];
		for(int i = 1; i < 6; i++) {
			int[] basic = new int[4];
			for(int d = 0; d < 4; d++)  basic[d] = (d+2) % 4;
			blocks[i] = basic;
		}
		
		blocks[1][3] = 0; blocks[1][2] =1; 
		blocks[2][3] = 2; blocks[2][0] =1; 
		blocks[3][1] = 2; blocks[3][0] =3; 
		blocks[4][1] = 0; blocks[4][2] =3; 
		
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc < T+1; tc++) {
			answer = 0;
			wormHole = new int[5][5]; // 첫번째 웜홀좌표, 두번째 웜홀 좌표, 웜홀개수
			
			n = Integer.parseInt(br.readLine().trim());
			graph = new int[n][n];
			
			for(int r = 0; r < n; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c < n; c++) {
					int v = Integer.parseInt(st.nextToken());
					graph[r][c] = v;
					if(v > 5) {
						int i = wormHole[v-6][4]++;
						wormHole[v-6][i*2] = r;
						wormHole[v-6][i*2+1] = c;
					}
				}
			}

			for(int r = 0; r < n; r++) {
				for(int c = 0; c < n; c++) {
					if(graph[r][c] != 0) continue;
					for(int d = 0; d < 4; d++) run(r, c, d);
				}
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.print(sb);

	}

	private static void run(int r, int c, int d) {
		int score = 0;
		int nr = r, nc = c;
		while(true) {
			nr += dr[d];  nc += dc[d];
			if(nr < 0 || nr >= n || nc < 0 || nc >= n) {
				score++;
				d = (d+2) % 4;
				continue;
			}
			
			if((nr == r && nc == c) || graph[nr][nc] == -1) {
				answer = Math.max(answer, score);
				return;
			}
			
			int v= graph[nr][nc];
			if(v == 0) continue;
			else if(v > 5) {
				int nnr = wormHole[v-6][0]; int nnc = wormHole[v-6][1];
				if(nnr == nr && nnc == nc) {
					nr = wormHole[v-6][2];  nc = wormHole[v-6][3];
				} else {
					nr = nnr; nc = nnc;
				}
			} else {
				d = blocks[v][d];
				score++;
			}
		}
		
		
	}

}