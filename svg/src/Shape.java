public abstract class Shape {
    //Nie mozna stworzyc instancji klasy abstrakcyjnej
    final protected Style style;

    public Shape(){
        this.style = new Style();
    }

    public Shape(Style style){
        this.style = style;
    }

    public abstract Point getBound();

    public abstract String toSvg();
}
