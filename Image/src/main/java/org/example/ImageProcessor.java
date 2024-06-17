package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ImageProcessor {
    private BufferedImage image;    //BufferedImage sluzy do przechowywania obrazow

    public void readImage(String path) throws IOException {
        image = ImageIO.read(new File(path));
    }

    public void writeImage(String path) throws IOException {
        ImageIO.write(image, "jpg", new File(path));
    }

    //R  G  B
    //FF 00 00
    public void setBrightness(int brightness){
        for(int y = 0; y < image.getHeight(); y++){
            for(int x = 0; x < image.getWidth(); x++){
                int rgb = image.getRGB(x,y);
                int b = rgb & 0xFF;                     //Sprawdzamy niebieski
                int g = (rgb & 0xFF00) >> 8;            //Sprawdzamy zielony. Przesuwamy o 8 bitów, bo przesuwamy o 2 znaki szesznastkowe
                int r = (rgb & 0xFF0000) >> 16;
                b = Math.clamp(b,brightness,255);       //Dziala od Javy 21
                g = Math.clamp(g,brightness,255);       //Sprawdzamy, czy g zwiekszone o brightness bedzie <= od 255
                r = Math.clamp(r,brightness,255);
                rgb = (r << 16) + (g << 8) + b;
                image.setRGB(x,y,rgb);
            }
        }
    }

    public void setBrightnessWithThreads(int brightness) throws InterruptedException {
        int threadsCount = Runtime.getRuntime().availableProcessors();     //To nam zwraca liczbe rdzeni procesora
        Thread[] threads = new Thread[threadsCount];
        int chunk = image.getHeight() / threadsCount;   //Wysokosc obrazu, ktora bedzie zajmował sie jeden watek
        for(int i = 0; i < threadsCount; i++){
            int begin = i * chunk;
            int end = (i == threadsCount - 1)? image.getHeight() : (i+1) * chunk;
            threads[i] = new Thread(new SetBrightnessWorker(begin, end, brightness, image));
            threads[i].start();

        }
        for(int i = 0; i < threadsCount; i++){
            threads[i].join();
        }
    }

    public void setBrightnessWithThreadsPool(int brightness){
        int threadsCount = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(threadsCount);   //Tworzymy pule watkow
        for(int i = 0; i < image.getHeight(); i++){
            final int y = i;
            executor.submit(() -> {
                for(int x = 0; x < image.getWidth(); x++){
                    int rgb = image.getRGB(x,y);
                    int b = rgb & 0xFF;                     //Sprawdzamy niebieski
                    int g = (rgb & 0xFF00) >> 8;            //Sprawdzamy zielony. Przesuwamy o 8 bitów, bo przesuwamy o 2 zanki szesznastkowe
                    int r = (rgb & 0xFF0000) >> 16;
                    b = Math.clamp(b,brightness,255);       //Dziala od Javy 21
                    g = Math.clamp(g,brightness,255);       //Sprawdzamy, czy g zwiekszone o brightness bedzie <= od 255
                    r = Math.clamp(r,brightness,255);
                    rgb = (r << 16) + (g << 8) + b;
                    image.setRGB(x,y,rgb);
                }
            });
        }
        executor.shutdown();
        try {
            boolean b = executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}