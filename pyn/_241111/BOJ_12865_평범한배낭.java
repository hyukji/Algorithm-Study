import java.io.*;
import java.util.*;

public class BOJ_12865_평범한배낭 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] things = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            things[i][0] = w;
            things[i][1] = v;
        }

        int[][] dp = new int[N + 1][K + 1];

        for (int k = 1; k <= K; k++) {
            for (int n = 1; n <= N; n++) {
                dp[n][k] = dp[n - 1][k];
                if (k - things[n][0] >= 0) dp[n][k] = Math.max(dp[n - 1][k], things[n][1] + dp[n - 1][k - things[n][0]]);
            }
        }


        System.out.println(dp[N][K]);
        br.close();
    }
}
