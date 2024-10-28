import java.io.*;
import java.util.*;
public class BOJ_1541_잃어버린괄호 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();

		String s = "";
		long total = 0;
		int count = 0;
		for(int i=0; i<input.length(); i++) {
			char c = input.charAt(i);
			if(c!='-') {
				s+=c;
			}else {
				long sum = 0;
				StringTokenizer st = new StringTokenizer(s,"+");
				while(st.hasMoreTokens()) {
					int num = Integer.parseInt(st.nextToken());
					sum+=(long)num;
				}

				if(count==0) {
					total=sum;
					count ++;
				}
				else {
					total-=sum;
				}
				s = "";
			}
		}


		long sum = 0;
		StringTokenizer st = new StringTokenizer(s,"+");
		while(st.hasMoreTokens()) {
			int num = Integer.parseInt(st.nextToken());
			sum+=(long)num;
		}

		if(count==0) {
			total=sum;
			count ++;
		}
		else {
			total-=sum;
		}




		System.out.println(total);

		br.close();
	}
}
