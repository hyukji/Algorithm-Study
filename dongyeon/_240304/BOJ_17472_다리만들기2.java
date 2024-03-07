package a0303;

import java.io.*;
import java.util.*;

public class BOJ_17472_다리만들기2 {
	
	static int n,m;
	static int[][] map,temp;
	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	static boolean[][] v;
	// 모든 섬을 연결하는 다리 길이의 최솟값을 출력
	// 모든 섬을 연결하는 것이 불가능하면 -1 출력
	// 최소신장트리 => 가중치 최소로 모든 노드를 연결하는 트리 
	// mst 알고리즘 => 프림(정점 중심), 크루스칼(간선 중심)이 있다. 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//일단 섬들이 모두 1이라서 2부터 숫자로 다른 섬이라는 것을 표시 해주자
		int idx = 1;
		v = new boolean[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j]==1) {
					idx++;
					dfs(i,j,idx);
				}
			}
		}
		// 이제 모든 섬을 잇는 최소 다리길이를 구해보자
		// 프림 사용 
		// 다리는 세로 or 가로만 되고 길이가 2이상이어야 한다.
		// 총 섬의 갯수는 idx-1임. 2부터 시작
		// 섬들의 연결 관계를 인접행렬에 저장한다. 
		temp = new int[idx+1][idx+1];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				// 한 섬이 정점으로 취급, 한 섬에서 다른 섬까지 다리길이 최소를 구해보자 
				if(map[i][j]!=0) {
					for(int d=0; d<4; d++) {
						int[] sum = bfs(i,j,d);
						if(sum[1]>=2) {
							if(temp[sum[0]][map[i][j]]==0) {//연결안되어있으면 연결
								temp[sum[0]][map[i][j]]= sum[1];
								temp[map[i][j]][sum[0]]= sum[1];
							}else {// 최소값으로 업데이트
								if(sum[1]<temp[sum[0]][map[i][j]]) {
									temp[sum[0]][map[i][j]]= sum[1];
									temp[map[i][j]][sum[0]]= sum[1];
								}
							}
						}
					}
				}
			}
		}
//		for(int[] a:temp) System.out.println(Arrays.toString(a));
		
 		int[] minEdge = new int[idx+1];
 		boolean[] visited = new boolean[idx+1];
		for(int i=2; i<idx+1; i++) minEdge[i] = Integer.MAX_VALUE;
		minEdge[2]=0;
		int result=0, cnt=0;
		for(int i=2; i<idx+1; i++) {
			int minVertex = -1;
			int min = Integer.MAX_VALUE;
			for(int j=2; j<idx+1; j++) {
				if(!visited[j] && min>minEdge[j]) {
					minVertex = j;
					min = minEdge[j];
				}
			}
			if(minVertex == -1) {result = -1; break;} // 최소신장트리가 만들어지지않을 때
			if(visited[minVertex]) continue;
 			visited[minVertex] = true;
			result+=min;
			if(cnt++ == idx-2) break;
			
			for(int j=2; j<idx+1; j++) {
				if(!visited[j] && temp[minVertex][j]!=0 && minEdge[j]>temp[minVertex][j]) {
					minEdge[j] = temp[minVertex][j];
				}
			}
			
		}
		System.out.println(result);
		br.close();
	}
	static int[] bfs(int i, int j, int d) {
		int cnt = 0;
		int[] ans = new int[2]; // 도착 섬 번호와 길이를 return
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {i,j,cnt});
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			// 다른 섬에 도착했을 때
			if(map[cur[0]][cur[1]]!=0 && map[cur[0]][cur[1]]!=map[i][j]) {
				ans = new int[] {map[cur[0]][cur[1]], cur[2]};
				break;
			}
			
			int nx = cur[0] + di[d];
			int ny = cur[1] + dj[d];
			if(nx>=0 && nx<n && ny>=0 && ny<m && map[nx][ny]!=map[i][j]) {
				q.offer(new int[] {nx,ny,cnt++});
			}
		}
		
		return ans;
			
	}
	
	// 섬들 표시해주는 dfs 함수
	static void dfs(int i, int j, int k) {
		v[i][j]= true;
		map[i][j] = k;
		for(int d=0; d<4; d++) {
			int nx = i + di[d];
			int ny = j + dj[d];
			
			if(nx>=0 && nx<n && ny>=0 && ny<m && !v[nx][ny] && map[nx][ny]==1) {
				dfs(nx,ny,k);
			}
		}
	}

}
