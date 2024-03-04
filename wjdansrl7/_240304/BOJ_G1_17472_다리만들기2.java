package _240304;

import java.util.*;
import java.io.*;

public class BOJ_G1_17472_다리만들기2 {
    static int n, m, size = 1;
    static int[][] arr, map, w;
    static int[] minEdge;
    static boolean[] v;
    static List<int[]> [] island = new List[7];
    static int[] dx = {-1,0,1,0}, dy = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        map = new int[n][m];

        for (int i = 0; i < 7; i++)
            island[i] = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 섬들의 번호 붙이기
        BFS();
        w = new int[size][size];
        minEdge = new int[size];

        for (int i = 1; i < size; i++) {
            for (int j = 1; j < size; j++) {
                w[i][j] = 13;
            }
            minEdge[i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i < size; i++) {
            DFS(i);
        }

        int result = 0, cnt = 0;
        minEdge[1] = 0;

        v = new boolean[size];
        for (int i = 1; i < size; i++) {
            int min = Integer.MAX_VALUE;
            int minVertex = -1;
            for (int j = 1; j < size; j++) {
                if(!v[j] && min > minEdge[j]) {
                    minVertex = j;
                    min = minEdge[j];
                }
            }

            if(minVertex== -1) break;
            v[minVertex] = true;
            result += min;
            if(cnt++ == size-2) {
                break;
            }
            for (int j = 1; j < size; j++) {
                if (!v[j] && w[minVertex][j] != 13 && minEdge[j] > w[minVertex][j]) {
                    minEdge[j] = w[minVertex][j];
                }
            }
        }
        boolean flag = false;
        for (int i = 1; i < size; i++) {
            if(!v[i]) {
                flag = true;
                break;
            }
        }
        if(result == 0) flag = true;

        if(flag) System.out.println(-1);
        else System.out.println(result);
        br.close();
    }
    private static void DFS(int start) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(map[i][j]==start) {
                    for (int d = 0; d < 4; d++) {
                        int x = i;
                        int y = j;
                        int dist = 0;
                        while(true) {
                            x += dx[d];
                            y += dy[d];

                            if(x >= 0 && x < n && y >= 0  && y < m && map[x][y] != start) {
                                if(arr[x][y] == 0) dist++; // 항해를 계속하자
                                else if(arr[x][y] == 1) { // 신대륙 발견!
                                    if(dist < 2) break;
                                    if(w[start][map[x][y]] > dist || w[map[x][y]][start] > dist) {
                                        w[start][map[x][y]] = dist;
                                        w[map[x][y]][start] = dist;
                                    }
                                    break;
                                }
                            } else break;
                        }
                    }
                }
            }
        }
    }
    private static void BFS() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        boolean[][] v = new boolean[n][m];

        // 모든 섬에 대해서 해당하는 섬의 번호에 좌표 저장하기.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 1 && !v[i][j]) {
                    v[i][j] = true;
                    q.offer(new int[]{i, j});
                    island[size].add(new int[]{i, j});
                    map[i][j] = size;

                    while (!q.isEmpty()) {
                        int[] p = q.poll();

                        for (int d = 0; d < 4; d++) {
                            int nx = p[0] + dx[d];
                            int ny = p[1] + dy[d];

                            if (nx >= 0 && nx < n && ny >= 0 && ny < m && !v[nx][ny] && arr[nx][ny]==1) {
                                q.offer(new int[]{nx, ny});
                                island[size].add(new int[]{nx, ny}); // 해당 섬에 해당하는 좌표들 저장하기.
                                v[nx][ny] = true;
                                map[nx][ny] = size;
                            }
                        }
                    }
                    size++; // 섬의 개수 count++
                }
            }
        }
    }
}
