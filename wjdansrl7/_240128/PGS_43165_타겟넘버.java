package _240128;

/**
 * packageName    : _240128
 * fileName       : PGS_타겟넘버
 * author         : moongi
 * date           : 1/28/24
 * description    :
 */
public class PGS_43165_타겟넘버 {
    class Solution {
        static int answer = 0;
        static void dfs(int[] numbers, int target, int sum, int L) {
            if(L == numbers.length) {
                if(sum == target)
                    answer++;
                return;
            }
            dfs(numbers, target, sum + numbers[L], L+1);
            dfs(numbers, target, sum - numbers[L], L+1);
        }
        public int solution(int[] numbers, int target) {
            dfs(numbers, target, 0, 0);
            return answer;
        }
    }
}
