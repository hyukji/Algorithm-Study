import java.io.*;
import java.util.*;

public class BOJ_2143_두배열의합 {
    static int m;
    static int[] sumB;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n + 1];
//        int[] sumA = new int[n + 1];
        int[] sumA = new int[n * (n + 1) / 2];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
//            sumA[i] = sumA[i - 1] + A[i];
        }

        int index = 0;
        for (int i = 1; i <= n; i++) {
            int sum = 0;
            for (int j = i; j <= n; j++) {
                sum += A[j];
                sumA[index++] = sum;
            }
        }

        m = Integer.parseInt(br.readLine());
        int[] B = new int[m + 1];
//        sumB = new int[m + 1];
        HashMap<Integer, Integer> sumB = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            B[i] = Integer.parseInt(st.nextToken());
//            sumB[i] = sumB[i - 1] + B[i];
        }

        for (int i = 1; i <= m; i++) {
            int sum = 0;
            for (int j = i; j <= m; j++) {
                sum += B[j];
                if (sumB.containsKey(sum)) {
                    sumB.put(sum, sumB.get(sum) + 1);
                } else {
                    sumB.put(sum, 1);
                }
            }
        }

        long count = 0;
        for (int i = 0; i < sumA.length; i++) {
            if (sumB.containsKey(T - sumA[i])) {
                count += sumB.get(T - sumA[i]);
            }
        }

//        int count=0;
//        for(int i=0; i<sumA.length; i++){
//            count+=findB(T-sumA[i]);
//        }

//        int count = 0;
//        int start = 1;
//        int end = 1;
//        while (true) {
//            if (start > n) break;
//            if (end > n) {
//                start++;
//                end = start;
//            } else {
//                if (sumA[end] - sumA[start - 1] >= T) {
//                    start++;
//                    end = start;
//                } else {
//                    count += findB(T - (sumA[end] - sumA[start - 1]));
//                    end++;
//                }
//            }
//        }
        System.out.println(count);
        br.close();
    }

//    static int findB(int remain) {
//        int cnt = 0;
//
//        int start = 1;
//        int end = 1;
//
//        while (true) {
//            if (start > m) break;
//            if (end > m) {
//                start++;
//                end = start;
//            } else {
//                if (sumB[end] - sumB[start - 1] > remain) {
//                    start++;
//                    end = start;
//                } else if (sumB[end] - sumB[start - 1] < remain) end++;
//                else {
//                    cnt++;
//                    start++;
//                    end = start;
//                }
//            }
//        }
//
//        return cnt;
//    }
}
