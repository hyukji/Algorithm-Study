package study;

import java.io.*;
import java.util.*;

public class BOJ_10799_쇠막대기 {
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int stick=0;
		int total=0;
		String line=br.readLine();
		char[] s=new char[line.length()];
		for(int i=0; i<line.length(); i++) {
			char a=line.charAt(i);
			s[i]=a;
			if(i==0) {
				stick++;
				//System.out.println(i+"에서 막대 시작! 현재 막대의 개수: "+stick);
			}
			else {
				if(s[i]=='(') {
					stick++;
					//System.out.println(i+"에서 막대 시작! 현재 막대의 개수: "+stick);
				}
				else {
					if(s[i-1]=='(') {
						stick--;
						//System.out.println(i+"에서 레이저 시작! 막대의 개수: "+stick);
						total+=stick;
						//System.out.println("지금까지 막대의 개수: "+total);
					} else {
						total++;
						//System.out.println(i+"에서 막대 끝! 막대 한조각++ : "+stick);
						stick--;
						//System.out.println("지금까지 막대의 개수: "+total);
					}
				}
			}
		}
		System.out.println(total);
	}
}
