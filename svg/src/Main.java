public class Main {
    public static void main(String[] args) {
        Point point = new Point(20.4, 50.5);
        //System.out.println(point.x + "," + point.y);

        Segment seg = new Segment(point, new Point(10.7, 80.6));
        //System.out.println(seg.getDistance());
        System.out.println(seg.toSvg());

        //Segment[] pSeg = Segment.perpendicularTo(seg, point);
        //System.out.println(pSeg[0].toSvg());
        //System.out.println(pSeg[1].toSvg());

        Polygon poly = new Polygon(new Point[]{
                new Point(300, 400),
                new Point(600, 100),
                new Point(700, 900)
        });

        Style style = new Style("purple", "black", 1.0);
        Polygon polygon = new Polygon(new Point[]{
                new Point(500, 400),
                new Point(700, 300),
                new Point(200, 700),
                new Point(500, 450),
        });

        System.out.println(poly.toSvg());
        System.out.println(polygon.toSvg());

        SvgScene scene = new SvgScene();
        Ellipse ellipse = new Ellipse(style, new Point(100, 200), 50.5, 75.7);
        scene.addShape(poly);
        scene.addShape(polygon);
        scene.addShape(ellipse);
        scene.save("/tmp/out.html");
    }
}