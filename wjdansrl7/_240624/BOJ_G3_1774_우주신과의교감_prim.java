package _240624;

import java.util.*;
import java.io.*;

public class BOJ_G3_1774_우주신과의교감_prim {
    static int N, M;

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Node implements Comparable<Node>{
        int to;
        double dist;

        public Node(int to, double dist) {
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare(this.dist, o.dist);
        }
    }

    static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    static Point[] points;
    static List<Node>[] nodes;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        points = new Point[N + 1];
        nodes = new List[N + 1];
        for (int i = 0; i < N + 1; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 1; i < N + 1; i++) {
            for (int j = i+1; j < N + 1; j++) {
                double dist = distance(points[i], points[j]);
                nodes[i].add(new Node(j, dist));
                nodes[j].add(new Node(i, dist));
            }
        }

        int a, b;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            for (Node node : nodes[a]) if (node.to == b) node.dist = 0;
            for (Node node : nodes[b]) if (node.to == a) node.dist = 0;
        }

        double[] d = new double[N + 1];
        double res = 0;
        for (int i = 0; i < N+1; i++) d[i] = Double.MAX_VALUE;
        d[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N + 1];
        pq.offer(new Node(1, d[1]));

        while (!pq.isEmpty()) {
            Node p = pq.poll();

            if (visited[p.to]) continue;

            visited[p.to] = true;
            res += p.dist;

            for (Node node : nodes[p.to]) {
                if (!visited[node.to]) {
                    pq.offer(node);
                }
            }
        }
        System.out.printf("%.2f", res);
    }
}
