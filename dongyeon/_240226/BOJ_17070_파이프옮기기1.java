package a0227.camp;
import java.io.*;
import java.util.*;
public class Main_BOJ_17070_파이프옮기기1_서울_20반_고동연 {

	static int n, ans;
	static int[][] map;
	static boolean[][] v;
	//파이프 한쪽 끝을 n,n으로 이동시키는 방법의 수 구하기
	// DP로 풀어보기 !!!!!!!
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		map=new int[n][n];
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<n;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		v = new boolean[n][n];
		v[0][0]=true;
		v[0][1]=true;
		// 처음 위치 표시
		ans = 0; //이동시킬 수 없는 경우에는 0임
		//파이프는 처음에 1,1 1,2 를 차지하고 있다.-> 배열은 0,0 부터이므로 0,0넣어줌
		dfs(0,1);
		System.out.println(ans);
		br.close();
	}
	static void dfs(int x, int y) {
		
		if(x==n-1 && y==n-1) {
			ans++;
			return;
		}
		
		// 대각선으로 놓여져있을 때
		if(isValid(x,y,1) && v[x-1][y] && v[x][y] && v[x][y-1] && v[x-1][y-1]) {
			// 가로로 옮기기
			if(y+1<n && map[x][y+1]==0 && !v[x][y+1]) {
				v[x-1][y]=false;
				v[x-1][y-1]=false;
				v[x][y-1]=false;
				v[x][y+1]=true;
				dfs(x,y+1);
				v[x-1][y]=true;
				v[x-1][y-1]=true;
				v[x][y-1]=true;
				v[x][y+1]=false;
				
			}
			// 세로로 옮기기
			if(x+1<n && map[x+1][y]==0 && !v[x+1][y]) {
				v[x-1][y]=false;
				v[x-1][y-1]=false;
				v[x][y-1]=false;
				v[x+1][y]=true;
				dfs(x+1,y);
				v[x-1][y]=true;
				v[x-1][y-1]=true;
				v[x][y-1]=true;
				v[x+1][y]=false;
				
			}
			// 대각선으로 옮기기
			if(y+1<n && x+1<n && map[x][y+1]==0 && map[x+1][y+1]==0 && map[x+1][y]==0 && !v[x][y+1] && !v[x+1][y+1] && !v[x+1][y]) {
				v[x-1][y]=false;
				v[x-1][y-1]=false;
				v[x][y-1]=false;
				v[x][y+1]=true;
				v[x+1][y+1]=true;
				v[x+1][y]=true;
				dfs(x+1,y+1);
				v[x][y+1]=false;
				v[x+1][y+1]=false;
				v[x+1][y]=false;
				v[x-1][y]=true;
				v[x-1][y-1]=true;
				v[x][y-1]=true;
				
			}
		}
		//가로로 놓여져있을 때
		else if(isValid(x,y,2)&&v[x][y-1]&& v[x][y]) {
			// 가로로 옮기기
			if(y+1<n && map[x][y+1]==0 && !v[x][y+1]) {
				v[x][y-1]=false;
				v[x][y+1]=true;
				dfs(x,y+1);
				v[x][y+1]=false;
				v[x][y-1]=true;
				
			}
			// 대각선으로 옮기기
			if(y+1<n && x+1<n && map[x][y+1]==0 && map[x+1][y+1]==0 && map[x+1][y]==0 && !v[x][y+1] && !v[x+1][y+1] && !v[x+1][y]) {
				v[x][y-1]=false;
				v[x][y+1]=true;
				v[x+1][y+1]=true;
				v[x+1][y]=true;
				dfs(x+1,y+1);
				v[x][y+1]=false;
				v[x+1][y+1]=false;
				v[x+1][y]=false;
				v[x][y-1]=true;
				
			}
		}
		// 세로로 놓여져있을 때
		else if(isValid(x,y,3)&&v[x-1][y] && v[x][y] ) {
			// 세로로 옮기기
			if(x+1<n && map[x+1][y]==0 && !v[x+1][y]) {
				v[x-1][y]=false;
				v[x+1][y]=true;
				dfs(x+1,y);
				v[x-1][y]=true;
				v[x+1][y]=false;
				
			}
			// 대각선으로 옮기기
			if(y+1<n && x+1<n && map[x][y+1]==0 && map[x+1][y+1]==0 && map[x+1][y]==0 && !v[x][y+1] && !v[x+1][y+1] && !v[x+1][y]) {
				v[x-1][y]=false;
				v[x][y+1]=true;
				v[x+1][y+1]=true;
				v[x+1][y]=true;
				dfs(x+1,y+1);
				v[x][y+1]=false;
				v[x+1][y+1]=false;
				v[x+1][y]=false;
				v[x-1][y]=true;
				
			}
		}
		
	}
	
	static boolean isValid(int x, int y, int check) {
		if(check==1) {// 대각선 범위 확인
			if(x-1>=0 && x-1<n && y-1>=0 && y-1<n) return true;
		}
		else if(check==2) {// 가로 범위 확인
			if(y-1>=0 && y-1<n) return true;
		}else if(check==3) {//세로 범위 확인
			if(x-1>=0 && x-1<n) return true;
		}
		return false;
	}
	
}
