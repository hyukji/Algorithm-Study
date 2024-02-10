package a0208.camp;

import java.util.*;
import java.io.*;

public class Main_BOJ_16234_인구이동_서울_20반_고동연 {
    
    static int N,L,R,ans,total;
    static int[][] A;
    static int[] di= {-1,1,0,0};
    static int[] dj= {0,0,-1,1};
    static boolean[][] visited;
    static boolean move;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        
        A = new int[N][N];
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine()," ");
            for(int j=0; j<N; j++) {
                A[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        ans=0;
        // 인구이동이 며칠동안 발생하는지 구하기
        while(true) {
            
//        	for(int[] a:A)System.out.println(Arrays.toString(a));
            visited = new boolean[N][N];
            move=false; // 국경이 열리는지 체크
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(!visited[i][j]) {
                    	total=0;
                    	visited[i][j]=true;
                    	bfs(i,j);
                    }
                }
            }
            
            if(!move) break; // 국경 더이상 안열려 
            else ans++; // 열린국경이 있으면 인구이동한다.
        }
        System.out.println(ans);
    }

	static void bfs(int i, int j) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {i,j});
		total+=A[i][j]; // 국경선이 열린나라의 인구 모두 더하기
		
		// 인구이동 표시해줄려고 좌표 저장해두기
		ArrayDeque<int[]> stack =new ArrayDeque<>();
		stack.add(new int[] {i,j});
		while(!q.isEmpty()) {
			int[] ij= q.poll();
			
			for(int d=0;d<4;d++) {
				int ni = ij[0]+di[d];
				int nj = ij[1]+dj[d];
				if(ni>=0 && ni<N && nj>=0 && nj<N && !visited[ni][nj]) {
					if(Math.abs(A[ij[0]][ij[1]]-A[ni][nj])>=L && Math.abs(A[ij[0]][ij[1]]-A[ni][nj])<=R) {
						move=true;
						visited[ni][nj]=true;
						q.offer(new int[] {ni,nj});
						total+=A[ni][nj];
						stack.add(new int[] {ni,nj});
					}
				}
			}
		}
		
		//연합 인구 수 계산 
		int newpeople=total/stack.size();
		// 연합 인구 수로 업데이트
		while(!stack.isEmpty()) {
			int[] np = stack.pop();
			A[np[0]][np[1]]=newpeople;
		}
		
	}

}