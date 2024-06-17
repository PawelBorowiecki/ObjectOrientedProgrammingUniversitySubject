package oop.umcs;

import java.io.*;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.*;

public class City {
    private String cityName, latitude, longtitude, timeZone;

    public City(String cityName, String latitude, String longtitude, String timeZone) {
        this.cityName = cityName;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.timeZone = timeZone;
    }

    public City(City city){
        this.cityName = city.cityName;
        this.latitude = city.latitude;
        this.longtitude = city.longtitude;
        this.timeZone = city.timeZone;
    }

    private static City parseLine(String line){
        String[] lineElements = line.split(",");
        return new City(lineElements[0], lineElements[2], lineElements[3], lineElements[1]);
    }

    public static Map<String, City> parseFile(Path filePath) throws IOException {
        Map<String, City> cityMap = new HashMap<>();
        FileReader fileReader = new FileReader(filePath.toFile());
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        bufferedReader.readLine();
        String line;
        while((line = bufferedReader.readLine()) != null){
            String[] lineElements = line.split(",");
            cityMap.put(lineElements[0], parseLine(line));
        }
        bufferedReader.close();
        return cityMap;
    }

    public LocalTime localMeanTime(){   //ta metoda tez nie dziala jak powinna
        try{
            String[] properLongtitude = this.longtitude.split(" ");
            int totalSeconds= Integer.parseInt(properLongtitude[0]) * 240;
            int properHour = totalSeconds % 3600;
            int properMinute = totalSeconds % 60;
            int properSecond = totalSeconds - properHour * 3600 - properMinute * 60;
            return LocalTime.of(properHour, properMinute, properSecond);
        }catch(NumberFormatException e){
            System.err.println(e.getCause());
        }
        return LocalTime.now();
    }

    public static void worstTimeZoneFit() throws IOException {  //Trohce inaczej wystwiela niz zpowinno
        Map<String, City> cities = parseFile(Path.of("C:\\Users\\pavlo\\OneDrive\\Pulpit\\Foldery\\Nauka\\Informatyka\\Studia\\2Semestr\\ProgramowanieObiektowe\\Kolokwium1\\Kolokwium1\\strefy.csv"));
        List<City> sortedCities = new ArrayList<>(cities.values());
        sortedCities.stream()
                .sorted((a, b) -> LocalTime.now().compareTo(a.localMeanTime()) > LocalTime.now().compareTo(b.localMeanTime())? 1 : -1)
                .forEach(System.out::println);
    }

    public static void generateAnalogClockSvg(List<City> cities, AnalogClock analogClock) throws IOException {
        File file1 = new File("C:\\Users\\pavlo\\OneDrive\\Pulpit\\Foldery\\Nauka\\Informatyka\\Studia\\2Semestr\\ProgramowanieObiektowe\\Kolokwium1\\Kolokwium1)\\Zegary");
        file1.mkdir();
        int iterator = 0;
        for(City city : cities){
            String path = String.format("C:\\Users\\pavlo\\OneDrive\\Pulpit\\Foldery\\Nauka\\Informatyka\\Studia\\2Semestr\\ProgramowanieObiektowe\\Kolokwium1\\Kolokwium1\\Zegary\\zegar%d.svg", ++iterator);
            File file2 = new File(path);
            analogClock.toSvg(Path.of(path));
        }
    }

    public String getCityName() {
        return cityName;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public String getTimeZone() {
        return timeZone;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityName='" + cityName + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longtitude='" + longtitude + '\'' +
                ", timeZone=" + timeZone +
                '}';
    }
}
