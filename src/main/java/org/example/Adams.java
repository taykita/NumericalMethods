package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.abs;
import static java.lang.Math.exp;

public class Adams {
    /**
     *     x           y
     * 0,000000	1,0000000000
     * Кутта Рунге
     * 0,100000	0,9523809346
     * 0,200000	0,9093537237
     * 0,300000	0,8700431221
     * 0,400000	0,8339832365
     * 0,500000	0,8007812297
     * 0,600000	0,7701041129
     * 0,700000	0,7416684510
     * 0,800000	0,7152322051
     * 0,900000	0,6905881232
     * 1,000000	0,6675582473
     * 1,100000	0,6459892369
     * 1,200000	0,6257483205
     * 1,300000	0,6067197800
     * 1,400000	0,5888019285
     * 1,500000	0,5719045767
     */
    private static final double eps = 0.000001;

    private static final double alpha = 0.5;
    private static final double a = 0;
    private static final double b = 1.5;

    private static double x;
    private static double y;
    private static double h;

    private static double xn;
    private static double yn;

    private static List<Double> first;
    private static List<Double> second;

    public static double f(double x, double y) {
        return -x*x*y*y + (x*x - alpha)/(1 + alpha*x)/(1 + alpha*x);
//        return -x*y;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("h = ");
        h = scanner.nextDouble();

        System.out.printf("%5s %11s\n", "x", "y");

        init();
        rungeKutta();
        first = adams();

        System.out.println("===========h/2===========");

        h = h/2;

        init();
        rungeKutta();
        second = adams();

        for (int i = 0; i < first.size(); i++) {
//            System.out.println(first.get(i) + "        " + second.get(2*i));
            System.out.println(abs(first.get(i) - second.get(2*i+1))/3);
        }

    }

    private static void print() {
        System.out.printf("%f\t%.10f\n", x, y);
//        System.out.println(x + "     " + y + "     " + (exp(-x*x/2) - y));
    }

    private static void init() {
        x = a;
        y = 1;
    }

    private static List<Double> adams() {
        List<Double> values = new ArrayList<>();

        double[] r = new double[5];
        double dr;

        values.add(y);

        while (abs(x - b) > eps) {

            print();

            r[0] = h*f(xn, yn);
            r[1] = h*f(x, y);

            xn = x;
            yn = y;

            dr = r[1] - r[0];

            y = y + r[1] + dr/2;
            x = x + h;

            values.add(y);
        }
        print();

        values.add(y);

        return values;
    }

    private static void rungeKutta() {
        for (int i = 0; i < 1; i++){

            print();
            System.out.println("Кутта Рунге");

            double k1 = h * f(x, y);
            double k2 = h * f(x + h/2, y + k1/2);
            double k3 = h * f(x + h/2, y + k2/2);
            double k4 = h * f(x + h, y + k3);

            xn = x;
            yn = y;

            x = x + h;
            y = y + (k1 + 2*k2 + 2*k3 + k4)/6;
        }
    }
}
