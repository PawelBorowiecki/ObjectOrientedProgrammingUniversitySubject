package org.example;

import java.awt.image.BufferedImage;

import static java.lang.Math.clamp;

public class SetBrightnessWorker implements Runnable{
    private int begin, end, brightness;
    private BufferedImage image;

    public SetBrightnessWorker(int begin, int end, int brightness, BufferedImage image) {
        this.begin = begin;
        this.end = end;
        this.brightness = brightness;
        this.image = image;
    }

    @Override
    public void run() {
        for(int y = begin; y < end; y++) {
            for (int x = 0; x < image.getWidth(); x++) {
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
        }
    }

}