package a0222.camp;

import java.io.*;
import java.util.*;
public class Main_BOJ_15683_감시_서울_20반_고동연 {
	
	static int n,m,ans;
	static int[][] map, newmap;
	static ArrayList<int[]> cctv;
	
	// cctv의 방향을 적절히 정해서, 사각지대의 최소크기를 구하라
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		cctv = new ArrayList<>();
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]!=0 && map[i][j]!=6) {// cctv 좌표와 번호 저장
					cctv.add(new int[] {i,j,map[i][j]});
				}
			}
		}
		ans = Integer.MAX_VALUE; // 최댓값으로 초기화 
		dfs(0,cctv,map); // cctv 몇개 탐지했는지, cctv 정보, map 정보
		System.out.println(ans);
		
		br.close();
	}

	static void dfs(int cnt, ArrayList<int[]> cctv, int[][] temp) {
		if(cnt == cctv.size()) {
			int result = countsquare(temp);
			ans = Math.min(ans, result);
			return;
		}
		
		int[] now = cctv.get(cnt);
		int x = now[0]; // cctv의 x좌표
		int y = now[1]; // cctv의 y좌표
		 
		int numbercctv = now[2]; // 몇번 cctv인지
		
		if(numbercctv==1) {
			//위쪽
			newmap = copyOf(temp);
			up(x,y,newmap);
			dfs(cnt+1, cctv, newmap);
			
			//아래쪽
			newmap = copyOf(temp);
			down(x,y,newmap);
			dfs(cnt+1, cctv, newmap);
			
			//왼쪽
			newmap = copyOf(temp);
			left(x,y,newmap);
			dfs(cnt+1, cctv, newmap);
			
			//오른쪽
			newmap = copyOf(temp);
			right(x,y,newmap);
			dfs(cnt+1, cctv, newmap);
			
		}else if(numbercctv==2) {
			//왼쪽,오른쪽
			newmap = copyOf(temp);
			left(x,y,newmap);
			right(x,y,newmap);
			dfs(cnt+1, cctv, newmap);
			
			//위쪽, 아래쪽
			newmap = copyOf(temp);
			up(x,y,newmap);
			down(x,y,newmap);
			dfs(cnt+1, cctv, newmap);
			
		}else if(numbercctv==3) {
			//오른쪽, 아래쪽
			newmap = copyOf(temp);
			right(x,y,newmap);
			down(x,y,newmap);
			dfs(cnt+1, cctv, newmap);
			//아래쪽, 왼쪽
			newmap = copyOf(temp);
			left(x,y,newmap);
			down(x,y,newmap);
			dfs(cnt+1, cctv, newmap);
			//왼쪽, 위쪽
			newmap = copyOf(temp);
			up(x,y,newmap);
			left(x,y,newmap);
			dfs(cnt+1, cctv, newmap);
			//위쪽, 오른쪽
			newmap = copyOf(temp);
			up(x,y,newmap);
			right(x,y,newmap);
			dfs(cnt+1, cctv, newmap);
		}else if(numbercctv==4) {
			// 위빼고 다
			newmap = copyOf(temp);
			right(x,y,newmap);
			left(x,y,newmap);
			down(x,y,newmap);
			dfs(cnt+1, cctv, newmap);
			// 오른쪽 빼고 다
			newmap = copyOf(temp);
			up(x,y,newmap);
			left(x,y,newmap);
			down(x,y,newmap);
			dfs(cnt+1, cctv, newmap);
			// 왼쪽 빼고 다
			newmap = copyOf(temp);
			right(x,y,newmap);
			up(x,y,newmap);
			down(x,y,newmap);
			dfs(cnt+1, cctv, newmap);
			// 아래 빼고 다
			newmap = copyOf(temp);
			right(x,y,newmap);
			left(x,y,newmap);
			up(x,y,newmap);
			dfs(cnt+1, cctv, newmap);
		}else if(numbercctv==5) {
			// 상하좌우 다
			newmap = copyOf(temp);
			right(x,y,newmap);
			left(x,y,newmap);
			down(x,y,newmap);
			up(x,y,newmap);
			
			dfs(cnt+1, cctv, newmap);
			
		}
		
		
	}
	static int[][] copyOf(int[][] temp) {
		int[][] newmap = new int[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				newmap[i][j]=temp[i][j];
			}
		}
		return newmap;
	}

	// cctv가 위쪽 방향으로 감시 가능할때 감시 칸을 -1로 표시해준다.
	// 범위를 벗어나거나 6인 벽을 만나면 멈춘다. 
	static void up(int x, int y, int[][] map) {
		while(true) {
			int nx = x + -1;
			int ny = y;
			if(nx<0 || map[nx][ny] == 6) break;
			if(map[nx][ny]==0) map[nx][ny]=-1;
			x=nx;
		}
		
	}
	// cctv가 아래 방향으로 감시 가능할때 감시 칸을 -1로 표시해준다. 
	static void down(int x, int y, int[][] map) {
		while(true) {
			int nx = x + 1;
			int ny = y;
			if(nx>=n || map[nx][ny] == 6) break;
			if(map[nx][ny]==0) map[nx][ny]=-1;
			x=nx;
		}
	}
	// cctv가 왼쪽 방향으로 감시 가능할때 감시 칸을 -1로 표시해준다. 
	static void left(int x, int y, int[][] map) {
		while(true) {
			int nx = x;
			int ny = y-1;
			if(ny<0 || map[nx][ny] == 6) break;
			if(map[nx][ny]==0) map[nx][ny]=-1;
			y=ny;
		}
	}
	// cctv가 오른쪽 방향으로 감시 가능할때 감시 칸을 -1로 표시해준다. 
	static void right(int x, int y, int[][] map) {
		while(true) {
			int nx = x;
			int ny = y+1;
			if(ny>=m || map[nx][ny] == 6) break;
			if(map[nx][ny]==0) map[nx][ny]=-1;
			y=ny;
		}
	
	}
	// 사각지대(0)인 칸의 갯수를 return 하는 함수
	static int countsquare(int[][] map) {
		int count = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j]==0) count++;
			}
		}
		
		return count;
	}

}