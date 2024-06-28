package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_9935_문자열폭발 {
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		
		String str=br.readLine();
		String bomb=br.readLine();
		
		Stack<Character> stri = new Stack<Character>();
		for(int i=0; i<str.length(); i++) {
			int count=0;
			stri.push(str.charAt(i));
			
			if(stri.size() >= bomb.length()) {
				for(int j=0; j<bomb.length(); j++) {
					if(stri.get(stri.size()-bomb.length()+j) == bomb.charAt(j)) count++;
					if(count==bomb.length()) for(int k=0; k<bomb.length(); k++) stri.pop();
				}
			}
		}
		
		if(stri.isEmpty()) sb.append("FRULA");
		else for(char c: stri) sb.append(c);
		System.out.println(sb);
		br.close();
	}
}