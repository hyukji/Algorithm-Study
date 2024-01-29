package com.ssafy.algo.study.week1;

import java.io.*;
import java.util.*;

public class BOJ_14503_로봇청소기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m;
    static int r, c, dic;
    static int[][] graph;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        dic = Integer.parseInt(st.nextToken());

        graph = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = robot(r, c, dic, graph);
        System.out.println(answer);
    }

    static int robot(int r, int c, int dic, int[][] graph) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{dic, r, c});
        int result = 0;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int now = current[0];
            int x = current[1];
            int y = current[2];

            if (graph[x][y] == 0) {
                result++;
                graph[x][y] = 2;
            }

            int count = 0;
            for (int i = 0; i < 4; i++) {
                int nd = (now + 3) % 4;
                int nx = x + dx[nd];
                int ny = y + dy[nd];

                if (graph[nx][ny] == 0) {
                    count++;
                    q.add(new int[]{nd, nx, ny});
                    break;
                } else {
                    now = nd;
                }
            }

            if (count == 0) {
                int nx, ny;
                if (now == 0) {
                    nx = x + 1;
                    ny = y;
                } else if (now == 1) {
                    nx = x;
                    ny = y - 1;
                } else if (now == 2) {
                    nx = x - 1;
                    ny = y;
                } else {
                    nx = x;
                    ny = y + 1;
                }

                if (graph[nx][ny] == 1) {
                    break;
                } else {
                    q.add(new int[]{now, nx, ny});
                }
            }
        }

        return result;
    }
}
