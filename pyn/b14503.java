package algo_study;

import java.io.*;
import java.util.*;

public class b14503 {
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		int[] vector=new int[] {0, 1, 2, 3};
		
		StringTokenizer st=new StringTokenizer(br.readLine(), " ");
		int N=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());
		st=new StringTokenizer(br.readLine()," ");
		int r=Integer.parseInt(st.nextToken());
		int c=Integer.parseInt(st.nextToken());
		int d=Integer.parseInt(st.nextToken());
		int[][] room=new int[N][M];
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine()," ");
			for(int j=0; j<M; j++) {
				String ro=st.nextToken();
				room[i][j]=Integer.parseInt(ro);
			}
		}
		if(room[r][c]==1) {
			room[r][c]=0;
			for(int k=0; k<4; k++) {
				int ni=r+di[k];
				int nj=r+dj[k];
				if(room[ni][nj]==1) {
					continue;
				}
			}
		}
		
	}

}
