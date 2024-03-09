package study;

import java.io.*;
import java.util.*;

public class Solution_bj_14891_톱니바퀴_서울_20반_박영남 {
	static int[][] tob=new int[4][8];
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int i=0; i<4; i++) {
			String line=br.readLine();
			for(int j=0; j<8; j++) {
				tob[i][j]=line.charAt(j)-'0';
			}
		}
		int K=Integer.parseInt(br.readLine());
		
		for(int i=0; i<K; i++) {
			st=new StringTokenizer(br.readLine(), " ");
			rotation(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
//			for(int x=0; x<4; x++) {
//				for(int y=0; y<8; y++) System.out.print(tob[x][y]);
//				System.out.println();
//			}
		}
		int sum=tob[0][0]+2*tob[1][0]+4*tob[2][0]+8*tob[3][0];
		System.out.println(sum);
	}
	static void rotation(int num, int w) {
		int right=tob[num-1][2];
		int left=tob[num-1][6];
		
		if(w==1) {
			if(num>1 && tob[num-2][2]!=left) {
				left=tob[num-2][6];
				counterclockwise(tob[num-2]);
				if(num>2 && tob[num-3][2]!=left) {
					left=tob[num-3][6];
					clockwise(tob[num-3]);
					if(num>3 && tob[num-4][2]!=left) counterclockwise(tob[num-4]);
				}
			}
			if(num<4 && tob[num][6]!=right) {
				right=tob[num][2];
				counterclockwise(tob[num]);
				if(num<3 && tob[num+1][6]!=right) {
					right=tob[num+1][2];
					clockwise(tob[num+1]);
					if(num<2 && tob[num+2][6]!=right) counterclockwise(tob[num+2]);
				}
			}
			clockwise(tob[num-1]);
		}else {
			if(num>1 && tob[num-2][2]!=left) {
				left=tob[num-2][6];
				clockwise(tob[num-2]);
				if(num>2 && tob[num-3][2]!=left) {
					left=tob[num-3][6];
					counterclockwise(tob[num-3]);
					if(num>3 && tob[num-4][2]!=left) clockwise(tob[num-4]);
				}
			}
			if(num<4 && tob[num][6]!=right) {
				right=tob[num][2];
				clockwise(tob[num]);
				if(num<3 && tob[num+1][6]!=right) {
					right=tob[num+1][2];
					counterclockwise(tob[num+1]);
					if(num<2 && tob[num+2][6]!=right) clockwise(tob[num+2]);
				}
			}
			counterclockwise(tob[num-1]);
		}
	}
	static void clockwise(int[] num) {
		int temp=num[7];
		for(int i=7; i>0; i--) num[i]=num[i-1];
		num[0]=temp;
	}
	static void counterclockwise(int[] num) {
		int temp=num[0];
		for(int i=0; i<7; i++) num[i]=num[i+1];
		num[7]=temp;
	}
}
