import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class ICDCodeTabularOptimizedForMemory implements ICDCodeTabular{
    Path path;
    public ICDCodeTabularOptimizedForMemory(Path path){
        this.path = path;
    }
    @Override
    public String getDescription(String code) {
        try(Stream<String> lines = Files.lines(path)){
            return lines.skip(88)           //Pomijamy pierwsze 88 linii
                    .map(String::trim)  //trim czysci linie
                    .filter(s -> s.matches("[A-Z][0-9]{2}.*"))    //{2} oznacza, ze beda 2 cyfry
                    .map(s -> s.split(" ", 2))
                    .filter(strings -> strings[0].equals(code))
                    .findFirst()
                    .map(strings -> strings[1])
                    .orElse("?");
        }catch(IOException e){
            throw new RuntimeException();
        }
    }
}