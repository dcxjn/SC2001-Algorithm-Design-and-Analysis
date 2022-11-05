import numpy as np


def knapsack(w, p, C, n):

    profit = np.array([[0 for x in range(n+1)] for x in range(C+1)])

    for i in range(C+1):
        for j in range(n+1):
            if i == 0 or j == 0:
                profit[i][j] = 0
            elif w[j-1] <= i:
                profit[i][j] = max(
                    profit[i][j-1], profit[i-w[j-1]][j] + p[j-1])
            else:
                profit[i][j] = profit[i][j-1]

    print(profit)

    return profit[C][n]


w = [4, 6, 8]
p = [7, 6, 9]
C = 14
n = len(p)
print("Profit is: " + str(knapsack(w, p, C, n)))
