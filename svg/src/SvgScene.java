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

        }catch(IOException e){
            System.err.println("Nie mozna wpisac do pliku " + path);
        }
    }

}
