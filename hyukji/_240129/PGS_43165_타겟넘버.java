package _240129;

class Solution {
    
    int target;
    int[] numbers;
    int numberSize;
    int answer = 0;
    
    public int PGS_43165_타겟넘버(int[] numbers, int target) {
        this.target = target;
        this.numbers = numbers;
        this.numberSize = numbers.length;
        dfs(0, 0);
        System.out.println(this.target);
        
        return answer;
    }
    
    public void dfs(int result, int depth) {
        if(depth == numberSize) {
            if(result == target) answer+=1;
            return;
        }
        int v = numbers[depth];
        dfs(result + v, depth + 1);
        dfs(result - v, depth + 1);
    }
    
}