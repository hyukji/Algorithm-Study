import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        Arrays.sort(citations);
        
        int h = 0;
        int n = citations.length;
        for(int i = 0; i < n; i++) {
            int remain = n - i; // 현재보다 같거나 큰 인용수를 가진 논문 수
            int c = citations[i]; // 인용 수
            int nh = Math.min(remain, c);
            h = Math.max(h, nh);
        }
        
        return h;
    }
}