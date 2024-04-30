package a0416;

import java.io.*;
import java.util.*;
public class BOJ_10799_쇠막대기 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        
        int ans = 0;
        
        ArrayDeque<Character> stack = new ArrayDeque<>();
        stack.push('(');
        for(int i=1; i<s.length(); i++) {
            char c = s.charAt(i);
            char pre_c=s.charAt(i-1);
            if(c=='(') {// 열린괄호 -> 쇠막대기의 시작 
            	stack.push(c);
            }else {// 닫는괄호 )일때
            	if(pre_c=='(') { //바로 전이 (이면 쇠막대기 아니고 레이저다.
            		stack.pop();
            		ans+=stack.size(); // 레이저가 자른 쇠막대기 갯수를 더해준다.
            	}else { //바로 전이 )이면 레이저아니고 쇠막대기의 끝이므로 끝자락 잘린 막대기 +1씩 해준다.
            		stack.pop(); // 그 앞의 쇠막대기 시작 ( 괄호도 빼준다. 쇠막대기 하나 끝!
            		ans+=1;
            	}
            }
        }
        
        System.out.println(ans);
        br.close();
    }

}

