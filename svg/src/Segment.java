import java.util.Locale;

public class Segment {
    private Point start;
    private Point end;

    public Segment(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Point getStart(){
        return start;
    }

    public Point getEnd(){
        return end;
    }
    public double getDistance(){
        return Math.hypot(end.x-start.x, end.y- start.y);
    }

    public String toSvg(){
        return String.format(Locale.ENGLISH,"<line x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:red;stroke-width:2\" />", start.x, start.y, end.x, end.y);
    }

    public static Segment[] perpendicularTo(Segment s, Point p){
        double dx = s.getEnd().x - s.getStart().x;
        double dy = s.getEnd().y - s.getStart().y;

        //return new Segment(p, new Point(p.x - dx, p.y - dy));
        return new Segment[]{
                new Segment(p, new Point(p.x - dy, p.y - dx)), new Segment(p, new Point(p.x + dy, p.y + dy)),
        };
    }
}
