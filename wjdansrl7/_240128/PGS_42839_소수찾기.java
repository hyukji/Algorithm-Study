package _240128;

import java.util.*;

/**
 * packageName    : _240128
 * fileName       : PGS_소수찾기
 * author         : moongi
 * date           : 1/28/24
 * description    :
 * ref: https://hstory0208.tistory.com/entry/Java%EC%9E%90%EB%B0%94-%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-Lv2-%EC%86%8C%EC%88%98-%EC%B0%BE%EA%B8%B0-%EC%99%84%EC%A0%84%ED%83%90%EC%83%89DFS
 */
public class PGS_42839_소수찾기 {

    static Set<Integer> set;
    static boolean[] visited = new boolean[7];

    public static int solution(String numbers) {
        int answer = 0;
        set = new HashSet<>(); // 중복 제거
        dfs(numbers, "", 0);

        for (Integer num : set) {
            if (isPrime(num)) {
                answer++;
            }
        }
        return answer;
    }

    static void dfs(String numbers, String s, int L) {
        if (L > numbers.length()) {
            return;
        }

        for (int i = 0; i < numbers.length(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                set.add(Integer.parseInt(s + numbers.charAt(i)));
                dfs(numbers, s + numbers.charAt(i), L + 1);
                visited[i] = false;
            }
        }
    }

    static boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        for (int i = 2; i <= (int) Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
