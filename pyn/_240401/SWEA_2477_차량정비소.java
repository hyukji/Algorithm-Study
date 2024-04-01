package study;

import java.io.*;
import java.util.*;

public class Solution_swea_2477_차량정비소_서울_20반_박영남 {
	static int N, M, K, useA, useB;
	static int[][] deskA, deskB;
	static int[] qA; // A창구 대기열
	static ArrayDeque<Integer> qB; // B창구 대기열
	static int[] list; // 마지막에 고객이 이용한 창구들을 저장하는 배열
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("res/input_sw_2477.txt"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		StringTokenizer st;
		
		int T=Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			st=new StringTokenizer(br.readLine(), " ");
			
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());
			// A,B 찾기
			useA=Integer.parseInt(st.nextToken());
			useB=Integer.parseInt(st.nextToken());
			
			deskA=new int[N+1][3]; // 접수 소요 시간, 정비 남은 시간, 고객 번호
			deskB=new int[M+1][2]; // 정비 소요 시간, 고객 유무
			qA=new int[K+1];
			qB=new ArrayDeque<>();
			list=new int[K+1];
			st=new StringTokenizer(br.readLine(), " ");
			// A 창구별 시간
			for(int i=1; i<=N; i++) deskA[i][0]=Integer.parseInt(st.nextToken());
			st=new StringTokenizer(br.readLine(), " ");
			// B 창구별 시간
			for(int i=1; i<=M; i++) deskB[i][0]=Integer.parseInt(st.nextToken());
			st=new StringTokenizer(br.readLine(), " ");
			// 도착하는 고객별 시간
			for(int i=1; i<=K; i++) qA[i]=Integer.parseInt(st.nextToken());
			int count=1;
			int time=0;
			boolean done=false;
			while(!done) {
				for(int i=1; i<=N; i++) {
					if(deskA[i][1]==1) {// A창구에 사람이 있고, 시간이 다 됐다면
						qB.add(deskA[i][2]); // a_i번 창구에 있던 사람 B창구 대기열에 넣어주기
						deskA[i][1]=0; // a_i번 창구 비워주기
						//System.out.println(time+"분: a"+i+"번 창구 비움");
					} else if(deskA[i][1]!=0) {
						deskA[i][1]--; // 아직 시간이 다 안됐다면 시간 줄여주기
						//System.out.println(time+"분: a"+i+"번 시간 줄임");
					}
				}
				// B창구에 사람이 있거나 대기하는 사람이 있다면 창구 B 관리
				if(!checkB() || !qB.isEmpty()) B(time);
				// 마지막 사람이 도착한 이후, 모든 대기열과 창구가 비어있다면 while문 탈출
				if(checkA() && checkB() && qB.isEmpty() && time>=qA[K]) done=true;
				for(int i=1; i<=N; i++) {
					if(count>K || qA[count]>time) break;
					if(deskA[i][1]==0) { // i번째 창구가 비어있다면
						deskA[i][1]=deskA[i][0]; // i번째 창구의 시간을 채워주고
						deskA[i][2]=count; // 몇번째 고객이 들어갔는지 기록
						//System.out.println(time+"분: a"+i+"번 창구에 "+count+"입장");
						list[count]+=10*i; // list에 이용한 창구를 기록(십의 자리)
						count++; // 다음사람으로 넘어가기 (다음사람이 동시에 왔을수도 있기때문에 time은 증가시키지 않음)
					}
				}
				time++;	// 시간 증가시키기
			}
//			for(int i=1; i<=K; i++) {
//				System.out.println(i+"번: "+list[i]+" ");
//			}
			// 모든 사람이 끝나면 그 중 조건과 맞는 사람 찾기
			int ans=0;
			for(int i=1; i<=K; i++) {
				if(list[i]==(useA*10+useB)) ans+=i;
			}
			sb.append("#").append(tc).append(" ");
			if(ans==0) sb.append(-1).append("\n");
			else sb.append(ans).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
	static boolean checkA() { // A창구에 사람이 있다면 false
		for(int i=1; i<=N; i++) {
			if(deskA[i][1]!=0) return false;
		}
		return true;
	}
	static boolean checkB() { // 어떤 B창구에 사람이 있다면 false
		for(int i=1; i<=M; i++) {
			if(deskB[i][1]!=0) return false;
		}
		return true; // 모든 B창구가 비어있다면 true
	}
	static void B(int time) {
		for(int i=1; i<=M; i++) {
			if(deskB[i][1]!=0) { // B창구에 사람이 있다면
				deskB[i][1]--; // 시간 줄여주기
				//System.out.println(time+"분: b"+i+"번 시간 줄임");
			}
			if(deskB[i][1]==0 && !qB.isEmpty()) { // b_i번 창구가 비었고 대기하는 사람이 있다면
				deskB[i][1]=deskB[i][0]; // 시간을 채워주고
				//System.out.println(time+"분: b"+i+"번 창구에 "+qB.peek()+"입장");
				list[qB.poll()]+=i; // list에 이용한 창구 기록(일의 자리)
			}
		}
	}

}
