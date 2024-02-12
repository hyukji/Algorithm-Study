package _240211;

import java.io.*;
import java.util.*;

public class BOJ_G4_17144_미세먼지안녕 {
    static int R, C, T;
    static int[][] arr;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        arr = new int[R][C]; // 집의 크기 설정
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < T; i++) {
            spreadDust();
            airCondition();
        }

        int res = 0;
        for (int i = 0; i < R; i++)
            for (int j = 0; j < C; j++)
                res += arr[i][j];
        System.out.println(res+2); // 모든 미세먼지의 값을 res에 더할 때, 공기청정기가 -1이므로 +2한다.
    }
    /*
    (r, c)에 있는 미세먼지는 인접한 네 방향으로 확산된다.
    인접한 방향에 공기청정기가 있거나, 칸이 없으면 그 방향으로는 확산이 일어나지 않는다.
    확산되는 양은 Ar,c/5이고 소수점은 버린다. 즉, ⌊Ar,c/5⌋이다.
    (r, c)에 남은 미세먼지의 양은 Ar,c - ⌊Ar,c/5⌋×(확산된 방향의 개수) 이다.
     */
    static void spreadDust() {
        int[][] tmp = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] > 0) {
                    int v = arr[i][j];
                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if (nx >= 0 && nx < R && ny >= 0 && ny < C && arr[nx][ny] >= 0) {
                            v -= arr[i][j]/5;
                            tmp[nx][ny] += arr[i][j] / 5;
                        }
                    }
                    arr[i][j] = v;
                }
            }
        }
        for (int i = 0; i < R; i++)
            for (int j = 0; j < C; j++)
                arr[i][j] += tmp[i][j];
    }
    /*
    공기청정기가 작동한다.
    공기청정기에서는 바람이 나온다.
    위쪽 공기청정기의 바람은 반시계방향으로 순환하고, 아래쪽 공기청정기의 바람은 시계방향으로 순환한다.
    바람이 불면 미세먼지가 바람의 방향대로 모두 한 칸씩 이동한다.
    공기청정기에서 부는 바람은 미세먼지가 없는 바람이고, 공기청정기로 들어간 미세먼지는 모두 정화된다.
     */
    static void airCondition() {
        int uX = 0, uY = 0, dX = 0, dY = 0;
        for (int i = 0; i < R; i++) {
            if(arr[i][0] == -1) {
                uX = i; // 위쪽 공기청정기의 바람 x
                uY = 0; // 위쪽 공기청정기의 바람 y
                dX = i+1; // 아래쪽 공기청정기의 바람 x
                dY = 0; // 아래쪽 공기청정기의 바람 y
                break;
            }
        }

        dx = new int[]{0, -1, 0, 1}; dy = new int[]{1, 0, -1, 0}; // 반시계방향
        // 덮어씌워지는 값들 tmp에 저장
        int tmp1 = arr[uX][C - 1];
        int tmp2 = arr[0][C - 1];
        int tmp3 = arr[0][0];

        // 오른쪽
        for (int i = C-1; i > 1; i--)
            arr[uX][i] = arr[uX][i - 1];
        arr[uX][1] = 0;

        // 위쪽
        for (int i = 0; i < uX-1; i++)
            arr[i][C - 1] = arr[i + 1][C - 1];
        arr[uX - 1][C - 1] = tmp1;

        // 왼쪽
        for (int i = 0; i < C - 2; i++)
            arr[0][i] = arr[0][i + 1];
        arr[0][C - 2] = tmp2;

        // 아래쪽
        for (int i = uX-1; i > 1; i--)
            arr[i][0] = arr[i - 1][0];
        arr[1][0] = tmp3;

        dx = new int[]{0, 1, 0, -1}; dy = new int[]{1, 0, -1, 0}; // 시계방향
        // 덮어씌워지는 값들 tmp에 저장
        tmp1 = arr[dX][C - 1];
        tmp2 = arr[R - 1][C - 1];
        tmp3 = arr[R - 1][0];

        // 오른쪽
        for (int i = C-1; i > 1; i--)
            arr[dX][i] = arr[dX][i - 1];
        arr[dX][1] = 0;

        // 아래쪽
        for (int i = R-1; i > dX+1; i--)
            arr[i][C - 1] = arr[i - 1][C - 1];
        arr[dX+1][C - 1] = tmp1;

        // 왼쪽
        for (int i = 0; i < C - 2; i++)
            arr[R - 1][i] = arr[R - 1][i + 1];
        arr[R - 1][C - 2] = tmp2;

        // 위쪽
        for (int i = dX+1; i < R - 2; i++)
            arr[i][0] = arr[i + 1][0];
        arr[R - 2][0] = tmp3;
    }
}
