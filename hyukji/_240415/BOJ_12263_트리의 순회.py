import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**9)

def printPreOrder(in_s, in_e, po_s, po_e):
    if(in_s == in_e):
        print(in_order[in_s] , end=" ")
        return
    
    root = post_order[po_e]
    pos = position[root]

    left_size = pos - in_s
    right_size = in_e - pos
    
    print(root , end=" ")

    if(left_size > 0):
        printPreOrder(in_s, in_s+left_size-1, po_s, po_s+left_size-1)
    if(right_size > 0):
        printPreOrder(in_e-right_size+1, in_e, po_e-right_size, po_e-1)
            

n = int(input())
in_order = list(map(int, input().split())) # left, root, right
post_order = list(map(int, input().split())) # left, right, root

position = [0 for _ in range(n+1)] # 특정 값이 값이 in_order의 어느 위치에 있는지 **** 매번 찾는 과정은 비효율적임!!! *****
for i in range(n):
    position[in_order[i]] = i

printPreOrder(0, n-1, 0, n-1) # root, left, right