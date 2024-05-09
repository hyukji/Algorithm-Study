package _240506;
import java.util.*;
import java.io.*;
import java.lang.*;

public class BOJ_G4_5710_전기요금 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int A = 0, B = 0;
        while(true) {
            st = new StringTokenizer(br.readLine());

            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            if(A == 0 && B == 0) return;

//            A: 이웃의 사용량과 합쳤을 때 내야하는 요금 1100
//            B: 이웃과의 전기 요금의 차이(절댓값) 300
            /*
            1~100: 2
            101~10000: 3
            10001~1000000: 5
            >1000_000: 7

             */
            /*
            |x-y| = B

             */
        }
    }
}
