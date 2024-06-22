package _240506;

import java.util.*;
import java.io.*;

public class BOJ_G5_20207_달력 {
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        int start = 0, end = 0;
        int[] count = new int[367];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());

            for (int j = start; j <= end; j++) count[j]++;
        }
        int res = 0;
        int l = 0, r = 0, h = 0;
        for (int i = 1; i < 366; i++) {
            if(count[i]>0) {
                l = i;
                h = count[i];
                for (int j = i+1; j <= 366; j++) {
                    if(count[j] == 0) {
                        r = j;
                        i = j;
                        break;
                    }
                    h = h < count[j] ? count[j] : h;
                }
            }
            res += h * (r - l);
            l = 0;
            r = 0;
            h = 0;
        }
        System.out.println(res);
    }
}
