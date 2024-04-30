package a0416;
import java.io.*;
import java.util.*;
public class BOJ_7682_틱택토 {
	static char[][] game;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			String s = br.readLine();
			//end 나오면 반복문 탈출
			if(s.equals("end")) break;
			
			game = new char[3][3];
			
			int idx=0;
			int x_cnt=0;
			int o_cnt=0;
			int dot_cnt=0;
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					game[i][j]=s.charAt(idx);
					idx ++;
					if(game[i][j]=='X') x_cnt++;
					if(game[i][j]=='O') o_cnt++;
					if(game[i][j]=='.') dot_cnt++;
				}
			}
			
			String ans = "valid";
			// 먼저 o가 x보다 많은 경우는 발생할 수 없다. 
			// o와 x의 갯수 차이가 0 또는 1이 아닐 때는 발생할 수 없다. 
			if(o_cnt>x_cnt || x_cnt-o_cnt>1) {
				ans = "invalid";
			}else {
				// 게임종료 조건을 만족하는 경우 
				// 1. 3만족 조건을 찾기
				boolean x_win=false;
				boolean o_win=false;
				for(int i=0; i<3; i++) {
					for(int j=0; j<3; j++) {
						if(game[i][j]=='X') {
							// 세로, 가로, 대각선으로 연속으로 오나용?
							if(check(i,j, 'X')) { // 만약 3잇는 조건 만족하는 경우 있으묘는
								x_win=true;
							}
						}else if(game[i][j]=='O') {
							if(check(i,j, 'O')) { // 만약 3잇는 조건 만족하는 경우 있으묘는
								o_win=true;
							}
						}
					}
				}
				// 둘다 승리하는 경우 있을 수 없음.
				if(x_win && o_win) ans = "invalid";
				else if(!x_win && !o_win) { // 둘다 승리 안하면 꽉찬 게임판이어야함.
					if(dot_cnt!=0) ans="invalid";
				}else if(x_win && !o_win) { //x가 승리하는 경우 
					if(x_cnt==o_cnt) ans="invalid";
				}else if(!x_win && o_win) { //o가 승리하는 경우
					if(x_cnt>o_cnt) ans="invalid";
				}
				
			}
			
			
			
			System.out.println(ans);
		}
		
		br.close();
	}
	
	private static boolean check(int i, int j, char g) {
		int w = 0;
		// 가로 검사 
		for(int a=0; a<3; a++) {
			if(game[i][a]==g) w++;
		}
		if(w==3) return true;
		// 세로 검사
		int h = 0;
		for(int a=0; a<3; a++) {
			if(game[a][j]==g) h++;
		}
		if(h==3) return true;
		// 대각선 검사 
		// 대각선은 애초에 2가지 경우밖에 없음
		if(i==0 && j==0) {
			if(game[1][1]==g && game[2][2]==g) {
				return true;
			}
		}else if(i==0 && j==2) {
			if(game[1][1]==g && game[2][0]==g) {
				return true;
			}
		}
		
		return false;
	}


}
