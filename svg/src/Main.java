public class Main {
    public static void main(String[] args) {
        Point point = new Point(20.4, 50.5);
        //System.out.println(point.x + "," + point.y);

        Segment seg = new Segment(point, new Point(10.7, 80.6));
        //System.out.println(seg.getDistance());
        System.out.println(seg.toSvg());

        Segment[] pSeg = Segment.perpendicularTo(seg, point);
        System.out.println(pSeg[0].toSvg());
        System.out.println(pSeg[1].toSvg());
    }


}