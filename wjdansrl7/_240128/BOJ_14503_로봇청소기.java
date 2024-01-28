package _240128;

import java.io.*;
import java.util.*;

/**
 * packageName    : _240128
 * fileName       : BOJ_14503_로봇청소기
 * author         : moongi
 * date           : 1/28/24
 * description    :
 * 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
 * 현재 칸의 주변 $4$칸 중 청소되지 않은 빈 칸이 없는 경우,
 * 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
 * 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
 * 현재 칸의 주변 $4$칸 중 청소되지 않은 빈 칸이 있는 경우,
 * 반시계 방향으로 $90^\circ$ 회전한다.
 * 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
 * 1번으로 돌아간다.
 */
public class BOJ_14503_로봇청소기 {
    static int n, m;
    static int[] ni = {-1, 0, 1, 0}; // 북, 동, 남, 서
    static int[] nj = {0, 1, 0, -1};
    static int[][] arr = new int[51][51];
    static int answer = 0;

    static void clean(int x, int y, int dir) {
//        청소한 칸은 2
        if (arr[x][y] == 0) {
            answer++;
            arr[x][y] = 2;
        }

        int cnt = 0;
        for (int d = 0; d < 4; d++) {
            int nx = x + ni[d];
            int ny = y + nj[d];

//            청소되지 않은 빈칸이 있는 경우
            if (nx >= 0 && nx < n && ny >= 0 && ny < m && arr[nx][ny] == 0) {
//                반시계방향으로 90도 회전
//                바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진
//                clean()으로 돌아감
                EmptySpace(x, y, dir);
                break;
            }
            cnt++;
        }

//        청소되지 않은 빈칸이 없는 경우
//        1. 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 되돌아감
//        2. 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
        if(cnt == 4)
            notEmptySpace(x, y, dir);


    }

    static void EmptySpace(int x, int y, int dir) {
        if (dir - 1 >= 0) {
            dir--;
        } else {
            dir = 3;
        }

        int nx = x + ni[dir];
        int ny = y + nj[dir];

        if(nx >= 0 && nx < n && ny >= 0 && ny < m) {
            if(arr[nx][ny] == 0) {
                clean(nx, ny, dir);
            } else {
                clean(x, y, dir);
            }
        }

    }

    static void notEmptySpace(int x, int y, int dir) {
        int tmp = -1;
        if(dir == 0) { // 북쪽
            tmp = 2;
        } else if (dir == 1) { // 동쪽
            tmp = 3;
        } else if (dir == 2) { // 남쪽
            tmp = 0;
        } else if (dir == 3) { // 서쪽
            tmp = 1;
        }

        int nx = x + ni[tmp];
        int ny = y + nj[tmp];

        if (nx >= 0 && nx < n && ny >= 0 && ny < m && arr[nx][ny] != 1) {
            clean(nx, ny, dir);
        } else {
            return;
        }

    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        clean(x, y, dir);
        System.out.println(answer);
    }
}
