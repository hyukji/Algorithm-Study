package _240304;

import java.sql.Array;
import java.util.*;
import java.io.*;

public class BOJ_G4_14502_연구소 {
    static int n, m, size = 0, res = -1;
    static int[][] virus, arr;
    static int[] dx = {-1,0,1,0}, dy = {0,1,0,-1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        virus = new int[10][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j]==2) {
                    virus[size][0] = i;
                    virus[size++][1] = j;
                }
            }
        }

        DFS(0);
        System.out.println(res);

        br.close();

    }

    private static void DFS(int cnt) {
        if(cnt == 3) {
            BFS();
            return;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(arr[i][j] == 0) {
                    arr[i][j] = 1;
                    DFS(cnt+1);
                    arr[i][j] = 0;
                }
            }
        }
    }
    private static void BFS() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < size; i++) {
            q.offer(new int[]{virus[i][0], virus[i][1]});
        }
        int[][] tmp = copyArr(arr);

        while (!q.isEmpty()) {
            int[] p = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = p[0] + dx[d];
                int ny = p[1] + dy[d];

                if(nx>=0 && nx < n && ny>= 0 && ny < m) {
                    if(tmp[nx][ny] == 0) {
                        q.offer(new int[]{nx, ny});
                        tmp[nx][ny] = 2;
                    }
                }
            }
        }
        int count = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if(tmp[i][j] == 0) count++;

        if(res < count) res = count;
    }
    static int[][] copyArr(int[][] arr) {
        int[][] tmp = new int[n][m];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                tmp[i][j] = arr[i][j];
        return tmp;
    }
}
