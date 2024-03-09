package aHomework;

import java.io.*;
import java.util.*;

public class Solution_swea_2105_디저트카페_서울_20반_박영남 {
	static int N, max;
	// 오른쪽 아래↘, 오른쪽 위↗, 왼쪽 위↖, 왼쪽 아래↙
	static int[] di= {1, -1, -1, 1};
	static int[] dj= {1, 1, -1, -1};
	static int[][] cafe;
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_2105.txt"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		StringTokenizer st;
		
		int T=Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			N=Integer.parseInt(br.readLine());
			cafe=new int[N][N];
			for(int i=0; i<N; i++) {
				st=new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; j++) cafe[i][j]=Integer.parseInt(st.nextToken());
			}
			
			max=0;
			for(int i=1; i<N-1; i++) { // 첫째줄에서 시작하는건 겹치므로 제외, 마지막줄도 겹치거나 못만드므로 제외
				for(int j=0; j<N-2; j++) { // 마지막 두 열에서 시작하면 사각형을 못만들거나 겹치므로 제외 
					visited=new boolean[101];
					visited[cafe[i][j]]=true;
					find(i, j, i, j, 0, 0);
				}
			}
			sb.append("#").append(tc).append(" ");
			if(max<4) sb.append(-1).append("\n"); // max가 4보다 작으면 한 바퀴를 못돌은 것이므로 -1 출력
			else sb.append(max).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
	static void find(int starti, int startj, int i, int j, int count, int vector) { // 방향값을 기억하여 dfs
		for(int d=vector; d<4; d++) { // 왔던 방향을 제외하고 그 후부터
			int ni=i+di[d];
			int nj=j+dj[d];
			
			if(0<=ni&&ni<N && 0<=nj&&nj<N) {
				// 시작 지점을 방문처리하고 시작하기 때문에 시작점 ni, nj로 확인
				if(ni==starti && nj==startj) { // 시작점으로 돌아왔다면 
					max=Math.max(max, count+1); // count값 저장 후 리턴
					return;
				}
				if(visited[cafe[ni][nj]]) continue; // 방문한 카페라면 다음 방향 탐색
				else { // 방문한 적 없는 카페라면
					visited[cafe[ni][nj]]=true; // 방문처리 
					find(starti, startj, ni, nj, count+1, d); // 같은 방향으로 한번 더 탐색
					visited[cafe[ni][nj]]=false; // 방문처리 취소
				}
			}
		}
	}
}
