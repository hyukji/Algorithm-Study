package _240304;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ_G4_11559_PuyoPuyo {
    static int cnt, res = 0;
    static int[][] pan;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        pan = new int[12][6];

        // 입력
        for (int i = 0; i < 12; i++) {
            String str = br.readLine();
            for (int j = 0; j < 6; j++) {
                if (str.charAt(j) == '.') {
                    pan[i][j] = 0;
                    continue;
                } else if (str.charAt(j) == 'R') pan[i][j] = 1;
                else if (str.charAt(j) == 'G') pan[i][j] = 2;
                else if (str.charAt(j) == 'B') pan[i][j] = 3;
                else if (str.charAt(j) == 'P') pan[i][j] = 4;
                else if (str.charAt(j) == 'Y') pan[i][j] = 5;
                cnt++; // puyo의 개수
            }
        }

        // 종료 조건 :
        boolean flag = false;
        int puyo = 0;
        while (true) {
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (pan[i][j] != 0) {
                        if(BFS(i, j))
                            flag = true;
                    }
                }
            }
            if(flag)
                puyo++;
            if (!flag || cnt == 0) {
                if(res < puyo) res = puyo;
                break; // 종료조건
            }
            sortPuyo();
            flag = false;
        }
        System.out.println(res);
    }
    private static void sortPuyo() {
        for (int i = 11; i >= 0; i--) {
            for (int j = 0; j < 6; j++) {
                if (i+1 < 12 && pan[i][j] != 0 && pan[i+1][j] == 0) {
                    int curX = i;
                    while (true) {
                        if (curX + 1 < 12 && pan[curX+1][j] == 0) {
                            curX++;
                        } else {
                            pan[curX][j] = pan[i][j];
                            pan[i][j] = 0;
                            break;
                        }
                    }
                }
            }
        }
    }

    private static boolean BFS(int x, int y) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        ArrayDeque<int[]> tt = new ArrayDeque<>();
        boolean[][] v = new boolean[12][6];
        int count = 0;
        q.offer(new int[]{x, y});
        tt.offer(new int[]{x, y});
        v[x][y] = true;
        int color = pan[x][y];

        while (!q.isEmpty()) {
            int[] p = q.poll();
            count++;

            for (int d = 0; d < 4; d++) {
                int nx = p[0] + dx[d];
                int ny = p[1] + dy[d];

                if (nx >= 0 && nx < 12 && ny >= 0 && ny < 6 && !v[nx][ny]) {
                    if (pan[nx][ny] == color) {
                        q.offer(new int[]{nx, ny});
                        tt.offer(new int[]{nx, ny});
                        v[nx][ny] = true;
                    }
                }
            }
        }
        if (count >= 4) {
            for (int[] p : tt)
                pan[p[0]][p[1]] = 0;
            cnt -= count;
            return true;
        }
        return false;
    }
}
