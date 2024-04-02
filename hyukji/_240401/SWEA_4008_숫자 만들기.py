import copy, math

def cal(left, i, right):
    if(i == 0):
        return left + right
    elif(i == 1):
        return left - right
    elif(i == 2):
        return left * right
    else:
        return math.trunc(left / right)
    
T = int(input())
for tc in range(1, T+1):
    n = int(input())
    opt_count = list(map(int, input().split()))
    nums = list(map(int, input().split()))

    min_result = float("inf")
    max_result = -float("inf")
    
    stack = []
    stack.append((nums[0], 1, copy.deepcopy(opt_count)))
    while(stack):
        res, cnt, counts = stack.pop()
        for i in range(4):
            if(counts[i] == 0):
                continue
            
            nRes = cal(res, i, nums[cnt])
            nCnt = cnt + 1
            if(nCnt == n):
                min_result = min(min_result, nRes)
                max_result = max(max_result, nRes)
            else:
                nCounts = copy.deepcopy(counts)
                nCounts[i] -= 1
                stack.append((nRes, nCnt, nCounts))
                
    
    print(f"#{tc} {max_result - min_result}")