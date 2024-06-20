package _240624;

import java.io.*;
import java.util.*;

public class BOJ_G3_1774_우주신과의교감_kruskal {
    static int N, M;

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class Node implements Comparable<Node>{
        int from, to;
        double dist;

        public Node(int from, int to, double dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }
        @Override
        public int compareTo(Node o) {
            return Double.compare(this.dist, o.dist);
        }
    }
    static int find(int a) {
        if (p[a] == a) return a;
        return p[a] = find(p[a]);
    }
    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) return false;

        p[bRoot] = aRoot;
        return true;
    }
    static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }
    static int[] p;
    static Point[] points;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        points = new Point[N + 1];
        p = new int[N + 1];
        for (int i = 0; i < N + 1; i++) p[i] = i;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            points[i + 1] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 1; i < N + 1; i++) {
            for (int j = i+1; j < N + 1; j++) {
                double dist = distance(points[i], points[j]);
                pq.offer(new Node(i, j, dist));
            }
        }
        double res = 0;
        while (!pq.isEmpty()) {
            Node p = pq.poll();

            if (union(p.from, p.to)) {
                res += p.dist;
            }
        }
        System.out.printf("%.2f", res);
    }
}
