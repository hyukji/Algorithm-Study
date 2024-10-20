package BOJ;

import java.util.*;
import java.io.*;

public class BOJ_11657_타임머신 {
	static Edge[] graph;
	static class Edge{
		int start;
		int end;
		int weight;
		public Edge(int s, int e, int w) {
			this.start=s;
			this.end=e;
			this.weight=w;
		}
	}
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine(), " ");
        StringBuilder sb=new StringBuilder();

        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());
        graph=new Edge[M];
        long[] dist=new long[N];
        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine(), " ");
            int a=Integer.parseInt(st.nextToken())-1;
            int b=Integer.parseInt(st.nextToken())-1;
            int c=Integer.parseInt(st.nextToken());
            graph[i]=new Edge(a,b,c);
        }
        for(int i=0;i<N;i++) dist[i]=100_000_000;
        dist[0]=0;
        boolean isMinusCycle=false;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
            	Edge now_edge=graph[j];
                if(dist[now_edge.start]==100_000_000) continue;
                if(dist[now_edge.end]>dist[now_edge.start]+now_edge.weight){
                    dist[now_edge.end]=dist[now_edge.start]+now_edge.weight;
                    if(i==N-1) isMinusCycle=true;
                }
            }
        }
        if(isMinusCycle) sb.append(-1);
        else{
            for(int i=1;i<N;i++) {
                if(dist[i]==100_000_000) sb.append(-1).append("\n");
                else sb.append(dist[i]).append("\n");
            }
        }
        System.out.print(sb);
        br.close();
    }
}