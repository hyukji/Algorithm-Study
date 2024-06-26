package _240628;

import java.io.*;
import java.util.*;
public class BOJ_S3_2559_수열 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        int sum = 0;
        for (int i = 0; i < K; i++) sum += arr[i];
        int res = sum;

        int start = 0, end = K;
        while (end < N) {
            sum += arr[end++];
            sum -= arr[start++];
            if (sum > res) res = sum;
        }
        System.out.println(res);
    }
}
