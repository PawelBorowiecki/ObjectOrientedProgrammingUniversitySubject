public class DropShadowDecorator extends ShapeDecorator{

    private int index;
    public DropShadowDecorator(Shape decoratedShape) {
        super(decoratedShape);
        index = SvgScene.getInstance().addFilter(
                "\\t<filter id=\\\"f%d\\\" x=\\\"-100%%\\\" y=\\\"-100%%\\\" width=\\\"300%%\\\" height=\\\"300%%\\\">\\n\" +\n" +
                        "\"\\t\\t<feOffset result=\\\"offOut\\\" in=\\\"SourceAlpha\\\" dx=\\\"5\\\" dy=\\\"5\\\" />\\n\" +\n" +
                        "\"\\t\\t<feGaussianBlur result=\\\"blurOut\\\" in=\\\"offOut\\\" stdDeviation=\\\"5\\\" />\\n\" +\n" +
                        "\"\\t\\t<feBlend in=\\\"SourceGraphic\\\" in2=\\\"blurOut\\\" mode=\\\"normal\\\" />\\n\" +\n" +
                        "\"\\t</filter>\""
        );
    }

    @Override
    public String toSvg(String parameters) {
        String filterWithID = String.format("filter=\"url(#f%d)\" %s ", index, parameters);
        return decoratedShape.toSvg(filterWithID);
    }
}
