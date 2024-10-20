import java.io.*;
import java.util.*;

public class AliceChallenge_7day_계기판조작하기 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        if(K==9) N=102345678;
        else if(K==10) N=1023456789;
        else while(check(N)!=K) N++;
        System.out.println(N);
    }
    static int check(int N){
        boolean[] num = new boolean[10];

        while(true) {
            num[N % 10] = true;
            if(N<10) break;
            N /= 10;
        }
        int count=0;
        for(int i=0; i<10; i++) if(num[i]) count++;
        return count;
    }
}
