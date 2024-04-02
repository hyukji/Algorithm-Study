from collections import deque

T = int(input())
for tc in range(1, T+1):
    n, m, k, a, b = map(int, input().split())
    n_arr = list(map(int, input().split()))
    m_arr = list(map(int, input().split()))
    c_arr = list(map(int, input().split()))

    answer = 0
    t_idx = 0
    n_waiting = deque()
    n_processing = [None for _ in range(n)]
    m_waiting = deque()
    m_processing = [None for _ in range(m)]
    
    time = 0
    while(time != float('inf')):
        nextTime = float('inf')

        while(t_idx < k and c_arr[t_idx] <= time):
            n_waiting.append(t_idx)
            t_idx += 1

        if(t_idx < k):
            nextTime = c_arr[t_idx]

        for i in range(n):
            if n_processing[i] != None:
                c_idx, nTime = n_processing[i]
                if time == nTime + n_arr[i]:
                    m_waiting.append((c_idx, i))
                    n_processing[i] = None
                else:
                    nextTime = min(nextTime, nTime + n_arr[i])

            if(n_processing[i] == None and len(n_waiting) > 0):
                c_idx = n_waiting.popleft()
                n_processing[i] = (c_idx, time)
                nextTime = min(nextTime, time + n_arr[i])

        # print(time, n_waiting, n_processing, m_waiting)
                
        
        for j in range(m):
            if m_processing[j] != None:
                c_idx, nTime = m_processing[j]
                if time == nTime + m_arr[j]:
                    m_processing[j] = None
                else:
                    nextTime = min(nextTime, nTime + m_arr[j])

            if(m_processing[j] == None and len(m_waiting) > 0):
                c_idx, i = m_waiting.popleft()
                m_processing[j] = (c_idx, time)
                nextTime = min(nextTime, time + m_arr[j])

                if(i+1 == a and j+1 == b):
                    answer += c_idx+1
        
        time = nextTime
        # print(time, n_waiting, n_processing, m_waiting, m_processing)
    
    if(answer == 0):
        answer = -1
    print(f"#{tc} {answer}")