package com.peao.nunes;

public class ApplicationMain {

    public static void main(String[] args) throws Exception {
        long endTimeMillis = System.currentTimeMillis() + 1000;
        String message = " It is running =D!";

        while (true) {
            if (System.currentTimeMillis() > endTimeMillis) {
                System.out.println("- " + message);
                endTimeMillis = System.currentTimeMillis() + 1000;
            }
        }
    }
}
