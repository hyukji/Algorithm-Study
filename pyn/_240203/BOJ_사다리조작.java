package aHomework;

import java.io.*;
import java.util.*;

public class Solution_bj_15684_사다리조작_서울_20반_박영남 {
	// N: 세로선의 개수, M: 놓여진 가로선의 개수, H: 가로줄의 개수
	static int N, M, H, count=-1;
	static int[][] ladder;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		H=Integer.parseInt(st.nextToken());
		
		ladder=new int[H+1][N+1];
		for(int k=0; k<M; k++) {
			st=new StringTokenizer(br.readLine());
			int i=Integer.parseInt(st.nextToken());
			int j=Integer.parseInt(st.nextToken());
			ladder[i][j]=1;
		}
		for(int i=0; i<=3; i++) { // 사다리 0~3개까지 놓기
			comb(1, 0, i);
			if(count!=-1) break; // 중간에 사다리 놓기가 성공하면 break
		}
		System.out.println(count);
	}
	static boolean check() { // 사다리 결과 확인
		for(int start=1; start<=N; start++) { // 사다리 타기
			int i=1;
			int j=start;
			while(i<=H) {
				if(ladder[i][j]==1) { // 현재칸이 1이면 오른쪽 아래로 한칸 이동
					j++;
					i++;
				} else if(ladder[i][j-1]==1) { // 왼쪽칸이 1이면 왼쪽 아래로 한칸 이동
					j--;
					i++;
				}
				else i++; // 둘다 아니면 아래로 한칸 이동
			}
			if(start!=j) return false; // 내려간 위치가 처음 시작한 위치와 같지 않으면 false
		}
		return true; // 내려간 위치가 처음 시작한 위치와 모두 일치하면 true
	}
	static void comb(int h, int cnt, int size) { // 사다리 놓기(h: 세로줄, cnt: 놓은 개수, size: 놓을 개수)
		if(cnt==size) {
			if(check()) count=size; // 사다리를 내려간 결과가 true라면 사다리를 놓은 갯수 count에 저장
			return;
		}
		for(int i=h; i<=H; i++) {
			for(int j=1; j<N; j++) {
				if(ladder[i][j]==1) continue; // 현재칸이 1이면 사다리를 놓을 수 없음
				if(ladder[i][j+1]==1) continue; // 현재칸의 오른쪽이 1이면 사다리를 놓을 수 없음
				if(ladder[i][j-1]==1) continue; // 현재칸의 왼쪽이 1이면 사다리를 놓을 수 없음
				
				ladder[i][j]=1; // 사다리 놓기
				comb(i, cnt+1, size); // 사다리 추가
				ladder[i][j]=0; // 놓은 사다리 빼기
			}
		}
	}
}
