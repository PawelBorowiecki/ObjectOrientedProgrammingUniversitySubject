package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ImageProcessor imageProcessor = new ImageProcessor();
        try {
            imageProcessor.readImage("IMTNOTB.jpg");
            {
                long startTime = System.currentTimeMillis();
                imageProcessor.setBrightness(100);
                long endTime = System.currentTimeMillis();
                System.out.println(endTime - startTime);
            }
            {
                long startTime = System.currentTimeMillis();
                imageProcessor.setBrightnessWithThreads(100);
                long endTime = System.currentTimeMillis();
                System.out.println(endTime - startTime);
            }
            {
                long startTime = System.currentTimeMillis();
                imageProcessor.setBrightnessWithThreadsPool(100);
                long endTime = System.currentTimeMillis();
                System.out.println(endTime - startTime);
            }
            imageProcessor.writeImage("Obrazek.jpg");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}