import java.io.*;
import java.util.*;

public class BOJ_12100_2048Easy {
    static int[][] board;
    static int N;
    static int max = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        play(0);
        System.out.println(max);
    }

    static void play(int cnt) {
        if (cnt == 5) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) max = Math.max(max, board[i][j]);
            }
            return;
        }
        int[][] copy = new int[N][N];
        for (int i = 0; i < N; i++) copy[i] = board[i].clone();
        for (int d = 0; d < 4; d++) {
            if (d == 0) up();
            else if (d == 1) down();
            else if (d == 2) left();
            else right();
            play(cnt + 1);
            for (int i = 0; i < N; i++) board[i] = copy[i].clone();
        }
    }

    static void up() {
        for (int i = 0; i < N; i++) {
            int index = 0;
            int block = 0;
            for (int j = 0; j < N; j++) {
                if (board[j][i] != 0) {
                    if (block == board[j][i]) {
                        board[index - 1][i] = block * 2;
                        block = 0;
                        board[j][i] = 0;
                    } else {
                        block = board[j][i];
                        board[j][i] = 0;
                        board[index][i] = block;
                        index++;
                    }
                }
            }
        }
    }

    static void down() {
        for (int i = 0; i < N; i++) {
            int index = N - 1;
            int block = 0;
            for (int j = N - 1; j >= 0; j--) {
                if (board[j][i] != 0) {
                    if (block == board[j][i]) {
                        board[index + 1][i] = block * 2;
                        block = 0;
                        board[j][i] = 0;
                    } else {
                        block = board[j][i];
                        board[j][i] = 0;
                        board[index][i] = block;
                        index--;
                    }
                }
            }
        }
    }

    static void left() {
        for (int i = 0; i < N; i++) {
            int index = 0;
            int block = 0;
            for (int j = 0; j < N; j++) {
                if (board[i][j] != 0) {
                    if (block == board[i][j]) {
                        board[i][index - 1] = block * 2;
                        block = 0;
                        board[i][j] = 0;
                    } else {
                        block = board[i][j];
                        board[i][j] = 0;
                        board[i][index] = block;
                        index++;
                    }
                }
            }
        }
    }

    static void right() {
        for (int i = 0; i < N; i++) {
            int index = N - 1;
            int block = 0;
            for (int j = N - 1; j >= 0; j--) {
                if (board[i][j] != 0) {
                    if(block == board[i][j]) {
                        board[i][index + 1] = block * 2;
                        block = 0;
                        board[i][j] = 0;
                    } else {
                        block = board[i][j];
                        board[i][j] = 0;
                        board[i][index] = block;
                        index--;
                    }
                }
            }
        }
    }
}
