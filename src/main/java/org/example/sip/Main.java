package org.example.sip;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Main {

    @FunctionalInterface
    public interface Уравнение {
        double f(double x1, double x2);
    }

    public static class yШтрих implements Уравнение {
        @Override
        public double f(double x1, double x2) {
            return (double) (x1 - cos(x2));
        }
    }

    public static double zШтрих(double y, double x) {
        return (double) (y + sin(x));
    }

    public static double K1(double step, Уравнение уравнение, double x1, double x2) {
        return step*уравнение.f(x1, x2);
    }

    public static double K2(double step, Уравнение уравнение, double x1, double x2, double K1) {
        return step*уравнение.f(x1 + K1/2, x2 + step/2);
    }

    public static double K3(double step, Уравнение уравнение, double x1, double x2, double K2) {
        return step*уравнение.f(x1 + K2/2, x2 + step/2);
    }

    public static double K4(double step, Уравнение уравнение, double x1, double x2, double K3) {
        return step*уравнение.f(x1 + K3, x2 + step);
    }

    public static double дельтаY(double step, Уравнение уравнение, double x1, double x2) {
        double K1 = K1(step, уравнение, x1, x2);
        double K2 = K2(step, уравнение, x1, x2, K1);
        double K3 = K3(step, уравнение, x1, x2, K2);
        double K4 = K4(step, уравнение, x1, x2, K3);
        return (K1 + 2*K2 + 2*K3 + K4)/6;
    }

    public static void main(String[] args) {
        double y = 0;
        double oldY = y;
        double x = 0;
        double z = 0;
        double h = 0.1;
        double a = 0;
        double b = 0.5;


        while (a < b) {
            x = a;
            oldY = y;
            y = y + дельтаY(h, (x1, x2) -> x1 - cos(x2), z, x);
            z = z + дельтаY(h, (x1, x2) -> x1 + sin(x2), oldY, x);
            System.out.printf("y = %s z = %s x = %s \n", y, z, x);
            a += h;
        }

    }
}