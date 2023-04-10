package org.example.sip;

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

    /**
     alpha  beta   x      y
     1,00   1,00   0,00   0,000000
     1,00   1,00   0,10   0,099344
     1,00   1,00   0,20   0,194751
     1,00   1,00   0,30   0,283848
     1,00   1,00   0,40   0,365220
     1,00   1,00   0,50   0,438546
     1,00   1,00   0,60   0,504188
     1,00   1,00   0,70   0,562838
     1,00   1,00   0,80   0,615282
     1,00   1,00   0,90   0,662289
     1,00   1,00   1,00   0,704554

     1,00   1,00   0,20   0,194751


     1,00   1,00   0,20   0,196422

     alpha  beta   x      y
     1,00   1,00   0,00   0,000000
     1,00   1,00   0,10   0,099344
     1,00   1,00   0,20   0,196422
     1,00   1,00   0,30   0,286467
     1,00   1,00   0,40   0,368090
     1,00   1,00   0,50   0,441216
     1,00   1,00   0,60   0,506450
     1,00   1,00   0,70   0,564634
     1,00   1,00   0,80   0,616630
     1,00   1,00   0,90   0,663237
     1,00   1,00   1,00   0,705157



     1,00   1,00   0,20   0,194976
     1,00   1,00   0,20   0,194751


     h
     1,00   1,00   0,90   0,662289
     h/2
     1,00   1,00   0,90   0,662270
     h/10
     1,00   1,00   0,90   0,6622995069
     h/20
     1,00   1,00   0,90   0,6623011579

     1,00   1,00   0,90   0,6622995007
     */

    public static void main(String[] args) {
        System.out.println((0.55178568984823 - 0.55178285031142)/7);
        System.out.println((0.66226713587187 - 0.66226810558424)/7);
        System.out.println((0.65328515644593 - 0.65330357389020)/7);
        double alpha = 1;
        double beta = 1;
        double step = 0.01;
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
        System.out.printf("%.2f   %.2f   %.2f   %.10f\n", alpha, beta, x, y);
        y = y + rungeKuttaMethod.find(step, уравнение, x, y);
        x = x + step;
        System.out.printf("%.2f   %.2f   %.2f   %.10f\n", alpha, beta, x, y);
        adamsIntra(alpha, beta, step, x, y, b, η, уравнение, xOld, yOld);
    }

    private static void adamsExtra(double alpha, double beta, double step, double x, double y, double b, double[] η, Уравнение уравнение, double xOld, double yOld) {
        double delta;
        while (x <= b) {
            η[0] = step * уравнение.f(xOld, yOld);
            η[1] = step * уравнение.f(x, y);
            xOld = x;
            yOld = y;
            delta = η[1] - η[0];
            y = y + η[1] + delta/2;
            x = x + step;
            System.out.printf("%.2f   %.2f   %.2f   %.6f\n", alpha, beta, x, y);
        }
    }


    private static double adamsExtraOne(double step, double x, double y, Уравнение уравнение, double xOld, double yOld) {
        double[] η = new double[2];
        double delta;
        η[0] = step * уравнение.f(xOld, yOld);
        η[1] = step * уравнение.f(x, y);
        delta = η[1] - η[0];
        y = y + η[1] + delta/2;
        return y;
    }

    private static void adamsIntra(double alpha, double beta, double step, double x, double y, double b, double[] η, Уравнение уравнение, double xOld, double yOld) {
        double delta;
        while (x < b) {
            η[0] = step * уравнение.f(x, y);
            double yNew = adamsExtraOne(step, x, y, уравнение, xOld, yOld);
            η[1] = step * уравнение.f(x + step, yNew);
            delta = η[1] - η[0];
            xOld = x;
            yOld = y;
            y = y + η[1] - delta/2;
            x = x + step;
            System.out.printf("%.2f   %.2f   %.4f   %.14f\n", alpha, beta, x, y);
        }
    }
}
