package _240226;

import java.io.*;
import java.util.*;

public class BOJ_G5_17070_파이프옮기기1 {
    static int N, res = 0;
    static int[][] arr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }
        // 가로, 세로, 대각선
        DFS(0,1,0);

        System.out.println(res);
    }
    static void DFS(int x, int y, int dir) {
        if(x == N-1 && y == N-1) {
            res++;
            return;
        }
        if(dir == 0) {
            if(isChecked(x, y))
                DFS(x, y+1, dir);
            if(isChecked3(x, y))
                DFS(x+1, y+1, 2);
        } else if(dir == 1) {
            if(isChecked2(x, y))
                DFS(x+1, y, dir);
            if(isChecked3(x, y))
                DFS(x+1, y+1, 2);
        } else if (dir == 2){
            if(isChecked(x, y))
                DFS(x,y+1,0);
            if(isChecked2(x, y))
                DFS(x+1, y, 1);
            if(isChecked3(x, y))
                DFS(x+1, y+1, 2);
        }
    }

    static boolean isChecked(int x, int y) { // 가로
        if (x >= 0 && x < N && y+1 >= 0 && y+1 < N && arr[x][y+1] == 0)
            return true;
        return false;
    }
    static boolean isChecked2(int x, int y) { // 세로
        if (x+1 >= 0 && x+1 < N && y >= 0 && y < N && arr[x+1][y] == 0)
            return true;
        return false;
    }
    static boolean isChecked3(int x, int y) {
        if (x>= 0 && x < N-1 && y>= 0 && y < N-1 && arr[x+1][y] == 0 && arr[x][y+1]==0 && arr[x+1][y+1]==0)
            return true;
        return false;
    }
}
