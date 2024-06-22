package _240506;

import java.util.*;
import java.io.*;

public class BOJ_G4_가장긴등차부분수열 {
    static int N;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        int[] dp = new int[101];
        int res = 0;
        for (int i = -99; i < 100; i++) {
            for (int j = 0; j < 101; j++) dp[j] = 0;

            for (int j = 0; j < N; j++) {
                if(arr[j] - i < 1 || arr[j] - i > 100) dp[arr[j]] = 1;
                else dp[arr[j]] = dp[arr[j]-i] + 1;

                res = res < dp[arr[j]] ? dp[arr[j]] : res;
            }
        }
        System.out.println(res);
    }
}
