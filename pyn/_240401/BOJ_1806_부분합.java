package study;

import java.io.*;
import java.util.*;

public class BOJ_1806_부분합 {
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine(), " ");
		
		int N=Integer.parseInt(st.nextToken());
		int S=Integer.parseInt(st.nextToken());
		
		int[] numbers=new int[N+1];
		st=new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			numbers[i]=Integer.parseInt(st.nextToken());
		}
		int min=Integer.MAX_VALUE;
		
		int start=0;
		int end=0;
		int sum=0;
		while(start<=N && end<=N) {
			if(S<=sum && min>end-start) min=end-start;
			if(sum<S) sum+=numbers[end++];
			else sum-=numbers[start++];
		}
		if(min==Integer.MAX_VALUE) System.out.println(0);
		else System.out.println(min);
		
	}

}
