package com.peao.nunes.hystrix.commands;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.peao.nunes.hystrix.workers.HTTPRequesterWorker;

import java.util.HashMap;

public class ResilientPingCommand extends HystrixCommand<String> {

    private final HTTPRequesterWorker worker;

    public ResilientPingCommand(String url) {
        super(Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("PingGroup")));
        this.worker = new HTTPRequesterWorker(url);
    }

    @Override
    protected String run() throws Exception {
        System.out.println("--- Is Circuit open? " + this.isCircuitBreakerOpen());

        HashMap<String, String> response = this.worker.healthCheck();

        if(!response.get("exception").isEmpty()) {
            System.out.println("-- Something went wrong.");
            throw new RuntimeException(response.get("exception"));
        } else {
            return response.get("message");
        }
    }

    @Override
    protected String getFallback() {
        return "Default fallback";
    }
}
