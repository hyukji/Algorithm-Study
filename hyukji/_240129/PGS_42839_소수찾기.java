package _240129;

import java.util.*;

class PGS_42839_소수찾기 {

    static int maxValue = 0;

    static int n;
    static int[] visited;
    static Set<Integer> result = new HashSet<>();

    public int solution(String numbers) {
        n = numbers.length();
        
        visited = new int[n];
        for(int size=1; size<=n; size++) {
            permutation("", numbers, size);
        }
        
        int[] primes = getPrimes();
        return countPrime(primes);
    }

    private void permutation(String out, String nums, int size) { // 순열로 가능한 값 찾기.
        if(out.length() == size) {
        	int value = Integer.parseInt(out);
        	if(maxValue < value) maxValue = value;
            result.add(value);
            return;
        }

        for(int i = 0 ; i < n; i++) {
            if(visited[i] == 1) continue;
            visited[i] = 1;
            permutation(out + nums.charAt(i), nums, size);
            visited[i] = 0;
        }

    }
    
    private int[] getPrimes() { // 소수들 찾기. 에라토스테네스의 체 
        int[] primes = new int[maxValue+1];

        primes[0] = 1;
        primes[1] = 1;
        for(int i = 2; i <= Math.sqrt(maxValue); i++) {
            if(primes[i] == 0) checkNotPrime(primes, i);
        }
        
        return primes;
    }

    private void checkNotPrime(int[] primes, int i) {
        int num = i + i;
        while(num <= maxValue) {
            primes[num] = 1;
            num+=i;
        }
    }
    
    private int countPrime(int[] primes) { // 찾은 경우의 수들이 소수인지 확인.
        int answer = 0;
        for(int v :result) {
            if(primes[v] == 0) answer++;
        }

        return answer;
    }
    
}