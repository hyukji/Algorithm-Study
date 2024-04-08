import sys
input = sys.stdin.readline

n, m = map(int, input().split())
arr = list(map(int, input().split()))

cnt = 0
bef_is_even = False
for s in range(n): # 앞에 홀수인거는 필요없음
    if arr[s] % 2 == 0:
        cnt = 1
        bef_is_even = True
        break
    
even = []
odd = []
for i in range(s+1, n):
    if(bef_is_even == (arr[i] % 2 == 0)): # 이전이랑 같다면 CNT+=1
        cnt+=1
        continue
    
    if(bef_is_even): # 같지 않다면 짝수나 홀수에 넣어둔다.
        even.append(cnt)
    else:
        odd.append(cnt)

    cnt = 1
    bef_is_even = not bef_is_even
        
if(bef_is_even): # 마지막에 짝수라면 even에 추가
    even.append(cnt)
    

sum_even = [0] # 구간합을 위해 i번째 까지의 구간합 구하기
for e in even:
    sum_even.append(e + sum_even[-1])
sum_odd = [0]
for o in odd:
    sum_odd.append(o + sum_odd[-1])
    
answer = 0
if even: 
    answer = max(even) # 홀수가 없을 경우를 대비해서
    
s = 0
e = 1
max_odd = len(sum_odd)
while(e < max_odd):
    odd_cnt = sum_odd[e] - sum_odd[s] # 해당 구간에서의 홀수 개수
    if odd_cnt > m: # 홀수개수가 m보다 크다면 홀수개수를 줄이기 위해 s+=1
        s += 1
        continue
    
    e += 1 # 아니라면 e += 1
    answer = max(answer, sum_even[e] - sum_even[s])

print(answer)