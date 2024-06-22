import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] row = br.readLine().toCharArray();
		
		int size = row.length, stick = 0, answer = 0;
		for(int i = 0; i < size; i++) {
			if(row[i] == ')') { // ()가 아닌 ) 만 있는 경우 
				stick--;
				answer++;
			}
			else if(row[i+1] == ')') { // () 인 경우 
				answer += stick;
				i++;
			} else { // ( 만 있는 경우 
				stick++;
			}
		}
		
		System.out.println(answer);
	}

}