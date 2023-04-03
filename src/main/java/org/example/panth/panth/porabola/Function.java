package org.example.panth.panth.porabola;

public class Function {
    private final double a;
    private final double b;

    Function(double a, double b) {
        if (a > b) {
            double buff = b;
            b = a;
            a = buff;
        }

        this.a = a;
        this.b = b;
    }

    public double apply(double u) {
        final double a = 0.8648;
        final double b = -11.2956;
        final double c = 51.6184;
        final double d = -103.7830;
        final double e = 72.6754;
        return -(-8.1953 * u * u + 16.2999 * u + 24.2977) / (9.1366 * 9.1366 + 3.2129 * u * u * u * u);
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }
}