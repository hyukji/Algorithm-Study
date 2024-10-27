import java.io.*;
import java.util.*;

public class BOJ_3020_개똥벌레 {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[] bottom = new int[N / 2];
        int[] top = new int[N / 2];

        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) bottom[i / 2] = Integer.parseInt(br.readLine());
            else top[i / 2] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bottom);
        Arrays.sort(top);
        int min = N;

        int cnt = 0;
        for (int i = 1; i < H + 1; i++) {
            int conflict = find(i, bottom) + find(H - i + 1, top);
            if (min > conflict) {
                min = conflict;
                cnt = 1;
            } else if (min == conflict) cnt++;
        }
        System.out.println(min + " " + cnt);
    }
    static int find(int h, int[] arr) {
        int left = 0;
        int right = N / 2;
        while (left < right) {
            int mid = (left + right) / 2;

            if (arr[mid] >= h) right = mid;
            else left = mid + 1;
        }
        return arr.length - right;
    }
}
