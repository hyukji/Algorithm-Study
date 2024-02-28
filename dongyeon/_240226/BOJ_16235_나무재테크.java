package a0226.camp;

import java.io.*;
import java.util.*;

public class Main_BOJ_16235_나무재테크_서울_20반_고동연 {

	static int n,m,k;
    static int[][] map,temp;
    static PriorityQueue<int[]> tree;
    static PriorityQueue<int[]> live;
    static ArrayList<int[]> died;
    static ArrayList<int[]> now;
    static int[] di = {-1,-1,-1,0,0,1,1,1};
    static int[] dj = {-1,0,1,-1,1,-1,0,1};
    
    // 처음에 양분은 모든 칸에 5만큼 들어있다.
    // NxN크기의 땅, M개의 나무를 구매 
    // 같은 칸에 여러개의 나무가 심어져있을 수도 있음.
    // 봄: 자신의 나이만큼 양분먹음, 나이 +1 
    // 하나의 칸에 여러개의 나무가 있으면, 나이가 어린 나무부터 양분을 먹음.
    // 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 죽음.
    // 여름: 봄에 죽은 나무가 양분으로 변함. 죽은 나무마다 나이를 2로 나눈 값이 양분으로 추가댐.
    // 가을: 나무가 번식, 나이가 5의 배수인 나무가 번식, 인접한 8개의 칸에 나이1인 나무 생김.
    // 겨울: 로봇이 돌아다니면 땅에 양분추가 
    // k년이 지난 후 땅에 살아있는 나무의 개수?
    // 조건 존나 많네 
    // 시간초과
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        map = new int[n][n];
        
        for(int i=0;i<n;i++) {
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<n;j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        
        tree = new PriorityQueue<>((o1,o2)->{return o1[2]-o2[2];});
        for(int k = 0; k<m; k++) {
            st=new StringTokenizer(br.readLine()," ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            
            tree.offer(new int[]{x-1,y-1,z});
        }
        
        temp = new int[n][n];
        
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                temp[i][j]=5;
            }
        }
        
        
        for(int i=0; i<k; i++) {
        	died = new ArrayList<>();
        	live = new PriorityQueue<>((o1,o2)->{return o1[2]-o2[2];});
        	// 봄 
        	while(!tree.isEmpty()) {
        		int[] t = tree.poll();
        		System.out.println(Arrays.toString(t));
        		if(temp[t[0]][t[1]]-t[2]>=0) {
        			temp[t[0]][t[1]]-=t[2];
        			live.offer(new int[] {t[0],t[1],t[2]+1});
        		}else {
        			died.add(new int[] {t[0],t[1],t[2]});
        		}
        	}
        	
        	tree=live;
        	// tree가 비어 있지 않으면 답은 맞는데 틀렸다고 나온다. . 
        	// 계속 값들을 두고 가면, , 
        	// pq는 for each로 돌리면 최상단만 최소고 그 뒤는 정렬이 되어있지 않은 상태로 나와서
        	// 틀리는 듯하다. - 경헌 피셜 - 맞다
        	
        	// 여름
        	for(int j=0; j<died.size();j++) {
        		int[] t = died.get(j);
    			int plus = t[2]/2;
    			temp[t[0]][t[1]]+=plus;
        	}
        	
        	// 가을
        	now = new ArrayList<>();
        	for(int[] t:tree) {
        		int x = t[0];
        		int y = t[1];
        		if(t[2]%5==0) {
        			for(int d=0; d<8; d++) {
        				int nx = x+di[d];
        				int ny = y+dj[d];
        				
        				if(nx>=0 && nx<n && ny>=0 && ny<n) {
        					now.add(new int[]{nx,ny,1});
        				}
        			}
        		}
        	}
        	
        	for(int[] t:now) tree.offer(new int[]{t[0],t[1],t[2]});
        	
        	//겨울
        	for(int p=0;p<n;p++) {
                for(int q=0;q<n;q++) {
                    temp[p][q]+=map[p][q];
                }
            }
        }
        
        
        System.out.println(tree.size());
        
        br.close();
    }


}
