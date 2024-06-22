package _240624;

import java.util.*;
import java.io.*;

public class BOJ_G3_24337_가희와탑 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        List<Integer> building = new ArrayList<>();

        for (int i = 1; i < a; i++) building.add(i);
        building.add(Math.max(a, b));

        for (int i = b-1; i > 0; i--) building.add(i);

        for (int i = 0; i < N + 1 - a - b; i++) building.add(1, 1);

        if (a + b - 1 > N) sb.append(-1);
        else for (Integer h : building) sb.append(h).append(" ");
        System.out.println(sb);
    }
}
