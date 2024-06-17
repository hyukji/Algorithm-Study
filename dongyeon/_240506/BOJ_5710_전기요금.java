package a0502;

import java.io.*;
import java.util.*;
public class BOJ_5710_전기요금 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[] wh= {100,10000,1000_000,1000_000_000};
		int[] fees= {200,29900,4979900};
		
		while(true) {
			st = new StringTokenizer(br.readLine()," ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(a==0 && b==0) break;
			
			//a:이웃의 사용량과 상근이의 사용량을 합쳤을 때 내야하는 요금
			//b:이웃의 전기 요금과의 차이(절댓값)
			//a와b가 주어졌을 때 상근이가 내야하는 전기요금을 구하라.
			
			long s=1;
			long e=0;
			for(int i=0; i<3; i++) {
				if(a <= fees[i]) e=wh[i];
			}
			if(e==0) e=wh[3];
			
			// 두사람의 총 전기 사용량
			long total=0;
			while(s<=e) {
				long mid = (s+e)/2;
				
				// 전기 요금으로 바꾸기
				long fee = money(mid);
				
				if(a<fee) e=mid-1;
				else if(a>fee) s=mid+1;
				else {// 값을 찾았을 때
					total=mid;
					break;
				}
			}
			long s1=1;
			long e1=total/2;
			
			long o=0;
			long t=0;
			while(s1<=e1) {
				long mid=(s1+e1)/2;
				
				long other=total-mid;
				//mid와 other의 값을 계산해서 차이가 b이면 정답
				
				long one = money(mid);
				long two = money(other);
				
				if(Math.abs(one-two)==b) {
					o=one;
					t=two;
					break;
				}
				
				if(Math.abs(one-two)>b) {
					s1=mid+1;
				}else if(Math.abs(one-two)<b) {
					e1=mid-1;
				}
			}
			
			System.out.println(o);
			
		}
		
		br.close();
	}
	
	static long money(long f) {
		long answer=0;
		
		if(f<=100) answer=2*f;
		else if(f<=10000) {
			answer+=2*100;
			f-=100;
			answer+=3*f;
		}else if(f<=1000_000) {
			answer+=2*100;
			answer+=3*9900;
			f-=10000;
			answer+=5*f;
		}else {
			answer+=2*100;
			answer+=3*9900;
			answer+=5*990000;
			f-=1000_000;
			answer+=7*f;
		}
		return answer;
	}

}
