import java.io.*;
import java.util.*;
public class BOJ_3020_개똥벌레 {
	static int n,h;
	static int[] list, answer;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st  = new StringTokenizer(br.readLine());

		// 동굴의 길이
		n = Integer.parseInt(st.nextToken());
		// 동굴의 높이
		h = Integer.parseInt(st.nextToken());

		list = new int[h+1];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int height = Integer.parseInt(st.nextToken());

			if(i%2==0) {
				list[0]+=1;
				list[height]-=1;
			}else {
				list[h]-=1;
				list[h-height]+=1;
			}
		}


		// 누적합
		int now = 0;
		answer = new int[h+1];
		int min = Integer.MAX_VALUE;
		for(int i=0; i<h+1; i++) {
			now+=list[i];
			answer[i] = now;

			if(i!=h) {
				min = Math.min(min, answer[i]);
			}
		}

		int min_num =0;
		for(int i=0; i<h; i++) {
			if(answer[i] == min) min_num++;
		}


		System.out.println(min+" "+min_num);


		br.close();
	}
}
