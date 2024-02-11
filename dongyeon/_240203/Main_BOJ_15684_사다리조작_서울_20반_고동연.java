package a0130.camp;

import java.io.*;
import java.util.*;

public class Main_BOJ_15684_사다리조작_서울_20반_고동연 {
	
	static int N,M,H,ans;
	static int[][] graph;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
	
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		graph = new int[H+1][N+1];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[a][b]=1;
			graph[a][b+1]=2;
		}
		
		ans = Integer.MAX_VALUE; 
		// 사다리를 0~3개 놓을 수 있기 때문에 모든 경우를 탐색해 본다. 
		// 최소 가로선 갯수 구하기 
		for(int i=0; i<4; i++) {
			search(1,0,i);
		}
		
		if(ans == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);
		
		
		
		br.close();
	}
	// 사다리 놓고 check하고 새로 놓은 사다리 갯수 update
	static void search(int c, int cnt, int R) {
		if(cnt == R) {
			if(check()) {
				ans = Math.min(ans, R);
			}
			return;
		}
		
		for(int x=c; x<H+1; x++) {
			for(int y=1; y<N; y++) {
				if(graph[x][y]==0 && graph[x][y+1]==0) {
					//사다리 놓기 
					graph[x][y]=1;
					graph[x][y+1]=2;
					
					search(1,cnt+1,R);
					
					// 사다리 돌려 놓기
					graph[x][y]=0;
					graph[x][y+1]=0;
				}
			}
		}
		
	}

	//i번 세로선의 결과가 i번이 나오는지 check
	static boolean check() {
		for(int i=1; i<=N; i++) {
			int y = i;
			int x = 1;
			
			while(x<=H) {
				if(graph[x][y]==1) {
					y++;
					x++;
				}else if(graph[x][y]==2) {
					y--;
					x++;
				}else {
					x++;
				}
			}
			if(y!=i) return false;
		}
		return true;
	}

}
