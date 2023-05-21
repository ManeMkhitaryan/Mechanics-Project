#!/usr/bin/env python
# coding: utf-8

# # TASK 1

# In[32]:


class Horizontal:
    r = 1.0

    def __init__(self, radius):
        pass

    
    def reflections(x, y, px, py, n):
        ref = [[x, y]]

        for i in range(n):
            t = ((Horizontal.r**2) - (x**2) - (y**2)) / ((px**2) + (py**2))
            x += t * px
            y += t * py

            pX = (y*y - x*x) * px - 2 * x * y * py
            pY = -2*x*y*px + (x*x - y*y)*py

            px = pX
            py = pY

            ref.append([x, y, px, py])

        return ref

   
    def reversible(ref):
        x0 = ref[-1][0]
        y0 = ref[-1][1]
        px0 = ref[-1][2]
        py0 = ref[-1][3]
        reverse = Horizontal.reflections(x0, y0, -px0, -py0, len(ref))

        deviation_point = [0.0, 0.0]
        flag = False
        for i in range(len(ref)):
            j = len(reverse) - 2 - i
            if j < 0:
                break

            if ref[i][0] != reverse[j][0] or ref[i][1] != reverse[j][1]:
                deviation_point[0] = ref[i][0]
                deviation_point[1] = ref[i][1]
                flag = True
                break

        if flag:
            return deviation_point
        return None


# In[33]:


def print_points(points, x, y, px, py):
    print(f"Reflection points with starting point ({x}, {y}) and momentum ({px}, {py}).")
    for p in points:
        print(f"x = {p[0]}, y = {p[1]}")
    print(f"Total: {len(points)}")


r_p = Horizontal.reflections(0.2, 0.15, -0.45, -0.75, 12)
print_points(r_p, 0.2, 0.15, -0.45, -0.75)


# # TASK 2

# In[34]:


class Vertical:
    r = 1.0
    g = 10.0

    def __init__(self):
        pass

    def f(x, x0, y0, px0, py0):
        y = y0 + ((py0 / px0) * (x - x0)) - ((Vertical.g * (x - x0) * (x - x0)) / (2 * px0 * px0))
        return (y**2) + (x**2) - 1

    def bisection(a, b, x0, y0, px0, py0, epsilon):
        f_a = Vertical.f(a, x0, y0, px0, py0)
        f_b = Vertical.f(b, x0, y0, px0, py0)

        if f_a * f_b >= 0:
            return "Values should have different signs"

        c = a
        while (b - a) >= epsilon:
            c = (a + b) / 2
            f_c = Vertical.f(c, x0, y0, px0, py0)

            if f_c == 0.0:
                break
            elif f_c * f_a < 0:
                b = c
            else:
                a = c

        return c


# # TASK 3

# In[35]:


import random
import math


class Stadium:
    r = 1.0

    def __init__(self):
        pass

    @staticmethod
    def reflections(n, x_0, y_0, px_0, py_0, L):
        norm = math.sqrt(px_0 * px_0 + py_0 * py_0)

        x, y = x_0, y_0
        px, py = px_0 / norm, py_0 / norm
        ref = []

        for i in range(n):
            if -math.sqrt(2) / 2 < py < math.sqrt(2) / 2 and y >= 0:
                x = x + px * (1 - y) / py
                y = 1
                py = -py
            elif -math.sqrt(2) / 2 < py < math.sqrt(2) / 2 and y < 0:
                x = x + px * (-1 - y) / py
                y = -1
                py = -py
            elif -math.sqrt(2) / 2 < px < math.sqrt(2) / 2 and y >= 0:
                xc = L
                intersec = Stadium.intersection(x, y, px, py, xc)
                x = intersec[0]
                y = intersec[1]
                px = (y**2 - (x - xc) * (x - xc))*px - 2*(x - xc) * y*py
                py = -2*(x - xc) * y*px + ((x - xc) * (x - xc) - y**2) * py
            elif -math.sqrt(2) / 2 < px < math.sqrt(2) / 2 and y < 0:
                xc = 0
                intersec = Stadium.intersection(x, y, px, py, xc)
                x = intersec[0]
                y = intersec[1]
                px = (y * y - (x - xc) * (x - xc)) * px - 2 * (x - xc) * y * py
                py = -2 * (x - xc) * y * px + ((x - xc) * (x - xc) - y * y) * py
            else:
                side = random.choice(["b,t"])
                if side == "t":
                    x = x + px*(1 - y)/py
                    y = 1
                else:
                    x = x + px*(-1 - y)/py
                    y = -1
                py = -py

            ref.append([x, y])

        return ref

    @staticmethod
    def random(n):
        ref = Stadium.reflections(n, -0.9, -1, 0.0002, 0.8, 1)
        n = set()

        for r in ref:
            if 0 <= r[0] <= 1 and (r[1] == 1 or r[1] == -1):
                n.add(r[0])

        return n

    @staticmethod
    def is_uniform(n, mean, sigma, epsilon):
        s_mean = sum(n) / len(n)

        sample_sigma = math.sqrt(sum([(n - s_mean) ** 2 for n in n]) / (len(n) - 1))
        
        if abs(s_mean - mean) < epsilon and abs(sample_sigma - sigma) < epsilon:
            print("Uniform on [0,1]")
        else:
            print("NOT Uniform on [0, 1]")

    def intersection(x0, y0, px, py, xc):
        inter_p = [0.0, 0.0]

        a = 1 + (py/px) * (py/px)
        b = (-2*xc) + (2 * (py/px) * y0) - (-2 * ((py**2) / (px**2)) * x0)
        c = y0*y0 + xc*xc - (2 * (py/px) * y0 * x0) + ((py**2) / (px**2) * x0 * x0) - 1

        d = b*b - 4*a*c

        if d >= 0:
            if px < 0:
                x = (-b + math.sqrt(d)) / (2 * a)
            else:
                x = (-b - math.sqrt(d)) / (2 * a)

            y = y0 + (py / px) * (x - x0)

            inter_p[0] = x
            inter_p[1] = y

        return inter_p

