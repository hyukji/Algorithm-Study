package _240628;

import java.util.*;
import java.io.*;

public class ST_L3_6292_수퍼바이러스 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        long N = Long.parseLong(st.nextToken());

        N *= 10;

        long res = spread(P, N);
        System.out.println(K * res % 1000000007);
    }

    static long spread(int p, long n) {
        if (n == 1) return p;

        long half = spread(p, n/2);

        if (n % 2 == 0) return half * half % 1000000007;
        else return (half * half % 1000000007) * p % 1000000007;
    }
}
