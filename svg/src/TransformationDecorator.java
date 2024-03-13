public class TransformationDecorator implements Shape {
    private String transform;

    public static class Builder{
        String transform;
        public Builder(){
            this.transform = "";
        }

        public Builder translate(Vec2 translation){
            this.transform += String.format("translate(%f %f)", translation.x, translation.y);
            return this;
        }

        public Builder rotate(Vec2 rotator){
            this.transform += String.format("translate(%f %f)", rotator.x, rotator.y);
            return this;
        }

        public Builder scale(Vec2 scaleFactor){
            this.transform += String.format("scale(%f %f)", scaleFactor.x, scaleFactor.y);
            return this;
        }

        public TransformationDecorator build(Shape shape){
            return new TransformationDecorator(shape, transform);
        }
    }
    public TransformationDecorator(Shape shape, String transform) {
        super(shape);
        this.transform = transform;
    }

    @Override
    public String toSvg(String parameters) {
        String t = String.format("%s transform= ", parameters);
        return super.toSvg(t + parameters);
    }
}
