package study;

import java.io.*;
import java.util.*;

public class SWEA_4008_숫자만들기 {
	static int N, max, min;
	static int[] oper=new int[4];
	static int[] num;
	static int[] temp;
	static boolean[] visited;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_sw_4008.txt"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		StringTokenizer st;
		
		int T=Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			N=Integer.parseInt(br.readLine());
			num=new int[N];
			st=new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<4; i++) {
				oper[i]=Integer.parseInt(st.nextToken());
			}
			temp=new int[N-1];
			visited=new boolean[N-1];
			st=new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<N; i++) {
				num[i]=Integer.parseInt(st.nextToken());
			}
			
			max=Integer.MIN_VALUE;
			min=Integer.MAX_VALUE;
			perm(0);
			sb.append("#").append(tc).append(" ").append(max-min).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
	static void perm(int cnt) {
		int res=num[0];
		if(cnt==N-1) {
			for(int i=0; i<N-1; i++) {
				if(temp[i]==0) res+=num[i+1];
				else if(temp[i]==1) res-=num[i+1];
				else if(temp[i]==2) res*=num[i+1];
				else res/=num[i+1];
			}
			max=Math.max(max, res);
			min=Math.min(min, res);
			return;
		}
		for(int i=0; i<4; i++) {
			if(oper[i]==0) continue;
			oper[i]--;
			temp[cnt]=i;
			perm(cnt+1);
			oper[i]++;
		}
	}
}
