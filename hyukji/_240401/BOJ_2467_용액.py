import sys
input = sys.stdin.readline

from bisect import bisect_left, bisect_right

n = int(input())
arr = list(map(int, input().split()))

min_diff = float('inf')
answer = 0, 0

for i, v in enumerate(arr):
    ri = bisect_right(arr, 0-v)
    li = bisect_left(arr, 0-v)
    if(ri == li):
        li-=1
    if(ri == i):
        ri+=1
    if(li == i):
        li-=1
    
    if(li >= 0):
        lv = arr[li]
        diff = abs(lv + v)
        if(diff == 0):
            answer = v, lv
            break
        if(min_diff > diff):
            min_diff = diff
            answer = v, lv
            
    if(ri < n):
        rv = arr[ri]
        diff = abs(rv + v)
        if(diff == 0):
            answer = v, rv
            break
        if(min_diff > diff):
            min_diff = diff
            answer = v, rv
            
print(min(answer), max(answer))