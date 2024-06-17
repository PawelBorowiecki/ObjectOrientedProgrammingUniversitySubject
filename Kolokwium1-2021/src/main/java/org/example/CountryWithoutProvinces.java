package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CountryWithoutProvinces extends Country{
    private int deathsCount = 0;
    private int infectionCount = 0;

    private static Map<LocalDate, CountryWithoutProvinces> statistics = new HashMap<>();
    public CountryWithoutProvinces(String name) {
        super(name);
    }

    public static void fillStatistics(){
        try {
            BufferedReader bufferedReader1 = new BufferedReader(new FileReader(getConfirmedCasesCSVfilePath()));
            String line1 = bufferedReader1.readLine();
            String[] dividedLine1 = line1.split(",");
            for(int i=0; i < dividedLine1.length; i++){
                statistics.put(LocalDate.parse(dividedLine1[0]), new CountryWithoutProvinces(dividedLine1[i]));
            }
            while((line1 = bufferedReader1.readLine()) != null){
                dividedLine1 = line1.split(",");
                for(int i=0; i < dividedLine1.length; i++){
                    statistics.get(dividedLine1[0]).infectionCount += Integer.parseInt(dividedLine1[i]);
                }
            }
            bufferedReader1.close();

            BufferedReader bufferedReader2 = new BufferedReader(new FileReader(getConfirmedCasesCSVfilePath()));
            String line2 = bufferedReader2.readLine();
            String[] dividedLine2 = line2.split(",");
            for(int i=0; i < dividedLine2.length; i++){
                statistics.put(LocalDate.parse(dividedLine2[0]), new CountryWithoutProvinces(dividedLine2[i]));
            }
            while((line2 = bufferedReader1.readLine()) != null){
                dividedLine2 = line2.split(",");
                for(int i=0; i < dividedLine2.length; i++){
                    statistics.get(dividedLine2[0]).infectionCount += Integer.parseInt(dividedLine2[i]);
                }
            }
            bufferedReader2.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addDailyStatistic(LocalDate date, int deaths, int infections, String name){
        CountryWithoutProvinces countryWithoutProvinces = new CountryWithoutProvinces(name);
        countryWithoutProvinces.deathsCount = deaths;
        countryWithoutProvinces.infectionCount = infections;
        statistics.put(date, countryWithoutProvinces);
    }

    @Override
    public int getConfirmedCases(LocalDate date) {
        return statistics.get(date).infectionCount;
    }

    @Override
    public int getDeaths(LocalDate date) {
        return statistics.get(date).deathsCount;
    }
}
