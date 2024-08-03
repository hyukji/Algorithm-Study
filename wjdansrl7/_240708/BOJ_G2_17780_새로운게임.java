package _240708;

import java.util.*;
import java.io.*;

public class BOJ_G2_17780_새로운게임 {
    static int N, K; // N: 체스판의 크기, K: 말의 개수
    static int[][] arr;
    static int[] dx = {0, 0,0,-1,1}, dy = {0, 1, -1, 0, 0};

    static class Point {
        int x, y, dir;

        public Point(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    static List<Point>[][] status;
    static Point[] points;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][N + 1];
        points = new Point[K + 1];
        status = new List[N + 1][N + 1];

        // 각 체스판에 상태를 담는 배열
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < N + 1; j++) {
                status[i][j] = new ArrayList<>();
            }
        }

        // 0: 흰 색, 1: 빨간 색, 2: 파란 색
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int x, y, dir;
        for (int i = 1; i < K + 1; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            dir = Integer.parseInt(st.nextToken());

            points[i] = new Point(x, y, dir);
            status[x][y].add(points[i]);
        }
        int res = chessGame();
        if (res > 1000) System.out.println(-1);
        else System.out.println(res);
    }

    static boolean checked(int x, int y) {
        if (x >= 1 && x <= N && y >= 1 && y <= N) return true;
        return false;
    }
    static int dirChange(int dir) {
        if (dir == 1) return 2;
        else if (dir == 2) return 1;
        else if (dir == 3) return 4;
        else return 3;
    }
    static int chessGame() {
        int turn = 0;
        label: while (turn <= 1000) {
            for (int i = 1; i < K + 1; i++) {
                int x = points[i].x; // 이전 말의 위치 x
                int y = points[i].y; // 이전 말의 위치 y

                // 현재 움직이려는 체스 말이 가장 아래에 잇는 경우에만 진행한다.
                if (status[x][y].size() > 0 && (status[x][y].get(0).equals(points[i]))) {
                    int nx = points[i].x + dx[points[i].dir];
                    int ny = points[i].y + dy[points[i].dir];

                    // 벽에 부딫힌 경우나, 파란색 칸에 도착한 경우는 마지막 체스 말의 방향을 바꿔야 한다.
                    if (!checked(nx, ny) || arr[nx][ny] == 2) points[i].dir = dirChange(points[i].dir);

                    // 만약 벽에 부딫혔거나, 파란색이었다면 방향이 바뀌었을 것.
                    nx = points[i].x + dx[points[i].dir];
                    ny = points[i].y + dy[points[i].dir];

                    if (!checked(nx, ny) || arr[nx][ny] == 2) continue;

                    if (arr[nx][ny] == 0) {
                        //TODO: 그냥 이동하기
                        for (int j = 0; j < status[x][y].size(); j++) {
                            status[x][y].get(j).x = nx;
                            status[x][y].get(j).y = ny;
                            status[nx][ny].add(status[x][y].get(j));
                        }
                        status[x][y].clear();

                    } else if (arr[nx][ny] == 1) {
                        //TODO: 빨간색인 경우, 이동하려는 체스말들의 위치를 뒤집어서 이동
                        for (int j = status[x][y].size() - 1; j >= 0; j--) {
                            status[x][y].get(j).x = nx;
                            status[x][y].get(j).y = ny;
                            status[nx][ny].add(status[x][y].get(j));
                        }
                        status[x][y].clear();

                    }
                }
            }
            // 한 차례가 턴이 끝난 이후 체스의 말이 K개 이상 붙어 있는 경우 종료한다.
            turn++;
            for (int i = 1; i < N + 1; i++) for (int j = 1; j < N + 1; j++) if (status[i][j].size() >= 4) break label;
        }
        return turn;
    }
}
