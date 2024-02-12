package _240210;

import java.io.*;
import java.util.*;

public class SWEA_샘플_프로세서연결하기 {
    static int N, size, res;
    static int[][] arr, po;
    static int[] dx = {-1,0,1,0}, dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("/Users/moongi/Desktop/SSAFY/Algorithm_Study/wjdansrl7/res/SWEA_샘플_1767_프로세서연결하기.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());
            size = 0;
            res = Integer.MAX_VALUE;
            sb = new StringBuilder();

            arr = new int[N][N];
            po = new int[12][2];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());

                    if (arr[i][j] == 1 && i > 0 && i < N - 1 && j > 0 && j < N - 1) {
                        po[size][0] = i;
                        po[size++][1] = j;
                    }
                }
            }
            for (int i = size; i > -1; i--) {
                Perm(0, 0, i, new boolean[size]);
                if(res < Integer.MAX_VALUE) break;
            }

            sb.append("#").append(tc + 1).append(" ").append(res);
            System.out.println(sb);
        }
        br.close();
    }

    static void Perm(int idx, int cnt, int R, boolean[] v) {
        if (cnt == R) {
            DFS(0, 0, v);
            return;
        }

        for (int i = idx; i < size; i++) {
            if(v[i]) continue;
            v[i] = true;
            Perm(i + 1, cnt + 1, R, v);
            v[i] = false;
        }
    }

    static void DFS(int idx, int cnt, boolean[] v) {
        if (idx == size) {
            res = res > cnt ? cnt : res;
            return;
        }
        if(!v[idx]) {
            DFS(idx + 1, cnt, v);
            return;
        }
        for (int d = 0; d < 4; d++) {
            int x = po[idx][0], y = po[idx][1], tmp = 0;
            boolean flag = false;
            while (true) {
                x += dx[d];
                y += dy[d];

                if (x < 0 || x >= N || y < 0 || y >= N) {
                    flag = true;
                    break;
                }
                if(arr[x][y] != 0) break;
                arr[x][y] = 2;
                tmp++;
            }
            if(flag) DFS(idx + 1, cnt + tmp, v);
            while(true) {
                x -= dx[d];
                y -= dy[d];
                if (x == po[idx][0] && y == po[idx][1]) {
                    break;
                }
                arr[x][y] = 0;
            }
        }
    }
}