package _240628;

import java.util.*;
import java.io.*;

public class ST_L3_6291_강의실배정 {
    static class Lecture implements Comparable<Lecture>{
        int start, end;

        public Lecture(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Lecture o) {
            if(this.end == o.end) return this.start - o.start;
            return this.end - o.end;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        int a, b;

        List<Lecture> lectures = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            lectures.add(new Lecture(a, b));
        }
        Collections.sort(lectures);

        int curTime = lectures.get(0).end, res = 1;
        for(Lecture l : lectures) {
            if(curTime <= l.start) {
                res++;
                curTime = l.end;
            }
        }
        System.out.println(res);
    }
}
