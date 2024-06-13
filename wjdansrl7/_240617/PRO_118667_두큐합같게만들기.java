package _240617;

import java.util.ArrayDeque;
public class PRO_118667_두큐합같게만들기 {
        public int solution(int[] queue1, int[] queue2) {
            int answer = 0;

            long sum1 = 0, sum2 = 0;

            for(int i = 0; i < queue1.length; i++) sum1 += queue1[i];
            for(int j = 0; j < queue2.length; j++) sum2 += queue2[j];

            if((sum1 + sum2) % 2 == 1) return -1;

            ArrayDeque<Integer> q1 = new ArrayDeque<>();
            ArrayDeque<Integer> q2 = new ArrayDeque<>();

            for(int i = 0; i < queue1.length; i++) q1.offer(queue1[i]);
            for(int j = 0; j < queue2.length; j++) q2.offer(queue2[j]);

            int size = queue1.length > queue2.length ? queue1.length : queue2.length;

            while(sum1 != sum2) {

                if(sum1 > sum2) {
                    int data = q1.poll();
                    q2.offer(data);
                    sum1 -= data;
                    sum2 += data;
                    answer++;
                    if(sum1 == sum2) break;
                } else if (sum1 < sum2){
                    int data = q2.poll();
                    q1.offer(data);
                    sum2 -= data;
                    sum1 += data;
                    answer++;
                    if(sum1 == sum2) break;
                }
                if(answer > 3 * size) {
                    answer = -1;
                    break;
                }
            }
            return answer;
        }
}
