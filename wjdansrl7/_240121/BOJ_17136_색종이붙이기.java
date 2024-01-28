package _240121;
import java.io.*;
import java.util.*;

/**
 * packageName    : _240121
 * fileName       : BOJ_17136_색종이붙이기
 * author         : moongi
 * date           : 1/21/24
 * description    :
 */

public class BOJ_17136_색종이붙이기 {

    static int[][] pan = new int[11][11];
    static boolean[][] visited = new boolean[11][11];
    static int res = 0;
    static int tmp = 0, tmp1 = 0;
    static int[] menu = new int[5];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 1; i <= 10; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= 10; j++) {
                pan[i][j] = Integer.parseInt(st.nextToken());
                if (pan[i][j] == 1) {
                    tmp1++;
                    tmp++;
                    res++;
                }
            }
        }

        for (int i = 5; i >= 2; i--) {
            solution(i);
        }

        if (res == tmp1 && res > 5) {
            System.out.println(-1);
        } else {
            System.out.println(res);
        }
    }

    static void solution(int L) {

        int m = 0; // L*L의 개수
        a: for (int i = 1; i <= 10 - L + 1; i++) {
            label:
            for (int j = 1; j <= 10 - L + 1; j++) {
                if (m == 5) {
                    break a;
                }
                int cnt = 0;
                for (int k = i; k < i + L; k++) {
                    for (int l = j; l < j + L; l++) {
                        if (pan[k][l] != 1 || visited[k][l]) {
                            continue label;
                        }
                        cnt++;
                    }
                }

                if (cnt == L * L) {
                    m++;
                    for (int k = i; k < i + L; k++) {
                        for (int l = j; l < j + L; l++) {
                            visited[k][l] = true;
                        }
                    }
                }
            }
        }
        int ans = tmp;
        ans = ans - m * ((L * L) - 1);
        if (res > ans) {
            res = ans;
            tmp = ans;
        }
    }
}

