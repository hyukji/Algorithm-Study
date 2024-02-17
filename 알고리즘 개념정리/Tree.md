# 트리

- 노드들이 나무 가지처럼 연결된 비선형 계층적 자료구조
- 비선형 구조
- 원소들 간에 1:n 관계를 가지는 자료구조
- 원소들 간에 계층관계를 가지는 계층형 자료구조

<img width="506" alt="Untitled" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/d2ce1740-1f15-44f2-97c7-9c6034a94ad8">

# 용어 정리

- 노드(node) - 트리의 원소
- 간선(edge) - 노드와 노드를 연결하는 선으로 부모 노드와 자식 노드를 연결
- 루트 노드(root node) - 트리의 시작 노드인 최상위 노드 → A
- 형제 노드(sibling node) - 같은 부모 노드의 자식 노드들 - B,C,D는 형제 노드
- 조상 노드 - 간선을 따라 루트 노드까지 이르는 경로에 있는 모든 노드들 → K의 조상 노드: F, B, A
- 서브 트리(subtree) - 부모 노드와 연결된 간선을 끊었을 때 생성되는 트리

<img width="506" alt="Untitled 1" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/2604bf34-632d-4971-a3ec-b78946279430">

이들 T1, … TN은 각각 하나의 트리가 되며(재귀적 정의) 루트의 부 트리(subtree)라 한다.

⇒ 하위 트리안에 또 다른 하위 트리가 있는 재귀적 자료구조

- 자손 노드 - 서브 트리에 있는 하위 레벨의 노드들 - B의 자손 노드 - E,F, K
- 차수(degree)
    - 노드의 차수: 노드에 연결된 자식 노드의 수(B의 차수=2, C의 차수=1)
    - 트리의 차수: 트리에 있는 노드의 차수 중에서 가장 큰 값(트리 T의 차수=3)
    - 단말 노드(리프 노드): 차수가 0인 노드 즉, 자식 노드가 없는 노드
- 높이
    - 노드의 높이: 루트에서 노드에 이르는 간선의 수. 노드의 레벨(B의 높이=1, F의 높이=2)
    - 트리의 높이: 트리에 있는 노드의 높이 중에서 가장 큰 값. 최대 레벨(트리 T의 높이=3)

# 이진트리

- 차수가 2인 트리
- 각 노드가 자식 노드를 최대한 2개 까지만 가질 수 있는 트리
    - 왼쪽 자식 노드(left child node)
    - 오른쪽 자식 노드(right child node)
- 위에서부터 높이0 (레벨0) ~ 차례로 내려감
- 모든 노드들이 최대 2개의 서브트리를 갖는 특별한 형태의 트리
- 이진 트리의 예

<img width="584" alt="Untitled 2" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/dfb1a497-f024-497e-bba8-5b0522e7f548">

이진트리의 특성

- 높이 i(레벨 i)에서의 노드의 최대 개수: 2^i개
- 높이가 h인 이진 트리가 가질 수 있는 노드의 최소 개수는 (h+1)개가 되며, 최대 개수는 2^(h+1)-1개

<img width="821" alt="Untitled 3" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/bccb5a42-968d-46b7-b3d1-d483b9814698">

# 이진 트리의 종류

### 포화 이진 트리(Perfect Binary Tree)

- 모든 레벨에 노드가 포화 상태로 차 있는 이진 트리
- 높이가 h일 때, 최대의 노드 개수인 2^(h+1)-1의 노드를 가진 이진 트리
    - 높이가 3일 때, 2^3+1-1 = 15개의 노드
- 루트를 1번으로 하여 2^(h+1)-1까지 정해진 위치에 대한 노드 번호를 가짐
- ⇒ 모든 리프노드의 높이가 동일

<img width="538" alt="Untitled 4" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/d46e0042-a929-491f-8e14-5ae8c6e9f8c2">

### 완전 이진 트리(Complete Binary Tree)

- 높이가 h이고 노드 수가 n개일 때 (단 h+1 ≤ n < 2^(h+1)-1), 포화 이진 트리의 노드 번호 1번부터 n번까지 빈 자리가 없는 이진 트리
- 리프노드의 높이가 동일하지 않을 수 있다.

<img width="544" alt="Untitled 5" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/3e7099da-ec29-48d3-b575-ab2b20c31f7a">

### 편향 이진 트리(Skewed Binary Tree)

- 높이 h에 대한 최소 개수의 노드를 가지면서 한쪽 방향의 자식 노드 만을 가진 이진 트리
    - 왼쪽 편향 이진 트리
    - 오른쪽 편향 이진 트리

<img width="502" alt="Untitled 6" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/24847ab5-9822-4060-8f8f-d99dc3664731">

이진트리의 표현 - 배열

- 노드 번호가 i인 노드의 노드 번호: i / 2
- 노드 번호가 i인 노드의 왼쪽 자식 노드 번호? 2 * i
- 노드 번호가 i인 노드의 오른쪽 자식 노드 번호? 2 * i + 1
- 레벨 n의 노드 번호 시작 번호는? 2^n

<img width="413" alt="Untitled 7" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/5a5cf45f-c7a2-42c3-a6c8-e59f4ad275b8">
<img width="321" alt="Untitled 8" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/2b7c5dc2-9792-4579-8636-c44ec6896e29">
<img width="765" alt="Untitled 9" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/fbbc01ea-cdc0-4a0d-af9f-f2ed4f83c41a">


배열을 이용한 이진 트리의 표현의 단점

- 편향 이진 트리의 경우에 사용하지 않는 배열 원소에 대한 메모리 공간 낭비 발생
- 트리의 중간에 새로운 노드를 삽입하거나 기존의 노드를 삭제할 경우 배열의 크기 변경 어려워 비효율적

```java
import java.util.ArrayDeque;
import java.util.Queue;

// 완전이진트리 = 배열 구현
// 크기 늘리지 않는 버전
public class CompleteBinaryTree<T> {
	
	private Object[] nodes;
	private final int SIZE;
	private int lastIndex; // 마지막으로 저장된 노드의 인덱스
	
	public CompleteBinaryTree(int size) {
		SIZE = size;
		nodes = new Object[size+1]; // 0인덱스 사용하지 않음
}
	public boolean isEmpty() {
		return lastIndex == 0;
	}
	public boolean isFull() {
		return lastIndex == SIZE;
	}
	public void add(T e) {
		if(isFull()) {
			System.out.println("포화상태입니다.");
			return;
		}
		nodes[++lastIndex] = e;
	}
```

비선형 자료구조 완전탐색

- 비선형 구조인 트리, 그래프의 각 노드(정점)를 중복되지 않게 정부 방문(visit) 하는 것을 말하는데, 비선형구조는 선형 구조에서와 같이 선후 연결 관계를 알 수 없다.
    - 너비 우선 탐색(BFS)
        - 루트 노드의 자식 노드들을 먼저 모두 차례로 방문한 후에, 방문했던 자식 노드들을 기준으로 하여 다시 해당 노드의 자식 노드들을 차례로 방문하는 방식
            - 인접한 노드들에 대해 탐색을 한 후, 차례로 다시 너비우선탐색을 진행해야 하므로, 선입선출 형태의 자료구조인 큐를 활용함.
    
    ```java
    // BFS sudo code
    BFS()
    	큐를 생성
    	루트 v를 큐에 삽입
    	while (큐가 비어 있지 않은 경우) {
    		t <- 큐의 첫 번째 원소 반환
    		t 방문
    		for ( t와 연결된 모든 간선에 대해) {
    			u <- t의 자식노드
    			u를 큐에 삽입
    		}
    	}
    end BFS()
    ```
    <img width="837" alt="Untitled 10" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/0a16376c-0f56-477f-ab19-da6f539e1aa5">
    
    ```java
    public void bfs() {
    		if(isEmpty()) return;
    		
    		Queue<Integer> queue = new ArrayDeque<>(); 
    		queue.offer(1);// 방문할 노드를 관리할 값 넣기(노드 번호인덱스)
    		
    		while(!queue.isEmpty()) { // 방문할 대상이 있는 동안 반복
    			int current = queue.poll();
    			System.out.println(nodes[current]); // 노드 처리로직
    			
    			// 왼쪽 자식노드
    			if(current*2<=lastIndex) queue.offer(current*2);
    			// 오른쪽 자식노드
    			if(current*2+1<=lastIndex) queue.offer(current*2+1);
    		}
    	}
    /*
    A
    B
    C
    D
    E
    F
    G
    H
    I
    J
    */
    	//큐에 탐색할 노드 번호와 너비 함께 넣기
    	public void bfs2() {
    		if(isEmpty()) return;
    		Queue<int[]> queue = new ArrayDeque<>(); 
    		queue.offer(new int[] {1,0});// 방문할 노드를 관리할 값 넣기(노드 번호인덱스)
    		
    		int[] info;
    		while(!queue.isEmpty()) { // 방문할 대상이 있는 동안 반복
    			info = queue.poll();
    			int current = info[0];
    			int breadth = info[1];
    			System.out.println(nodes[current]+"("+breadth+")"); // 노드 처리로직
    			// 왼쪽 자식노드
    			if(current*2<=lastIndex) queue.offer(new int[] {current*2,breadth+1});
    			// 오른쪽 자식노드
    			if(current*2+1<=lastIndex) queue.offer(new int[] {current*2+1,breadth+1});
    		}
    	}
    /*
    A(0)
    B(1)
    C(1)
    D(2)
    E(2)
    F(2)
    G(2)
    H(3)
    I(3)
    J(3)
    */
    	// 동일 너비 단위로 처리
    	public void bfs3() {
    		if(isEmpty()) return;
    		Queue<Integer> queue = new ArrayDeque<>(); 
    		queue.offer(1);// 방문할 노드를 관리할 값 넣기(노드 번호인덱스)
    		
    		int current=0,size=0,breadth=0;
    		while(!queue.isEmpty()) { // 방문할 대상이 있는 동안 반복
    			size = queue.size(); // 큐 사이즈 체크 : 동일 너비의 탐색대상노드의 개수
    			System.out.println("breadth"+breadth+" : ");
    			while(--size>=0) {
    				current = queue.poll();
    				System.out.println(nodes[current]); // 노드 처리로직
    				// 왼쪽 자식노드
    				if(current*2<=lastIndex) queue.offer(current*2);
    				// 오른쪽 자식노드
    				if(current*2+1<=lastIndex) queue.offer(current*2+1);
    			}
    			System.out.println();
    			++breadth; //너비증가(다음 탐색을 위해)
    		}
    	}
    /*
    breadth0 : 
    A
    
    breadth1 : 
    B
    C
    
    breadth2 : 
    D
    E
    F
    G
    
    breadth3 : 
    H
    I
    J
    */
    ```
    
    - 깊이 우선 탐색(DFS)
        - 루트 노드에서 출발하여 한 방향으로 갈 수 있는 경로가 있는 곳까지 깊이 탐색해 더 이상 갈 곳이 없게 되면, 가장 마지막에 만났던 갈림길 간선이 있는 노드로 되돌아와서 다른 방향의 노드로 탐색을 계속 반복하여 결국 모든 노드를 방문하는 순회방법
        - 가장 마지막에 만났던 갈림길의 노드로 되돌아가서 다시 깊이 우선 탐색을 반복해야 하므로 재기ㅜ적으로 구현하거나 후입선출 구조의 스택 사용해서 구현
    
    ```java
    // DFS sudo code
    DFS(v)
    	v 방문;
    	for(v의 모든 자식 노드 w) {
    		DFS(w);
    	}
    end DFS()
    ```
<img width="516" alt="Untitled 11" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/d8ca855c-daec-4136-896b-edd783c044bf">

이진트리 - 순회(traversal)

순회(traversal): 트리의 노드들을 체계적으로 방문하는 것.

- 전위순회(preorder traversal): VLR
    - 부모노드 방문 후, 자식노드를 좌,우 순서로 방문한다.
- 중위순회(inorder traversal): LVR
    - 왼쪽 자식노드, 부모 노드, 오른쪽 자식노드 순으로 방문한다.
- 후위순회(postorder traversal): LRV
    - 자식노드를 좌우 순서로 방문한 후, 부모노드로 방문한다.

전위 순회(preorder traversal)

```java
// 전위 순회
preorder_traverse(T)
	if (T is not null)
	{
		visit(T);
		preorder_traverse(T.left);
		preorder_traverse(T.right);
	}
End preorder_traverse
```

<img width="850" alt="Untitled 12" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/9c828393-cb91-403d-aeb3-54b492f2ff7c">

```java
public void dfsByPreorder() {
		if(isEmpty()) return;
		System.out.println("===preorder===");
		dfsByPreoder(1);
		System.out.println();
	}
	private void dfsByPreoder(int current) { //전위 순회
		System.out.print(nodes[current]+" "); // 노드 처리로직
		// 왼쪽 자식노드
		if(current*2<=lastIndex) dfsByPreoder(current*2);
		// 오른쪽 자식노드
		if(current*2+1<=lastIndex) dfsByPreoder(current*2+1);
		
	}

/*
===preorder===
A B D H I E J C F G
*/
```

중위 순회(inorder traversal)

```java
// 중위 순회 sudo code

inorder_traverse(T)
	if(T is not null)
	{
		inorder_traverse(T.left);
		visit(T);
		inorder_traverse(T.right);
	}
End inorder_traverse
```

<img width="850" alt="Untitled 13" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/ad96fcfd-8d58-4af3-bde6-ba2dd23d101d">

```java
public void dfsByInorder() {
		if(isEmpty()) return;
		System.out.println("===inorder===");
		dfsByInorder(1);
		System.out.println();
	}
	private void dfsByInorder(int current) { // 중위 순회

		// 왼쪽 자식노드
		if(current*2<=lastIndex) dfsByInorder(current*2);
		System.out.print(nodes[current]+" "); // 노드 처리로직
		// 오른쪽 자식노드
		if(current*2+1<=lastIndex) dfsByInorder(current*2+1);
		
	}

/*
===inorder===
H D I B J E A F C G
*/
```

후위 순회(postorder traversal)

```java
// 후위 순회 sudo code
postorder_traverse(T)
	if(T is not null)
	{
		postorder_traverse(T.left);
		postorder_traverse(T.right);
		visit(T);
	}
End postorder_traverse
```

<img width="835" alt="Untitled 14" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/eb0d316f-eccf-4b6c-8612-4e7bee3507b6">

```java
public void dfsByPostorder() {
		if(isEmpty()) return;
		System.out.println("===postorder===");
		dfsByPostorder(1);
		System.out.println();
	}
	private void dfsByPostorder(int current) { // 후위 순회
		if(current>lastIndex) return;
		// 왼쪽 자식노드
		if(current*2<=lastIndex) dfsByPostorder(current*2);
		// 오른쪽 자식노드
		if(current*2+1<=lastIndex) dfsByPostorder(current*2+1);
		System.out.print(nodes[current]+" "); // 노드 처리로직
		
	}

/*
===postorder===
H I D J E B F G C A
*/
```

수식 트리

- 수식을 표현하는 이진 트리
- 수식 이진 트리(Expression Binary Tree)라고 부르기도 함.
- 연산자는 루트 노드이거나 가지 노드
- 피연산자는 모두 잎 노드

수식트리의 순회

<img width="323" alt="Untitled 15" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/343b0547-208d-4dbf-83c5-6ce1056c0fa8">

- 중위 순회: A / B * C * D + E
- 후위 순회: A B / C * D * E +
- 전위 순회: + * * / A B C D E

힙(heap)

완전 이진 트리에 있는 노드 중에서 키 값이 가장 큰 노드나 키 값이 가장 작은 노드를 찾기 위해서 만든 자료구조

힙의 종류

- 최대 힙(max heap)
    - 키 값이 가장 큰 노드를 찾기 위한 완전 이진 트리
    - 부모≥ : 최대힙 → 루트노드값: 최댓값 O(logN)
- 최소 힙(min heap)
    - 키 값이 가장 작은 노드를 찾기 위한 완전 이진 트리
    - 부모≤ 최소힙→ 루트노드값: 최솟값

<img width="679" alt="Untitled 16" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/c2a44b69-3dad-4805-9a02-15bb82490608">

힙 연산 - 삽입 ⇒ 규칙: 완전 이진 트리의 형태

ex) 최대힙에서 17 삽입

<img width="720" alt="Untitled 17" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/79135404-c1e1-4e5d-a351-72ebeff9d7f0">

ex) 최대힙에서 23 삽입

<img width="703" alt="Untitled 18" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/a1f52dce-84e6-41ea-8958-bda02b1cbcf1">

힙 연산 - 삭제 O(logN) 규칙: 완전 이진 트리의 형태

- 힙에서는 루트 노드의 원소만을 삭제할 수 있다.
- 루트 노드의 원소를 삭제하여 반환한다.
- 힙의 종류에 따라 최대값 또는 최소값을 구할 수 있다.

<img width="703" alt="Untitled 19" src="https://github.com/hyukji/Algorithm-Study/assets/48114924/001777fc-8e17-4f04-b0b3-fedce336539f">

힙의 활용1 - 우선순위 큐(Priority Queue)

- 우선순위 큐를 구현하는 가장 효율적인 방법이 힙을 사용하는 것이다.
    - 노드 하나의 추가/삭제 시간 복잡도가 O(logN이고, 최대값/최소값을 O(1)에 구할 수 있다.
    - 완전 정렬보다 관리 비용이 적다
- 배열을 통해 트리 형태를 쉽게 구현할 수 있다.
    - 부모나 자식 노드를 O(1) 연산으로 쉽게 찾을 수 있다.
    - n위치에 있는 노드의 자식은 2n과 2n+1위치에 존재한다.
    - 완젅 이진 트리의 특성에 의해 추가/삭제의 위치는 자료의 시작과 끝 인덱스로 쉽게 판단 가능

우선순위 큐의 특성

- 우선순위를 가진 항목들을 저장하는 큐
- FIFO 순서가 아니라 우선순위가 높은 순서대로 먼저 나가게 된다.
- java.util.PriorityQueue
- java.util.PriorityQueue(Comparator comparator)

```java
static class Car implements Comparable<Car>{
		String name; int year;
		Car(String name, int year){
			setName(name);
			setYear(year);
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getYear() {
			return year;
		}
		public void setYear(int year) {
			this.year = year;
		}
		@Override
		public String toString() {
			return getName()+getYear();
		}
		@Override
		public int compareTo(Car o) {
			return name.compareTo(o.name);
		}
	}

		//PriorityQueue<Car> pq=new PriorityQueue<>(Comparator.reverseOrder());
		PriorityQueue<Car> pq=new PriorityQueue<>(new Comparator<Car>() {
			@Override
			public int compare(Car o1, Car o2) {
				return Integer.compare(o1.year, o2.year);
			}
		});
		
		
		PriorityQueue<Car> pq=new PriorityQueue<>((o1,o2) -> -Integer.compare(o1.year, o2.year));
		PriorityQueue<Car> pq=new PriorityQueue<>(Comparator.comparing(t -> t.year, Comparator.reverseOrder()));
		PriorityQueue<Car> pq=new PriorityQueue<>(Comparator.comparing(Car::getYear).reversed());
		pq.offer(new Car("아반떼",2015));
		pq.offer(new Car("그랜저",2007));
		pq.offer(new Car("소나타",2024));
		pq.offer(new Car("아반떼",2015));
		pq.offer(new Car("그랜저",2007));
		pq.offer(new Car("소나타",2024));
		System.out.println(pq);
		System.out.println();
		while(!pq.isEmpty()) System.out.println(pq.poll());
		System.out.println();

/*
그랜저2007
그랜저2007
아반떼2015
아반떼2015
소나타2024
소나타2024
*/
// ==============================

		PriorityQueue<Integer> pq=new PriorityQueue<>();
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		pq.offer(22);
		pq.offer(11);
		pq.offer(33);
		pq.offer(22);
		pq.offer(11);
		pq.offer(33);
		System.out.println(pq);
		System.out.println();
		while(!pq.isEmpty()) System.out.println(pq.poll());
		System.out.println();

/*
11
11
22
22
33
33

33
33
22
22
11
11
*/
```

힙의 활용2 - 힙 정렬

- 힙 정렬은 힙 자료구조를 이용해서 이진트리와 유사한 방법으로 수행
- 정렬을 위한 2단계
    - 하나의 값을 힙에 삽입(반복)
    - 힙에서 순차적(오름차순)으로 값을 하나씩 제거
- 힙 정렬의 시간 복잡도
    - N개의 노드 삽입 연산 + N개의 노드 삭제 연산
    - 노드 하나의 삽입과 삭제 연산은 각각 O(logN)이다.
    - 따라서 전체 정렬은 O(NlogN)이다.
