package _240429;

import java.io.*;
import java.util.*;

public class BOJ_S2_1654_랜선자르기 {
    static int N, K;
    static long[] arr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new long[K];
        long start = 1, end = -1;
        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            if(end < arr[i]) end = arr[i];
        }

        long result = 0;
        while (start <= end) {
            long mid = (start + end) / 2;
            long cnt = 0;

            for (int i = 0; i < K; i++) cnt += arr[i] / mid;

            if(cnt >= N) {
                if(result < mid) result = mid;
                start = mid+1;
            } else end = mid-1;
        }
        System.out.println(result);
    }
}
