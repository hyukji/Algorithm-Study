import sys
input = sys.stdin.readline

T = int(input())
for _ in range(T):
    n, m, k = map(int, input().split())
    arr = list(map(int, input().split()))

    answer = 0

    nk = 0 # 처음 m개 합
    for i in range(m):
        nk += arr[i]

    if nk < k:
        answer += 1

    if n != m: # n == m이라면 그 외의 경우 확인할 필요없음...
        for s in range(n-1): # 남은 n-1번의 경우의 수 확인하기
            nk -= arr[s]
            e = (s+m)%n
            nk += arr[e]

            if nk < k:
                answer += 1
        
    print(answer)