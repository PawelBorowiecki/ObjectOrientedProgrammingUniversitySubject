package oop.umcs;

import java.time.LocalTime;

public abstract class Clock {
    public LocalTime time;
    private City city;

    public void setCurrentTime(){
        this.time = LocalTime.now();
    };
    public void setTime(int hour, int minute, int second){
        if(hour > 23 || hour < 0){
            throw new IllegalArgumentException("Podano zla godzine");
        }else if(minute > 59 || minute < 0){
            throw new IllegalArgumentException("Podano zla minute");
        }else if(second > 59 || second < 0) {
            throw new IllegalArgumentException("Podano zla sekunde");
        }

        this.time = LocalTime.of(hour, minute, second);
    }

    public void setCity(City cityRef){
        this.city = new City(cityRef);
        setCurrentTime();
        int zoneChange = Integer.parseInt(this.city.getTimeZone());
        if(zoneChange > 0){
            this.time.plusHours(zoneChange);
        }else if(zoneChange < 0){
            this.time.minusHours(zoneChange);
        }
    }

    @Override
    public String toString() {
        return "Clock{" +
                "time=" + time.getHour() +
                ":" + time.getMinute() +
                ":" + time.getSecond() +
                '}';
    }
}
