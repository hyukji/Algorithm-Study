package a0408;
import java.io.*;
import java.util.*;
public class BOJ_1005_ACMCraft {
	static int n,k,w,time;
	static int[] bd;
	static int[][] arr;
	static int[] edge;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<T+1; tc++) {
			st = new StringTokenizer(br.readLine()," ");
			n = Integer.parseInt(st.nextToken());
			// 건물의 갯수
			k = Integer.parseInt(st.nextToken());
			// 건설 순서 규칙의 총 개수
			bd = new int[n+1]; // 건물의 번호는 1번부터 n번임.
			st = new StringTokenizer(br.readLine()," ");
			for(int i=1; i<n+1; i++) {
				bd[i]=Integer.parseInt(st.nextToken());
			}
			// 건물이 걸리는 시간 저장
			
			arr = new int[n+1][n+1]; // 인접행렬 
			edge = new int[n+1]; // 건물 별 진입 차수 저장 배열
			// 건설 순서 x,y (k개) // 건물 x를 지은 다음 건물 y를 짓는 것이 가능하다는 의미 
			for(int i=0; i<k; i++) {
				st = new StringTokenizer(br.readLine()," ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				arr[a][b]=1; // 인접 노드 표시 
				edge[b]++; // 진입차수 업데이트
			}
			
			// 백준이가 승리하기 위해 건설해야 할 건물 번호 w
			w = Integer.parseInt(br.readLine());
			
			// 건물 w를 건설완료하는데 드는 최소 시간을 출력 
			time = Integer.MAX_VALUE;
			search();
			System.out.println(time);
			
		}
		
		br.close();
	}
	
	static void search() {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		for(int i=1; i<n+1; i++) {
			if(edge[i]==0) {
				q.offer(new int[] {i,bd[i]}); // 건물 번호, 시간
			}
		}
		int[] mp = new int[n+1];
		while(!q.isEmpty()) {
			int[] cur = q.poll();
//			System.out.println(cur[0]+" "+cur[1]);
			if(cur[0]==w) { // 건물 w를 건설완료 했을 때
				time = Math.min(time, cur[1]);
			}
			
			for(int j=1; j<n+1; j++) {
				if(arr[cur[0]][j]==1) { // 인접해 있을 때
					edge[j]--;
					if(mp[j]==0) {
						mp[j]=cur[1];
					}else {
						mp[j]=Math.max(mp[j],cur[1]);
					}
//					System.out.println(j);
//					System.out.println(edge[j]);
//					System.out.println();
					if(edge[j]==0) {
						q.offer(new int[] {j,mp[j]+bd[j]});
					}
				}
			}
		}
	}

}
