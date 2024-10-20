import java.io.*;
import java.util.*;

public class BOJ_12787_지금밥이문제냐 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            if (M == 1) {
                long binary = 0;
                String ip = st.nextToken();
//                System.out.println("이게 IPv8 주소야: " + ip);
                String[] ip8 = ip.split("\\.");
//                System.out.println(Arrays.toString(ip8));
                for (int i = 0; i < 8; i++) {
                    long num = Long.parseLong(ip8[i]);
                    binary = (binary << 8) | num;
                }
                sb.append(binary).append('\n');
            } else {
                long num = Long.parseLong(st.nextToken());
                StringBuilder ip = new StringBuilder();
                for(int i=7; i>=0; i--) {
                    long bin = (num >> (i * 8)) & 0xFF;
                    ip.append(bin);
                    if (i > 0) ip.append(".");
                }

                sb.append(ip).append('\n');
            }
        }
        System.out.println(sb);
        br.close();
    }
}
