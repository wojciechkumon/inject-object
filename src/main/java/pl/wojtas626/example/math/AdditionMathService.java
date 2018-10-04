package pl.wojtas626.example.math;

public class AdditionMathService implements MathService {
    @Override
    public double calculate(double a, double b) {
        return a + b;
    }
}
