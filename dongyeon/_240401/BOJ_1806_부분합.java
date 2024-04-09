package a0401;
import java.io.*;
import java.util.*;
public class BOJ_1806_부분합 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int n = Integer.parseInt(st.nextToken());
		long s = Long.parseLong(st.nextToken());
		
		int[] arr= new int[n];
		
		st = new StringTokenizer(br.readLine()," ");
		for(int i=0; i<n; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		int ans = Integer.MAX_VALUE;
		
		int l =0;
		int r =1;
		long sum = arr[0];
		
		while(l<=r) {
			if(sum<s) {
				if(r>=n) break;
				sum+=arr[r];
				r++;
			}else {
				ans = Math.min(ans, r-l);
				sum-=arr[l];
				l++;
			}
			
			
		}
		if(ans==Integer.MAX_VALUE) System.out.println(0);
		else System.out.println(ans);
		br.close();
	}

}
