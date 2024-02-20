package a0220.camp;

import java.io.*;
import java.util.*;

public class BOJ_14890_경사로 {
	
	static int n,x,ans;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine()," ");
		
		n = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		
		map = new int[n][n];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<n; j++) {
				map[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		
		ans =0; //활주로 건설 방법 갯수
		for(int k=0; k<n; k++) {
			if(Calcol(k)) ans++;
			if(Calrow(k)) ans++;
		}
		
		System.out.println(ans);
		
		
		br.close();
	}

	static boolean Calrow(int k) { //행
		boolean[] v = new boolean[n];
		
		for(int i=0; i<n-1; i++) {
			// 높이 차이가 1이상이면 건설 불가능하므로 바로 false를 return 한다.
			if(map[k][i]-map[k][i+1]>1 || map[k][i]-map[k][i+1]<-1) {
				return false;
			}
			// +1 높은 지형이 나왔을 때 왼쪽으로 내려가는 활주로를 설치할 수 있는지 확인
			else if(map[k][i]+1==map[k][i+1]) {
				// 길이가 x인 활주로를 설치할 수 있는지 
				for(int j=0; j<x; j++) {
					if(i-j<0 || v[i-j]) return false; // 범위를 벗어나거나 이미 활주로가 있으면 설치 못해용
					if(map[k][i-j]!=map[k][i]) return false; // 활주로 설치 구역에 지형이 모두 같아야함 틀리면 설치 x
					v[i-j]=true;
					
				}
			// -1 낮은 지형이 나왔을 때 오른쪽으로 내려가는 활주로를 설치할 수 있는지 확인
			}else if(map[k][i]-1==map[k][i+1]) {
				for(int j=1; j<x+1; j++) {
					if(i+j>=n || v[i+j]) return false; // 범위를 벗어나거나 이미 활주로가 있으면 설치 못해용
					if(map[k][i+j]!=map[k][i]-1) return false; // 활주로 설치 구역에 지형이 모두 같아야함 틀리면 설치 x
					v[i+j]=true;
					
				}
			}
		}

		return true;
	}

	static boolean Calcol(int k) { //열
		boolean[] v = new boolean[n];
		
		for(int i=0; i<n-1; i++) {
			if(map[i][k]-map[i+1][k]>1 || map[i][k]-map[i+1][k]<-1) {
				return false;
			}
			// +1 높은 지형이 나왔을 때 왼쪽으로 내려가는 활주로를 설치할 수 있는지 확인
			else if(map[i][k]+1==map[i+1][k]) {
				// 길이가 x인 활주로를 설치할 수 있는지 
				for(int j=0; j<x; j++) {
					if(i-j<0 || v[i-j]) return false; // 범위를 벗어나거나 이미 활주로가 있으면 설치 못해용
					if(map[i-j][k]!=map[i][k]) return false; // 활주로 설치 구역에 지형이 모두 같아야함 틀리면 설치 x
					v[i-j]=true;
				}
			// -1 낮은 지형이 나왔을 때 오른쪽으로 내려가는 활주로를 설치할 수 있는지 확인
			}else if(map[i][k]-1==map[i+1][k]) {
				for(int j=1; j<x+1; j++) {
					if(i+j>=n || v[i+j]) return false; // 범위를 벗어나거나 이미 활주로가 있으면 설치 못해용
					if(map[i+j][k]!=map[i][k]-1) return false; // 활주로 설치 구역에 지형이 모두 같아야함 틀리면 설치 x
					v[i+j]=true;
				}
			}
		}
		return true;
	}

}	

