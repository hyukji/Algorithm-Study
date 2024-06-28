package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2559_수열 {
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine(), " ");
		
		int N=Integer.parseInt(st.nextToken());
		int K=Integer.parseInt(st.nextToken());
		int[] temp=new int[N];
		
		st=new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) temp[i]=Integer.parseInt(st.nextToken());
		int sum=0;
		int max=-100_000_000;
		for(int i=0; i<K; i++) sum+=temp[i];
		for(int i=K; i<N; i++) {
			max=Math.max(sum, max);
			sum-=temp[i-K];
			sum+=temp[i];
		}
		max=Math.max(sum, max);
		System.out.println(max);
		br.close();
	}
}