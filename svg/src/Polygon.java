import java.util.Locale;

public class Polygon extends Shape {
    private Point[] points;

    public Polygon(Point[] points, Style style) {
        super(style);
        this.points = points;
    }

    public Polygon(Point[] points){
        this.points = points;
    }

    @Override
    public String toSvg(){
        String result = "";
        for(int i = 0; i < this.points.length; i++){
            result += String.format(Locale.ENGLISH, "%f,%f ", points[i].x, points[i].y);
        }
        //<polygon points="200,10 250,190 150,190" style="fill:lime;stroke:purple;stroke-width:3" />
        return String.format(Locale.ENGLISH, "<polygon points=\"%s\" style=\"%s\" />", result, style.toSvg());
    }

    @Override
    public Point getBound(){
        double x = 0, y = 0;
        for(int i = 0; i < points.length; i++){
            x = Math.max(x, points[i].x);
            y = Math.max(y, points[i].y);
        }
        return new Point(x, y);
    }
}
