public interface Shape {
    //Nie mozna stworzyc instancji klasy abstrakcyjnej
    Vec2 getBound();

    String toSvg(String parameters);
}
