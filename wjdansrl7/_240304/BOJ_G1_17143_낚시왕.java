package _240304;

import java.util.*;
import java.io.*;

public class BOJ_G1_17143_낚시왕 {
    static class Shark {
        int r, c, s, d, z;
        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }
    static int R, C, M, kingY = -1, res = 0;
    static int[] dx = {-1,1,0,0}, dy = {0, 0, 1, -1};
    static Shark[] sharkInfo;
    static int[][] map;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        sharkInfo = new Shark[M+1];
        int r, c, s, d, z; // (r,c) 상어의 위치, s: 속력, d: 이동 방향, z: 크기
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            sharkInfo[i] = new Shark(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken()));
            map[sharkInfo[i].r][sharkInfo[i].c] = i;
        }
        while (++kingY < C) {
            eat(); // 가장 가까운 상어를 잡는다.
            move();
        }
        System.out.println(res);
        br.close();
    }
    private static void move() {
        for (int i = 1; i <= M; i++) {
            if(sharkInfo[i] == null) continue; // 이미 없는 상어
            // 이동할 위치
            int nd = sharkInfo[i].d;

            if(nd == 0 || nd == 1) { // 상하
                sharkInfo[i].s %= (R-1) * 2;
            } else if(nd == 2 || nd==3) {
                sharkInfo[i].s %= (C-1) * 2;
            }
            for (int j = 0; j < sharkInfo[i].s; j++) {
                int nx = sharkInfo[i].r + dx[sharkInfo[i].d];
                int ny = sharkInfo[i].c + dy[sharkInfo[i].d];

                if(nx < 0 || nx >= R || ny < 0 || ny>= C) {
                    sharkInfo[i].r -= dx[sharkInfo[i].d];
                    sharkInfo[i].c -= dy[sharkInfo[i].d];

                    if(nd == 0) nd = 1;
                    else if(nd==1) nd = 0;
                    else if(nd==2) nd = 3;
                    else if(nd==3) nd = 2;
                    sharkInfo[i].d = nd;

                    continue;
                }
                sharkInfo[i].r = nx;
                sharkInfo[i].c = ny;
            }
        }
        // 상어들의 이동이 끝난 후, 상어들이 중복되는 위치에 있으면 상어들끼리의 싸움이 시작된다./
        for (int i = 0; i < R; i++) {
            Arrays.fill(map[i], 0);
        }
        for (int i = 1; i <= M; i++) {
            if(sharkInfo[i]==null) continue;
            if(map[sharkInfo[i].r][sharkInfo[i].c] == 0)
                map[sharkInfo[i].r][sharkInfo[i].c] = i;
            else {
                int preSize = sharkInfo[map[sharkInfo[i].r][sharkInfo[i].c]].z;
                if(preSize < sharkInfo[i].z) {
                    sharkInfo[map[sharkInfo[i].r][sharkInfo[i].c]] = null;
                    map[sharkInfo[i].r][sharkInfo[i].c] = i;
                } else {
                    sharkInfo[i] = null;
                }
            }
        }
    }
    private static void eat() {
        for (int i = 0; i < R; i++) {
            if(map[i][kingY] != 0) {
                res += sharkInfo[map[i][kingY]].z;
                sharkInfo[map[i][kingY]] = null;
                map[i][kingY] = 0;
                break;
            }
        }
    }
}
