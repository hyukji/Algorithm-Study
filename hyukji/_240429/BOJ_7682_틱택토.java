import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        while(true) {
            String row = br.readLine();
            if(row.charAt(0) == 'e') break;

            char[] tictecto = row.toCharArray();
            int diff = compareCnt(tictecto);
            
            if(diff == 0) { // O만 가능 
                if(hasLine(tictecto, 'X')) {
                	sb.append("invalid\n");
                	continue;
                }
                if(hasLine(tictecto, 'O')) {
                	sb.append("valid\n");
                	continue;
                }
            }
            else if(diff == 1) { // 1 가능 or 9개 
                if(hasLine(tictecto, 'O')) {
                	sb.append("invalid\n");
                	continue;
                }
                if(hasLine(tictecto, 'X') || fullGameBoard(tictecto)) {
                	sb.append("valid\n");
                	continue;
                }
            } 
            
        	sb.append("invalid\n");
   
        }

	    System.out.println(sb);
	}	
	
	private static boolean fullGameBoard(char[] tictecto) {
		for(char c : tictecto) {
			if(c == '.') return false;
		}
	    return true;
	}
	
	private static boolean hasLine(char[] tictecto, char target) {
	    // 가로
	    for(int r = 0; r < 3; r++)
	        if(tictecto[r*3] == target && tictecto[r*3+1] == target	&& tictecto[r*3+2] == target)  
	        	return true;
	    
	    // 세로 
	    for(int c = 0; c < 3; c++)
	        if(tictecto[c] == target && tictecto[3+c] == target && tictecto[6+c] == target)
	            return true;
	    
	    // 대각선
	    if(tictecto[0] == target && tictecto[4] == target && tictecto[8] == target) return true;
	    if(tictecto[2] == target && tictecto[4] == target && tictecto[6] == target) return true;
	    
	    return false;
	}
	
	// x의 개수와 o의 개수의 차가 1 or 0 이어야 한다.
	private static int compareCnt(char[] tictecto) {
	    int xMinusO = 0;
	    for(char c : tictecto) {
	        if(c == 'X') xMinusO++;
	        else if(c == 'O') xMinusO--;
	    }
	    
	    return xMinusO;
	}
}