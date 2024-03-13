public class SolidFillShapeDecorator extends ShapeDecorator{
    private String colour;

    public SolidFillShapeDecorator(Shape decoratedShape, String colour) {
        super(decoratedShape);
        this.colour = colour;
    }

    @Override
    public String toSvg(String parameters) {
        String sf = String.format("fill=\"%s\" %s ", colour, parameters);
        return super.toSvg(sf);
    }
}
