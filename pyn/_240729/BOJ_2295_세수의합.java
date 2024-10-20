package study;

import java.io.*;
import java.util.*;

public class BOJ_2295_세수의합 {
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		long[] arr=new long[N];
		
		for(int i=0; i<N; i++) arr[i]=Integer.parseInt(br.readLine());
		Arrays.sort(arr);
		
		HashSet<Long> sumSet=new HashSet<Long>();
		long ans=0;
		for(int i=N-1; i>=0; i--) {
			for(int j=N-1; j>=0; j--) {
				sumSet.add(arr[i]+arr[j]);
			}
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				long k=arr[i]-arr[j];
				if(sumSet.contains(k)) ans=Math.max(ans, arr[i]);
			}
		}
		System.out.println(ans);
		br.close();
	}
}