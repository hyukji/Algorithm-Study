import java.io.*;
import java.util.*;

public class BOJ_17425_약수의합 {
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();

        long[] f = new long[1000001];
        long[] g = new long[1000001];

        Arrays.fill(f,1);

        for(int i=2; i<1000001; i++){
            for(int j=1; i*j<1000001; j++){
                f[i*j]+=i;
            }
        }

        for(int i=1; i<1000001; i++) g[i] += g[i-1] + f[i];

        int T=Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++){
            int N=Integer.parseInt(br.readLine());

            sb.append(g[N]).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}
