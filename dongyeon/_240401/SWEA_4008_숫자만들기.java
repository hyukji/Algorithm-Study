package a0401;
import java.io.*;
import java.util.*;

public class SWEA_4008_숫자만들기 {
	static int n, ans, max_result, min_result;
	static int[] oper; // +, -, *, /
	static int[] numbers;
	static int[] b;
	static boolean[] v;
	public static void main(String[] args) throws Exception{
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb;
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<T+1; tc++) {
			sb = new StringBuilder();
			n = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine()," ");
			oper =new int[4];
			
			for(int i=0; i<4; i++) {
				oper[i]= Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine()," ");
			numbers =new int[n];
			for(int j=0; j<n; j++) {
				numbers[j]=Integer.parseInt(st.nextToken());
			}
			
			max_result = Integer.MIN_VALUE;
			min_result = Integer.MAX_VALUE;
			
			b = new int[n-1];
			v = new boolean[n-1];
			solve(1,numbers[0]);// 연산횟수, 연산 결과 값
			
			
			ans = max_result-min_result;
			sb.append("#").append(tc).append(" ").append(ans);
			System.out.println(sb.toString());
		}
		
		br.close();
	}
	static void solve(int cnt, int sum) {
		if(cnt == n) {
			min_result = Math.min(sum, min_result);
			max_result = Math.max(sum, max_result);
			return;
		}
		
		if(oper[0]!=0) {
			oper[0]--;
			solve(cnt+1, sum+numbers[cnt]);
			oper[0]++;
		}
		if(oper[1]!=0) {
			oper[1]--;
			solve(cnt+1, sum-numbers[cnt]);
			oper[1]++;
		}
		if(oper[2]!=0) {
			oper[2]--;
			solve(cnt+1, sum*numbers[cnt]);
			oper[2]++;
		}
		if(oper[3]!=0) {
			oper[3]--;
			solve(cnt+1, sum/numbers[cnt]);
			oper[3]++;
		}
		
		
	}
}
