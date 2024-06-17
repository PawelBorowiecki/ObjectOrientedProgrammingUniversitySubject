package oop.umcs;

import java.time.LocalTime;

public class SecondHand extends ClockHand{
    @Override
    public int setTime(LocalTime time) {
        return time.getSecond();
    }

    @Override
    public String toSvg() {
        DigitalClock digitalClock = new DigitalClock("24");
        int seconds = this.setTime(digitalClock.time);
        if(seconds > 30){
            return String.format("<svg>\n<line x1=\"30\" y1=\"30\" x2=\"15\" y2=\"%d\" style=\"stroke:red;stroke-width:2\" />\n</svg>", seconds);
        }else if(seconds < 30){
            return String.format("<svg>\n<line x1=\"30\" y1=\"30\" x2=\"45\" y2=\"%d\" style=\"stroke:red;stroke-width:2\" />\n</svg>", (seconds + 30));
        }else if(seconds == 30){
            return "<svg>\n<line x1=\"30\" y1=\"30\" x2=\"30\" y2=\"10\" style=\"stroke:red;stroke-width:2\" />\n</svg>";
        }
        return "<svg>\n<line x1=\"30\" y1=\"30\" x2=\"30\" y2=\"60\" style=\"stroke:red;stroke-width:2\" />\n</svg>";
    }
}
