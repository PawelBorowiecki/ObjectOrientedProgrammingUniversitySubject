package oop.umcs;

public class DigitalClock extends Clock{
    private final EClockType clockType;

    public DigitalClock(String kind) {
        super();
        if(kind.equals("24")){
            this.clockType = EClockType.TWENTY_FOUR;
        }else{
            this.clockType = EClockType.TWELVE;
        }
    }

    @Override
    public String toString() {
        if(clockType.equals(EClockType.TWENTY_FOUR)){
            return "Clock{" +
                    "time=" + time.getHour() +
                    ":" + time.getMinute() +
                    ":" + time.getSecond() +
                    '}';
        }
        if(time.getHour() > 12){
            return "Clock{" +
                    "time=" + (time.getHour() - 12) +
                    ":" + time.getMinute() +
                    ":" + time.getSecond() +
                    " PM}";
        }
        return "Clock{" +
                "time=" + time.getHour() +
                ":" + time.getMinute() +
                ":" + time.getSecond() +
                " AM}";
    }
}
