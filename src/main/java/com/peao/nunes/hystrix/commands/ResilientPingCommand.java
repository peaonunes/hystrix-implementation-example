package com.peao.nunes.hystrix.commands;

import com.peao.nunes.hystrix.workers.HTTPRequesterWorker;

public class ResilientPingCommand {

    private final HTTPRequesterWorker worker;

    public ResilientPingCommand(String url) {
        this.worker = new HTTPRequesterWorker(url);
    }

}
