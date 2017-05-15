package com.peao.nunes.hystrix.notifier;

public class CircuitBreakerNotifier {

    private CircuitBreakerNotifier() {}

    private static CircuitBreakerNotifier INSTANCE = new CircuitBreakerNotifier();

    public static CircuitBreakerNotifier getCircuitBreakerNotifierInstance() {
        return INSTANCE;
    }
}
