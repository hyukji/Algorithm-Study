package study;

import java.io.*;
import java.util.*;

public class BOJ_2467_용액 {
	static int N;
	static double[] sol;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		N=Integer.parseInt(br.readLine());
		sol=new double[N];
		double[] memo= {Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE};
		int flag=0;
		StringTokenizer st=new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			sol[i]=Integer.parseInt(st.nextToken());
			if(sol[i]>=0 && flag==0) flag=i;
		}
		if(flag==0) {
			if(sol[0]<0) {
				memo[1]=sol[N-2];
				memo[2]=sol[N-1];
			} else {
				memo[1]=sol[0];
				memo[2]=sol[1];
			}
		} else {
			for(int i=0; i<flag; i++) {
				double find=bs(sol[i]);
//				System.out.println(sol[i]+" "+find+" "+(find-sol[i]));
				if(Math.abs(find)<Math.abs(memo[0])) {
					memo[0]=find;
					memo[1]=sol[i];
					memo[2]=find-sol[i];
				}
				if(memo[1]==memo[2]) {
					if(0<i&&i<N-1) {
						if(Math.abs(sol[i-1]+sol[i])<Math.abs(sol[i]+sol[i+1])) memo[1]=sol[i-1];
						else memo[2]=sol[i+1];
					} else if(i==0) {
						memo[2]=sol[i+1];
					} else {
						memo[1]=sol[i-1];
					}
				}
			}
		}
		if(memo[1]<memo[2]) {
			System.out.println((long)memo[1]+" "+(long)memo[2]);
		} else {
			System.out.println((long)memo[2]+" "+(long)memo[1]);
		}
	}
	static double bs(double n) {
		double find=-n;
		int left=0;
		int right=N-1;
		while(left+1<right) {
			int mid=(left+right)/2;
			if(sol[mid]<find) left=mid;
			else right=mid;
		}
		if(Math.abs(sol[left]+n)<Math.abs(sol[right]+n)) return sol[left]+n;
		else return sol[right]+n;
	}
}
