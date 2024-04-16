package study;

import java.io.*;
import java.util.*;

public class BOJ_7682_틱택토 {
	static char[][] ttt;
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		
		while(true) {
			String line=br.readLine();
			if(line.equals("end")) break;
			ttt=new char[3][3];
			int c=0;
			int x=0;
			for(int i=0; i<9; i++) {
				ttt[i/3][i%3]=line.charAt(i);
				if(ttt[i/3][i%3]=='O') c++;
				else if(ttt[i/3][i%3]=='X') x++;
			}
			String ans=null;
			if(countX()+countO()==0) { // 가로, 세로줄이 완성되지 않은 경우
				// 대각선 확인
				if(countCross()==0) { // 승자가 없이 끝난 경우
					if(x==5 && c==4) ans="valid";
					else ans="invalid";
				} else if(countCross()==1) { // 대각선으로 승자가 결정된 경우
					if(countCrossX()==1 && x-c==1) ans="valid";
					else if(countCrossO()==1 && x==c) ans="valid";
					else ans="invalid";
				} else {// 대각선이 2개인 경우(X:5개 O:4개)
					if(x==5 && c==4) ans="valid";
					else ans="invalid";
				}
			} else if(countX()+countO()==1) {
				// X가 이긴 경우 C보다 하나 많아야하고
				if(countX()==1 && x-c==1) ans="valid";
				// C가 이긴 경우 X와 C의 숫자가 같아야함
				else if(countO()==1 && x==c) ans="valid";
				else ans="invalid";
			} else if(countX()==2){ // 가로, 세로 줄이 2개인 경우
				if(x==5 && c==4) ans="valid";
				else ans="invalid";
			} else { // 가로, 세로 줄이 3개이상 완성된 경우=불가
				ans="invalid";
			}
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
	static int countX() {
		int count=0;
		for(int i=0; i<3; i++) {
			if(ttt[i][0]=='X' && ttt[i][1]=='X' && ttt[i][2]=='X') count++;
		}
		for(int j=0; j<3; j++) {
			if(ttt[0][j]=='X' && ttt[1][j]=='X' && ttt[2][j]=='X') count++;
		}
		return count;
	}
	static int countO() {
		int count=0;
		for(int i=0; i<3; i++) {
			if(ttt[i][0]=='O' && ttt[i][1]=='O' && ttt[i][2]=='O') count++;
		}
		for(int j=0; j<3; j++) {
			if(ttt[0][j]=='O' && ttt[1][j]=='O' && ttt[2][j]=='O') count++;
		}
		return count;
	}
	static int countCross() {
		int count=0;
		if(ttt[0][0]==ttt[1][1] && ttt[1][1]==ttt[2][2]) count++;
		if(ttt[0][2]==ttt[1][1] && ttt[1][1]==ttt[2][0]) count++;
		return count;
	}
	static int countCrossX() {
		int count=0;
		if(ttt[1][1]=='X' && ttt[0][0]==ttt[1][1] && ttt[1][1]==ttt[2][2]) count++;
		if(ttt[1][1]=='X' && ttt[0][2]==ttt[1][1] && ttt[1][1]==ttt[2][0]) count++;
		return count;
	}
	static int countCrossO() {
		int count=0;
		if(ttt[1][1]=='O' && ttt[0][0]==ttt[1][1] && ttt[1][1]==ttt[2][2]) count++;
		if(ttt[1][1]=='O' && ttt[0][2]==ttt[1][1] && ttt[1][1]==ttt[2][0]) count++;
		return count;
	}
}
