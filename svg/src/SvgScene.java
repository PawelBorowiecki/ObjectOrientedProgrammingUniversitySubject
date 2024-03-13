import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SvgScene {
    private ArrayList<Shape> shapes = new ArrayList<>();          //Shapes zawiera referencje na liste

    public void addShape(Shape polygon){
        shapes.add(polygon);
    }

    public void save(String path){
        try{
            FileWriter fileWriter = new FileWriter(path);               //FileWriter umozliwia zapis do pliku
            Vec2 bounds = getBounds();
            fileWriter.write("<HTML>");
            fileWriter.write("<body>");
            fileWriter.write(
                    String.format(
                            "<svg width=\"%f\" height=\"%f\">\n",
                            bounds.x,
                            bounds.y
                    )
            );
            for(Shape polygon : shapes)
                fileWriter.write("\t" + polygon.toSvg() + "\n");
            fileWriter.write("</svg>");
            fileWriter.write("</body>");
            fileWriter.write("</HTML>");
            fileWriter.close();
        }catch(IOException e){
            System.err.println("Nie mozna wpisac do pliku " + path);
        }
    }

    public Vec2 getBounds(){
        double x = 0, y = 0;
        for(Vec2 p : shapes
                .stream()
                .map(shape -> shape.getBound())
                .toList()){
               x = Math.max(x, p.x);
               y = Math.max(y, p.y);
        }
        return new Vec2(x, y);
    }

}