package study;

import java.io.*;
import java.util.*;

public class BOJ_16437_양구출작전 {
	static List<Integer>[] tree;
	static long[] memo;
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N=Integer.parseInt(br.readLine());
		memo=new long[N+1];
		tree=new ArrayList[N+1];//섬이 연결되어 있는 정보 저장
		for(int i=0; i<N+1; i++) tree[i]=new ArrayList<>();
		for(int i=0; i<N-1; i++) {
			st=new StringTokenizer(br.readLine(), " ");
			
			String sw=st.nextToken(); // sheep or wolf
			long a=Integer.parseInt(st.nextToken()); // 동물의 수
			int b=Integer.parseInt(st.nextToken()); // 연결되어있는 섬의 정보
			
			tree[b].add(i+2); // i+2번 섬이 b 섬에 연결되어 있으므로 index b에 i+2 저장
			if(sw.equals("W")) a*=-1; // 늑대는 양을 잡아먹으므로 음수로 저장
			
			memo[i+2]=a; // 섬의 index에 마리수 저장
		}
		dfs(1, -1);
		System.out.println(memo[1]);
	}
	static void dfs(int next, int pa) {
		for(int i: tree[next]) dfs(i, next);
		if(pa!=-1) if(memo[next]>0) memo[pa]+=memo[next];
	}
}
