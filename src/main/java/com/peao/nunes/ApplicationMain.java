package com.peao.nunes;

import com.peao.nunes.hystrix.commands.ResilientPingCommand;

public class ApplicationMain {

    private static final String URL = "http://localhost:4000/";

    public static void main(String[] args) throws Exception {
        long endTimeMillis = System.currentTimeMillis() + 1000;
        String message = " It is running =D!";

        while (true) {
            if (System.currentTimeMillis() > endTimeMillis) {
                ResilientPingCommand command = new ResilientPingCommand(URL);
                message = command.execute();

                System.out.println("- " + message);
                endTimeMillis = System.currentTimeMillis() + 1000;
            }
        }
    }
}
