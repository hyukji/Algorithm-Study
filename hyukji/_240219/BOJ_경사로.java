import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());

		int[][] graph = new int[n][n];
		for(int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int c= 0; c < n; c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = 2*n;
		for(int r = 0; r < n; r++) {
			int count = 1;
			int bef = graph[r][0];
			for(int c = 1; c < n; c++) {
				int now = graph[r][c];
				int diff = now-bef;
				
				if(diff < -1 || diff>1) {
					answer--; 
					break;
				}
				else if(diff == 0) count++;
				else if(diff == 1) {
					if(count >= l)  {
						count = 1;
					} else {
						answer--;
						break;
					}
				} else if(diff == -1) {
					count = 1;
					boolean check = true;
					for(int i = 1; i < l; i++) {
						c++;
						if(c >= n) {
							answer--;
							break;
						}
						if(graph[r][c] != now) {
							check = false;
							answer--;
							break;
						}
					}
					if(!check) break;
					count = 0;
				}
				bef = now;
			}
		}
		
		for(int c = 0; c < n; c++) {
			int count = 1;
			int bef = graph[0][c];
			for(int r = 1; r < n; r++) {
				int now = graph[r][c];
				int diff = now-bef;
				
				if(diff < -1 || diff>1) {
					answer--; 
					break;
				}
				else if(diff == 0) count++;
				else if(diff == 1) {
					if(count >= l)  {
						count = 1;
					} else {
						answer--;
						break;
					}
				} else if(diff == -1) {
					count = 1;
					boolean check = true;
					for(int i = 1; i < l; i++) {
						r++;
						if(r >= n) {
							answer--;
							break;
						}
						if(graph[r][c] != now) {
							check = false;
							answer--;
							break;
						}
					}
					if(!check) break;
					count = 0;
				}
				bef = now;
			}
		}
		
		System.out.println(answer);
	}
	
}