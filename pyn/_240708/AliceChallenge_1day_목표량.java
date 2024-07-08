import java.io.*;
import java.util.*;

 class AliceChallenge_1day_목표량 {
     static String num;
     static boolean isEnd=false;
     static int index=0;
    static int[] numbers;
    static boolean[] visited;
    static String[] sortNum=new String[720];
    static int[] makeNum;
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        num=br.readLine();
        numbers=new int[num.length()];
        makeNum=new int[num.length()];
        visited=new boolean[num.length()];
        for(int i=0; i<num.length(); i++) numbers[i]=num.charAt(i)-'0';
        Arrays.sort(numbers);
        perm(0);
        int ansIdx=0;
        for(int i=0; i<720; i++) if(sortNum[i].equals(num)) {
            ansIdx=i;
            break;
        }
        System.out.println(sortNum[ansIdx+1]);
        br.close();
    }
    static void perm(int cnt){
        if(cnt==numbers.length || isEnd){
            StringBuilder sb=new StringBuilder();
            for (int j : makeNum) sb.append(j);
            sortNum[index++]=sb.toString();
            if(index>2 && sortNum[index-2].equals(num)) isEnd=true;
            return;
        }
        for(int i=0; i<numbers.length; i++){
            if(visited[i]) continue;
            visited[i]=true;
            makeNum[cnt]=numbers[i];
            perm(cnt+1);
            visited[i]=false;
        }
    }
}
