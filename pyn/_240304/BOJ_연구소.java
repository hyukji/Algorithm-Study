package a0229;

import java.io.*;
import java.util.*;

public class Solution_bj_14502_연구소_서울_20반_박영남 {
	static int[] di= {-1, 0, 1, 0};
	static int[] dj= {0, 1, 0, -1};
	static int N, M, max=0;
	static int[][] map;
	static boolean[][] visit;
	static boolean[][] visited;
	static List<int[]> virus=new ArrayList<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine(), " ");
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		map=new int[N][M];
		
		visit=new boolean[N][M];
		visited=new boolean[N][M];
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==2) virus.add(new int[] {i,j});
			}
		}
		comb(0,0);
		
		System.out.println(max);
		br.close();
	}
	static void comb(int cnt, int start) {
		if(cnt==3) {
			int[][] spreadMap=new int[N][M];
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					spreadMap[i][j]=map[i][j];
				}
			}
			for(int[] v: virus) {
				visited=new boolean[N][M];
				spreadMap=spread(v[0], v[1], spreadMap);
			}
			max=Math.max(max, count(spreadMap));
			return;
		}
		for(int i=start; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==0) {
					map[i][j]=1;
					comb(cnt+1, start);
					map[i][j]=0;
				}
			}
		}
	}
	static int count(int[][] map) {
		int count=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) if(map[i][j]==0) count++;
		}
		return count;
	}
	static int[][] spread(int i, int j, int[][] map) {
		visited[i][j]=true;
		map[i][j]=2;
		for(int d=0; d<4; d++) {
			int ni=i+di[d];
			int nj=j+dj[d];
			if(0<=ni&&ni<N && 0<=nj&&nj<M && !visited[ni][nj] && map[ni][nj]==0) spread(ni,nj,map);
		}
		return map;
	}
}
