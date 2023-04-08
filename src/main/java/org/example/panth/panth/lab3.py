import math

# -17.4259*sin(-3.8778*x-9)+0.0642*exp(0.7443*x)
# 0.04778406*exp(0.7443*x) + 67.57415502*cos(9 + 3.8778*x)

ab = [0, 5]
a = 0.0806
b = 4.1478
c = 1.5999
d = 6.4501
e = 20.8204

[a, b, c, d, e]

L = 400
eps = 0.0001

# -7.0659*sin(11.6516x +  -6.2986) + 5.3095*exp(0.4014x)


def funcJ(u):
    return d * math.sin(b * u + e) + c * math.exp(a * u)

def funcG(u, v):
    return funcJ(v) - L * abs(v - u)

def minFunc():
    u0 = 3.5
    x = [ab[0], u0, ab[1]]
    y = [funcG(ab[0], u0), funcJ(u0), funcG(ab[1], u0)]

    k = y.index(min(y))
    minY = y[k]
    minX = x[k]
    y[k] = funcJ(minX)

    # print("Минимум (", minX, ") =", minY)

    while (abs(y[k] - minY) >= eps):
        if (minX == ab[0]):
            dot = (funcJ(minX) - funcJ(x[k+1]) + L * (minX + x[k+1])) / (2 * L)
            x.insert(k+1, dot)
            y.insert(k+1, funcG(dot, minX))
        elif (minX == ab[1]):
            dot = (funcJ(x[k-1]) - funcJ(minX) + L * (x[k-1] + minX)) / (2 * L)
            x.insert(k, dot)
            y.insert(k, funcG(dot, minX))
        else:
            dot1 = (funcJ(x[k-1]) - funcJ(minX) + L * (x[k-1] + minX)) / (2 * L)
            dot2 = (funcJ(minX) - funcJ(x[k+1]) + L * (minX + x[k+1])) / (2 * L)
            x.insert(k+1, dot2)
            y.insert(k+1, funcG(dot2, minX))
            x.insert(k, dot1)
            y.insert(k, funcG(dot1, minX))
            
        k = y.index(min(y))
        minY = y[k]
        minX = x[k]

        # print("Минимум (", minX, ") =", minY)

        y[k] = funcJ(minX)
        
    print("Минимум (", minX, ") =", minY)

def maxFunc():

    def funcJ_(u):
        return -funcJ(u)

    def funcG_(u, v):
        return funcJ_(v) - L * abs(v - u)
    
    x = [ab[0], 0, ab[1]]
    y = [funcG_(ab[0], 0), funcJ_(0), funcG_(ab[1], 0)]

    k = y.index(min(y))
    minY = y[k]
    minX = x[k]
    y[k] = funcJ_(minX)

    while (abs(y[k] - minY) >= eps):
        if (minX == ab[0]):
            dot = (funcJ_(minX) - funcJ_(x[k+1]) + L * (minX + x[k+1])) / (2 * L)
            x.insert(k+1, dot)
            y.insert(k+1, funcG_(dot, minX))
        elif (minX == ab[1]):
            dot = (funcJ_(x[k-1]) - funcJ_(minX) + L * (x[k-1] + minX)) / (2 * L)
            x.insert(k, dot)
            y.insert(k, funcG_(dot, minX))
        else:
            dot1 = (funcJ_(x[k-1]) - funcJ_(minX) + L * (x[k-1] + minX)) / (2 * L)
            dot2 = (funcJ_(minX) - funcJ_(x[k+1]) + L * (minX + x[k+1])) / (2 * L)
            x.insert(k+1, dot2)
            y.insert(k+1, funcG_(dot2, minX))
            x.insert(k, dot1)
            y.insert(k, funcG_(dot1, minX))
            
        k = y.index(min(y))
        minY = y[k]
        minX = x[k]
        y[k] = funcJ_(minX)
        
    print("Максимум (", minX, ") =", -minY)

minFunc()
maxFunc()