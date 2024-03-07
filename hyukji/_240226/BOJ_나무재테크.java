import java.io.*;
import java.util.*;

public class Main {
	
	static int n;
	static int[][] food, graph;
	static PriorityQueue<Tree> pq;
	static int[]  dr = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[]  dc = {-1, 0, 1, -1, 1, -1, 0, 1};
	
	static class Tree {
		int r, c, age;

		public Tree(int r, int c, int age) {
			super();
			this.r = r;
			this.c = c;
			this.age = age;
		}

		@Override
		public String toString() {
			return "Tree [r=" + r + ", c=" + c + ", age=" + age + "]";
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		n =  Integer.parseInt(st.nextToken());
		int m =  Integer.parseInt(st.nextToken());
		int k =  Integer.parseInt(st.nextToken());
		
		food = new int[n][n];
		graph = new int[n][n];
		for (int r = 0; r < n; r++)  {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < n; c++)  {
				food[r][c] = Integer.parseInt(st.nextToken());
				graph[r][c] = 5;
			}
		}

		pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.age, o2.age));
		for (int i = 0; i < m; i++)  {
			st = new StringTokenizer(br.readLine());
			int r =  Integer.parseInt(st.nextToken()) - 1;
			int c =  Integer.parseInt(st.nextToken()) - 1;
			int age =  Integer.parseInt(st.nextToken());
			pq.add(new Tree(r, c, age));
		}
		
		for(int i = 0; i < k; i++) {
			List<Tree> died = spring(); 
			summer(died);
			fall();
			winter();
		}
		
		System.out.println(pq.size());
	}

	private static void winter() {
		for (int r = 0; r < n; r++)  {
			for (int c = 0; c < n; c++)  {
				graph[r][c] += food[r][c];
			}
		}
	}

	private static void fall() {
		List<Tree> temp = new ArrayList<>();
		for(Tree tree : pq) {
			 if(tree.age % 5 == 0) {
				 for(int d = 0; d <8;d++) {
					 int nr = tree.r + dr[d];
					 int nc = tree.c + dc[d];
					 if(nr < 0 || nr >= n || nc< 0|| nc>=n) continue;
					 temp.add(new Tree(nr, nc, 1));
				 }
			 }
		}
		for(Tree tree : temp) pq.offer(tree);
	}

	private static void summer(List<Tree> died) {
		for(Tree tree : died) {
			int r = tree.r;
			int c = tree.c;
			graph[r][c] += (tree.age/2);
		}
	}

	private static List<Tree> spring() {
		List<Tree> died = new ArrayList<>();
		PriorityQueue<Tree> tempPQ = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.age, o2.age));
		while(!pq.isEmpty()) {
			Tree tree = pq.poll();
			int r = tree.r;
			int c = tree.c;
			if(graph[r][c] < tree.age) {
				died.add(tree);
				continue;
			}
			graph[r][c] -= tree.age++;
			tempPQ.offer(tree);
		}
		pq = tempPQ;
		return died;
	}
}