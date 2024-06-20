package _240624;

public class PGS_150368_이모티콘할인행사 {
    static void permutation(int cnt, int[][] users, int[] emoticons) {
        if(cnt == emoticonLen) {
            solve(discount, users, emoticons);
            return;
        }
        for(int i = 1; i < 5; i++) {
            discount[cnt] = i;
            permutation(cnt+1, users, emoticons);
        }
    }

    static void solve(int[] discount, int[][] users, int[] emoticons) {
        int tmp1 = 0, tmp2 = 0;
        for(int i = 0; i < users.length; i++) {
            int minDiscount = users[i][0];
            int maxMoney = users[i][1];
            int sum = 0;
            for(int j = 0; j < emoticonLen; j++) {
                // 할인율에 따라서 유저가 해당 이모티콘 구매 비용을 추가한다.
                if(discount[j] * 10 >= minDiscount) sum += ((emoticons[j] * (10 - discount[j])) / 10);
            }
            // 구매 비용이 유저 기준 금액을 넘어설 경우 -> 이모티콘 플러스 가입!
            if(sum >= maxMoney) tmp1++;
                // 기준 금액 안쪽일 경우 판매액만 늘려준다.
            else tmp2 += sum;
        }
        if(result1 < tmp1) {
            result1 = tmp1;
            result2 = tmp2;
        } else if(result1 == tmp1) {
            if(result2 < tmp2) result2 = tmp2;
        }
    }

    static int emoticonLen, result1, result2;
    static int[] discount;

    public int[] solution(int[][] users, int[] emoticons) {
        // 할인율 설정은 10, 20, 30, 40%로 설정할 수 있음.
        int[] answer = new int[2];

        emoticonLen = emoticons.length;
        discount = new int[emoticonLen];

        permutation(0, users, emoticons);

        answer[0] = result1;
        answer[1] = result2;

        return answer;
    }
}
