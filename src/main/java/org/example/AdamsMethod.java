package org.example;

import static java.lang.Math.abs;

public class AdamsMethod {
    public static final RungeKuttaMethod rungeKuttaMethod = new RungeKuttaMethod();

    public static class НАШЕУравнение implements Уравнение {

        public НАШЕУравнение(double alpha, double beta) {
            this.alpha = alpha;
            this.beta = beta;
        }

        private final double alpha;
        private final double beta;

        @Override
        public double f(double x, double y) {
            return alpha/(x*x + y*y + beta);
        }
    }



    public static void main(String[] args) {
        double alpha = 1;
        double beta = 1;
        double step = 0.1;
        double x = 0;
        double y = 0;
        double eps = 0.000001;
        double b = 0.9;
        double[] η = new double[2];
//      Уравнение уравнение = new МоеУравнение(alpha, beta);
        Уравнение уравнение = new НАШЕУравнение(alpha, beta);

        double delta;

        double xOld = x;
        double yOld = y;
        System.out.printf("alpha  beta   x      y\n");
        System.out.printf("%.2f   %.2f   %.2f   %.6f\n", alpha, beta, x, y);
        y = y + rungeKuttaMethod.find(step, уравнение, x, y);
        x = x + step;
        System.out.printf("%.2f   %.2f   %.2f   %.6f\n", alpha, beta, x, y);
        while (x < b) {
            η[0] = step*уравнение.f(xOld, yOld);
            η[1] = step*уравнение.f(x, y);
            xOld = x;
            yOld = y;
            delta = η[1] - η[0];
            y = y + η[1] + delta/2;
            x = x + step;
            System.out.printf("%.2f   %.2f   %.2f   %.6f\n", alpha, beta, x, y);
        }

    }
}
