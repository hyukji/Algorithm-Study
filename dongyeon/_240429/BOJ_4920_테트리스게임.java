package a0416;

import java.io.*;
import java.util.*;
public class BOJ_4920_테트리스게임 {
	
	static int n, ans;
	static int[][] map;
	static int[][] di = {
			{0,0,0,0},
			{0,1,2,3},
			{0,0,1,1},
			{0,1,1,2},
			{0,0,0,1},
			{0,1,2,2},
			{0,1,1,1},
			{0,0,1,2},
			{0,0,0,1},
			{0,1,2,1},
			{0,1,1,1},
			{0,1,1,2},
			{0,0,1,1}
	};
	static int[][] dj = {
			{0,1,2,3},
			{0,0,0,0},
			{0,1,1,2},
			{0,0,-1,-1},
			{0,1,2,2},
			{0,0,0,-1},
			{0,0,1,2},
			{0,1,0,0},
			{0,1,2,1},
			{0,0,0,-1},
			{0,0,-1,1},
			{0,1,0,0},
			{0,1,0,1}
	};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb;
		StringTokenizer st;
		
		int idx =1;
		while(true) {
			st=new StringTokenizer(br.readLine()," ");
			n = Integer.parseInt(st.nextToken());
			if(n==0) break;
			sb = new StringBuilder();
			
			map = new int[n][n];
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine()," ");
				for(int j=0; j<n; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			ans = Integer.MIN_VALUE;
			
			search();
			
			
			sb.append(idx).append(". ").append(ans);
			idx++;
			System.out.println(sb.toString());
		}
		
		br.close();
	}
	static void search() {
		for(int a=0; a<13; a++) {
				for(int i=0; i<n; i++) {
					for(int j=0; j<n; j++) {
						int total = 0;
						boolean check = true;
						for(int k=0; k<4; k++) {
							int nx = i+di[a][k];
							int ny = j+dj[a][k];
//							System.out.println(di[a][k]);
							if(nx>=0 && nx<n && ny>=0 && ny<n) { //범위안에 있으면
								total+=map[nx][ny];
							}else {//범위 탈출하면
								check=false;
								break;
							}
						}
						if(check) {
							ans = Math.max(ans, total);
						}
					}
				}
		}
	}
	
}
