package org.example;

import java.io.*;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

public class Country {
    private final String name;
    private int deathsCount = 0;
    private int infectionCount = 0;
    private LocalDate date;
    private static String confirmedCasesCSVfilePath = "confirmed_cases.csv";
    private static String deathsCSVfilePath = "deaths.csv";

    private static class CountryColumns{
        public final int firstColumnIndex, columnCount;

        public CountryColumns(int firstColumnIndex, int columnCount) {
            this.firstColumnIndex = firstColumnIndex;
            this.columnCount = columnCount;
        }
    }

    public Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDeathsCount(int deathsCount) {
        this.deathsCount = deathsCount;
    }

    public void setInfectionCount(int infectionCount) {
        this.infectionCount = infectionCount;
    }

    public int getDeathsCount() {
        return deathsCount;
    }

    public int getInfectionCount() {
        return infectionCount;
    }

    public static String getConfirmedCasesCSVfilePath() {
        return confirmedCasesCSVfilePath;
    }

    public static String getDeathsCSVfilePath() {
        return deathsCSVfilePath;
    }

    public static void setFiles(String ccCSVfilePath, String dCSVFilePath) throws FileNotFoundException {
        if(!ccCSVfilePath.equals(Country.confirmedCasesCSVfilePath)){
            throw new FileNotFoundException(ccCSVfilePath);
        }else if(!dCSVFilePath.equals(Country.deathsCSVfilePath)){
            throw new FileNotFoundException(dCSVFilePath);
        }

        try{
            FileReader fr = new FileReader(ccCSVfilePath);
        }catch(FileNotFoundException e){
            System.err.println(ccCSVfilePath);
        }

        try{
            FileReader fr = new FileReader(dCSVFilePath);
        }catch(FileNotFoundException e){
            System.err.println(dCSVFilePath);
        }
    }

    public static void fromCsv(String countryName){
        try {
            BufferedReader r1 = new BufferedReader(new FileReader(confirmedCasesCSVfilePath));
            getCountryColumns(r1.readLine(), countryName);
            CountryWithoutProvinces.fillStatistics();
            r1.close();
            BufferedReader r2 = new BufferedReader((new FileReader(deathsCSVfilePath)));
            getCountryColumns(r2.readLine(), countryName);
            CountryWithoutProvinces.fillStatistics();
            r2.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void fromCsv(String[] countriesNames){
        Country[] countries = new Country[countriesNames.length];
        for(int i=0; i< countriesNames.length; i++){
            try{
                fromCsv(countriesNames[i]);
                countries[i] = new Country(countriesNames[i]);
            }catch(Exception e){
                System.err.println(e.getMessage());
            }finally {
                continue;
            }
        }
    }

    private static CountryColumns getCountryColumns(String csvLine, String countryName){
        int index = 0;
        int iterator = 0;
        String[] countries = csvLine.split(",");
        for(int i=0; i< countries.length; i++){
            if(countries[i].equals(countryName)){
                index = i;
            }
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(confirmedCasesCSVfilePath));
            String line;
            while((line = bufferedReader.readLine()) != null){
                String[] dividedLine = line.split(",");
                if(dividedLine.equals("nan")){
                    continue;
                }else{
                    iterator++;
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new CountryColumns(index, iterator);
    }

    public int getConfirmedCases(LocalDate date){
        return 0;
    }

    public int getDeaths(LocalDate date){
        return 0;
    }

    public static void sortByDeaths(List<Country> countries, LocalDate beginning, LocalDate end){
        countries.stream()
                .filter(n -> n.date.isAfter(beginning) && n.date.isBefore(end))
                .sorted((n,m) -> n.deathsCount > m.deathsCount ? n.deathsCount : m.deathsCount)
                .toList();
    }

    public void saveToDataFile(String outputFile){
        try {
            FileWriter writer = new FileWriter(outputFile);
            writer.write(date.toString() + "\t" + String.valueOf(infectionCount) + "\t" + String.valueOf(deathsCount));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
