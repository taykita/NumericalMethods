package org.example.panth.panth.porabola;

public class ParabolicMethod {
    public static double parabolicMethod(Function f, double epsilon) {
        double a = f.getA();
        double b = f.getB();
        double delta = 1;

        double x_1 = a;
        double x_2 = (b + a) / 2;
        double x_3 = b;

        double a_0 = f.apply(x_1);
        double a_1 = (f.apply(x_2) - f.apply(x_1)) / (x_2 - x_1);
        double a_2 = (1 / (x_3 - x_2)) * (((f.apply(x_3) - f.apply(x_1)) / (x_3 - x_1))
                - ((f.apply(x_2) - f.apply(x_1) / (x_2 - x_1))));

        double x = 0.5 * (x_1 + x_2 - a_1 / a_2);
        double result = x;

        if ((f.apply(x) >= f.apply(x_2)) && (x > x_1)) {
            x_1 = x;
        }

        if ((f.apply(x_2) >= f.apply(x)) && (x < x_3)) {
            x_1 = x_2;
            x_2 = x;
        }

        while (delta > epsilon) {
            a_1 = (f.apply(x_2) - f.apply(x_1)) / (x_2 - x_1);
            a_2 = (1 / (x_3 - x_2)) * (f.apply(x_3) - f.apply(x_1))
                    / (x_3 - x_1) - (f.apply(x_2) - f.apply(x_1)) / (x_2 - x_1);
            x = 0.5 * (x_1 + x_2 - a_1 / a_2);
            result = x;

            if ((f.apply(x) >= f.apply(x_2)) && (x > x_1)) {
                x_1 = x;
            }

            if ((f.apply(x_2) >= f.apply(x)) && (x < x_3)) {
                x_1 = x_2;
                x_2 = x;
            }

            delta = Math.abs(result - x);
            result = x;
        }

        return result;
    }
}