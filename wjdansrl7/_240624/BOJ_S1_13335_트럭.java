package _240624;

import java.util.*;
import java.io.*;

public class BOJ_S1_13335_트럭 {
    static int n, w, l;
    static int[] truck;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        truck = new int[n];

        ArrayDeque<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < w; i++) q.offer(0);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) truck[i] = Integer.parseInt(st.nextToken());

        int time = 0, sum = 0, idx = 0;
        while (!q.isEmpty()) {
            sum -= q.poll();

            if (idx < n) {
                if (sum + truck[idx] <= l) {
                    q.offer(truck[idx]);
                    sum += truck[idx++];
                } else {
                    q.offer(0);
                }
            }
            time++;
        }
        System.out.println(time);
    }
}
