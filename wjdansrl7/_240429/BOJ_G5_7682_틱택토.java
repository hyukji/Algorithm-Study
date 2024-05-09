package _240429;

import java.util.*;
import java.io.*;

/**
 * 빈 칸이 있을 경우, col, row, cross 를 통해 확인
 * xCount - yCount == 1 or xCount == yCount
 * 예외처리하려면 X, O 둘 다 구해야함.
 *
 * 말이 다른 경우, X가 승리해야한다
 * 말의 개수가 같은 경우, O가 승리해야 한다.
 * invalid한 경우
 * 모두 채우지 않았는데 개수가 xCount <= 2 && yCount <= 2
 * 모두 채우지 않았고, 게임이 성공하지 않은 경우
 * 모두 채우지 않았고, 게임은 성공했지만, 자신의 말이 아닌 다른 말도 게임에서 성공한 경우
 * 모두 채웠는데, 자신의 말이 아닌 다른 말이 게임에서 승리한 경우
 */
public class BOJ_G5_7682_틱택토 {
    static boolean rowValid(char m) {
        for (int i = 0; i < 3; i++) {
            int cnt = 0;
            if(arr[i][0] != m) continue;

            for (int j = 0; j < 3; j++) {
                if(arr[i][j] == m) cnt++;
            }

            if(cnt == 3) return true;
        }
        return false;
    }

    static boolean crossValid(char m) {
        int cnt = 0;
        for (int i = 0; i < 3; i++) {
            if (arr[i][i] == m) cnt++;
        }

        if (cnt == 3) return true;

        cnt = 0;

        for (int i = 0; i < 3; i++) {
            if(arr[i][2-i] == m) cnt++;
        }

        if(cnt == 3) return true;

        return false;
    }

    static boolean colValid(char m) {
        for (int i = 0; i < 3; i++) {
            int cnt = 0;
            if(arr[0][i] != m) continue;

            for (int j = 0; j < 3; j++) {
                if(arr[j][i] == m) cnt++;
            }

            if(cnt == 3) return true;
        }
        return false;
    }

    static int xCount, yCount;
    static boolean flag;
    static char[][] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        arr = new char[3][3];
        while (true) {

            String str = br.readLine();

            flag = false;
            xCount = 0;
            yCount = 0;

            if (str.equals("end")) return;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    arr[i][j] = str.charAt(i * 3 + j);

                    if (arr[i][j] == 'X') xCount++;
                    else if(arr[i][j] == 'O') yCount++;
                }
            }
            // 1. 차이가 1을 넘어가거나, O의 개수가 더 많은 경우
            if(xCount - yCount > 1 || xCount < yCount) {
                flag = true;
            }

            if(flag) {
                System.out.println("invalid");
                continue;
            }

            if(xCount == 5 && yCount == 4) {
                // 모두 채운 경우
                if (rowValid('O') || colValid('O') || crossValid('O')) {
                    // true가 나올 경우, invalid한 경우
                    System.out.println("invalid");
                } else {
                    // valid
                    System.out.println("valid");
                }
            } else {
                // 모두 채우지 못한 경우
                boolean xx = false, yy = false;
                if(rowValid('X') || colValid('X') || crossValid('X')) {
                    xx = true;
                }
                if(rowValid('O') || colValid('O') || crossValid('O')) {
                    yy = true;
                }

                // xCount - yCount = 1
                if(xCount - yCount == 1) {
                    if(xx && !yy) System.out.println("valid");
                    else System.out.println("invalid");
                }
                if(xCount == yCount) {
                    if(!xx && yy) System.out.println("valid");
                    else System.out.println("invalid");
                }
            }
        }
    }
}
