package _240628;

import java.util.*;
import java.io.*;
public class BOJ_G4_9935_문자열폭발 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();
        String target = br.readLine();

        Stack<Character> stk = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            stk.push(str.charAt(i));
            if (stk.size() >= target.length() && stk.peek() == target.charAt(target.length() - 1)) {
                int idx = 0, cnt = 0;
                for (int j = stk.size()-target.length(); j < stk.size(); j++) if (stk.get(j) == target.charAt(idx++)) cnt++;
                if (cnt == target.length()) for (int j = 0; j < target.length(); j++) stk.pop();
            }
        }
        if (stk.isEmpty()) sb.append("FRULA");
        else for (int i = 0; i < stk.size(); i++) sb.append(stk.get(i));
        System.out.println(sb);
    }
}
