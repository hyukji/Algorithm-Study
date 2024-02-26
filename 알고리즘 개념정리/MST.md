# 최소신장트리

작성일시: 2024년 2월 22일 오전 8:52
주차: 6주차
복습: No

## 최소 신장트리(MST)

## 신장트리

- 신장 트리란 **그래프 상에서 모든 노드가 사이클 없이 연결된 부분 그래프**를 의미한다.
- **n개의 정점**으로 이루어진 **무향** 그래프에서 n개의 정점과 **n-1개의 간선**으로 이루어진 **트리**
    - 무향이 아니라면, 연결이 되지 않은 경우가 생기기 때문에 안됨!
    

## 최소 신장트리

- **무향 가중치** 그래프에서 신장트리를 구성하는 간선들의 **가중치의 합이 최소**인 신장트리

- 문제 유형
    - 모든 정점을 연결하는 간선들의 가중치의 합이 최소가 되는 트리
    - 두 정점 사이의 최소 비용의 경로 찾기
    
- 완탐 관점 → (E)_C_(N-1)  ⇒ 20C10 : 18만, 30C15 : 1억 5천만 ⇒ 완탐으로 찾기에는 너무 부담!

# KRUSKAL 알고리즘

- 간선을 하나씩 선택해서 MST를 찾는 알고리즘 → 간선중심의 해결 알고리즘.
- 전체 중에 최선(가중치가 가장 작은 간선)을 선택하는 방식으로 구현! but 그래프가 되지 않도록
    1. 모든 간선을 가중치에 따라 오름차순으로 정렬
    2. 가중치가 가장 낮은 + 사이클 형성되지 않을 간선을 선택 → 사이클 형성 여부에 Union, find 이용!
    3. n-1개의 간선이 선택될 때까지 2번 반복


### Union-find

1. make-set : 모든 정점은 각각 크기가 1인 트리 → 간선을 아무것도 선택하지 않은 상태
2. union(a,b) : (a가 속한 집합) U (b가 속한 집합)
3. find(a) : a가 속한 집합 대표자 찾기

## 시간복잡도

- **O(ElogV)의 시간복잡도**
    - **모든 가중치를 정렬하는데 걸리는 시간 = O(ElogE)**
    - **간선의 수는 최대 V^2 → O(ElogE) = O(ElogV^2) = O(2ElogV) = O(ElogV)**

```java
import java.io.*;
import java.util.*;
 
public class Solution  {
 
    static int n, m;
    static int[] p;
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
 
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc < T+1; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
             
            p = new int[n];
            for(int i = 0; i < n; i++) p[i] = i;
            PriorityQueue<int[]> pq= new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
             
            for(int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                int w = Integer.parseInt(st.nextToken());
                pq.offer(new int[] {a, b, w}); // pq에 넣어 정렬
            }
     
            long answer = 0, cnt = 0;
            while(cnt < n-1) {
                int[] cur = pq.poll();
                int a = cur[0];
                int b= cur[1];
                int w = cur[2];
                 
                if(!union(a, b)) continue;
                cnt++;
                answer += w;
            }
             
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
         
        System.out.print(sb);
    }
 
    private static boolean union(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        if(ra == rb) return false;
        p[ra] = rb;
        return true;
    }
 
    private static int find(int a) {
        if(p[a] == a) return a;
        return p[a] = find(p[a]);
    }

}
```

# PRIM 알고리즘

- 하나의 정점에서 연결된 간선들 중에 하나씩 선택하면서 MST를 만들어 가는 방식
    - 인접행렬, 인접리스트
    1. 임의 정점을 하나 선택해서 시작
    2. 선택한 정점과 인접하는 정점들 중의 최소 비용의 간선이 존재하는 정점을 선택
    3. 모든 정점이 선택될때까지 2번 반복

- 서로소인 2개의 집합 정보를 유지
    - 트리 정점들 - MST를 만들기 위해 선택된 정점들
    - 비트리 정점들 - 선택되지 않은 정점들

```java
import java.io.*;
import java.util.*;
 
// 프림
public class Solution  {
 
    static List<int[]>[] graph ;
    static int n, m;
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
 
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc < T+1; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
             
            boolean[] visited = new boolean[n];
            int[] minEdges = new  int[n];
            graph = new List[n];
            for(int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
                minEdges[i] = Integer.MAX_VALUE;
            }
             
            for(int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                int w = Integer.parseInt(st.nextToken());
                graph[a].add(new int[] {b, w});
                graph[b].add(new int[] {a, w});
            }
 
            long answer = 0, cnt = 0;       
            PriorityQueue<int[]> pq= new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
            minEdges[0] = 0;
            pq.offer(new int[] {0, 0});
            while(!pq.isEmpty()){
                int[] cur = pq.poll();
                int minV = cur[0];
                int minE =cur[1];
                 
                if(visited[minV]) continue;
                visited[minV] = true;
                answer += minE;
                if(++cnt == n) break;
                 
                for(int[] edge : graph[minV]) { // 연결된 간선들 중 최소
                    int nv = edge[0], ne = edge[1];
                    if(visited[nv]) continue;
                    if(minEdges[nv] > ne) { // 백트래킹 minEdge보다 작을 경우에만
                        pq.offer(new int[] {nv, ne});
                        minEdges[nv] = ne;
                    }
                }
            }
             
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
         
        System.out.print(sb);
    }
 
 
}
```

## 프림 알고리즘 시간복잡도

(개인적인 생각입니다. 참고만 해주세요)

pq를 이용해서 구현하게 된다면 당시 minEdge보다 작은 값들이 pq에 계속 쌓이게 된다. 

pq를 이용하지 않는다면 매번 n만큼 돌면서 가장작은 minEdge를 찾아야 한다. 

⇒ 문제 상황에 따라 더 빠른 방법이 달라진다.. 예측할 수도 없지 않나? 그냥 편한 방법을 하나 정해서 하는 게 좋아보인다!

![image](https://github.com/hyukji/Algorithm-Study/assets/52347271/7e586297-61a1-4446-9be5-5e6f17c8f353)


## **크루스칼 vs 프림**

|  | 크루스칼 알고리즘 | 프림 알고리즘 |
| --- | --- | --- |
| 탐색 방법 | 간선 위주 | 정점 위주 |
| 탐색 과정 | 시작점 따로 지정없이 최소 비용의 간선을 차례대로 대입하면서 사이클이 이루어지기 전까지 탐색 | 시작점을 지정한 후 가까운 정점을 선택하면서 모든 정점을 탐색 |
| 사용 | 간선의 개수가 적은 경우 크루스칼 알고리즘이 용이 | 간선의 개수가 많은 경우에는 정점 위주 탐색인 프림이 용이 |
| 시간복잡도 | O(ElogV) | O(ElogV) |
