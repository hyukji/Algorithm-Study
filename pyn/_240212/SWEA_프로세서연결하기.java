package a0228;

import java.io.*;
import java.util.*;
 
public class Solution_swea_1767_프로세서연결하기_서울_20반_박영남 {
    static int[] di = { -1, 0, 1, 0 }; // 상우하좌
    static int[] dj = { 0, 1, 0, -1 };
    static int N, max, min;
    static int[][] map;
    static List<int[]> core;
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
 
        int T = Integer.parseInt(br.readLine());
 
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            core = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 1) {
                        if (i == 0 || i == N - 1 || j == 0 || j == N - 1) continue;
                        core.add(new int[] { i, j });
                    }
                }
            }
            max = 0;
            min = Integer.MAX_VALUE;
            connectCore(0, 0, 0);
 
            sb.append("#").append(tc).append(" ").append(min).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
 
    static void fillLine(int i, int j, int d, int count, int line) {
        int ni = i;
        int nj = j;
        for (int c = 0; c < count; c++) {
            ni += di[d];
            nj += dj[d];
            map[ni][nj] = line;
        }
    }
 
    static void connectCore(int num, int countCore, int countLine) {
        if (num == core.size()) {
            if (countCore > max) {
                max = countCore;
                min = countLine;
            } else if (countCore == max) min = Math.min(min, countLine);
            return;
        }
        int i = core.get(num)[0];
        int j = core.get(num)[1];
 
        for (int d = 0; d < 4; d++) {
            int count = 0;
            int ni = i;
            int nj = j;
 
            while (true) {
                ni += di[d];
                nj += dj[d];
 
                if (ni < 0 || ni >= N || nj < 0 || nj >= N) break;
                if (map[ni][nj] == 1) {
                    count = 0;
                    break;
                }
                count++;
            }
            if (count == 0)
                connectCore(num + 1, countCore, countLine);
            else {
                fillLine(i, j, d, count, 1);
                connectCore(num + 1, countCore + 1, countLine + count);
                fillLine(i, j, d, count, 0);
            }
        }
 
    }
}