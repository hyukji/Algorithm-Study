package _240708;

import java.util.*;
import java.io.*;

public class BOJ_G3_31885_YunnyTrip {
    static int N, K;
    static long Ex, Ey;
    static List<Point> items;

    static class Point implements Comparable<Point>{
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            if (this.x == o.x) return Long.compare(this.y, o.y);
            return Long.compare(this.x, o.x);
        }
    }
    static int[] dx = {0,-1,0,1,0}, dy = {0,0, 1, 0, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 아이템
        K = Integer.parseInt(st.nextToken()); // 기력

        items = new ArrayList<>();
        long a, b;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            a = Long.parseLong(st.nextToken());
            b = Long.parseLong(st.nextToken());
            items.add(new Point(a, b));
        }

        st = new StringTokenizer(br.readLine());
        Ex = Long.parseLong(st.nextToken());
        Ey = Long.parseLong(st.nextToken());

        long res = Integer.MAX_VALUE;

        res = Math.min(res, Math.abs(Ex) + Math.abs(Ey));

        for (Point p : items) {
            res = Math.min(res, Math.abs(Ex - p.x) + Math.abs(Ey - p.y) + 2);
        }

        for (Point p : items) {
            for (int d = 0; d < 5; d++) {
                long power = d == 0 ? 4 : 5;
                long nx = p.x + dx[d];
                long ny = p.y + dy[d];

                int start = 0, end = N - 1;
                while (start <= end) {
                    int mid = (start + end) / 2;

                    if (check(mid, nx, ny)) {
                        end = mid-1;
                    } else start = mid+1;
                }
                nx += items.get(start).x;
                ny += items.get(start).y;

                if (nx == Ex && ny == Ey) res = Math.min(res, power);
            }
        }
        System.out.println(res > K ? -1 : res);
    }

    static boolean check(long mid, long x, long y) {
        if (items.get((int) mid).x + x == Ex) {
            return items.get((int) mid).y + y >= Ey;
        }
        return items.get((int) mid).x + x > Ex;
    }
}
