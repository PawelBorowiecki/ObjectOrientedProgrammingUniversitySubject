package org.example;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FoodProduct extends Product{
    Map<String, Double[]> prices;

    private FoodProduct(String name, Map<String, Double[]> prices){
        super(name);
        this.prices = prices;
    }
    public static FoodProduct fromCsv(Path path){
        String bName;
        Map<String, Double[]> bPrices = new HashMap<>();

        try {
            Scanner scanner = new Scanner(path);
            bName = scanner.nextLine();
            scanner.nextLine();
            while(scanner.hasNextLine()){
                String[] dividedLine = scanner.nextLine().split(";");
                bPrices.put(dividedLine[0], Arrays.stream(dividedLine)
                        .skip(1)
                        .map(value -> value.replace(",", "."))
                        .map(Double::valueOf)
                        .toArray(Double[]::new)
                );
            }
            scanner.close();
            return new FoodProduct(bName, bPrices);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Double getPrice(int year, int month, String province) {
        if(month < 1 || month > 12 || year < 2010 || year > 2022){
            throw new IndexOutOfBoundsException();
        }
        Double[] properPrices = Arrays.stream(this.prices.get(province)).toArray(Double[]::new);
        return properPrices[(2022-2010)*12-1-((2022-year)*12-month)];
    }

    @Override
    public double getPrice(int year, int month) {
        double sum = 0;
        Double[][] properPrices = this.prices.values().toArray(Double[][]::new);
        for(int i = 0; i < 16; i++){
            sum += properPrices[i][(2022-2010)*12-1-((2022-year)*12-month)];
        }
        return sum/16;
    }
}
