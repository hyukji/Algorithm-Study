package study;

import java.io.*;
import java.util.*;

public class BOJ_1005_ACMCraft_dijk {
	static int N, K;
	static long[] build, maxEdge, need;
	static boolean[] v;
	static long[][] graph;
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		StringTokenizer st;
		
		int T=Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			st=new StringTokenizer(br.readLine(), " ");
			
			N=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());
			build=new long[N];
			need=new long[N];
			maxEdge=new long[N];
			graph=new long[N][N];
			v=new boolean[N];
			st=new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<N; i++) {
				build[i]=Integer.parseInt(st.nextToken());
				maxEdge[i]=0;
			}
			for(int i=0; i<K; i++) {
				st=new StringTokenizer(br.readLine(), " ");
				int prev=Integer.parseInt(st.nextToken())-1;
				int next=Integer.parseInt(st.nextToken())-1;
				graph[prev][next]=build[next];
				need[next]++;
			}
			int find=Integer.parseInt(br.readLine())-1;
			//System.out.println(Arrays.toString(need));
			PriorityQueue<long[]> pq=new PriorityQueue<>((o1,o2)->-Long.compare(o1[1], o2[1]));
			for(int i=0; i<N; i++) if(need[i]==0) {
				maxEdge[i]=build[i];
				pq.offer(new long[] {i, maxEdge[i]});
				//System.out.println("이거 시작! "+i+" "+maxEdge[i]);
			}
			while(!pq.isEmpty()) {
				long[] cur=pq.poll();
				long maxVertex=cur[0];
				long max=cur[1];
				if(v[(int) maxVertex]) continue;
				v[(int) maxVertex]=true;
				if(maxVertex==find) break;
				for(int j=0; j<N; j++) {
					if(!v[j] && graph[(int) maxVertex][j]!=0 && maxEdge[j]<max+graph[(int) maxVertex][j]) {
						maxEdge[j]=max+graph[(int) maxVertex][j];
						pq.offer(new long[] {j,(int) maxEdge[j]});
						//System.out.println("이거 넣었어! "+j+" "+maxEdge[j]);
					}
				}
			}
			sb.append(maxEdge[find]).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
}

