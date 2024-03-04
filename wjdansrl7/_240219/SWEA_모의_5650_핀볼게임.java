package _240219;

import java.io.*;
import java.util.*;


// 구현
/*
 *  구하고자 하는 것
 *  벽이나 블록에 부딫힌 횟수에 대한 최댓값
 *  
 *  핀볼의 시작
 *  1. 어디서든 시작 가능(but, 블록, 웜홀, 블랙홀이 있는 위치는 출발 불가) => 즉 빈공간에서만 시작
 *  
 *  게임 종료 조건
 *  1. 핀볼이 출발 위치에 돌아온다.
 *  2. 블랙홀에 빠질 때 끝남.
 */
public class SWEA_모의_5650_핀볼게임 {
	static class Point {
		int x, y; // 점 좌표 x, y

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int ans = 0, N;
	static int map[][];
	static int dr[] = { -1, 0, 1, 0 }; // 상, 좌, 하, 우
	static int dc[] = { 0, -1, 0, 1 };
	static List<Point>[] warm; // 웜홀에 대한 리스트 배열 생성

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			ans = 0;
			N = Integer.parseInt(in.readLine());
			map = new int[N][N];
			List<Point> departList = new ArrayList<Point>(); // 빈 공간을 저장
			warm = new ArrayList[11];
			for (int i = 6; i <= 10; i++) {
				warm[i] = new ArrayList<>(); // 웜홀 번호에 대해 각각 arrayList 생성
			}
			for (int i = 0; i < map.length; i++) {
				st = new StringTokenizer(in.readLine());
				for (int j = 0; j < map.length; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 0) { // 빈 공간일 경우, departList 리스트에 해당 점을 추가
						departList.add(new Point(i, j));
					}
					if (map[i][j] >= 6) {
						warm[map[i][j]].add(new Point(i, j)); // 해당 웜홀에 대하여 좌표 저장.
					}
				}
			} // 입력끝
			for (Point point : departList) {
				for (int d = 0; d < 4; d++) { // 상, 좌, 하, 우 에 대한 시작 방향
					int cnt = go(point.x, point.y, d); // 빈공간에서 시작
					ans = Math.max(ans, cnt); // 부딫힌 횟수에 대한 최댓값 저장
				}
			}
			System.out.println("#" + tc + " " + ans);
		}
	}

	private static int go(int startR, int startC, int d) {
		int cnt = 0;
		boolean flag = false;
		int r = startR; // 시작점
		int c = startC; // 끝점
		while (true) {
			int nr = 0;
			int nc = 0;
			int num = map[r][c]; // 현재 위치에 대한 정보
			// 블록
			if (1 <= num && num <= 5) { // 블록일 경우
				cnt++; // 부딪히는 횟수 증가
				switch (num) { // 각각의 블록에 따라서.
				case 1: {
					if (d == 0 || d == 3) {
						d = (d % 2 == 0) ? 2 - d : 4 - d; // 직각으로 꺾이는 경우
					} else {
						d = (d == 1) ? 0 : 3; // 반사되는 경우
					}
					break;
				}
				case 2: {
					if (d == 2 || d == 3) {
						d = (d % 2 == 0) ? 2 - d : 4 - d;
					} else {
						d = (d == 0) ? 3 : 2;
					}
					break;
				}
				case 3: {
					if (d == 1 || d == 2) {
						d = (d % 2 == 0) ? 2 - d : 4 - d;
					} else {
						d = (d == 0) ? 1 : 2;
					}
					break;
				}
				case 4: {
					if (d == 0 || d == 1) {
						d = (d % 2 == 0) ? 2 - d : 4 - d;
					} else {
						d = (d == 2) ? 1 : 0;
					}
					break;
				}
				case 5: {
					d = (d % 2 == 0) ? 2 - d : 4 - d;
					break;
				}
				}
			}
			nr = r + dr[d]; // 이동
			nc = c + dc[d];
			// 벽에 만났을 경우 반사됨.
			if (nr < 0 || nr >= N || nc < 0 || nc >= N) { 
				d = (d % 2 == 0) ? 2 - d : 4 - d;
				cnt++;
				nr = r; // 부딫히고 다시 제자리로 돌아오므로 현재 위치를 그대로 저장.
				nc = c;
			}
			// 종료조건
			if (map[nr][nc] == -1 || (nr == startR && nc == startC)) { // 시작점에 도착하거나, 블랙홀을 만났을 경우 종료
				break;
			}
			if (map[nr][nc] >= 6) {// 웜홀을 만났을 경우 다른 웜홀로 이동
				int warmNum = map[nr][nc]; // 어떤 웜홀인지 찾는다.
				for (int i = 0; i < 2; i++) {
					if (warm[warmNum].get(i).x == nr && warm[warmNum].get(i).y == nc) {
						nr = (i == 0) ? warm[warmNum].get(1).x : warm[warmNum].get(0).x;
						nc = (i == 0) ? warm[warmNum].get(1).y : warm[warmNum].get(0).y;
						break;
					}
				}
			}
			r = nr; // 현재위치를 이동한 웜홀의 위치로 변경
			c = nc;
		}
		return cnt;
	}
	
}
