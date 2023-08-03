package com.company;

import java.util.concurrent.Callable;

import static com.company.Main.generateText;

public class IntervalCalculator implements Callable<Integer> {
    @Override
    public Integer call() {
        String text = generateText("aab", 30_000);
        int maxSize = 0;
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < text.length(); j++) {
                if (i >= j) {
                    continue;
                }
                boolean bFound = false;
                for (int k = i; k < j; k++) {
                    if (text.charAt(k) == 'b') {
                        bFound = true;
                        break;
                    }
                }
                if (!bFound && maxSize < j - i) {
                    maxSize = j - i;
                }
            }
        }
        System.out.println(text.substring(0, 100) + " -> " + maxSize);
        return maxSize;
    }
}
