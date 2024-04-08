import sys
input = sys.stdin.readline

from collections import deque

n = int(input())
in_arr = [0 for _ in range(n+1)]
graph = [[] for _ in range(n+1)]

graph[1] = [0, 0, 0]
for i in range(2, n+1):
    t, a, p = input().split()
    sheep, wolf, nxt = int(a), 0, int(p)
    if(t == 'W'):
        wolf += sheep
        sheep = 0
        
    graph[i] = [sheep, wolf, nxt] # 해당 섬의 양의 수, 늑대의 수, 다음 섬
    in_arr[nxt] += 1 
    
dq = deque()
for i in range(2, n+1):
    if(in_arr[i] == 0):
        dq.append(i) # 출발할 섬들
        
while(dq): # 출발 섬들이 있다면
    i = dq.popleft()
    sheep, wolf, nxt = graph[i] # 그 섬의 양, 늑대, 다음 섬
    
    result = max(sheep-wolf, 0) # 양-늑대 => 음수라면 0으로
    graph[nxt][0] += result # 다음 섬에 양의 수 업데이트
    
    in_arr[nxt] -= 1 # 이동 완료표시
    if(in_arr[nxt] == 0 and nxt != 1):
        dq.append(nxt) # 출발섬으로 추가
    
print(graph[1][0]) # 마지막 보트에서의 양의 수