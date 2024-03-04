package _240226;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_15683_감시 {
    static int[] dx = {-1,0,1,0}, dy = {0, 1, 0, -1};
    static int N, M, size, res;
    static int[][] arr, po, visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        res = N * M;
        arr = new int[N][M];
        visited = new int[N][M];
        po = new int[8][2];
        size = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j]>0 && arr[i][j]<6) {
                    visited[i][j]++;
                    po[size][0] = i;
                    po[size++][1] = j;
                }
                if(arr[i][j] == 6) visited[i][j]++;
            }
        }
        DFS(0);
        System.out.println(res);
    }

    static void DFS(int cnt) {
        if(cnt == size) {
            int count = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(visited[i][j] == 0) count++;
                }
            }
            if(res > count) res = count;
            return;
        }

        int menu = arr[po[cnt][0]][po[cnt][1]]; // cctv 종류

        if (menu == 1) {
            for (int d = 0; d < 4; d++) {
                move(po[cnt][0], po[cnt][1], d, arr);
                DFS(cnt + 1);
                moveBack(po[cnt][0], po[cnt][1], d, arr);
            }
        } else if (menu == 2) {
            for (int d = 0; d < 2; d++) {
                move(po[cnt][0], po[cnt][1], d, arr);
                move(po[cnt][0], po[cnt][1], d + 2, arr);
                DFS(cnt + 1);
                moveBack(po[cnt][0], po[cnt][1], d+2, arr);
                moveBack(po[cnt][0], po[cnt][1], d, arr);
            }
        } else if (menu == 3) {
            for (int d = 0; d < 4; d++) {
                move(po[cnt][0], po[cnt][1], d % 4, arr);
                move(po[cnt][0], po[cnt][1], (d + 1) % 4, arr);
                DFS(cnt + 1);
                moveBack(po[cnt][0], po[cnt][1], (d + 1) % 4, arr);
                moveBack(po[cnt][0], po[cnt][1], d % 4, arr);
            }
        } else if (menu == 4) {
            for (int d = 0; d < 4; d++) {
                move(po[cnt][0], po[cnt][1], d % 4, arr);
                move(po[cnt][0], po[cnt][1], (d + 1) % 4, arr);
                move(po[cnt][0], po[cnt][1], (d + 2) % 4, arr);
                DFS(cnt + 1);
                moveBack(po[cnt][0], po[cnt][1], (d + 2) % 4, arr);
                moveBack(po[cnt][0], po[cnt][1], (d + 1) % 4, arr);
                moveBack(po[cnt][0], po[cnt][1], d % 4, arr);
            }
        } else {
            move(po[cnt][0], po[cnt][1], 0, arr);
            move(po[cnt][0], po[cnt][1], 1, arr);
            move(po[cnt][0], po[cnt][1], 2, arr);
            move(po[cnt][0], po[cnt][1], 3, arr);
            DFS(cnt + 1);
            moveBack(po[cnt][0], po[cnt][1], 0, arr);
            moveBack(po[cnt][0], po[cnt][1], 1, arr);
            moveBack(po[cnt][0], po[cnt][1], 2, arr);
            moveBack(po[cnt][0], po[cnt][1], 3, arr);
        }
    }

    static void move(int x, int y, int dir, int[][] arr) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        while (true) {
            if(nx >= 0 && nx < N && ny >= 0 && ny < M && arr[nx][ny] != 6) {
                visited[nx][ny]++;
                nx += dx[dir];
                ny += dy[dir];
            } else break;
        }
    }
    static void moveBack(int x, int y, int dir, int[][] arr) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        while (true) {
            if (nx >= 0 && nx < N && ny >= 0 && ny < M && arr[nx][ny] != 6) {
                visited[nx][ny]--;
                nx += dx[dir];
                ny += dy[dir];
            } else break;
        }
    }
}
