package _240127;
import java.util.*;
/**
 * packageName    : _240127
 * fileName       : PGS_기능개발
 * author         : moongi
 * date           : 1/27/24
 * description    :
 */
public class PGS_42586_기능개발 {

//    다른 사람의 풀이1
    class Solution1 {
        public int[] solution(int[] progresses, int[] speeds) {
            Queue<Integer> q = new LinkedList<>();
            List<Integer> answerList = new ArrayList<>();

            for(int i = 0; i < speeds.length; i++){
                double remain = (100 - progresses[i])/(double) speeds[i];
                int date = (int)  Math.ceil(remain);

                if(!q.isEmpty() && q.peek() < date) {
                    answerList.add(q.size());
                    q.clear();
                }

                q.offer(date);

            }
            answerList.add(q.size());

            int[] answer = new int[answerList.size()];

            for(int  i =0; i < answer.length; i++) {
                answer[i] = answerList.get(i);
            }
            return answer;
        }
    }
//    나의 풀이
    class Solution {
        public int[] solution(int[] progresses, int[] speeds) {
            int[] answer = new int[101];

            int[] days = new int[101];
            for(int i = 0 ; i< progresses.length; i++) {
                days[i] = (100 - progresses[i]) / speeds[i];
                if((100 - progresses[i]) % speeds[i] != 0) {
                    days[i] += 1;
                }
            }
            int max = days[0];
            int res = 1, idx = 0;
            for(int i = 1; i < progresses.length; i++) {
                if(max >= days[i]) {
                    res++;
                } else {
                    max = days[i];
                    answer[idx++] = res;
                    res = 1;
                }
            }
            answer[idx] = res;
            return Arrays.copyOf(answer, idx+1);
        }
    }
    public static void main(String[] args) {
    }
}
