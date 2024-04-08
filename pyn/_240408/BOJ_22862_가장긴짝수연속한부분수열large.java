package study;

import java.io.*;
import java.util.*;

public class BOJ_22862_가장긴짝수연속한부분수열large {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine(), " ");
		int N=Integer.parseInt(st.nextToken());
		int K=Integer.parseInt(st.nextToken());
		int[] s=new int[N];
		st=new StringTokenizer(br.readLine(), " ");
		int countE=0;
		for(int i=0; i<N; i++) s[i]=Integer.parseInt(st.nextToken());
		int max=0;
		int countO=0;
		int start=0;
		int end=0;
		
		while(start<N) {
			while(end<N && countO<=K) {
				if(s[end]%2!=0) countO++;
				end++;
			}
			max=Math.max(max, end-start-countO);
			if(s[start]%2!=0) countO--;
			start++;
		}
		System.out.println(max);
		br.close();
	}
}

//st=new StringTokenizer(br.readLine(), " ");
//int countE=0;
//for(int i=0; i<N; i++) {
//	int a=Integer.parseInt(st.nextToken());
//	if(a%2==0) countE++;
//	else {
//		s.add(countE);
//		countE=0;
//		if(i!=0) s.add(0);
//	}
//	if(i==N-1 && countE!=0) s.add(countE);
//}
//int sum=0;
////for(int i=0; i<s.size(); i++) System.out.print(s.get(i)+" ");
////System.out.println();
//for(int i=0; i<K+1; i++) sum+=s.get(i);
//int max=0;
//int countO=0;
//int start=0;
//int end=0;
//
//while(start<N) {
//	while(end<N && countO<K) {
//		if(s.get(end)==0) {
//			countO++;
//			end++;
//		} else {
//			sum+=s.get(end);
//			end++;
//		}
//		//System.out.println(start+" "+end+" countO: "+countO);
//	}
//	max=Math.max(max, sum);
//	
//	if(s.get(start)==0) {
//		countO--;
//		start++;
//	} else {
//		sum-=s.get(start);
//		start++;
//	}
//	//System.out.println(start+" "+end+" countO: "+countO);
//}