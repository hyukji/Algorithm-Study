import sys
input = sys.stdin.readline

n, m = map(int, input().split())
arr = list(map(int, input().split()))

answer = float('inf')
s, e, _sum = 0, 1, arr[0]
while(s <= e):
    if(_sum < m):
        if(e == n):
            break
        _sum += arr[e]
        e += 1
    else:
        answer = min(answer, e-s)
        _sum -= arr[s]
        s += 1

if(answer == float('inf')):
    answer = 0
print(answer)