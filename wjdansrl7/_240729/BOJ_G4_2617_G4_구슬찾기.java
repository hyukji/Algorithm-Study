package _240729;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * packageName    : _240729
 * fileName       : BOJ_G4_2617_G4_구슬찾기
 * author         : moongi
 * date           : 7/29/24
 * description    : 플로이드 워셜 알고리즘을 사용하는 문제( O(N^3) )
 */
public class BOJ_G4_2617_G4_구슬찾기 {
    static int N, M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N + 1][N + 1];
        int x, y;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            arr[x][y] = 1;
            arr[y][x] = -1;
        }

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                for (int k = 1; k < N + 1; k++) {
                    if (arr[j][i] != 0 && arr[j][i] == arr[i][k]) arr[j][k] = arr[j][i];
                }
            }
        }

        int half = (N+1) / 2;
        int[] up = new int[N + 1];
        int[] down = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (arr[i][j] == 1) up[i]++;
                if (arr[i][j] == -1) down[i]++;
            }
        }

        int res = 0;
        for (int i = 1; i < N + 1; i++) {
            if (up[i] >= half) res++;
            if (down[i] >= half) res++;
        }
        System.out.println(res);
    }
}
