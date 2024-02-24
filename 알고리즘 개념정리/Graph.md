# SSAFY GRAPH1

# 그래프

- 정점들의 집합과 이들을 연결하는 간선들의 집합으로 구성된 자료구조
- “비선형” 자료구조
- 정점들과 정점들간의 관계(간선) 표현
- N:N 관계를 가지는 원소들을 표현하기에 용이

<img width="150" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/8547f37e-9880-4a62-8d58-9c322a646adc" />

- 정점: 그래프의 구성요소로 하나의 연결 점
- 간선: 두 정점을 연결하는 선(관계를 나타냄)
- 차수: 정점에 연결된 간선의 수(인접정점의 수)
    - 트리에서 노드 차수: 노드와 연결된 간선 수 ⇒ 자식노드의 수
- V 개의 정점을 가지는 무향 그래프는 최대 V * (V-1) / 2 간선이 가능
- 관계성
    - 단방향
    - 양방향(무방향)

# 문제 유형

- 정점 중심으로 해결: 인접 행렬, 인접 리스트 필요
    - 최소신장트리: 프림
    - 최단경로: 다익스트라(양의 가중치 그래프에 한정)
- 간선 중심으로 해결: 간선 리스트
    - ⇒ 최소신장트리: 크루스칼
    - 최단경로: 벨만 포드(간선을 기준으로 소모)

# 그래프 유형

### 무향(양방향) 그래프

- 간선의 개수: v*(v-1)/2

### 유향 그래프

- 각 접점 가질 수 있는 최대 간선수 V-1 ⇒ 완전 그래프 ( dense graph)
- 모든 정점이 서로 연결되어있다면 … v(v-1)

### 가중치 그래프

- 간선에 수치 부여(관계의 정도)

사이클 없는 방향 그래프(무방향은 사이클이 있는거임)

### 완전 그래프

- 정점들에 대해 가능한 모든 간선들을 가진 그래프

### 부분 그래프(기존 그래프의 부분집합)

- 원래 그래프에서 일부의 정점이나 간선을 제외한 그래프

### 트리도 그래프 (계층형 구조)

- 두 노드 사이에는 유일한 경로가 존재 MST(최소신장트리) → 트리는 싸이클이 발생하면 X

# 용어 정리

인접

- 두 개의 정점에 간선이 존재 ⇒ 서로 인접해있다.

경로

- 어떤 정점 A에서 시작하여 다른 정점 B로 끝나는 순회 → 간선따라 ~
    
    ⇒ 간선들의 순서가 경로가 된다.
    
    ⇒ 경로는 여러 가지일 수 있다.
    

싸이클

- 시작 정점 == 종료 정점

# 간선의 정보를 저장하는 방식(구성 형태, 공간 복잡도)

→ 메모리 - 구성하는 방식(형태)에 따라서 복잡도가 달라지기 때문에

→ 성능 - 정점의 개수, 간선 개수 ⇒ 그래프 탐색(인접한 정점 간선 따라따라~)

1. 인접 행렬 (정점 중심) - V * V 2차원 배열
    
    adjMatrix[i][j]: i → j 인접 관계 여부(수치로) from(x) → to(y)
    
    a와 인접한 행렬을 다 보기위해서 하나의 행을 다 봐야 한다.
    
    V는 크지만, E는 상대적으로 적다.
    
    - ex) V: 10000, E: 100000 (유향 그래프 기준), 최대 V^2
    - ⇒ 한 정점당 평균 10개 ⇒ 최소 그래프(밀집 그래프X)
    - ⇒ 10000 * 10000 graph인데 한 행에 평균 10개만 쓰므로 99990개는 쓰지 않음, 그러나 이 크기를 유지해야함.
    - 인접 행렬은 가능한 크기안에서 밀집 그래프 형태 일때 유리하다.(완전 그래프는 밀집 그래프), 대부분의 정점이 관계를 대부분 가지고 있을 떄
    - 만약 가중치가 없다면, 인접 여부(true: 1, false: 0)만 확인하면 된다.
    - 가중치가 있다면, 가중치 값을 저장한다, 가중치가 없다면 인접하지 않은 것, 1이상일 경우 가중치 O
    - 무향 그래프의 경우, i번째 행의 합(가중치 표현 0이 아닌 개수)  = i번째 열의 합 = Vi의 차수(간선 개수)
    - 유향 그래프의 경우, 행 i의 합 = Vi의 진출 차수, 열 i의; 합 = Vi의 진입 차수
    - 최대간선수 계산 50% 위 → 밀집 else 희소
    
    <img width="500" alt="Untitled" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/e4f1af66-9d89-431b-b7dc-ee27fa33abb5">
    <img width="150" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/b88ecbc6-1e9c-47c2-8220-ac18648402c0" />

2. 인접 리스트(정점 중심) ⇒ 인접 행렬의 단점을 보안 → 따라서 인접한 것들만 쏙쏙 빼서 각 정점마다 인접한 행렬의 정점만 저장하는 형태를 인접 리스트라고 한다. ⇒ 희소 그래프일 때 훨씬 유리하다.
    1. 희소그래프(공간복잡도 개선, 탐색 속도 개선)

<img width="500" alt="Untitled 1" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/2f204d59-a9fb-4e54-856e-3a4d53cb126d">

1. 간선 리스트(간선 중심)
- 간선 정보 하나(시작 정점, 끝 정점)를 객체로 표현해서 리스트에 저장, 만약 가중치가 있다면 가중치도 저장한다.

<img width="500" alt="Untitled 2" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/12c53b2a-a3f4-4a18-950a-06802e23c1e8">

# 그래프 탐색

- 정점 중심: 인접 행렬(V*V), 인접 리스트
- 간선 중심: 간선리스트

그래프 순회는 비선형구조인 그래프 → 모든 자료를 빠짐없이 탐색 → DFS, BFS

# 너비우선탐색(BFS)

- 탐색 시작점의 인접한 정점들을 먼저 모두 차례로 방문한 후에, 방문했던 정점을 시작점으로 하여 다시 인접한 정점들을 차례로 방문하는 방식

## 선입선출 형태의 자료구조인 큐를 활용함.

<img width="500" alt="Untitled 3" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/c2f6d3f2-02d0-473f-a5db-4399b71d0bfa">

## 인접행렬을 통한 BFS 구현

<img width="500" alt="Untitled 4" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/73eaf92d-7653-44e0-867c-ff07cb789c9b">

## 인접 리스트를 통한 BFS 구현

<img width="500" alt="Untitled 5" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/716d790a-d02e-41dc-a113-3411a7d3b74c">

# 깊이 우선 탐색(DFS) - 정점중심

- 시작 정점의 한 방향으로 갈 수 있는 경로가 있는 곳까지 깊이 탐색해 가다가 더 이상 갈 곳이 없게 되면, 가장 마지막에 만났던 갈림길 간선이 있는 정점으로 되돌아와서 다른 방향의 정점으로 탐색을 계속 반복하여 결국 모든 정점을 방문하는 순회방법

## 재귀적으로 구현하거나, 후입 선출 구조의 스택 사용

<img width="358" alt="Untitled 6" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/482dbb21-c934-4b00-8a1c-d4e66cdedec3">

## 인접 행렬을 통한 DFS 구현

<img width="500" alt="Untitled 7" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/d38d745b-02f9-44a0-a204-8659a568976e">

## 인접 리스트를 통한 DFS 구현

<img width="500" alt="Untitled 8" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/c9356303-77d2-4303-81f8-5f5c60def5b1">

그래프에 대한 알고리즘 → 둘 다 풀수는 있음.

- Flood Fill 알고리즘(DFS)
    - 그래프(2차원격자) → 4방, 8방탐색을 할 것인데, BFS OR DFS?
    - → 그래프의 연결성을 확인한다.
- 위상 정렬(BFS)
    - ‘유향 그래프’ (방향)
    - 순서, 사전(사후) 관계 → 정점과 정점간의 관계, 사전의 관계, 사후 관계를 통해서 순서를 통해 나타낸다.
    
    BFS: 탐색순서: 큐로 관리
    
    DFS: 탐색순서: 메소드 호출(재귀 호출) 관리, (간접: Stack)
    
    공통: 방문 관리(그래프 같은 정점 2번이상 방문 가능을 막기 위해서)
    
    # Flood Fill 알고리즘
    
    - 다차원 배열의 어떤 칸과 연결된 영역을 찾는 알고리즘
    - 시작점으로부터 연결된 영역을 찾는 알고리즘
    - 연결된 영역에 “채우기” 도구에 사용되며, 지뢰 찾기 같은 게임에서 어떤 비어 있는 칸을 표시 할 지를 결정할 때에도 사용된다.
    - 4방향, 8방향
    - 구현방법: DFS, BFS
    
    연결된 땅(걸어갈 수 있는 땅을) ⇒ 하나의 섬
    
    섬 식별되지 않은 땅에서 출발
    
    8방 인접해있는 섬 식별되지 않은 땅 탐색
    
    - DFS
    - BFS

# 위상 정렬(Topology Sort)

<img width="500" alt="Untitled 9" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/95640e16-883e-4151-9fbc-ceaa18a57b17">

- 순서가 정해져 있는 작업들을 차례대로 수행해야 할 때, 그 순서를 결정해 주는 알고리즘
- 유향 그래프의 정점들을 변(간선)의 방향을 거스르지 않도록 나열하는 것.
- 정점: 과목, 간선: 선수 과목 관계 ⇒ 유향 그래프
- 중요 !! 위상 정렬이 성립하기 위해서는 반드시 그래프의 순환이 존재하지 않아야 한다.(싸이클X)
    - 싸이클 생성시 → 순서를 알 수 없게됨.

관계를 알 수 없는 (간선정보가 없는) 정점들은 선/후 관계가 없다.

→ 따라서 결과가 여러 개

위상정렬이 가능하다면 → 정렬된 결과(싸이클 X)

가능하지 않다면 → 싸이클이 발생된 결과이다.

# 구현방식

- DFS
- BFS

BFS가 훨씬 이해하기 쉽고, 구현도 쉽다.

# 위상 정렬의 구현(BFS)

진입차수: 자신으로 들어오는 간선의 수

차수: 정점에 연결된 간선 수

![12121211111111](https://github.com/hyukji/Algorithm-Study/assets/48114924/c287c676-39f9-4016-867d-395f9666d293)

전 → 후 관계 : 진입차수가 0인 노드부터 시작!(들어오는 간선이 X)

## “Queue” 정렬 순서 관리

1. 전처리: 모든 정점의 진입차수를 계산
2. 진입차수가 0인 노드들을 queue에 넣는다.(선수(사전) 정점이 없는 정점)
3. 간선을 지워주는 느낌으로 해석하자! 다른 노드의 간선의 진입차수를 줄여주면서, 진입차수가 0인 노드들이 들어온다.
- 진입차수가 0인 노드가들어와서 1인 노드의 진입차수를0으로 줄여주고, 그 노드를 다시 큐에 집어넣는다.
- 싸이클의 경우 0인 진입차수가 없다. ⇒ 서로 맞물려서
- count로 체크한다.

⇒ C A B

위상 정렬 예시 코드)

<img width="545" alt="Untitled 4" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/c59207f6-4087-4558-9623-e58747802001"> <br/>
<img width="545" alt="Untitled 5" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/f140a643-d2d1-441a-b06d-3c2b814249cf"> <br/>
<img width="545" alt="Untitled 6" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/f72aa56d-b70f-4f73-b3ca-3200cff12424"> <br/>
<img width="545" alt="Untitled 7" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/8d16fc8d-3c94-40dd-8b80-e5b262b6aed3"> <br/>
<img width="545" alt="Untitled 8" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/ddff96a5-278c-4d64-91dd-735b01dbc0ed"> <br/>
<img width="545" alt="Untitled 9" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/501fee91-1c24-4bd1-a4a7-68ac1a01375a"> <br/>
<img width="545" alt="Untitled 10" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/05569fa6-1e28-467e-834e-afa01ddb8bce"> <br/>
<img width="545" alt="Untitled 11" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/54c0d5c6-ab70-414b-8f68-32d87a5a6654"> <br/>

<br/>


```java
import java.util.*;

public class TopologicalSort {
    static int n;
    static int e;

    public static void main(String[] args) {
        n = 7; // 정점 갯수
        e = 9; // 간선 갯수
        int[] indegree = new int[n + 1];
        List<List<Integer>> array = new ArrayList<List<Integer>>();

        for (int i = 0; i < n + 1; i++)
            array.add(new ArrayList<Integer>());

        // 간선 목록 v1 -> v2
        int[] v1 = {1, 1, 2, 4, 3, 3, 5, 2, 5};
        int[] v2 = {2, 3, 5, 6, 4, 7, 6, 4, 4};

        /**
         * 1. v1 -> v2 인접리스트 생성
         * 2. v2 를 가리키는 노드 갯수 indegree 증가
         */
        for (int i = 0; i < e; i++) {
            int c1 = v1[i];
            int c2 = v2[i];

            array.get(c1).add(c2);
            indegree[c2]++;
        }

        topologicalSort(indegree, array);
    }

    static void topologicalSort(int[] indegree, List<List<Integer>> array) {
        Queue<Integer> q = new LinkedList<Integer>();
        Queue<Integer> result = new LinkedList<Integer>();

        // 큐에 indegree 가 0 인 노드 담기
        for (int i = 1; i < n + 1; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        /**
         * 1. 큐에서 값을 꺼내며 해당 노드가 가리키는 노드의 indegree 를 1 감소
         * 2. 만약 indegree가 0 이 된다면 큐에 넣기
         * 3. 큐가 빌때까지 반복
         */
        while (!q.isEmpty()) {
            int node = q.poll();
            result.offer(node);

            for (Integer i : array.get(node)) {
                indegree[i]--;

                if (indegree[i] == 0) {
                    q.offer(i);
                }
            }
        }

        System.out.println(result);
    }
}
```
