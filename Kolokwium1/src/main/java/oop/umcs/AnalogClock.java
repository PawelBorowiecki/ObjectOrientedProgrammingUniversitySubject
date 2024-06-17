package oop.umcs;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AnalogClock extends Clock{
    private final List<ClockHand> clockHands = new ArrayList<>();

    public AnalogClock() {
        clockHands.add(new HourHand());
        clockHands.add(new SecondHand());
    }

    public void toSvg(Path filePath) throws IOException {
        FileWriter fileWriter = new FileWriter(Path.of(filePath.toString()).toFile());
        fileWriter.write("<svg height=\"100\" width=\"100\">\n<circle r=\"30\" cx=\"30\" cy=\"30\" fill=\"red\" />\n</svg>");
        fileWriter.write(clockHands.get(0).toSvg());
        fileWriter.write(clockHands.get(1).toSvg());
        fileWriter.close();
    }

    @Override
    public String toString() {
        return "AnalogClock{" +
                "clockHands=" + clockHands +
                '}';
    }
}
