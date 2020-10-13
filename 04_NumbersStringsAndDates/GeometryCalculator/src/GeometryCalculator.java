public class GeometryCalculator {
    // метод должен использовать абсолютное значение radius
    public static double getCircleSquare(double radius) {
        double s = Math.PI * radius * radius;
        return s;
    }

    // метод должен использовать абсолютное значение radius
    public static double getSphereVolume(double radius) {
        double v = (4.0 / 3.0) * Math.PI * radius * radius * radius;
        return v;
    }

    public static boolean isTriangleRightAngled(double a, double b, double c) {
        if (a > b + c || b > a + c || c > a + b || b <= 0 || a <= 0 || c <= 0) {
            return false;
        }
        else
        return true;
    }

    // перед расчетом площади рекомендуется проверить возможен ли такой треугольник
    // методом isTriangleRightAngled, если невозможен вернуть -1.0
    public static double getTriangleSquare(double a, double b, double c) {
        if (!isTriangleRightAngled(a, b, c)) {
            return -1.0;
        }
        else {
            double p = (a + b + c) / 2;
            double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));
            return s;
        }
    }
}
