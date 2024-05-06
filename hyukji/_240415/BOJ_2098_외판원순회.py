# https://hongcoding.tistory.com/83 를 참조하여 작성했습니다.
# 비트 연산자, dp

from math import inf


n = int(input())
W = []
for _ in range(n):
    W.append(list(map(int, input().split())))

dp = [[0 for _ in range((1 << n) -1)] for _ in range(n)]

def Get_Route(cur, visited):
    if visited == (1 << n) -1: # 모든 도시를 방문했다면
        if W[cur][0] == 0: # 0으로 돌아가는 길이 없다면
            return inf
        else: # 돌아가는 길이 있다면

            return W[cur][0]

    if dp[cur][visited] != 0: # 최소를 이미 구했다
        return dp[cur][visited]

    
    dp[cur][visited] = inf
    should_visit = list(c for c in range(1, n) if not visited & (1 << c)) # 방문해야 할 도시
    if sum(list(map(lambda c:W[c][0], should_visit))) == 0: # 방문해야 할 도시들이 원점으로 돌아갈 수 있는 것들인지 확인
        return inf

    for next in should_visit:
        if not W[cur][next]: # 다음 도시로 가는 길이 없다면
            continue
        
        dp[cur][visited] = min(dp[cur][visited], Get_Route(next, visited | (1 << next)) + W[cur][next])

    return dp[cur][visited]


print(Get_Route(0,1))