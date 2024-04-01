package study;

import java.io.*;
import java.util.*;

public class Solution_bj_2457_공주님의정원 {
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		StringTokenizer st;
		
		int N=Integer.parseInt(br.readLine());
		int[] year=new int[365];
		int[][] flowers=new int[N][2];
		
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine(), " ");
			int m1=Integer.parseInt(st.nextToken());
			int d1=Integer.parseInt(st.nextToken());
			int m2=Integer.parseInt(st.nextToken());
			int d2=Integer.parseInt(st.nextToken());
			
			year[date(m1,d1)]=Math.max(date(m2,d2), year[date(m1,d1)]);
			//System.out.println(date(m1,d1)+" "+year[date(m1,d1)]);
		}
		int lastDay=1;
		int day=60;
		int flower=0;
		while(day<335) {
			int maxDay=0;
			for(int i=lastDay; i<=day; i++) {
				maxDay=Math.max(maxDay, year[i]);
			}
			if(maxDay==0) break;
			lastDay=day;
			day=maxDay;
			flower++;
			//System.out.println("lastDay: "+lastDay+" day: "+day+" flower: "+flower);
		}
		if(day>=335) System.out.println(flower);
		else System.out.println(0);

	}
	static int date(int month, int days) {
		int day=0;
		if(month==1) day=days;
		else if(month==2) day=days+31;
		else if(month==3) day=days+31+28;
		else if(month==4) day=days+31+28+31;
		else if(month==5) day=days+31+28+31+30;
		else if(month==6) day=days+31+28+31+30+31;
		else if(month==7) day=days+31+28+31+30+31+30;
		else if(month==8) day=days+31+28+31+30+31+30+31;
		else if(month==9) day=days+31+28+31+30+31+30+31+31;
		else if(month==10) day=days+31+28+31+30+31+30+31+31+30;
		else if(month==11) day=days+31+28+31+30+31+30+31+31+30+31;
		else if(month==12) day=days+31+28+31+30+31+30+31+31+30+31+30;
		return day;
	}
}
