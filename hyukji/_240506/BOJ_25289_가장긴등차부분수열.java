import java.io.*;
import java.util.StringTokenizer;

public class Main {
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        int[][] pdp = new int[101][101]; // [값][등차] = 개수
        int[][] mdp = new int[101][101]; // [값][등차] = 개수
        boolean[] visited = new boolean[101];
        
        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int answer = 1;
        for(int i = 0; i < n; i++) {
        	int v = Integer.parseInt(st.nextToken());
        	if(!visited[v]) { // 처음 들어오는 값이라면 모든 모든 등차에 대해 1 체크해줘야함
            	visited[v] = true;
            	for(int j = 0; j < 101; j++) {
            		pdp[v][j] = 1;
            		mdp[v][j] = 1;
            	}
        	} else { // 이전에 같은 값이 들어온 적 있다면 등차 0에 대해서는 1 추가해줘야함
        		pdp[v][0] = pdp[v][0] + 1;
        		answer = Integer.max(pdp[v][0], answer);
        	}
        	
        	for(int j = 1; j < v; j++) { // 등차 양수인 경우
        		pdp[v][j] = pdp[v-j][j] + 1;
        		answer = Integer.max(pdp[v][j], answer);
        	}

        	for(int j = 1; j < 101-v; j++) {  // 등차 음수인 경우
        		mdp[v][j] = mdp[v+j][j] + 1;
        		answer = Integer.max(mdp[v][j], answer);
        	}
        }
        
        System.out.println(answer);
    }


}