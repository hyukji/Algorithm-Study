import java.io.*;
import java.util.*;

public class BOJ_1941_소문난칠공주 {
    static class Student {
        int i;
        int j;

        Student(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
    static int[] di = {0, -1, 0, 1};
    static int[] dj = {1, 0, -1, 0};
    static char[][] room;
    static boolean[] visited;
    static int ans=0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        room = new char[5][5];
        visited = new boolean[25];

        for (int i = 0; i < 5; i++) {
            String line = br.readLine();
            for (int j = 0; j < 5; j++) room[i][j] = line.charAt(j);
        }

        comb(0,0,0);

        System.out.println(ans);
        br.close();
    }
    static void comb(int index, int count, int lim){
        if(lim >= 4) return;
        if(count == 7) {
            int curIndex = index-1;
            if(bfs(curIndex/5, curIndex%5)) ans++;
            return;
        }
        for(int i=index; i<25; i++){
            visited[i]=true;
            if(room[i/5][i%5]=='Y') comb(i+1, count+1, lim+1);
            else comb(i+1, count+1, lim);
            visited[i]=false;
        }
    }
    static boolean bfs(int i, int j) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        boolean[][] visit = new boolean[5][5];
        visit[i][j]=true;
        q.offer(new int[]{i, j});
        int cnt = 1;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            for(int d=0; d<4; d++){
                int ni=cur[0]+di[d];
                int nj=cur[1]+dj[d];
                if(0<=ni&&ni<5 && 0<=nj&&nj<5 && !visit[ni][nj] && visited[ni*5+nj]){
                    visit[ni][nj]=true;
                    q.offer(new int[]{ni,nj});
                    cnt++;
                }
            }
        }
        return cnt==7;
    }
}
