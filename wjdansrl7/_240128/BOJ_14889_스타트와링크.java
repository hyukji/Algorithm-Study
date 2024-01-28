package _240128;

import java.io.*;
import java.util.*;

/**
 * packageName    : _240128
 * fileName       : BOJ_14889_스타트와링크
 * author         : moongi
 * date           : 1/28/24
 * description    :
 */
public class BOJ_14889_스타트와링크 {
    static int n;
    static int answer = Integer.MAX_VALUE;
    static int[][] arr = new int[21][21];
    static boolean[] visited;
    static void Combination(int start, int num) {
        if (num == n / 2) {
            solution(visited);
            return;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            Combination(i + 1, num + 1);
            visited[i] = false;
        }
    }

    static void solution(boolean[] visited) {

        int[] red = new int[n / 2];
        int rIdx = 0, bIdx = 0;
        int[] blue = new int[n / 2];

        for (int i = 0; i < n; i++) {
            if(visited[i]) {
                red[rIdx++] = i;
            } else {
                blue[bIdx++] = i;
            }
        }

        int rSum = 0, bSum = 0;
        for(int i : red) {
            for(int j : red) {
                rSum += arr[i][j] + arr[j][i];
            }
        }

        for(int i : blue) {
            for (int j : blue) {
                bSum += arr[i][j] + arr[j][i];
            }
        }
        if(answer > Math.abs((rSum - bSum) / 2))
            answer = Math.abs((rSum - bSum) / 2);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb;

        n = Integer.parseInt(br.readLine());
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Combination(0, 0);

        System.out.println(answer);

    }
}
