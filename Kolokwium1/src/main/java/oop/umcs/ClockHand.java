package oop.umcs;

import java.time.LocalTime;

public abstract class ClockHand {
    public int setTime(LocalTime time){
        return 0;
    }
    public String toSvg(){
        return "<svg>\n<line x1=\"30\" y1=\"30\" x2=\"30\" y2=\"60\" style=\"stroke:red;stroke-width:2\" />\n</svg>";
    }
}
