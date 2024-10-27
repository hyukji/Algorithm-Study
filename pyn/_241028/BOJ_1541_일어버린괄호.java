import java.io.*;
import java.util.*;

public class BOJ_1541_일어버린괄호 {
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        // - 에서 끊어서 읽기 = 끊은 부분들을 모두 음수처리해서 더해주면 됨
        StringTokenizer st=new StringTokenizer(br.readLine(), "-");
        int ans=0;
        int first=-1;
        while(st.hasMoreTokens()) {
            int temp=0;
            StringTokenizer st2=new StringTokenizer(st.nextToken(), "+");
            while(st2.hasMoreTokens()) {
                temp+=Integer.parseInt(st2.nextToken());
            }
            if(first==-1) first=temp;
            else ans-=temp;
        }
        System.out.println(first+ans);
    }
}
