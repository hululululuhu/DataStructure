class BIT:
    def __init__(self, a):
        a = [0] + a
        n = len(a)
        c = [0] * (n + 1)
        for i in range(n):
            j = i - self.lowbit(i) + 1
            while j <= i:
                c[i] += a[j]
                j += 1
        self.c = c
        self.a = a
        self.n = n


    def lowbit(self, i):
        return i & -i


    def update(self, pos, val):
        i = pos + 1
        while i <= self.n:
            self.c[i] += val
            i += self.lowbit(i)
    
    def range_update(self, l, r, val):
        self.update(l, val)
        self.update(r + 1, -val)

    def query(self, pos):
        ans = 0
        i = pos
        while i > 0:
            ans += self.c[i]
            i -= self.lowbit(i)
        return ans


    def range_query(self, r, l):
        r += 1
        l += 1
        r = min(self.n - 1, r)
        return self.query(r) - self.query(l - 1)


a = [7, 2, 7, 2, 0]

bit = BIT(a)
bit.update(0, -5)
bit.update(0, 7)
bit.update(4, 6)
a1 = bit.range_query(4, 4)
bit.update(3, 6)
a2 = bit.range_query(4, 0)
bit.update(4, -5)
a3 = bit.range_query(4, 0)
a4 = bit.range_query(3, 0)
print(a1, a2, a3, a4)






