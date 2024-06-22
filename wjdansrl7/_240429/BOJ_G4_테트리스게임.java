package _240429;

import java.util.*;
import java.io.*;

public class BOJ_G4_테트리스게임 {
    static int N;
    static long res;
    static int[][] arr;

    static int[][] copy(int[][] arr) {
        int[][] tmp = new int[N][N];

        for (int i = 0; i < N; i++) for (int j = 0; j < N; j++) tmp[i][j] = arr[i][j];
        return tmp;
    }
    static int[][] rotation(int[][] arr) {

        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i++) for (int j = 0; j < N; j++) tmp[i][j] = arr[j][N - i - 1];
        return tmp;
    }

    static void test1() {
        long sum;
        int[][] tmp = copy(arr);
        for (int i = 0; i < 4; i++) {
            tmp = rotation(tmp);
            for (int j = 0; j < N; j++) {
                for (int k = 0; k <= N - 4; k++) {
                    sum = tmp[j][k] + tmp[j][k + 1] + tmp[j][k + 2] + tmp[j][k + 3];
                    if(res < sum) res = sum;
                }
            }
        }
    }
    static void test2() {
        long sum;
        int[][] tmp = copy(arr);
        for (int i = 0; i < 4; i++) {
            tmp = rotation(tmp);
            for (int j = 0; j < N - 1; j++) {
                for (int k = 0; k < N - 2; k++) {
                    sum = tmp[j][k] + tmp[j][k+1] + tmp[j + 1][k + 1] + tmp[j + 1][k + 2];
                    if(res < sum) res = sum;
                }
            }
        }
    }

    static void test3() {
        long sum;
        int[][] tmp = copy(arr);
        for (int i = 0; i < 4; i++) {
            tmp = rotation(tmp);
            for (int j = 0; j < N - 1; j++) {
                for (int k = 0; k <= N - 3; k++) {
                    sum = tmp[j][k] + tmp[j][k + 1] + tmp[j][k + 2] + tmp[j + 1][k + 2];
                    if(res < sum) res = sum;
                }
            }
        }
    }

    static void test4() {
        long sum;
        int[][] tmp = copy(arr);
        for (int i = 0; i < 4; i++) {
            tmp = rotation(tmp);
            for (int j = 0; j < N - 1; j++) {
                for (int k = 0; k <= N - 3; k++) {
                    sum = tmp[j][k] + tmp[j][k + 1] + tmp[j][k + 2] + tmp[j + 1][k + 1];
                    if(res < sum) res = sum;
                }
            }
        }
    }

    static void test5() {
        long sum;
        int[][] tmp = copy(arr);
        for (int i = 0; i < 4; i++) {
            tmp = rotation(tmp);
            for (int j = 0; j <= N - 2; j++) {
                for (int k = 0; k <= N - 2; k++){
                    sum = tmp[j][k] + tmp[j + 1][k] + tmp[j][k+1] + tmp[j + 1][k + 1];
                    if(res < sum) res = sum;
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb;
        StringTokenizer st;

        int idx = 1;
        while (true) {
            sb = new StringBuilder();

            N = Integer.parseInt(br.readLine().trim());
            if(N == 0) return;
            arr = new int[N][N];
            res = -Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++)
                    arr[i][j] = Integer.parseInt(st.nextToken());
            }
            test1(); test2(); test3(); test4(); test5();
            sb.append(idx++).append(". ").append(res);
            System.out.println(sb);
        }
    }
}
