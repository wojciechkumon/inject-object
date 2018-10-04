package pl.wojtas626.example.math;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.Random;

public class MathServiceProvider implements Provider<MathService> {
    private final Random random;

    @Inject
    public MathServiceProvider(Random random) {
        this.random = random;
    }

    @Override
    public MathService get() {
        if (random.nextBoolean()) {
            return new AdditionMathService();
        } else {
            return new SubtractionMathService();
        }
    }

}
