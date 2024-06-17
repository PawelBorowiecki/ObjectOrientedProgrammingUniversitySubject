import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class DeathCauseStatisticList {
    List<DeathCauseStatistic> stats = new ArrayList<>();

    public void repopulate(Path path){
        try(Stream<String> fileLines = Files.lines(path)){ //Metoda lines() odczytuje wszytskie linie pliku
            stats = fileLines.skip(2)
                        .map(DeathCauseStatistic::fromCsvLine)
                        .toList();
        }catch(IOException e){
            throw new RuntimeException();
            }
        }



    public List<DeathCauseStatistic> mostDeadlyDiseases(int age, int n){
//        List<DeathCauseStatistic> results = new ArrayList<>();
//        for(DeathCauseStatistic currentStat : stats){
//            results.add(currentStat.getDeathsForAge(age));
//        }
        List<DeathCauseStatistic> results = new ArrayList<>(stats); //wywolujemy konstruktor kopiujacy
        results.sort((disease1, disease2) -> Integer.compare(
                disease1.getDeathsForAge(age).deathCount(),
                disease2.getDeathsForAge(age).deathCount()
        ));
        return results.reversed().sublist(0, n);   //sublist(poczatek, koniec)
    }

    public List<DeathCauseStatistic> getStats() {
        return stats;
    }
}