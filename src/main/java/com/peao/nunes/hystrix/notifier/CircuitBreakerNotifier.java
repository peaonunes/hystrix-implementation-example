package com.peao.nunes.hystrix.notifier;

import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixEventType;
import com.netflix.hystrix.strategy.eventnotifier.HystrixEventNotifier;

public class CircuitBreakerNotifier extends HystrixEventNotifier {

    private CircuitBreakerNotifier() {}

    private static CircuitBreakerNotifier INSTANCE = new CircuitBreakerNotifier();

    public static CircuitBreakerNotifier getCircuitBreakerNotifierInstance() {
        return INSTANCE;
    }

    public void markEvent(HystrixEventType eventType, HystrixCommandKey key) {

        if(eventType.equals(HystrixEventType.SHORT_CIRCUITED)) {
            System.out.println("<NOTIFIER> :: Short Circuited is Open!");
        }

    }
}
