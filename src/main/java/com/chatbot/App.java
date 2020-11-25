package com.chatbot;

import com.chatbot.thread.RequestIbm;
import java.util.ArrayList;

public class App {
	
	private int numThreads;
	private int numRequestXThread;
	private ArrayList<RequestIbm> threadList;
	private long ini;
	private double secondsTotalTime;
	private double maxTime = -999999999999999.9;
	private double minTime = 999999999999999.9;
	private double sumTime = 0;
	private ArrayList<Double> listTimes;
	private int totalRequests;
	private double averageRequests;
	
    public App (int numThread, int numReqThread) {
        this.numThreads = numThread;
        this.numRequestXThread = numReqThread;
        ini = System.currentTimeMillis();
        threadList = new ArrayList<>();
        listTimes = new ArrayList<>();
        for (int i = 0; i < numThreads; i++) {
            RequestIbm thread = new RequestIbm(numRequestXThread);
            threadList.add(thread);
            thread.start();
        }
        joinAllThreads();
    }

    public void joinAllThreads() {
        maxTime = -999999999999999.9;
        minTime = 999999999999999.9;
        sumTime = 0;
        for (int i = 0; i < threadList.size(); i++) {
            try {
                threadList.get(i).join();
                double seconds = threadList.get(i).getTotalTime();
                System.out.println("Tiempo total del hilo #" + (i+1) + " es de: " + seconds + " segundos.");
                maxTime = Math.max(seconds, maxTime);
                minTime = Math.min(seconds, minTime);
                sumTime += seconds;
                listTimes.add(seconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.err.println("ERROR!!! \n" + e);
            }
        }
        double fin = System.currentTimeMillis();
        double time = (fin - ini) / 1000.00;
        secondsTotalTime = Math.round(time * 1000.00) / 1000.00;
        printAllResults();
    }

    public void printAllResults() {
        totalRequests = numThreads * numRequestXThread;
        double average = sumTime / numThreads;
        averageRequests = Math.round(average * 1000.00) / 1000.00;
        System.out.println("\nEl tiempo total de las peticiones fue de: " + secondsTotalTime + " segundos.");
        System.out.println("El Máximo tiempo de respuesta fue: " + maxTime + " segundos.");
        System.out.println("El Mínimo tiempo de respuesta fue: " + minTime + " segundos.");
        System.out.println("El tiempo promedio de las peticiones fue de " + averageRequests + " segundos.");
        System.out.println("El número de peticiones por hilo fue de: " + numRequestXThread);
        System.out.println("El número total de peticiones fue de: " + totalRequests);
    }
    
    public String[] getResults() {
    	Object[] results = new Object[] {numRequestXThread, secondsTotalTime, totalRequests, maxTime, averageRequests, minTime};
    	String[] resultStrings = new String[results.length];
    	for (int i = 0; i < results.length; i++) {
    		resultStrings[i] = String.valueOf(results[i]);
    	}
    	    	
    	return resultStrings;
    }

	public ArrayList<Double> getListTimes() {
		for (int i = 0; i < listTimes.size(); i++) {
			System.out.print("[" + listTimes.get(i) + "] ");
		}
		return listTimes;
	}

}
