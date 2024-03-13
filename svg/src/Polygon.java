import java.util.Locale;

public class Polygon implements Shape {
    private Vec2[] points;

    public Polygon(Vec2[] points) {
        this.points = points;
    }

    //Zad 3 lab 2. Dopisane przeze mnie, bo nie zdazylismy na labach
//    public static Polygon square(Segment line, Style style){
//        Point points[] = new Point[]{
//                line.getStart(),
//                line.getEnd(),
//                new Point(line.getStart().x + Polygon.perpendicularTo(line, line.getStart()), line.getStart().y + Polygon.perpendicularTo(line, line.getStart())),
//                new Point(line.getEnd().x + Polygon.perpendicularTo(line, line.getEnd()), line.getEnd().y + Polygon.perpendicularTo(line, line.getEnd())),
//        };
//        Polygon poly = new Polygon(points, style);
//        return poly;
//    }
//
//    public static double perpendicularTo(Segment s, Point p){
//        double dx = s.getEnd().x - s.getStart().x;
//        double dy = s.getEnd().y - s.getStart().y;
//
//        return Math.hypot(dx, dy);
//    }

    public String toSvg(String parameters){
        String result = "";
        for(int i = 0; i < this.points.length; i++){
            result += String.format(Locale.ENGLISH, "%f,%f ", points[i].x, points[i].y);
        }
        //<polygon points="200,10 250,190 150,190" style="fill:lime;stroke:purple;stroke-width:3" />
        return String.format(Locale.ENGLISH, "<polygon points=\"%s\"%s />", result, parameters);
    }

    @Override
    public Vec2 getBound(){
        double x = 0, y = 0;
        for(int i = 0; i < points.length; i++){
            x = Math.max(x, points[i].x);
            y = Math.max(y, points[i].y);
        }
        return new Vec2(x, y);
    }
}
