package pl.wojtas626.example.math;

public class SubtractionMathService implements MathService {
    @Override
    public double calculate(double a, double b) {
        return a - b;
    }
}
