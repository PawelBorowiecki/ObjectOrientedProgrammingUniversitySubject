public class SolidFilledPolygon extends Polygon {

    private String fillColour;
    public SolidFilledPolygon(Vec2[] points, String colour) {
        super(points);
        this.fillColour = colour;
    }

    @Override
    public String toSvg(String parameters) {
        String sf = String.format("fill=\"%s\" %s ", fillColour, parameters);
        return super.toSvg(sf);
    }
}