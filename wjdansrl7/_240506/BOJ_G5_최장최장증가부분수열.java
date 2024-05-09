package _240506;

import java.util.*;
import java.io.*;

public class BOJ_G5_최장최장증가부분수열 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][N];
        int[][] dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = 1;
            }
        }
        int res = 0;
        for (int i = 0; i < N; i++) for (int j = 0; j < N; j++) for (int k = 0; k < i + 1; k++) for (int l = 0; l < j + 1; l++) if (arr[i][j] > arr[k][l]) dp[i][j] = Math.max(dp[i][j], dp[k][l] + 1);
        for (int i = 0; i < N; i++) for (int j = 0; j < N; j++) res = res < dp[i][j] ? dp[i][j] : res;
        System.out.println(res);
    }
}
