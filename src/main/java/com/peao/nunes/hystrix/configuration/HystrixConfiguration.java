package com.peao.nunes.hystrix.configuration;

import com.netflix.hystrix.contrib.servopublisher.HystrixServoMetricsPublisher;
import com.netflix.hystrix.strategy.HystrixPlugins;
import com.peao.nunes.hystrix.notifier.CircuitBreakerNotifier;

public class HystrixConfiguration {

    public static void configureHystrixPlugins(){
        HystrixConfiguration.configureEventNotifier();
        HystrixConfiguration.configureMetrics();
    }

    private static void configureEventNotifier() {
        HystrixPlugins.getInstance()
                .registerEventNotifier(CircuitBreakerNotifier.getCircuitBreakerNotifierInstance());
    }

    private static void configureMetrics() {
        HystrixPlugins.getInstance()
                .registerMetricsPublisher(HystrixServoMetricsPublisher.getInstance());
    }
}
