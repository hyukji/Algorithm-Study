import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		List<int[]> flowers = new ArrayList<>(); 
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int sm = Integer.parseInt(st.nextToken());
			int sd = Integer.parseInt(st.nextToken());
			int em = Integer.parseInt(st.nextToken());
			int ed = Integer.parseInt(st.nextToken());
			
			flowers.add(new int[] {sm*100 + sd, em*100 + ed});
		}

		flowers.sort((o1, o2) ->  Integer.compare(o1[0], o2[0]));
		
		int answer = 0;
		int now = 301;
		for(int i = 0; i < n;) {
			int maxEnd = now;
			for(; i < n; i++) {
				int start = flowers.get(i)[0];
				int end = flowers.get(i)[1];
				if(start > now) break;
				maxEnd = Math.max(maxEnd, end);
//				System.out.println(start + " " + end);
			}
			
			
//			System.out.println(maxEnd);
			if(maxEnd == now) break;
			
			answer++; 
			now = maxEnd;
			if(now > 1130) {
				System.out.println(answer);
				return;
			}
		}

		if(now < 1201) answer = 0;
		System.out.println(answer);
	}
	
}