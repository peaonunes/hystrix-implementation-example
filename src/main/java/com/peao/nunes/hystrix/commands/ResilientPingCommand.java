package com.peao.nunes.hystrix.commands;

import com.peao.nunes.hystrix.workers.HTTPRequesterWorker;

public class ResilientPingCommand {

    private final String name;
    private final HTTPRequesterWorker requester;

    public ResilientPingCommand(String name, String url) {
        this.name = name;
        this.requester = new HTTPRequesterWorker(url);
    }

}
