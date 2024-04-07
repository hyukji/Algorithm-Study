package _240408;

import java.util.*;
import java.io.*;

public class BOJ_G3_16437_양구출작전2 {
    static int N;
    static class Island {
        char menu;
        long count;

        public Island(char menu, long count) {
            this.menu = menu;
            this.count = count;
        }
    }

    static Island[] islands;
    static List<ArrayList<Integer>> graph;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i < N+1; i++) graph.add(new ArrayList<>());
        islands = new Island[N + 1];
        islands[1] = new Island('S', 0);

        for (int i = 2; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            islands[i] = new Island(st.nextToken().charAt(0), Long.parseLong(st.nextToken()));
            graph.get(Integer.parseInt(st.nextToken())).add(i);
        }
        System.out.println(dfs(1));
    }

    static long dfs(int idx) {
        long sum = 0;

        for (int nxt : graph.get(idx)) sum += dfs(nxt);

        if (islands[idx].menu == 'S') return sum + islands[idx].count;
        else return sum - islands[idx].count < 0 ? 0 : sum - islands[idx].count;
    }
}
