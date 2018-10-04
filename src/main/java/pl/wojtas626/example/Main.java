package pl.wojtas626.example;


import pl.wojtas626.example.logging.Logger;
import pl.wojtas626.example.math.MathService;
import pl.wojtas626.injectobject.InjectObject;
import pl.wojtas626.injectobject.Injector;

public class Main {

    public static void main(String[] args) {
        Injector injector = InjectObject.createInjector(new InjectObjectAppConfig());
        MathService mathService = injector.get(MathService.class);
        Logger logger = injector.get(Logger.class);

        double a = 5;
        double b = 2;
        logger.log("Math result for: " + a + " and " + b + " = " + mathService.calculate(5, 3));
    }
}
