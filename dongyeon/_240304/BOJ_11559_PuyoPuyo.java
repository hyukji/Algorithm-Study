package a0229.camp;

import java.io.*;
import java.util.*;

public class Main_BOJ_11559_PuyoPuyo {
	
	static char[][] puyo;
	static boolean[][] v;
	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	static int ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		puyo = new char[12][6];
		
		for(int i=0; i<12; i++) {
			String s = br.readLine();
			for(int j=0; j<6; j++) {
				puyo[i][j] = s.charAt(j);
			}
		}
		
		// 주어진 상황에서 몇 연쇄가 되는지 출력 
		// 하나도 터지지 않으면 0 출력
		
		// 같은 색의 뿌요들이 4개이상 모이면 터진다. 
	
		ans = 0; // 연쇄의 횟수
		while(true) {
//			for(char[] a: puyo) System.out.println(Arrays.toString(a));
//			System.out.println();
			int cnt = 0;
			v = new boolean[12][6];
			for(int i=0; i<12; i++) {
				for(int j=0; j<6; j++) {
					if(puyo[i][j]=='R') {
						if(bfs(i,j,'R')) cnt++; // 터트리는 경우가 있으면 cnt를 늘린다. 
					}else if(puyo[i][j]=='G') {
						if(bfs(i,j,'G')) cnt++;
					}else if(puyo[i][j]=='B') {
						if(bfs(i,j,'B')) cnt++;
					}else if(puyo[i][j]=='P') {
						if(bfs(i,j,'P')) cnt++;
					}else if(puyo[i][j]=='Y') {
						if(bfs(i,j,'Y')) cnt++;
					}
				}
			}
			
			if(cnt == 0) break; // 더이상 터질 뿌요가 없을 때 
			ans ++;
			//x로 표시해준 터진 부분을 없애주자 
			for(int j=0; j<6; j++) {
				int x_cnt = 0;
				for(int i=11; i>=0; i--) {
					if(puyo[i][j]=='x') { // x 갯수 세주기
						x_cnt++;
					}else {
						puyo[i+x_cnt][j]=puyo[i][j]; //x 갯수만큼 밀리니까 그만큼 옮겨주기
					}
				}
				for(int k=0; k<x_cnt; k++) { // 밀려서 빈부분은 '.'으로 채워주기 
					puyo[k][j]='.';
				}
			}
		}
		
		System.out.println(ans);
		br.close();
	}

	static boolean bfs(int i, int j, char c) {
		v[i][j] = true;
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {i,j});
		
		int count=1;
		// 혹시 4개이상인 그룹이 있다면 터지기 때문에 좌표를 저장할 arraylist를 만들어준다.
		ArrayList<int[]> group = new ArrayList<>();
		group.add(new int[] {i,j});
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for(int d=0; d<4; d++) {
				int nx = cur[0]+di[d];
				int ny = cur[1]+dj[d];
				if(nx>=0 && nx<12 && ny>=0 && ny<6) {
					if(!v[nx][ny] && puyo[nx][ny]==c) {
						v[nx][ny]=true;
						count++;
						group.add(new int[] {nx,ny});
						q.offer(new int[] {nx,ny});
					}
				}
			}
		
		}
		
		if(count>=4) {
			for(int[] a:group) {
				puyo[a[0]][a[1]] = 'x'; // 터진 부분 표시
			}
			return true;
		}
		
		return false;
	}

}
