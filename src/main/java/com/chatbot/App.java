package com.chatbot;

import com.chatbot.client.ClientApp;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        ClientApp app = new ClientApp();
        long ini = System.currentTimeMillis();
        String ms = app.sendMessage("No ha llegado mi producto");
        System.out.println(ms);
        ms = app.sendMessage("AK4520202");
        System.out.println("AK4520202");
        System.out.println(ms);
        ms = app.sendMessage("Cedula");
        System.out.println("Cedula");
        System.out.println(ms);
        ms = app.sendMessage("10101001010100101010");
        System.out.println("10101001010100101010");
        System.out.println(ms);
        long fin = System.currentTimeMillis();
        System.out.println((fin-ini)/1000);
    }
}
