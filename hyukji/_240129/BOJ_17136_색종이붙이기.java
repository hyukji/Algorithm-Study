package _240129;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17136_색종이붙이기 {
	static int[] paper;
	static int min=Integer.MAX_VALUE;
	static int[][] arr = new int[10][10];
	public static void main(String[] args) throws IOException {
		paper = new int[6];
		for(int i=0;i<6;i++) {
			paper[i] = 5;
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0;i<10;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<10;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0,0);
		if(min==Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(min);			
		}
	}
	private static void dfs(int idx, int pcount) {
		if(idx==100) {
			if(min>pcount) {
				min = pcount;
				return;
			}
		}
		if(min<=pcount) {
			return;
		}
		int x = idx/10;
		int y = idx%10;
		if(arr[x][y]==1) {
			for(int i=5;i>0;i--) {
				if(check(x, y, i) && paper[i]>0) {//채울수 있다면 채운다...
//					System.out.println(x+", "+y);
					for(int j=x;j<(x+i);j++) {
						for(int k=y;k<(y+i);k++) {
							arr[j][k] = 0;
						}
					}
					paper[i]--;
					dfs(idx+1, pcount+1);
					for(int j=x;j<(x+i);j++) {
						for(int k=y;k<(y+i);k++) {
							arr[j][k] = 1;
						}
					}
					paper[i]++;
				}
			}
		}else {
			dfs(idx+1, pcount);
		}
	}
	
	private static boolean check(int x, int y, int size) {
		if(x+size>10 || y+size>10) {
			return false;
		}
		for(int i=x;i<x+size;i++) {
			for(int j=y;j<y+size;j++) {
				if(arr[i][j]!=1) {
					return false;
				}
			}
		}
		return true;
	}
}