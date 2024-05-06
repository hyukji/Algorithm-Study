import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int n = Integer.parseInt(br.readLine());
		int[][] schedules = new int[n][2];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			schedules[i][0] = Integer.parseInt(st.nextToken());
			schedules[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(schedules, (o1, o2) -> {
			int r = Integer.compare(o1[0], o2[0]);
			if(r == 0) return Integer.compare(o2[1], o1[1]);
			return r;
		});
		
		
		int answer = 0;
		
		int i = 0;
		List<int[]> paper = new ArrayList<int[]>();
		int paper_start = -1;
		int paper_end = -1;
		while(i < n) {
			int ns = schedules[i][0];
			int ne = schedules[i++][1];
			
			if(ns > paper_end+1) { // 새로운 코팅지 교체 
				answer += (paper_end - paper_start + 1) * paper.size();

				paper = new ArrayList<int[]>();
				paper.add(new int[] {ns, ne});
				paper_start = ns;
				paper_end = ne;
				continue;
			}
			
			boolean isChecked = false;
			for(int[] row : paper) {
				if(ns > row[1]) {
					row[1] = ne;
					paper_end = Integer.max(paper_end, ne);
					isChecked = true;
					break;
				}
			}
			
			if(!isChecked) {
				paper.add(new int[] {ns, ne});
				paper_end = Integer.max(paper_end, ne);
			}
		}
		

		answer += (paper_end - paper_start + 1) * paper.size();
		System.out.println(answer);
		
	}
	
}