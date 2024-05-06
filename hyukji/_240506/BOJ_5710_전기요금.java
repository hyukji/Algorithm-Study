import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int getElectric(int price) {
		int elec = 0;
		int[] pricePerWatt = {2, 3, 5, 7};
		int[] ranges = {100, 9900, 990000, Integer.MAX_VALUE};
		for(int i = 0; i < 4; i++) {
			int ppw = pricePerWatt[i];
			int range = ranges[i];
			
			if(price <= ppw * range) {
				elec += (int) (price / ppw);
				return elec;
			} 
			
			price -= ppw * range;
			elec += range;
		}
		
		return 0;
	}

	private static int getPrice(int elec) {
		if(elec <= 100) return 2 * elec;
		else if(elec <= 10000) return 2*100 + 3*(elec-100);
		else if(elec <= 1000000) return 2*100 + 3*9900 + 5*(elec-10000); 
		else return 2*100 + 3*9900 + 5*990000 + 7*(elec-1000000); 
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		
		// 금액을 받아서 전기요금을 반환하는 메서드
		// 둘을 합한 금액을 통해 총합을 알아낸다.
		// 이진탐색을 이용해서 둘의 차이에 맞는 전기요금을 찾느다.
		while(true) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(a == 0 && b == 0) break;
			
			int totElec = getElectric(a);
			int s = (int) (totElec / 2);
			int e = totElec + 1;
			while(s + 1 < e) {
				int mid = (int) ((s + e) / 2);
				int diff = getPrice(mid) - getPrice(totElec - mid);
				if(diff < b) {
					s = mid;
				} else if(diff > b) { 
					e = mid;
				} else {
					s = mid;
					break;
				}
			}
			
			sb.append(getPrice(totElec-s)).append("\n");
			
		}
		System.out.println(sb);
	}
}