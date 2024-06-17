package oop.umcs;

import java.time.LocalTime;

public class HourHand extends ClockHand{
    @Override
    public int setTime(LocalTime time) {
        return time.getHour();
    }

    @Override
    public String toSvg() {
        DigitalClock digitalClock = new DigitalClock("24");
        int hours = this.setTime(digitalClock.time);
        if(hours > 6){
            return String.format("<svg>\n<line x1=\"30\" y1=\"30\" x2=\"15\" y2=\"%d\" style=\"stroke:blue;stroke-width:4\" />\n</svg>", (hours-6) * 10);
        }else if(hours < 6){
            return String.format("<svg>\n<line x1=\"30\" y1=\"30\" x2=\"50\" y2=\"%d\" style=\"stroke:blue;stroke-width:4\" />\n</svg>", hours * 10);
        }else if(hours == 6){
            return "<svg>\n<line x1=\"30\" y1=\"30\" x2=\"30\" y2=\"5\" style=\"stroke:blue;stroke-width:4\" />\n</svg>";
        }
        return "<svg>\n<line x1=\"30\" y1=\"30\" x2=\"30\" y2=\"60\" style=\"stroke:blue;stroke-width:4\" />\n</svg>";
    }
}
