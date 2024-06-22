package _240429;

/**
 * packageName    : _240429
 * fileName       : BOJ_S2_10799_쇠막대기
 * author         : moongi
 * date           : 4/16/24
 * description    :
 */

import java.io.*;
import java.util.*;

/**
 * ()  (((()())(())()))  (())
 */
public class BOJ_S2_10799_쇠막대기 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String str = br.readLine();

        int res = 0;
        char last = '(';
        ArrayDeque<Character> q = new ArrayDeque<>();
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '(') {
                q.offer('(');
                last = '(';
            }
            else if(str.charAt(i) == ')') {
                q.pop();
                if(last == '(') {
                    res += q.size();
                } else res += 1;
                last = ')';
            }
        }

        System.out.println(res);
    }
}
