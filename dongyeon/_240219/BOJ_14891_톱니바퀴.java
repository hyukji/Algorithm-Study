package a0219;

import java.io.*;
import java.util.*;

public class BOJ_14891_톱니바퀴 {
	
	static int[] arr;
	static boolean[] v;
	static boolean[] check;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 톱니바퀴 4개 저장할 배열 생성
		arr = new int[32];
		// n극은 0, s극은 1이다.
		for(int i=0; i<4; i++) {
			String s = br.readLine();
			for(int j=0; j<8; j++) {
				arr[j+(8*i)]=s.charAt(j)-'0';
			}
		}
		// 회전 횟수
		int k = Integer.parseInt(br.readLine());
		
		for(int i=0; i<k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int num = Integer.parseInt(st.nextToken()); // 톱니바퀴 번호
			int direction = Integer.parseInt(st.nextToken()); // 뱡향
			// 1 -> 시계방향, -1 -> 반시계 방향
			
			// 어떤 톱니바퀴가 돌아가는지 check하고 방향에 맞게 돌림.
			rangecheck(num-1, direction);// 톱니바퀴 번호 0,1,2,3으로 지정
			
		}
		int ans = 0;
		for(int i=0; i<4; i++) {
			if(arr[i*8]==1) {
				ans += Math.pow(2, i);
			}
		}
		
		
		System.out.println(ans);
		
		br.close();
	}

	static void rangecheck(int num, int direction) {
		v = new boolean[4];
		check = new boolean[3];
		int idx = 0;
		for(int i=0; i<3; i++) {
			if(arr[2+idx]!=arr[14+idx]) {
				check[i] = true;
			}else {
				check[i] = false;
			}
			idx+=8;
		}
		
		dfs(num, direction);
		
	}
	
	
	static void dfs(int num, int direction) {
		rotate(num, direction);
		v[num]=true;
		
		if(num-1>=0 && num-1<3) {// 왼쪽
			if(check[num-1] && !v[num-1]) {
				int newnum = num-1;
				int newdirection = direction*-1;
				dfs(newnum,newdirection);
			}
		}
		if(num>=0 && num<3) {// 오른쪽
			if(check[num] && !v[num+1]) {
				int newnum = num+1;
				int newdirection = direction*-1;
				dfs(newnum,newdirection);
			}
		}
		
	}

	static void rotate(int num, int direction) {
		if(direction==1) {// 시계방향
			int start = arr[8*num];
			for(int i = 8*num+1; i< 8*num+8; i++) {
				int now = arr[i];
				arr[i] = start;
				start = now;
			}
			arr[8*num]=start;
		}else { // 반시계 방향
			int start = arr[8*num];
			for(int i = 8*num+8-1; i>8*num; i--) {
				int now = arr[i];
				arr[i] = start;
				start = now;
			}
			arr[8*num]=start;
		}
	}

}
