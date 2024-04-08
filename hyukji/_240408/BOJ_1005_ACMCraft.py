import sys
input = sys.stdin.readline

from collections import deque

T = int(input())
for _ in range(T):
    n, k = map(int, input().split())
    times = list(map(int, input().split()))
    start_times = [0 for _ in range(n)] # 건물들의 최소 시작 시간
    in_arr = [0 for _ in range(n)] # 사전에 미리 수행해야할 건물 개수
    graph = [[] for _ in range(n)] # 다음으로 지어야 하는 건물들
    
    for i in range(k):
        a, b = map(int, input().split())
        in_arr[b-1] += 1
        graph[a-1].append(b-1)
        
    w = int(input()) -1
    
    dq = deque() # 건물 번호
    for i in range(n):
        if(in_arr[i] == 0): # 처음부터 지을 수 있는 건물들
            dq.append(i)
    
    while(dq):
        i = dq.popleft() # 짓는 건물 번호
        t = start_times[i] + times[i] # 건축 끝나는 시각
        for nxt in graph[i]:
            start_times[nxt] = max(t, start_times[nxt]) # 다음으로 짓는 건물 시작시간 업데이트
            in_arr[nxt] -= 1 
            if(in_arr[nxt] == 0): # 지을 수 있다면 
                dq.append(nxt)
        
    print(start_times[w] + times[w])