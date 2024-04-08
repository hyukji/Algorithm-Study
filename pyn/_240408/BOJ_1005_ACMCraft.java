package study;

import java.io.*;
import java.util.*;

public class BOJ_1005_ACMCraft {
	static int N, K;
	static long[] build, maxEdge;
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
			int[] buildTime=new int[N+1];
			int[] building=new int[N+1];
			st=new StringTokenizer(br.readLine(), " ");
			for(int i=1; i<N+1; i++) buildTime[i]=Integer.parseInt(st.nextToken());
			//System.out.println(Arrays.toString(buildTime));
			ArrayList<ArrayList<Integer>> graph=new ArrayList<ArrayList<Integer>>();
			for(int i=0; i<N+1; i++) graph.add(new ArrayList<Integer>());
			for(int i=0; i<K; i++) {
				st=new StringTokenizer(br.readLine(), " ");
				int prev=Integer.parseInt(st.nextToken());
				int next=Integer.parseInt(st.nextToken());
				
				graph.get(prev).add(next);
				building[next]++;
			}
			int find=Integer.parseInt(br.readLine());
			ArrayDeque<Integer> q=new ArrayDeque<>();
			long[] memo=new long[N+1];
			for(int i=1; i<=N; i++) {
				memo[i]=buildTime[i];
				if(building[i]==0) q.add(i);
			}
			while(!q.isEmpty()) {
				int next=q.poll();
				for(int n: graph.get(next)) {
					memo[n]=Math.max(memo[n], memo[next]+buildTime[n]);
					building[n]--;
					if(building[n]==0) q.add(n);
				}
			}
			sb.append(memo[find]).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
}
/*
//for(int i=1; i<N+1; i++) building[i]=graph.get(i).size();
			//System.out.println(Arrays.toString(building));
			PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return Integer.compare(o1[2], o2[2]) == 0 ?  -Long.compare(o1[1], o2[1]) : Integer.compare(o1[2], o2[2]);
//					if(o1[2]==o2[2]) return -Long.compare(o1[1], o2[1]);
//					else return Integer.compare(o1[2], o2[2]);
				}
			});
			for(int i=1; i<building.length; i++) if(building[i]==0) pq.offer(new int[] {i, buildTime[i], 0});
			int find=Integer.parseInt(br.readLine());
			int sum=0;
			int level=0;
			while(!pq.isEmpty()) {
				int[] next=pq.poll();
				//System.out.println(next[0]+"번!"+Arrays.toString(next));
				if(level==next[2]) {
					sum+=next[1];
					level++;
				}
				if(next[0]==find) break;
				//System.out.println("여긴오니?");
				List<Integer> list=graph.get(next[0]);
				for(int i=0; i<list.size(); i++) {
					building[list.get(i)]--;
					if(building[list.get(i)]==0) {
						pq.offer(new int[] {list.get(i),buildTime[list.get(i)]+next[1],level});
						//System.out.println("뭐 집어넣었어? "+list.get(i)+" "+buildTime[list.get(i)]+" "+level);
					}
				}
			}
*/