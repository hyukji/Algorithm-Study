package _240129;

import java.util.*;

class PGS_42586_기능개발 {
    public int[] solution(int[] progresses, int[] speeds) {
		int n = progresses.length;
		List<Integer> list = new ArrayList<>();
		
		int t = 0; int count = 0;
		for(int i = 0; i < n; i++) {
			double remain = (100.0 - progresses[i]) / speeds[i];
			
			if(t < remain) {
                if(count != 0) {
                    list.add(count);
                    count = 0;
                }
                
                t = (int) remain;
                if(remain != (int) remain) t++;
			}
            
            count++;
		}
		
		if(count != 0) list.add(count);

		int[] answer = new int[list.size()];
		for(int i = 0; i < list.size(); i++) {
			answer[i] = list.get(i);
		}
		
		return answer;
    }
}