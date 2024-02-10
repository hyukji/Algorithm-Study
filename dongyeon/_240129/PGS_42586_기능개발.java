package com.ssafy.algo.study.week1;

import java.util.*;
import java.io.*;

public class PGS_기능개발 {

	public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        int[] day = new int[progresses.length];
        for(int i=0; i<progresses.length; i++){
            int d=progresses[i];
        	int cnt =1;
        	while (d<100) {
                d+=speeds[i];
                if(d>=100){
                    day[i]=cnt;
                    break;
                }
        		cnt+=1;
        	}
        }
        
        System.out.println(Arrays.toString(day));
        
        int result=1;
        int s=day[0];
        for(int j=1; j<day.length; j++){
            if(day[j]<=s){
                result+=1;
            }else{
                answer.add(result);
                result=1;
                s=day[j];
            }
        }
        answer.add(result);
        int[] arr = new int[answer.size()];

        for (int i = 0; i <answer.size(); i++) {
            arr[i] = answer.get(i);
        }

        return arr;
    }
}
