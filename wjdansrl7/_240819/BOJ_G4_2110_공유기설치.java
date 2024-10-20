package _240819;

import java.util.*;
import java.io.*;

public class BOJ_G4_2110_공유기설치 {
    static int N, C, result = 1;
    static int[] arr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new int[N];

        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(br.readLine());
        Arrays.sort(arr);

        int low = 1, high = arr[N-1];
        while (low <= high) {
            int dist = arr[0]; // 시작점은 첫 번째 지점부터
            int cnt = 1; // 무조건 시작하는 점에는 공유기가 설치되어 있으므로

            int mid = (low + high) / 2;

            for (int i = 1; i < N; i++) {
                if (arr[i] - dist >= mid) {
                    cnt++;
                    dist = arr[i];
                }
            }

            if (cnt >= C) {
                low = mid + 1;
                result = mid;
            } else {
                high = mid-1;
            }
        }
        System.out.println(result);
    }
}
