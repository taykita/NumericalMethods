package org.example.panth.panth.porabola;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Parabola {

    private static final double a = 0.8648;
    private static final double b = -11.2956;
    private static final double c = 51.6184;
    private static final double d = -103.7830;
    private static final double e = 72.6754;

    private static final double epsilon = 0.00001;


    // J\left(u\right)=(d\cdot u^{2}+b\cdot u+k)/(c^{2}+a\cdot u^{4})\left\{-2\le u\le2\right\}
    private static double J(int sign, double u) {
        return sign*(d*u*u + b*u + e)/(c*c + a*u*u*u*u);
    }

    public static void main(String[] args) {

        double ao = -2;
        double bo = 2;

        double u1 = parabola(-1,0, ao);
        double u2 = parabola(-1,0, bo);

        System.out.print("Min: ");
        if (J(1, u1) > J(1, u2)) {
            System.out.println(u2);
        } else {
            System.out.println(u1);
        }

        u1 = parabola(1, ao, 0);
        u2 = parabola(1, bo, 0);
        System.out.print("Max: ");
        if (J(1, u1) > J(1, u2)) {
            System.out.println(u1);
        } else {
            System.out.println(u2);
        }
    }

    public static double parabola(int sign, double ao, double bo) {

        List<Double> us = new ArrayList<>();
        double w1 = 0;
        double w2 = 1;
        double u0 = 1.6;
        double h = (bo - ao)/3;

        double hn;
        double tn;
        double u1;
        double u2;
        double w;
        int n;
        double d1;
        double d2;
        double Jmin;
        double un;
        while (abs(w2 - w1) >= epsilon) {
            hn = h;
            tn = h;
            u1 = getU(u0 + h, ao, bo);

            if (J(sign, u0) >= J(sign, u1)) {
                us.add(u0);
                us.add(u1);
                u2 = us.get(0) + 2*h;
            } else {
                us.add(u1);
                us.add(u0);
                u2 = us.get(0) - 2*h;
            }
            u2 = getU(u2, ao, bo);
            us.add(u2);

            w = 1;
            n = 2;

            while (w == 1) {
                d1 = J(sign, us.get(n - 2)) - J(sign, us.get(n - 1));
                d2 = J(sign, us.get(n)) - J(sign, us.get(n - 1));

                if ((d1 >= 0) && (d2 >= 0) && (d1 + d2 > 0)) {
                    w = us.get(n - 1) + (hn*hn*d1 - tn*tn*d2)/(2*(hn*d1 + tn*d2));
                    w = getU(w, ao, bo);
                } else if (us.get(n) == ao || us.get(n) == bo) {
                    Jmin = J(sign, us.get(0));
                    w = us.get(0);

                    for (int i = 0; i < n + 1; i++) {
                        if (Jmin > J(sign, us.get(i))) {
                            Jmin = J(sign, us.get(i));
                            w = us.get(i);
                        }
                    }
                } else {
                    n += 1;
                    if (u0 > 0) {
                        un = us.get(0) - sign * pow(2, n - 1) * h;
                    } else {
                        un = us.get(0) + sign * pow(2, n - 1) * h;
                    }
                    tn = hn;
                    hn = 2*hn;
                    un = getU(un, ao, bo);
                    us.add(un);
                }
            }
            w1 = w2;
            w2 = w;
            u0 = w;
            h /= 2;
            us.clear();
        }
        return w2;
    }

    private static double getU(double u, double a, double b) {
        if (u < a) {
            return a;
        } else if (u > b) {
            return b;
        } else {
            return u;
        }
    }
}
