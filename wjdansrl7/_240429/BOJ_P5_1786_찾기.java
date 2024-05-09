package _240429;

import java.util.*;
import java.io.*;

public class BOJ_P5_1786_찾기 {
    static String T, P;
    static int cnt = 0;
    static List<Integer> li;

    static int[] getPi(String p) {
        int pLen = p.length();
        int[] pi = new int[pLen];

        for (int i = 1, j = 0; i < pLen; i++) {
            while (j > 0 && p.charAt(i) != p.charAt(j)) j = pi[j - 1];
            if (p.charAt(i) == p.charAt(j)) pi[i] = ++j;
        }
        return pi;
    }

    static void KMP() {
        int[] pi = getPi(P);

        int tLen = T.length();
        int pLen = P.length();

        for (int i = 0, j = 0; i < tLen; i++) {
            while (j > 0 && T.charAt(i) != P.charAt(j)) j = pi[j - 1];

            if (T.charAt(i) == P.charAt(j)) {
                if (j == pLen - 1) {
                    ++cnt;
                    li.add(i - j + 1);
                    j = pi[j];
                } else j++;
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = br.readLine();
        P = br.readLine();

        li = new ArrayList<>();

        KMP();
        sb.append(cnt).append('\n');
        for (int i = 0; i < cnt; i++) sb.append(li.get(i)).append(' ');
        System.out.println(sb);
    }
}
