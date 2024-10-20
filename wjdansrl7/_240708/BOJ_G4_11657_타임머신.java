package _240708;

import java.util.*;
import java.io.*;
public class BOJ_G4_11657_타임머신 {
    static int N, M;

    static class Node {
        int to;
        long weight;

        public Node(int to, long weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    static List<Node>[] nodes;
    static long[] dist;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nodes = new List[N + 1];
        dist = new long[N + 1];

        for (int i = 0; i < N + 1; i++) {
            nodes[i] = new ArrayList<>();
        }

        int from, to, weight;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());
            nodes[from].add(new Node(to, weight));
        }

        for (int i = 0; i < N + 1; i++) dist[i] = Integer.MAX_VALUE;
        dist[1] = 0;

        boolean flag = false;
        for (int i = 1; i < N; i++) {
            flag = false;
            for (int j = 1; j < N + 1; j++) {
                for (Node node : nodes[j]) {
                    if (dist[j] == Integer.MAX_VALUE) {
                        break;
                    }
                    if (dist[node.to] > dist[j] + node.weight) {
                        dist[node.to] = dist[j] + node.weight;
                        flag = true;
                    }
                }
            }
            if (!flag) break;
        }

        if (flag) {
            label:
            for (int i = 1; i < N + 1; i++) {
                for (Node node : nodes[i]) {
                    if (dist[i] == Integer.MAX_VALUE) break;
                    if (dist[node.to] > dist[i] + node.weight) {
                        break label;
                    }
                }
            }
        }
        if (bellmanFord()) {
            sb.append(-1).append('\n');
        } else {
            for (int i = 2; i < N + 1; i++) {
                if (dist[i] ==Integer.MAX_VALUE) sb.append(-1).append('\n');
                else sb.append(dist[i]).append('\n');
            }
        }
        System.out.println(sb);
    }

    static boolean bellmanFord() {
        dist[1] = 0; // 시작점은 0으로 초기화.
        boolean flag = false;

        for (int i = 0; i < N; i++) {
            flag = false;

            for (int j = 1; j <= N; j++) {
                for (Node node : nodes[j]) {
                    if (dist[j] == Integer.MAX_VALUE) {
                        break;
                    }

                    if (dist[node.to] > dist[j] + node.weight) {
                        dist[node.to] = dist[j] + node.weight;
                        flag = true;
                    }
                }
            }
            if (!flag) break;
        }

        if (flag) {
            for (int i = 1; i < N + 1; i++) {
                for (Node node : nodes[i]) {
                    if (dist[i] == Integer.MAX_VALUE) {
                        break;
                    }

                    if (dist[node.to] > dist[i] + node.weight) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
