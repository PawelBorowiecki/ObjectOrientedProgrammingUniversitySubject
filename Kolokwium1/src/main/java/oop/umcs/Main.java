package oop.umcs;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        DigitalClock digitalClock = new DigitalClock("12");
        digitalClock.setCurrentTime();
        System.out.println(digitalClock.toString());

        try {
            Map<String, City> cities =  City.parseFile(Path.of("C:\\Users\\pavlo\\OneDrive\\Pulpit\\Foldery\\Nauka\\Informatyka\\Studia\\2Semestr\\ProgramowanieObiektowe\\Kolokwium1\\Kolokwium1\\strefy.csv"));
            cities.forEach((k,v) -> System.out.println(v.toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        City Lublin = new City("Lublin", "51.2465 N", "22.5684 E", "+2");
        City KualaLumpur = new City("Kuala Lumpur", "3.1390 N", "101.6869 E", "+8");
        digitalClock.setCity(KualaLumpur);  //To chyba nie dziala tak jak powinno
        System.out.println(Lublin.localMeanTime().toString());

        try {
            City.worstTimeZoneFit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<City> cityList = new ArrayList<>();
        cityList.add(Lublin);
        cityList.add(KualaLumpur);
        try {
            City.generateAnalogClockSvg(cityList, new AnalogClock());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}