package _240212;

import java.io.*;
import java.util.*;

public class BOJ_G4_16234_인구이동 {
    static int N, L, R;
    static int[][] arr;
    static int[] dx = {-1,0,1,0}, dy = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }

        int res = 0;
        int[][] tmp = new int[N][N];
        label:for (int i = 1; i <= 2000; i++) {
            int[][] visited = new int[N][N];

            for (int j = 0; j < N; j++)
                for (int k = 0; k < N; k++)
                    tmp[j][k] = arr[j][k];

            for (int j = 0; j < N; j++)
                for (int k = 0; k < N; k++)
                    if(visited[j][k] == 0)
                        BFS(j, k, visited);

            for (int j = 0; j < N; j++)
                for (int k = 0; k < N; k++)
                    if(tmp[j][k] != arr[j][k]) continue label;
            if(i == 1) res = 0;
            else res = i - 1;
            break label;
        }
        System.out.println(res);
    }

    static void BFS(int x, int y, int[][] visited) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        int idx = x * N + y + 1, cnt = 0;
        q.offer(new int[]{x, y});
        visited[x][y] = idx;
        cnt++;
        while (!q.isEmpty()) {
            int[] p = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = p[0] + dx[d];
                int ny = p[1] + dy[d];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && visited[nx][ny] == 0) {
                    int v = Math.abs(arr[p[0]][p[1]] - arr[nx][ny]);
                    if (v >= L && v <= R) {
                        q.offer(new int[]{nx, ny});
                        visited[nx][ny] = idx;
                        cnt++;
                    }
                }
            }
        }
        if(cnt>1)
            movePerson(idx, visited);
    }

    static void movePerson(int idx, int[][] visited) {
        int sum = 0, cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] == idx) {
                    sum += arr[i][j];
                    cnt++;
                }
            }
        }
        int avg = sum / cnt;

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if(visited[i][j] == idx)
                    arr[i][j] = avg;
    }
}
