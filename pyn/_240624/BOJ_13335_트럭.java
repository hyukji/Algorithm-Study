package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_13335_트럭 {
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine(), " ");
		int n=Integer.parseInt(st.nextToken());
		int w=Integer.parseInt(st.nextToken());
		int L=Integer.parseInt(st.nextToken());
		
		int[] trucks=new int[n];
		
		st=new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<n; i++) trucks[i]=Integer.parseInt(st.nextToken());
		
		int time=0;
		int bridgeW=0;
		ArrayDeque<Integer> onBridge=new ArrayDeque<>();
		for(int i=0; i<w; i++) onBridge.add(0);
		for(int i=0; i<n; i++) {
			time++;
			bridgeW-=onBridge.poll();
			if(bridgeW+trucks[i]<=L) {
				onBridge.add(trucks[i]);
				bridgeW+=trucks[i];
//				System.out.println("현재 하중: "+bridgeW);
			}else {
				onBridge.add(0);
				i--;
//				System.out.println("현재 하중: "+bridgeW);
			}
//			System.out.println();
		}
		while(!onBridge.isEmpty()) {
			onBridge.poll();
			time++;
		}
		System.out.println(time);
		br.close();
	}
}
//		for(int i=0; i<n; i++) {
//			if(onBridge+trucks[i]<=L) {
//				onBridge+=trucks[i];
//				count++;
//				if(count>w) onBridge-=trucks[i-w];
//				time++;
//				System.out.println("현재 하중: "+onBridge);
//				System.out.println("현재 대수: "+count);
//				System.out.println("현재 시간: "+time);
//			} else {
//				time+=w-(count);
//				onBridge-=trucks[i-count];
//				count--;
//				i--;
//				System.out.println("현재 하중: "+onBridge);
//				System.out.println("현재 대수: "+count);
//				System.out.println("현재 시간: "+time);
//			}
//			if(i==n-1) {
//				time+=w;
//				break;
//			}
//			System.out.println();
//		}