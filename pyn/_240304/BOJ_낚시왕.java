package a0223;

import java.io.*;
import java.util.*;

public class Solution_bj_17143_낚시왕_서울_20반_박영남 {
	static int[] di= {-1, 1, 0, 0};
	static int[] dj= {0, 0, 1, -1};
	static class Shark{
		int r, c, s, d, z, sharkNum;
		Shark(int r, int c, int s, int d, int z, int sharkNum){
			this.r=r;
			this.c=c;
			this.s=s;
			this.d=d;
			this.z=z;
			this.sharkNum=sharkNum;
		}
	}
	static int R, C, M;
	static Shark[][] sea;
	static Shark[] sharks;
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine(), " ");
		
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		sea=new Shark[R][C];
		sharks=new Shark[M];
		int sharkNum=1;
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine(), " ");
			
			int r=Integer.parseInt(st.nextToken())-1;
			int c=Integer.parseInt(st.nextToken())-1;
			int s=Integer.parseInt(st.nextToken());
			int d=Integer.parseInt(st.nextToken())-1;
			int z=Integer.parseInt(st.nextToken());
			
			sharks[sharkNum-1]=new Shark(r,c,s,d,z,sharkNum);
			sea[r][c]=new Shark(r,c,s,d,z,sharkNum++);
		}
		int ans=0;
		for(int j=0; j<C; j++) { // 사람 오른쪽으로 이동
			ans+=fishing(j); // 낚시하기
			moving(); // 움직이기
		}
		System.out.println(ans);
		br.close();
	}
	static int fishing(int j) { // 낚시
		int size=0;
		for(int i=0; i<R; i++) {
			if(sea[i][j]!=null) {
				size+=sea[i][j].z;
				sharks[sea[i][j].sharkNum-1]=null; // 잡은 상어 배열에서 빼주기
				sea[i][j]=null;
				break;
			}
		}
		return size;
	}
	static void moving() { // 움직이기
		for(int i=0; i<M; i++) {
			if(sharks[i]!=null) {
				int r=sharks[i].r;
				int c=sharks[i].c;
				int s=sharks[i].s;
				int d=sharks[i].d;
				sea[r][c]=null;
				if(d==0 || d==1) s%=2*(R-1); // 주기로 나눠 주기
				else s%=2*(C-1); // 주기로 나눠 주기
				while(s>0) {
					int ni=r+di[d];
					int nj=c+dj[d];
					if(0<=ni&&ni<R && 0<=nj&&nj<C) {
						r+=di[d];
						c+=dj[d];
					} else { // 경계에 부딪히면
						d=(5-d)%4; // 방향 바꿔주기
						r+=di[d];
						c+=dj[d];
					}
					s--;
				}
				// 움직인 후 좌표, 방향 결과를 sharks에 저장
				sharks[i]=new Shark(r,c,sharks[i].s,d,sharks[i].z,sharks[i].sharkNum);
			}
		}
		for(int i=0; i<M; i++) { // 이동이 끝난 후 같은 칸에 있을 시 잡아먹기
			if(sharks[i]!=null) {
				if(sea[sharks[i].r][sharks[i].c]==null)	sea[sharks[i].r][sharks[i].c]=sharks[i];
				else {
					if(sea[sharks[i].r][sharks[i].c].z>sharks[i].z) sharks[i]=null;
					else {
						sharks[sea[sharks[i].r][sharks[i].c].sharkNum-1]=null;
						sea[sharks[i].r][sharks[i].c]=sharks[i];
					}
				}
			}
		}
	}
}
