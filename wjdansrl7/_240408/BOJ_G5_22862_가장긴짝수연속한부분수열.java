package _240408;

import java.io.*;
import java.util.*;

public class BOJ_G5_22862_가장긴짝수연속한부분수열 {
    static int N, K;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int l = 0, r = 0;
        int cnt = 0, res = 0;
        while (r < N) {
            if (cnt > K) {
                if ((arr[l] & 1) == 1) cnt--;
                l++;
                continue;
            }
            if ((arr[r] & 1) == 1) cnt++;
            else res = res < r - l + 1 - cnt ? r - l + 1 - cnt : res;
            r++;
        }
        System.out.println(res);
    }
}

