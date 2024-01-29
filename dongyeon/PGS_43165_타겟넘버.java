package com.ssafy.algo.study.week1;

import java.util.*;
import java.io.*;

public class PGS_타겟넘버 {
	static int answer =0;
    public int solution(int[] numbers, int target) {
        DFS(0, numbers, target, 0);
        return answer;
    }
    static void DFS(int idx, int[] numbers, int target, int value) {
        int n = numbers.length;
        if (idx == n && target == value) {
            answer++;
            return;
        } else if (idx == n) {
            return;
        }

        DFS(idx + 1, numbers, target, value + numbers[idx]);
        DFS(idx + 1, numbers, target, value - numbers[idx]);
    }
    
}
