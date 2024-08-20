package _240819;

import java.util.*;
import java.io.*;

public class BOJ_G3_15684_사다리조작 {
    static int N, M, H, answer;
    static boolean check;
    static int[][] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        arr = new int[H + 1][N + 1];

        int a, b;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            // 순방향: 1, 역방향: -1
            arr[a][b] = 1;
            arr[a][b + 1] = -1;
        }

        // 가로선의 개수가 3개를 넘어가면 -1, 불가능한 경우에도 -1
        for (int i = 0; i <= 3; i++) {
            answer = i;
            dfs(1, 0);
            if (check) break;
        }

        System.out.println((check) ? answer : -1);
    }

    private static void dfs(int x, int count) {
        // 이미, 모든 점이 자신의 위치로 간다면 더 이상 체크할 필요 없음.
        if (check) return;

        if (answer == count) {
            if (isCheck()) check = true;
            return;
        }

        for (int i = x; i < H + 1; i++) {
            for (int j = 1; j < N; j++) {
                if (arr[i][j] == 0 && arr[i][j + 1] == 0) {
                    arr[i][j] = 1;
                    arr[i][j + 1] = -1;
                    dfs(i, count + 1);
                    arr[i][j] = arr[i][j + 1] = 0;
                }
            }
        }
    }

    private static boolean isCheck() {
        for (int i = 1; i < N + 1; i++) {
            int x = 1, y = i;
            for (int j = 0; j < H; j++) {
                if (arr[x][y] == 1) y++;
                else if (arr[x][y] == -1) y--;
                x++;
            }
            if (y != i) return false;
        }
        return true;
    }
}
