import sys
input = sys.stdin.readline

n = int(input())
arr = list(map(int, input().split()))
_dict = dict()
for v in arr:
    _dict[v] = _dict.get(v, 0) + 1

answer = 0
for t in arr:
    _dict[t] -= 1
    for s in arr:
        e = t - s
        if(_dict[s] == 0):
            continue
        
        cnt = _dict.get(e, 0)
        if(s == e and cnt > 1):
            answer += 1
            break
        elif(s != e and cnt > 0):
            answer+=1  
            break
    
    _dict[t] += 1
        
print(answer)