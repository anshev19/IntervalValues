package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        String[] texts = new String[25];
        ExecutorService executor = Executors.newFixedThreadPool(25);
        List<Future<Integer>> futureList = new ArrayList<>();
        Callable<Integer> intervalCalculator = new IntervalCalculator();

        long startTs = System.currentTimeMillis(); // start time
        for (int i = 0; i < texts.length; i++) {
            futureList.add(executor.submit(intervalCalculator));
        }

        Integer max = 0;
        for (Future<Integer> future : futureList) {
            Integer maxInterval = future.get();
            if (maxInterval > max) {
                max = maxInterval;
            }
        }
        long endTs = System.currentTimeMillis(); // end time
        System.out.println("Максимальный интервал значений: " + max);
        System.out.println("Time: " + (endTs - startTs) + "ms");
        executor.shutdown();
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}
