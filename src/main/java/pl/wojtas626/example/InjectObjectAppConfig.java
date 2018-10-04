package pl.wojtas626.example;

import pl.wojtas626.example.logging.Logger;
import pl.wojtas626.example.logging.TimestampLogger;
import pl.wojtas626.example.math.MathService;
import pl.wojtas626.example.math.MathServiceProvider;
import pl.wojtas626.injectobject.AbstractAppConfig;

import java.util.Random;

public class InjectObjectAppConfig extends AbstractAppConfig {

    @Override
    public void configure() {
        bindSingletonProvider(MathService.class, MathServiceProvider.class);
        bindSingleton(Logger.class, TimestampLogger.class);
        bind(Random.class, new Random());
    }
}
