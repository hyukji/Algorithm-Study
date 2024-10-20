package _240708;

import java.util.*;
import java.io.*;

public class BOJ_G4_7662_이중우선순위큐 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            int k = Integer.parseInt(br.readLine());
            sb = new StringBuilder();
            TreeMap<Integer, Integer> map = new TreeMap<>();
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());

                String str = st.nextToken();
                int value = Integer.parseInt(st.nextToken());

                if (str.equals("I")) {
                    map.put(value, map.getOrDefault(value, 0) + 1);
                } else {
                    if (map.size()==0) continue;
                    if (value == 1) {
                        int num = map.lastKey();
                        if (map.get(num) > 1) map.put(num, map.get(num) - 1);
                        else map.remove(num);
                    } else {
                        int num = map.firstKey();
                        if (map.get(num) > 1) map.put(num, map.get(num) - 1);
                        else map.remove(num);
                    }
                }
            }
            if (map.size() == 0) sb.append("EMPTY");
            else sb.append(map.lastKey() + " " + map.firstKey());
            System.out.println(sb);
        }

    }
}
