package com.chatbot.thread;

import com.chatbot.client.ClientApp;

public class RequestIbm extends Thread {

    private ClientApp app;
    private long totalTime = 0;
    private int numRequests;
    private int failedRequests;

    public RequestIbm(int numRequests) {
        this.numRequests = numRequests;
        app = new ClientApp();
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < numRequests; i++) {
                long ini = System.currentTimeMillis();
                String ms = app.sendMessage("No ha llegado mi producto");
                ms = app.sendMessage("AK4520202");
                ms = app.sendMessage("Cedula");
                ms = app.sendMessage("10101001010100101010");
                long fin = System.currentTimeMillis();
                totalTime += (fin - ini);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("ERROR!!! \n" + e);
            failedRequests += 1;
        }
    }

    public double getTotalTime() {
        double time = totalTime / 1000.0;
        double secondsTime = Math.round(time * 1000.0) / 1000.0;
        return secondsTime;
    }

    public int getFailedRequests() {
        return failedRequests;
    }
}
