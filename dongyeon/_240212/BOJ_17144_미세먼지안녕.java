package a0208.camp;

import java.io.*;
import java.util.*;

public class Main_BOJ_17144_미세먼지안녕_서울_20반_고동연 {
	
	static int R, C, T;
	static int[][] map, temp;
	static int[] di = {1,-1,0,0};
	static int[] dj = {0,0,1,-1};
	static int[] aircondition = new int[2];

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		int idx =0;
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<C; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==-1) aircondition[idx++]=i;
			}
		}
		
		
		//t 초동안 일어남
		for(int i=0; i<T; i++) {
			//미세먼지 확산
			spread();
			// 공기청정기 가동
			air();
		}
		
	
//		for(int[] a:temp)System.out.println(Arrays.toString(a));
		
		int ans = 0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j]!=-1) ans+=map[i][j];
			}
		}
		System.out.println(ans);
		
		br.close();
		
	}

	static void air() {
		// 위쪽 공기 청정기 반시계
		// 우 상 좌 하
		int[] dx1 = {0,-1,0,1};
		int[] dy1 = {1,0,-1,0};
		
		// 시작좌표
		int x1 = aircondition[0];
		int y1 = 0;
		
		int direction1 = 0;
		
		int now1 = 0;
		while(direction1<4) {
			int nx = x1 + dx1[direction1];
			int ny = y1 + dy1[direction1];
			
			if(nx>=0 && nx<R && ny>=0 && ny<C && map[nx][ny]!=-1) {
				int after1 = map[nx][ny];
				map[nx][ny] = now1;
				now1 = after1;
				x1=nx;
				y1=ny;
			}else direction1++;
		}
		// 아래쪽 공기 청정기 시계
		// 우 하 좌 상
		int[] dx = {0,1,0,-1};
		int[] dy = {1,0,-1,0};
		
		// 시작좌표
		int x = aircondition[1];
		int y = 0;
		
		int direction = 0;
		
		int now = 0;
		while(direction<4) {
			int nx = x + dx[direction];
			int ny = y + dy[direction];
			
			if(nx>=0 && nx<R && ny>=0 && ny<C && map[nx][ny]!=-1) {
				int after = map[nx][ny];
				map[nx][ny] = now;
				now = after;
				x=nx;
				y=ny;
			}else direction++;
		}
	}

	static void spread() {
		temp = new int[R][C];
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j]!=0 && map[i][j]!=-1) {
					int mj = map[i][j];
					int newmj = 0;
					for(int d=0; d<4; d++) {
						int nx = i + di[d];
						int ny = j + dj[d];
						
						if(nx>=0 && nx<R && ny>=0 && ny<C && map[nx][ny]!=-1) {
							temp[nx][ny]+=mj/5;
							newmj+=mj/5;
						}
					}
					temp[i][j]+=mj-newmj;
				}else if(map[i][j]==-1) {
					temp[i][j]=-1;
				}
			}
		}
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				map[i][j]=temp[i][j];
			}
		}
	}

}
