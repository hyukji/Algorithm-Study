import java.io.*;
import java.util.*;

public class Main {

	static int[][] graph = new int[4][8];
	static boolean[] v ;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		for(int i = 0; i < 4; i++) {
			String row = br.readLine();
			for(int j = 0; j < 8; j++) {
				graph[i][j] =row.charAt(j) - '0';
			}
		}

		int k = Integer.parseInt(br.readLine());
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int index = Integer.parseInt(st.nextToken()) -1;
			int d = Integer.parseInt(st.nextToken());
			
			v = new boolean[4];
			rotate(index, d);
		}

		int answer = 0;
		for(int i = 0; i < 4; i++) {
			answer += (1 << i) * graph[i][0] ;
		}
		
		System.out.println(answer);
	}
	
	private static void rotate(int i, int d) {
		v[i] = true;
		boolean left = false, right = false;
		if(i > 0 && !v[i-1] && graph[i][6] != graph[i-1][2]) left = true;
		if(i < 3 && !v[i+1] && graph[i][2] != graph[i+1][6]) right = true;

		if(d > 0) {
			int temp = graph[i][7];
			for(int l = 7; l > 0; l--) graph[i][l] = graph[i][l-1];
			graph[i][0] = temp;
		} else {
			int temp = graph[i][0];
			for(int l = 0; l < 7; l++) graph[i][l] = graph[i][l+1];
			graph[i][7] = temp;
		}
		
		if(left) rotate(i-1, -d);
		if(right) rotate(i+1, -d);
	}

}