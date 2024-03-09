package algo_study;

import java.io.*;
import java.util.*;

public class p42839 {
 	static int N,totalCount;
	static String[] num;
	static boolean[] isSelected;
	static Set<Integer> pa;

	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		StringTokenizer st=new StringTokenizer(br.readLine(), " ");
		
		String numbers=st.nextToken();
		num=new String[numbers.length()];
		for(int i=0; i<numbers.length(); i++) {
			num[i]=numbers.substring(i, i+1);
		}
		N=num.length;
		isSelected = new boolean[N];
		pa=new HashSet<>();
		for(int i=0; i<N; i++) {
			Permutation(0, "", i);
		}
		for(int p:pa) {
			checkPrime(p);
		}
		System.out.println(totalCount);
	}
	private static void Permutation(int cnt, String result, int n){
		if(cnt==n+1) {
			pa.add(Integer.parseInt(result));
			return;
		}
		for(int i=0; i<N; i++) {
			if(isSelected[i]) {
				continue;
			}
			isSelected[i]=true;
			Permutation(cnt+1, result+num[i], n);
			Permutation(cnt+1, num[i]+result, n);
			isSelected[i]=false;
		}
	}
	private static void checkPrime(int p) {
		if(p>3) {
			for(int i=2; i<p; i++) if(p%i==0) return;
			totalCount++;
			return;
		}else if(p==2 || p==3) totalCount++;
	}
}