import java.io.*;
import java.util.*;

public class Main {
	
	static class Node {
		int d;
		Node nxt, bef;
		public Node(int d) {
			super();
			this.d = d;
			this.nxt = null;
			this.bef = null;
		}
	}
	
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {1, 0, -1, 0};
	static boolean[][] graph;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int n = Integer.parseInt(br.readLine());
		graph = new boolean[101][101];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			graph[r][c] = true;
			r += dr[d]; c += dc[d];
			graph[r][c] = true;

			Node loc = new Node(d);
			solve(loc, loc, r, c, g);
		}
		
		int answer = check();
		System.out.println(answer);
	}
	
	private static void solve(Node head, Node tail, int r, int c, int g) {
		for(int i = 0; i < g; i++) {
			Node loc = tail;
			
			while(loc != null) {
				int d = (loc.d + 1) % 4;
				r += dr[d]; c += dc[d];
				graph[r][c] = true;
				
				Node nLoc = new Node(d);
				tail.nxt = nLoc;
				nLoc.bef = tail;
				tail = nLoc;
				
				loc = loc.bef;
			}
		}
	}

	private static int check() {
		int cnt = 0;
		for(int r = 0; r < 100; r++) {
			for(int c= 0; c <100; c++) {
				if(graph[r][c] && graph[r][c+1] && graph[r+1][c] && graph[r+1][c+1]) cnt++;
			}
		}
		return cnt;
	}

}