package _240729;

import java.util.*;
import java.io.*;

/**
 * N * M 인 지도가 존재한다.
 * 지도의 좌표는 (r,c)로 나타낸다.
 *
 *
 *    2
 *  4 1 3
 *    5
 *    6
 */
public class BOJ_G4_14499_주사위굴리기 {
    static int N, M, X, Y;
    static int[][] arr;
    static int[] dice;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        dice = new int[6];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < k; i++) {
            int order = Integer.parseInt(st.nextToken());
            Simulation(order - 1);
        }

        System.out.println(sb);
    }

    static void Simulation(int dir) {
        int nx = X + dx[dir];
        int ny = Y + dy[dir];

        if(nx < 0 || ny < 0 || nx >= N || ny >= M)
            return;

        int tmp = dice[5];
        switch (dir) {
            // 동
            case 0:
                dice[5] = dice[2];
                dice[2] = dice[0];
                dice[0] = dice[3];
                dice[3] = tmp;
                break;
            // 서
            case 1:
                dice[5] = dice[3];
                dice[3] = dice[0];
                dice[0] = dice[2];
                dice[2] = tmp;
                break;
            // 남
            case 2:
                dice[5] = dice[4];
                dice[4] = dice[0];
                dice[0] = dice[1];
                dice[1] = tmp;
                break;

            // 북
            default:
                dice[5] = dice[1];
                dice[1] = dice[0];
                dice[0] = dice[4];
                dice[4] = tmp;
        }

        sb.append(dice[0]).append("\n");

        X = nx;
        Y = ny;

        // 0인경우 주사위 바닥 -> 맵
        if(arr[X][Y] == 0) {
            arr[X][Y] = dice[5];
        }

        else {
            dice[5] = arr[X][Y];
            arr[X][Y] = 0;
        }
    }
}
