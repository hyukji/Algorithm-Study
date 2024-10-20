import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class AliceChallenge_2day_정리정돈을좋아하는k씨 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb=new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
        for (int i=0; i<m; i++){
            st= new StringTokenizer(br.readLine(), " ");
            int start= Integer.parseInt(st.nextToken())-1;
            int end= Integer.parseInt(st.nextToken())-1;
            int index=Integer.parseInt(st.nextToken())-1;
            int[] select=new int[end-start+1];
            for(int j=start; j<=end; j++) select[j-start]=arr[j];
            Arrays.sort(select);
            sb.append(select[index]).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}
