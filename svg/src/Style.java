import java.util.Locale;

public class Style {
    final public String fillColor, strokeColor;
    final public Double strokeWidth;

    public Style(){
        this("transparent", "black", 1.0);          //Domyslnie utworzy sie to
    }
    public Style(String fillColor, String strokeColor, Double strokeWidth) {
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
    }

    public String toSvg(){
        //<polygon points="200,10 250,190 150,190" style="fill:lime;stroke:purple;stroke-width:3" />
        return String.format(Locale.ENGLISH, "fill: %s; stroke: %s; stroke_width: %f\"/>", fillColor, strokeColor, strokeWidth);
    }
}
