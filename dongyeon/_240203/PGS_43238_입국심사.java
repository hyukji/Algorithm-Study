package a0210;

import java.io.*;
import java.util.*;

class PGS_43238_입국심사 {
    public long solution(int n, int[] times) {
        Arrays.sort(times); // 오름차순 정렬
        int length = times.length;
        
        long maxTime= (long)times[length-1]*n;
        // 심사 받는데 가장 오래 걸렸을 경우의 시간
        long minTime=1;
        // 최소는 1
        
        long answer = maxTime;
        // 최댓값으로 초기화 
        
        //이분탐색 시작
        while(minTime<=maxTime){
            long midi = (minTime+maxTime)/2;
            // 중간값
            long people =0;
            // 심사를 받는 사람
            
            // midi 시간동안 심사를 받을 수 있는 사람 계산
            for(int i=0; i<length; i++){
                people+=midi/times[i];
                if(people>=n) break; // n명보다 많을 땐 계산 중지
            }
            
            if(people>=n){ //midi 시간이 n명보다 같거나 많은 사람을 심사 할 수 있으면
                answer = Math.min(answer,midi); // answer에 최솟값 저장
                maxTime = midi-1; //중앙 기준 왼쪽 다시 탐색
            }else{
                minTime = midi+1; // 오른 쪽 탐색
            }
            
            
        }
        
        return answer;
    }
}
