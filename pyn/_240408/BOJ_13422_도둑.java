package study;

import java.io.*;
import java.util.*;

public class BOJ_13422_도둑 {
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		StringTokenizer st;
		
		int T=Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			st=new StringTokenizer(br.readLine(), " ");
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			long K=Integer.parseInt(st.nextToken());
			
			
			int[] money=new int[N+M];
			long sum=0;
			st=new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<N; i++) {
				money[i]=Integer.parseInt(st.nextToken());
				if(i<M) {
					money[i+N]=money[i];
					sum+=money[i];
				}
			}
			int count=0;
			if(N!=M) {
				int start=0;
				int end=M-1;
				while(start<N) {
					if(sum<K) count++;
					sum-=money[start++];
					sum+=money[++end];
				}
			}
			else {
				if(sum<K) count++;
			}
			sb.append(count).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
}