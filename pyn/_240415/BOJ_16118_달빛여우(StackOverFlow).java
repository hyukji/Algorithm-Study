package study;

import java.io.*;
import java.util.*;

public class BOJ_16118_달빛여우 {
	static int N, M;
	static long inf=1_000_000_000;
	static long[] memoF, memoWR;
	static long[][] memoW;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine(), " ");
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		map=new int[N+1][N+1];
		memoF=new long[N+1];
		memoW=new long[N+1][2];
		memoWR=new long[N+1];
		for(int i=0; i<N+1; i++) memoF[i]=inf;
		for(int i=0; i<N+1; i++) for(int j=0; j<2; j++) memoW[i][j]=inf;
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine(), " ");
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int d=Integer.parseInt(st.nextToken());
			
			map[a][b]=map[b][a]=2*d;
		}
		memoW[1][0]=0;
		memoW[1][1]=0;
		for(int i=1; i<N+1; i++) dpF(i);
		bfsW(1);
		for(int i=0; i<N+1; i++) memoWR[i]=Math.min(memoW[i][0], memoW[i][1]);
		//System.out.println(Arrays.toString(memoF));
		//System.out.println(Arrays.toString(memoWR));
		int count=0;
		for(int i=0; i<N+1; i++) if(memoF[i]<memoWR[i]) count++;
		System.out.println(count);
	}
	static long dpF(int n) {
		if(memoF[n]!=inf) return memoF[n];
		if(n==1) memoF[n]=0;
		else {
			for(int i=1; i<N+1; i++) {
				if(map[n][i]==0) continue;
				memoF[n]=Math.min(dpF(i)+map[n][i], memoF[n]);
			}
		}
		return memoF[n];
	}
	static void bfsW(int i) {
		ArrayDeque<int[]> q=new ArrayDeque<>();
		q.offer(new int[] {i,0});
		while(!q.isEmpty()) {
			int[] a=q.poll();
			if(a[1]%2==0) {
				for(int j=1; j<N+1; j++) {
					if(map[a[0]][j]!=0 && memoW[j][0]>memoW[a[0]][1]+map[a[0]][j]/2) {
						memoW[j][0]=memoW[a[0]][1]+map[a[0]][j]/2;
						q.offer(new int[] {j,a[1]+1});
					}
				}
			} else {
				for(int j=1; j<N+1; j++) {
					if(map[a[0]][j]!=0 && memoW[j][1]>memoW[a[0]][0]+2*map[a[0]][j]) {
						memoW[j][1]=memoW[a[0]][0]+2*map[a[0]][j];
						q.offer(new int[] {j,a[1]+1});
					}
				}
			}
		}
	}
}
/*
ArrayDeque<Integer> q=new ArrayDeque<>();
		q.offer(i);
		while(!q.isEmpty()) {
			int a=q.poll();
			for(int j=1; j<N+1; j++) {
				if(map[a][j]!=0 && memoW[j]>memoW[a]+map[a][j]) {
					memoW[j]=memoW[a]+map[a][j];
					q.offer(j);
				}
			}
		}
*/
