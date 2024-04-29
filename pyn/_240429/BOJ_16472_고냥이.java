package study;

import java.io.*;
import java.util.*;

public class BOJ_16472_고냥이 {
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int N=Integer.parseInt(br.readLine());
		int[] abc=new int[26];
		
		String line=br.readLine();
		char[] str=new char[line.length()];
		for(int i=0; i<line.length(); i++) str[i]=line.charAt(i);
		
		int start=0;
		int end=0;
		abc[str[start]-'a']++;
		int count=1; // 알파벳 종류 개수
		int ans=0;
		
		while(end<line.length()-1) {
			end++;
			// 다음 알파벳이 처음 나오는 알파벳이면
			if(abc[str[end]-'a']++==0) count++;
			while(count>N && start < end) { // count를 N보다 작게 만들어야 하므로
				// 현재 start에 있는 알파벳을 하나 줄여주고 start 증가 후 0인지 확인, 0이면 count--
				if(--abc[str[start++]-'a']==0) count--;
			}
			ans=Math.max(ans, end-start+1);
		}
		System.out.println(ans);
		br.close();
	}
}