import java.io.*;
import java.util.*;

public class Main {
	
	static int[][][] tetrises = {
			{{1, 0}, {2, 0}, {3, 0}}, {{0, 1}, {0, 2}, {0, 3}}, 
			{{0, 1}, {1, 1}, {1, 2}}, {{-1, 0}, {-1, 1}, {-2, 1}},
			{{0, 1}, {0, 2}, {1, 2}}, {{0, 1}, {-1, 1}, {-2, 1}}, {{1, 0}, {1, 1}, {1, 2}}, {{0,1}, {1, 0}, {2, 0}},
			{{0, 1}, {0, 2}, {1, 1}}, {{1, 0}, {2, 0}, {1, -1}}, {{0, 1}, {0,2}, {-1, 1}}, {{1, 0}, {2, 0}, {1, 1}},
			{{1, 0}, {0, 1}, {1, 1}}
	};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int tc = 1;
		while(true) {
			int n = Integer.parseInt(br.readLine().trim());
			if(n == 0) break;
			
			int answer = Integer.MIN_VALUE;
			int[][] board = new int[n][n];
			for(int r = 0; r < n; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int c = 0; c < n; c++) {
					board[r][c] = Integer.parseInt(st.nextToken());
				}
			}


			for(int r = 0; r < n; r++) {
				for(int c = 0; c < n; c++) {
					for(int[][] block : tetrises) {
						int result = board[r][c];
						for(int[] loc : block) {
							int nr = r + loc[0];
							int nc = c +loc[1];
							if(nr < 0 || nr >= n || nc < 0 || nc >= n) {
								result = Integer.MIN_VALUE;
								break;
							}
							result += board[nr][nc];
						}
						
						if(result == Integer.MIN_VALUE) continue;
						if(answer < result) answer = result;
					}
				}
			}
			
			sb.append(tc++).append(". ").append(answer).append("\n");
		}
	
		System.out.println(sb);
		
	}

}