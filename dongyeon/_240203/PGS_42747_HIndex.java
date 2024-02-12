package a0210;
import java.io.*;
import java.util.*;
public class PGS_42747_HIndex {
	public int solution(int[] citations) throws Exception{
        int answer = 0;
        Arrays.sort(citations);
        
        for(int i=citations.length-1; i>=0; i--){
            if(citations[i]>answer) answer++;
        }
        return answer;
    }
}
