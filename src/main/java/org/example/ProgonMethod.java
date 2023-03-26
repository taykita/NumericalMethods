package org.example;

import java.util.*;

public class ProgonMethod {

    static double f(double x) {
        return -2 / ((x + 1) * (x + 1) * (x + 1));
    }

    static double p(double x) {
        return -(x + 1);
    }

    static double q = -1;

    static double ai(double h, double x) {
        return 1 - p(x) * h / 2;
    }

    static double bi(double h, double x) {
        return 1 + p(x) * h / 2;
    }

    static double ci(double h) {
        return 2 - q * h * h;
    }

    static double fi(double x) {
        return f(x);
    }

    static double hi_1 = 0;
    static double di_1 = 1;

    static double hi_n(double hi, double h, double x) {
        return bi(h, x) / (ci(h) - ai(h, x) * hi);
    }

    static double di_n(double di, double hi, double x, double h) {
        return (fi(x) * h * h + ai(h, x) * di) / (ci(h) - ai(h, x) * hi);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);
        System.out.print("\033[0;95m h = ");
        double h = 0.1;
        System.out.print("\n");

        System.out.printf("\033[1;94m%4s %10s %14s %12s %16s\033[0m\n", "x", "h", "h/2", "d", "1/(x + 1)");

        List<Double> yf = calc(h);
        List<Double> ys = calc(h / 2);

        double x = 0;
        for (int i = 0; i < yf.size(); i++) {
            System.out.printf("\033[1;32m%.4f\033[0m  \033[1;93m%.10f\033[0m  \033[1;95m%.10f\033[0m  \033[1;96m%.10f\033[0m  \033[4;31m%.10f\033[0m\n",
                    x, yf.get(i), ys.get(2 * i), Math.abs(yf.get(i) - ys.get(2 * i)) / 3, Math.abs(ys.get(2 * i) - 1 / (x + 1)));
            x += h;
        }

    }

    static List<Double> calc(double h) {
        List<Double> H = new ArrayList<>();
        H.add(hi_1);

        List<Double> D = new ArrayList<>();
        D.add(di_1);

        int n = (int) Math.round(1 / h);
        for (int i = 1; i < n; i++) {
            H.add(hi_n(H.get(i - 1), h, i * h));
            D.add(di_n(D.get(i - 1), H.get(i - 1), i * h, h));
        }

        List<Double> y = new ArrayList<>();
        y.add(0.5);

        for (int i = 0; i < n; i++) {
            y.add(H.get(H.size() - i - 1) * y.get(i) + D.get(D.size() - i - 1));
        }

        Collections.reverse(y);
        return y;
    }
}