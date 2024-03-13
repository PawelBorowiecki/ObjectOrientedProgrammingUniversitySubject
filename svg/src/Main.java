public class Main {
    public static void main(String[] args) {
        Vec2 point = new Vec2(20.4, 50.5);
        //System.out.println(point.x + "," + point.y);

        Segment seg = new Segment(point, new Vec2(10.7, 80.6));
        //System.out.println(seg.getDistance());
        System.out.println(seg.toSvg());

        //Segment[] pSeg = Segment.perpendicularTo(seg, point);
        //System.out.println(pSeg[0].toSvg());
        //System.out.println(pSeg[1].toSvg());

        Polygon poly = new Polygon(new Vec2[]{
                new Vec2(30, 40),
                new Vec2(60, 10),
                new Vec2(70, 90)
        });

        //Style style = new Style("purple", "black", 1.0);
        Polygon polygon = new Polygon(new Vec2[]{
                new Vec2(50, 40),
                new Vec2(70, 30),
                new Vec2(20, 75),
                new Vec2(10, 45),
        });

        SolidFilledPolygon solidFilledPolygon = new SolidFilledPolygon(new Vec2[]{
                new Vec2(30, 40),
                new Vec2(60, 10),
                new Vec2(70, 90)
        }, "red");

        Shape polygonShape = new Polygon(new Vec2[]{
                new Vec2(50, 40),
                new Vec2(70, 30),
                new Vec2(20, 75),
                new Vec2(10, 45),
        });

        polygonShape = new SolidFillShapeDecorator(polygonShape, "red");

        System.out.println(poly.toSvg(""));
        System.out.println(polygon.toSvg(""));
        System.out.println(solidFilledPolygon.toSvg(""));
        //System.out.println(Polygon.square(seg, style).toSvg());

        SvgScene scene = SvgScene.getInstance();
        Ellipse ellipse = new Ellipse(new Vec2(100, 200), 50.5, 75.7);
        ellipse = new DropShadowDecorator(ellipse);
        Shape ellipseShape = new Ellipse(new Vec2(100, 200), 50.5, 75.7);
        ellipseShape = new SolidFillShapeDecorator(ellipseShape, "blue");

        scene.addShape(poly);
        scene.addShape(polygon);
        scene.addShape(solidFilledPolygon);
        scene.addShape(polygonShape);
        scene.addShape(ellipse);
        scene.addShape(ellipseShape);

        //scene.save("/tmp/out.html");
    }
}