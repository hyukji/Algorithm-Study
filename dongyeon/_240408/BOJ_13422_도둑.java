package a0408;
import java.io.*;
import java.util.*;

public class BOJ_13422_도둑 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<T+1; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			long K = Long.parseLong(st.nextToken());
			
			int[] arr = new int[N];
			int total = 0;
			st = new StringTokenizer(br.readLine()," ");
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				total +=arr[i];
			}
			
			// 도둑이 자동 방범장치에 붙잡히지 않도록 연속된 M개의 집에서 돈을 훔치는 방법의 가짓 수를 출력
			int ans = 0;
			
			int s = 0;
			int e = M-1;
			
			int sum = 0;
			
			if(N==M) {
				if(total < K) ans = 1;
				else ans =0;
			}
			else {
				for(int i=0; i<=e; i++) {
					sum+=arr[i];
				}
				while(s<N) {
					if(sum<K) ans++;
					sum-=arr[s];
					s++;
					e++;
					sum+=arr[e%N];
				}
			}
			System.out.println(ans);
			
		}
		
		br.close();
	}
}
