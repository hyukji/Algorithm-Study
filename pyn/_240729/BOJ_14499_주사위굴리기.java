import java.io.*;
import java.util.*;

public class BOJ_14499_주사위굴리기 {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N, M, x, y, K;
    static int[][] map;
    static int[] dice = new int[6];
    static int[] diceState = {1, 2, 4, 5, 3, 6};
    static StringBuilder sb;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb=new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st=new StringTokenizer(br.readLine());
        for(int i=0; i<K; i++){
            int dir = Integer.parseInt(st.nextToken())-1;
            play(dir);
        }
        System.out.println(sb);
        br.close();
    }
    static void play(int dir){
        if(dir==0){
            if(y+1<M){
                y++;
                right();
                if(map[x][y]==0){
                    map[x][y]=dice[diceState[5]-1];
                } else{
                    dice[diceState[5]-1]=map[x][y];
                    map[x][y]=0;
                }
                sb.append(dice[diceState[0]-1]).append("\n");
            }
        } else if(dir==1){
            if(y-1>=0){
                y--;
                left();
                if(map[x][y]==0){
                    map[x][y]=dice[diceState[5]-1];
                } else{
                    dice[diceState[5]-1]=map[x][y];
                    map[x][y]=0;
                }
                sb.append(dice[diceState[0]-1]).append("\n");
            }
        } else if(dir==2){
            if(x-1>=0){
                x--;
                up();
                if(map[x][y]==0){
                    map[x][y]=dice[diceState[5]-1];
                } else{
                    dice[diceState[5]-1]=map[x][y];
                    map[x][y]=0;
                }
                sb.append(dice[diceState[0]-1]).append("\n");
            }
        } else{
            if(x+1<N){
                x++;
                down();
                if(map[x][y]==0){
                    map[x][y]=dice[diceState[5]-1];
                } else{
                    dice[diceState[5]-1]=map[x][y];
                    map[x][y]=0;
                }
                sb.append(dice[diceState[0]-1]).append("\n");
            }
        }
    }
    // 1 2 4 5 3 6
    // 4 2 6 5 1 3
    static void right(){
        int temp = diceState[0];
        diceState[0]=diceState[2];
        diceState[2]=diceState[5];
        diceState[5]=diceState[4];
        diceState[4]=temp;
    }
    // 1 2 4 5 3 6
    // 3 2 1 5 6 4
    static void left(){
        int temp = diceState[0];
        diceState[0]=diceState[4];
        diceState[4]=diceState[5];
        diceState[5]=diceState[2];
        diceState[2]=temp;
    }
    // 1 2 4 5 3 6
    // 5 1 4 6 3 2
    static void up(){
        int temp = diceState[0];
        diceState[0]=diceState[3];
        diceState[3]=diceState[5];
        diceState[5]=diceState[1];
        diceState[1]=temp;
    }
    // 1 2 4 5 3 6
    // 2 6 4 1 3 5
    static void down(){
        int temp = diceState[0];
        diceState[0]=diceState[1];
        diceState[1]=diceState[5];
        diceState[5]=diceState[3];
        diceState[3]=temp;
    }
}
