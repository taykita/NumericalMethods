package org.example.panth.panth.porabola;

public class Main {
    public static void main(String[] args) {
        // J\left(u\right)=(d\cdot u^{2}+b\cdot u+k)/(c^{2}+a\cdot u^{4})\left\{-2\le u\le2\right\}
        Function f = new Function(-2, 2);

        double x = ParabolicMethod.parabolicMethod(f, 0.0001);

        System.out.println("u = " + x + "\nJ(u) = " + f.apply(x));
    }
}