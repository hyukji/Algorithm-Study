package a0229.camp;

import java.io.*;
import java.util.*;

public class Main_BOJ_14502_연구소_서울_20반_고동연 {
	
	static int n,m,ans; 
	static int[][] map;
	static int[] di = {1,-1,0,0};
	static int[] dj = {0,0,1,-1};
	static ArrayList<int[]> list;
	static int[] b;
	static boolean[][] v;
	
	// 벽은 꼭 3개를 세워야 한다. 
	// 안전영역의 최댓값을 구하기
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map =new int[n][m];
		// 0은 빈칸, 1은 벽, 2는 바이러스 
		// 새로운 벽을 빈칸인 0에 세울 수 있으니까 빈칸 좌표를 받아두자
		list = new ArrayList<>();
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<m; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==0) list.add(new int[] {i,j});
			}
		}
		b = new int[3];
		ans = Integer.MIN_VALUE;
		// 빈칸에 벽 3개를 세우는 모든 경우를 구해보자 
		comb(0,0);
		
		System.out.println(ans);
		
		br.close();
	}

	static void comb(int cnt, int start) {
		if(cnt == 3) {
			int[][] temp = Copyof(map);
			for(int i=0; i<3; i++) {
				int[] cur = list.get(b[i]);
				temp[cur[0]][cur[1]]=1; //벽세워주기 
			}
			// 바이러스 퍼트리기
			v =  new boolean[n][m];
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(temp[i][j]==2) temp = bfs(temp,i,j);
				}
			}
			// 안전영역 크기 구하기 
			int result = safeSize(temp);
			
			// 안전영역 최댓값 업데이트 
			ans = Math.max(ans, result);
			return;
		}
		
		for(int i=start; i<list.size(); i++) {
			b[cnt] = i;
			comb(cnt+1, i+1);
		}
	}

	static int[][] bfs(int[][] temp, int x, int y) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		v[x][y]=true;
		q.offer(new int[] {x,y});
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			for(int d=0; d<4; d++) {
				int nx = now[0]+di[d];
				int ny = now[1]+dj[d];
				if(nx>=0 && nx<n && ny>=0 && ny<m) {
					if(!v[nx][ny] && temp[nx][ny]==0) {
						v[nx][ny]=true;
						temp[nx][ny] = 2;
						q.offer(new int[] {nx,ny});
					}
				}
			}
		}
		
		return temp;
	}

	static int safeSize(int[][] temp) {
		int sum =0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(temp[i][j]==0) sum++;
			}
		}
		return sum;
	}

	static int[][] Copyof(int[][] map) {
		int[][] temp = new int[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				temp[i][j] = map[i][j];
			}
		}
		
		return temp;
	}
}
