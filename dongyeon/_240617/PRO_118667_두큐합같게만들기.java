package a0614;
import java.io.*;
import java.util.*;

public class PRO_118667_두큐합같게만들기 {
    public int solution(int[] queue1, int[] queue2) throws Exception{
        int answer = -1;
        
        ArrayDeque<Integer> q1 = new ArrayDeque<>();
        ArrayDeque<Integer> q2 = new ArrayDeque<>();
        int size = queue1.length+queue2.length;
        
        long sum1=0;
        long sum2=0;
        for(int a: queue1){
            q1.offer(a);
            sum1+=a;
        }
        for(int b: queue2){
            q2.offer(b);
            sum2+=b;
        }
        
        if((sum1+sum2)%2==0){ // 두 큐의 합이 짝수일때
            // 합으로 만들어야 할 수 
            long sum = (sum1+sum2)/2;
            answer=0;
            while(true){
                if(answer>size+2){
                    answer=-1;
                    break;
                }
                if(sum1==sum) break;
                else if(sum1>sum){
                    sum1-=q1.peek();
                    int a=q1.poll();
                    q2.offer(a);
                }else{
                    int b = q2.poll();
                    sum1+=b;
                    q1.offer(b);
                }
                
                answer++;
            }
        }
        
        return answer;
    }
}