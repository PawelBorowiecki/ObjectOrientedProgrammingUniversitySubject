import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SvgScene {
    private ArrayList<Polygon> shapes = new ArrayList<>();          //Shapes zawiera referencje na liste

    public void addShape(Polygon polygon){
        shapes.add(polygon);
    }

    public void save(String path){
        try{
            FileWriter fileWriter = new FileWriter(path);               //FileWriter umozliwia zapis do pliku
            fileWriter.write("<html> <body> <svg>");
            for(Polygon polygon : shapes){
                fileWriter.write(polygon.toSvg());
            }
            fileWriter.write("</svg>  </body> </html>");
            fileWriter.close();
        }catch(IOException e){
            System.err.println("Nie mozna wpisac do pliku " + path);
        }
    }

    public Point getBounds(){
        double x = 0, y = 0;
        for(Point p : shapes
                .stream().
                map(shape -> shape.getBound())
                .toList()){
               x = Math.max(x, p.x);
               y = Math.max(y, p.y);
        }
        return new Point(x, y);
    }

}