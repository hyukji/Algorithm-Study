package _240408;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_G4_13422_도둑 {
    static int N, M;
    static long K;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            arr = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++)
                arr[i] = Integer.parseInt(st.nextToken());

            long sum = 0;
            for (int i = 0; i < M - 1; i++)
                sum += arr[i];

            int res = 0;
            int start = 0;
            for (int i = M - 1; i < N + M - 1; i++) {
                sum += arr[i % N];
                if (sum < K) res++;
                sum -= arr[start++];
            }
            if (N == M) {
                sum = 0;
                for (int i = 0; i < N; i++) {
                    sum += arr[i];
                }
                if (sum < K) res = 1;
                else res = 0;
            }
            System.out.println(res);
        }
    }
}

