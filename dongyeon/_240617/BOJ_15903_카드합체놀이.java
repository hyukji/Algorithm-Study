package a0614;

import java.io.*;
import java.util.*;
public class BOJ_15903_카드합체놀이 {
	static int n,m;
	static long[] card;
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 카드의 갯수
		n = Integer.parseInt(st.nextToken());
		// 카드 합체 횟수
		m = Integer.parseInt(st.nextToken());
		
		card = new long[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			card[i]=Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<m; i++) {
			Arrays.sort(card);
			long sum = card[0]+card[1];
			card[0]=sum;
			card[1]=sum;
		}
		long answer=0;
		for(int i=0; i<n; i++) {
			answer+=card[i];
		}
		
		System.out.println(answer);
		br.close();
	}

}
