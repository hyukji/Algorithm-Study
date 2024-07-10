import java.io.*;
import java.util.*;
public class AliceChallenge_3day_문자열압축해제 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str=br.readLine();
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int ans=0;
        int count=0;
        for(int i=0;i<str.length();i++){
            char ch=str.charAt(i);
            if(ch==')'){
                while(true){
                    int last=stack.pollLast();
                    //System.out.println(last+"꺼내기");
                    if(last==('('-'0')){
                        break;
                    } else count++;
                }
                //System.out.println(count+" 곱하기 "+stack.peekLast());
                count=count*stack.pollLast();
                //System.out.println(count);
            } else {
                stack.add(ch-'0');

            }
        }
        System.out.println(count+stack.size());
        br.close();
    }
}
