package study;

import java.io.*;
import java.util.*;

public class Solution_bj_15685_드래곤커브_서울_20반_박영남 {
	static int[] di= {0, -1, 0, 1}; // 우상좌하
	static int[] dj= {1, 0, -1, 0};
	static int[][] map=new int[101][101];
	static List<Integer> makeDir;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N=Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			int d=Integer.parseInt(st.nextToken());
			int g=Integer.parseInt(st.nextToken());
			setDir(g, d); // 방향 설정
			map[y][x]++; // 시작점 값 증가
			makeCurve(x,y);
		}
		System.out.println(find());
	}
	static int find() { // 문제 조건 찾기(네 꼭지점이 0이 아닌 것)
		int ans=0;
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(map[i][j]!=0 && map[i][j+1]!=0 && map[i+1][j]!=0 && map[i+1][j+1]!=0) ans++;
			}
		}
		return ans;
	}
	static void makeCurve(int x, int y) {// 드래곤커브 만들기
		for(int d: makeDir) { // setDir에서 만든 방향 정보 가져와서 방향마다 하나씩 값 더해주기
			y+=di[d];
			x+=dj[d];
			map[y][x]++;
		}
	}
	static void setDir(int g, int d) {// 방향 설정
		makeDir=new ArrayList<>();
		makeDir.add(d);
		for(int i=0; i<g; i++) {
			for(int j=makeDir.size()-1; j>=0; j--) { // 1  12  1232  12323032
				makeDir.add((makeDir.get(j)+1)%4);
			}
		}
	}
}
