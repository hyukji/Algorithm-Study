package a0229.camp;

import java.io.*;
import java.util.*;

public class Main_BOJ_17143_낚시왕_서울_20반_고동연 {
	
	static int r,c,m,ans;
	static Queue<int[]>[][] map,temp;
	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,1,-1};
	// 낚시왕이 잡은 상어크기의 합 구하기
	// ArrayList => 시간초과난다. 
	// s 속도가 1000까지라서 하나하나 이동하면 시간초과 난다.
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		// 격자판 크기 r,c, 상어의 수 m
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new Queue[r+1][c+1];
		temp = new Queue[r+1][c+1];
		for(int i=1; i<r+1; i++) {
			for(int j=1; j<c+1; j++) {
				map[i][j] = new PriorityQueue<>((o1,o2)->{return o2[2]-o1[2];});
			}
		} 
		// m개의 상어의 정보 
		// x,y 상어의 위치, s 속력, d 이동방향, z 크기
		// d가 1->위, 2->아래, 3->오른쪽, 4->왼쪽
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			map[x][y].offer(new int[] {s,d,z});
		}
		ans = 0;
		for(int i=1; i<c+1; i++) {
			// 땅과 가장 가까운 순으로 정렬하기
			for(int j=1; j<r+1; j++) {
				// 가장 가까운 상어 잡기
				if(map[j][i].size()!=0) {
					int[] now = map[j][i].poll();
					ans+= now[2];
					map[j][i].clear();
					break;
				}
			}
			
			// 나머지 상어들 이동
			for(int a=1; a<r+1; a++) {
				for(int b=1; b<c+1; b++) {
					temp[a][b] = new PriorityQueue<>((o1,o2)->{return o2[2]-o1[2];});
				}
			}
			for(int a=1; a<r+1; a++) {
				for(int b=1; b<c+1; b++) {
					if(map[a][b].size()!=0) {
						int[] cur = map[a][b].poll();
						int x = a;
						int y = b;
						int d = cur[1];
						int speed = cur[0];
						if(d==1 || d==2) speed %= (r-1)*2;
						else speed %=(c-1)*2;
						for(int t=0; t<speed; t++) {
							int nx = x + di[d-1];
							int ny =  y + dj[d-1];
							if(nx<1 || nx>r || ny<1 || ny>c) {
								if(d==1) {
									d=2;
									nx = x + di[d-1];
									ny = y + dj[d-1];
								}
								else if(d==2) {
									d=1;
									nx = x + di[d-1];
									ny = y + dj[d-1];
								} 
								else if(d==3) {
									d=4;
									nx = x + di[d-1];
									ny = y + dj[d-1];
								} 
								else if(d==4) {
									d=3;
									nx = x + di[d-1];
									ny = y + dj[d-1];
								} 
							}
							x=nx;
							y=ny;
						}
						// 같은 칸의 상어가 2마리 이상 있다면 크기가 큰 상어가 작은상어를 잡아먹는다.
						temp[x][y].offer(new int[]{cur[0],d,cur[2]});
					}
				}
			}
			for(int a=1; a<r+1; a++) {
				for(int b=1; b<c+1; b++) {
					map[a][b]=temp[a][b];
				} 
			}
			
		}
		
		System.out.println(ans);
		br.close();
	}
}
