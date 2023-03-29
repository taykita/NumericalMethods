package org.example.sip;

public class RungeKuttaMethod {
    public static double K1(double step, Уравнение уравнение, double x1, double x2) {
        return step*уравнение.f(x1, x2);
    }

    public static double K2(double step, Уравнение уравнение, double x1, double x2, double K1) {
        return step*уравнение.f(x1 + step/2, x2 + K1/2);
    }

    public static double K3(double step, Уравнение уравнение, double x1, double x2, double K2) {
        return step*уравнение.f(x1 + step/2, x2 + K2/2);
    }

    public static double K4(double step, Уравнение уравнение, double x1, double x2, double K3) {
        return step*уравнение.f(x1 + step, x2 + K3);
    }

    public double find(double step, Уравнение уравнение, double x, double y) {
        double K1 = K1(step, уравнение, x, y);
        double K2 = K2(step, уравнение, x, y, K1);
        double K3 = K3(step, уравнение, x, y, K2);
        double K4 = K4(step, уравнение, x, y, K3);
        return (K1 + 2*K2 + 2*K3 + K4)/6;
    }

}
