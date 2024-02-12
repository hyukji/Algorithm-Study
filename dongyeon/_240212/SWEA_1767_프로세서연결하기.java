package a0204;

import java.io.*;
import java.util.*;

public class Solution_sample_1767_프로세서연결하기 {
	
	static int N;
	static ArrayList<int[]> core;
	static int[][] processor;
	static int[] di = {-1,1,0,0}; //상하좌우
	static int[] dj = {0,0,-1,1};
	static int min, max_core_cnt;
	
	public static void main(String[] args) throws Exception{
//		System.setIn(new FileInputStream("res/input_sample_1767.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb;
		StringTokenizer st;
		for(int tc =1 ; tc<T+1; tc++) {
			sb=new StringBuilder();
			
			N = Integer.parseInt(br.readLine());
			processor = new int[N][N];
			core = new ArrayList<>(); // core 좌표 값을 저장 할 ArrayList
			
			min = Integer.MAX_VALUE; // 전선 길이
			max_core_cnt = Integer.MIN_VALUE; // 연결된 core 갯수
			
			// 프로세서 값 입력받기
			for(int i=0; i<N ; i++) {
				st = new StringTokenizer(br.readLine()," ");
				for(int j=0; j<N ; j++) {
					processor[i][j]=Integer.parseInt(st.nextToken());
					// 테두리에 위치 하지 않은 core 좌표를 ArrayList에 저장한다.
					if(processor[i][j]==1 && i!=0 && i!=N-1 && j!=0 && j!=N-1) {
						core.add(new int[] {i,j});
					}
				}
			}
			
			// 탐색 시작
			search(0,0,0); // 확인한 core의 갯수, 연결된 core의 갯수, 전선 길이
			sb.append("#").append(tc).append(" ").append(min);
			System.out.println(sb.toString());
		}
		br.close();
	}
	
	static void search(int cnt, int coreCnt, int line) {
		if(cnt == core.size()) { // 모든 코어를 다 탐색햇을 때
			if(max_core_cnt< coreCnt) {
				max_core_cnt = coreCnt; // 최대로 연결된 코어 갯수 업데이트
				min = line; // 최대로 연결되었을 때 전선길이 업데이트
			}else if(max_core_cnt == coreCnt) {
				min = Math.min(min, line); // 최대로 연결된 코어갯수가 같을 때 최솟값으로 업데이트
			}
			return;
		}
		// core가 전원에 연결 될 때
		// 4방 탐색
		for(int i=0; i<4; i++) {
			if(!isValid(core.get(cnt)[0],core.get(cnt)[1], i)) continue;
			// i번째 방향으로 전원 연결이 불가능하면 continue;
			
			// 전원 연결이 가능하면 
			// 전선을 2로 바꾸고 다음 core 탐색
			search(cnt+1, coreCnt+1, line+lineUpdate(core.get(cnt)[0],core.get(cnt)[1], i,2));
			// 전선 0으로 복구 시켜주기
			lineUpdate(core.get(cnt)[0],core.get(cnt)[1], i,0);
		}
		// core가 전원에 연결 되지 않을 때
		search(cnt+1, coreCnt, line);
	}
	
	// 연결 가능 하면 cell -> 2로 바꿈 -> 전선 길이 return
	// 다시 cell 복구 -> 0으로 바꿈
	static int lineUpdate(int x, int y, int d, int num) { // x,y 좌표, 방향, 전선 바꿀 숫자
		int cnt =0; // 전선 길이
		while(true) {
			x+=di[d];
			y+=dj[d];
			if(0<=x && x<N && 0<=y && y<N) { // 좌표가 범위안에 있으면
				processor[x][y] = num; // cell 값 업데이트
				cnt++;
			}else break; // 범위 벗어나면 break
		}
		return cnt; // 전선 길이 return
	}

	// 전원 연결 가능한지 확인
	static boolean isValid(int x, int y, int d) { // x좌표, y 좌표 , 방향
		while(true) {
			x += di[d];
			y += dj[d];
			if(0<=x && x<N && 0<=y && y<N) { // 좌표가 범위안에 있을 때
				if(processor[x][y]>0) { // 연결 불가능한 cell이 있으면 false
					return false;
				}
			}else return true; // 범위를 벗어날 때까지 연결불가 cell이 없으면 -> 연결 가능 -> true
		}
	}

}
