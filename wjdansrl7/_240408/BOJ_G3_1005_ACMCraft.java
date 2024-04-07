package _240408;
import java.util.*;
import java.io.*;

// 위상 정렬 이용
public class BOJ_G3_1005_ACMCraft {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N, K, W;
        int[] edgeCount, buildingTime, result;
        List<ArrayList<Integer>> graph;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            edgeCount = new int[N + 1];
            buildingTime = new int[N + 1];
            result = new int[N + 1];
            graph = new ArrayList<ArrayList<Integer>>();
            for (int i = 0; i < N + 1; i++) graph.add(new ArrayList<Integer>());

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < N + 1; i++) buildingTime[i] = Integer.parseInt(st.nextToken());

            int x, y;
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                graph.get(x).add(y);
                edgeCount[y]++;
            }

            W = Integer.parseInt(br.readLine());

            ArrayDeque<Integer> q = new ArrayDeque<>();
            for (int i = 1; i < N + 1; i++) {
                if (edgeCount[i] == 0) {
                    q.offer(i);
                    result[i] = buildingTime[i];
                }
            }

            while (!q.isEmpty()) {
                int p = q.poll();

                for (int t : graph.get(p)) {
                    edgeCount[t]--;

                    if (edgeCount[t] == 0) q.offer(t);
                    result[t] = Math.max(result[t], buildingTime[t] + result[p]);
                }
            }
            System.out.println(result[W]);
        }
    }
}
