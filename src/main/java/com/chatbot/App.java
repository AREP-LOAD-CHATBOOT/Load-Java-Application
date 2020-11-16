package com.chatbot;

import com.chatbot.thread.RequestIbm;
import java.util.ArrayList;

public class App {

    public static void main( String[] args ) {
        int numThreads = 1000;
        int numRequestXThread = 10;
        long ini = System.currentTimeMillis();
        ArrayList<RequestIbm> threadList = new ArrayList<>();
        for (int i = 0; i < numThreads; i++) {
            RequestIbm thread = new RequestIbm(numRequestXThread);
            threadList.add(thread);
            thread.start();
        }
        joinAllThreads(ini, numThreads, numRequestXThread, threadList);
    }

    public static void joinAllThreads(long ini, int numThreads, int numRequestXThread, ArrayList<RequestIbm> threadList) {
        double maxTime = -999999999999999.9, minTime = 999999999999999.9, sumTime = 0;
        for (int i = 0; i < threadList.size(); i++) {
            try {
                threadList.get(i).join();
                double seconds = threadList.get(i).getTotalTime();
                System.out.println("Tiempo total del hilo #" + (i+1) + " es de: " + seconds + " segundos.");
                maxTime = Math.max(seconds, maxTime);
                minTime = Math.min(seconds, minTime);
                sumTime += seconds;
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.err.println("ERROR!!! \n" + e);
            }
        }
        double fin = System.currentTimeMillis();
        double time = (fin - ini) / 1000.00;
        double secondsTime = Math.round(time * 1000.00) / 1000.00;
        printAllResults(secondsTime, numThreads, numRequestXThread, maxTime, minTime, sumTime);
    }

    public static void printAllResults(double totalTime, int numThreads, int numReqFThread, double maxTime, double minTime, double sumTime) {
        int totalRequests = numThreads * numReqFThread;
        double average = sumTime / numThreads;
        double averageRequests = Math.round(average * 1000.00) / 1000.00;
        System.out.println("\nEl tiempo total de las peticiones fue de: " + totalTime + " segundos.");
        System.out.println("El máximo tiempo de respuesta fue: " + maxTime + " segundos.");
        System.out.println("El mínimo tiempo de respuesta fue: " + minTime + " segundos.");
        System.out.println("El tiempo promedio de las peticiones fue de " + averageRequests + " segundos.");
        System.out.println("El número de peticiones por hilo fue de: " + numReqFThread);
        System.out.println("El número total de peticiones fue de: " + totalRequests);
    }
}
