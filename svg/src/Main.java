public class Main {
    public static void main(String[] args) {
        Point point = new Point(2.4, 5.5);
        //System.out.println(point.x + "," + point.y);
        System.out.println(point);

        Segment seg = new Segment(point, new Point(1.7, 8.6));
        System.out.println(seg.getDistance());
    }
}