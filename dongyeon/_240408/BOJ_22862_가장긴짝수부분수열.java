package a0408;

import java.io.*;
import java.util.*;

public class BOJ_22862_가장긴짝수부분수열 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int k = Integer.parseInt(line[1]);
        boolean[] arr = new boolean[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(num%2==0) {
            	arr[i]=true;
            }
        }

        // 투포인터
        int max = 0;
        int l = 0;
        int r = 0;
        int ct = 0;
        while (r < n) {
            if (ct < k) {
                if (!arr[r]) {
                    ct++;
                }
                r++;
                max = Math.max(r - l - ct, max);
            } else if (arr[r]) {
                r++;
                max = Math.max(r - l - ct, max);
            } else {
                if (!arr[l]) {
                    ct--;
                }
                l++;
            }
        }

        System.out.println(max);
    }
}


