package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1654_랜선자르기 {
	static int K, N;
	static long[] has;
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine(), " ");
		K=Integer.parseInt(st.nextToken());
		N=Integer.parseInt(st.nextToken());
		has=new long[K];
		long total=0;
		long maxV=0;
		for(int i=0; i<K; i++) {
			st=new StringTokenizer(br.readLine(), " ");
			has[i]=Integer.parseInt(st.nextToken());
			maxV=Math.max(maxV, has[i]);
		}
		long left=1;
		long right=maxV+1;
		while(left+1<right) {
			long mid=(left+right)/2;
			
			if(check(mid)) {
				left=mid;
			} else {
				right=mid;
			}
		}

		System.out.println(left);
	}
	static boolean check(long value) {
		long count=0;
		for(int i=0; i<K; i++) {
//			if(count >= N)
//				return true;
			count+=has[i]/value;
		}
		return count>=N;
	}
}


//long max=total/N;
//while(true) {
//	long count=0; // 랜선 몇갠지 세기
//	long temp=0; // 나머지가 가장 큰 수 저장하는 변수
//	int maxindex=0; // 나머지가 가장 큰 수의 index 저장
//	for(int i=0; i<K; i++) {
//		count+=has[i]/max; // has[i]에서 몇개 만들 수 있는지
//		if(has[i]%max>temp) { // 나머지가 가장 큰 수 저장
//			temp=has[i]%max;
//			maxindex=i;
//		} else if(has[i]%max==temp) {
//			if(has[i]<has[maxindex]) {
//				temp=has[i]%max;
//				maxindex=i;
//			}
//		}
//	}
//	if(count>=N) break;
//	max=has[maxindex]/(has[maxindex]/max+1);
//}