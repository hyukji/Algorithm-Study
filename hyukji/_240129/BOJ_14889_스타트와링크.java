package _240129;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14889_스타트와링크 {
	
	static StringTokenizer st;
	static int n;
	static int maxMember;
	static int[][] graph;
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		maxMember = n/2;
		
		graph = new int[n][n];
		for(int r =0; r < n; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int c =0; c < n; c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] left = new int[maxMember];
		int[] right = new int[maxMember];
		dfs(left, right, 1, 0, 1, 0);
		
		System.out.println(answer);
	}
	
	
	static private void dfs(int[] left, int[] right, int lSize, int rSize, int p, int score) {
		if(p == n) {
			int s = Math.abs(score);
			if(s < answer) answer = s;
			return;
		}
		
		if(lSize < maxMember) {
			int[] nLeft = Arrays.copyOf(left, maxMember);
			nLeft[lSize] = p;
			dfs(nLeft, right, lSize+1, rSize, p+1, score + calScore(left, lSize, p));
		}
		if(rSize < maxMember) {
			int[] nRight = Arrays.copyOf(right, maxMember);
			nRight[rSize] = p;
			dfs(left, nRight, lSize, rSize+1, p+1, score - calScore(right,rSize, p));
		}
	}
	
	static private int calScore(int[] oriMem, int oriSize, int newMem) {
		int sum = 0;
		for(int i = 0; i < oriSize; i++) {
			int ori = oriMem[i];
			sum += graph[ori][newMem];
			sum += graph[newMem][ori];
		}
		return sum;
	}
}
