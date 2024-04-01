package study;

import java.io.*;
import java.util.*;

public class SWEA_5648_원자소멸시뮬레이션 {
	static class Atom{
		int x;
		int y;
		int d;
		int K;
		Atom(int x, int y, int d, int K){
			this.x=x;
			this.y=y;
			this.d=d;
			this.K=K;
		}
	}
	static int[] di= {-1, 1, 0, 0};
	static int[] dj= {0, 0, -1, 1};
	static int[][] map;
	static ArrayDeque<Atom> atom=new ArrayDeque<>();
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_sw_5648.txt"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		StringTokenizer st;
		
		int T=Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			int N=Integer.parseInt(br.readLine());
			map=new int[4001][4001];
			for(int i=0; i<N; i++) {
				st=new StringTokenizer(br.readLine(), " ");
				int y=(Integer.parseInt(st.nextToken())+1000)*2;
				int x=4000-((Integer.parseInt(st.nextToken())+1000)*2);
				int d=Integer.parseInt(st.nextToken());
				int K=Integer.parseInt(st.nextToken());
				
				map[x][y]=K;
				atom.offer(new Atom(x, y, d, K));
			}
			sb.append("#").append(tc).append(" ").append(move()).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
	static int move() {
		int value=0;
		while(!atom.isEmpty()) {
			Atom a=atom.poll();
			if(map[a.x][a.y]!=a.K) {
				value+=map[a.x][a.y];
				map[a.x][a.y]=0;
				continue;
			}
			int nx=a.x+di[a.d];
			int ny=a.y+dj[a.d];
			if(0<=nx&&nx<4001 && 0<=ny&&ny<4001) {
				if(map[nx][ny]==0) {
					map[nx][ny]=a.K;
					atom.offer(new Atom(nx,ny,a.d, a.K));
				} else {
					map[nx][ny]+=a.K;
				}
			}
			map[a.x][a.y]=0;
		}
		return value;
	}
}
