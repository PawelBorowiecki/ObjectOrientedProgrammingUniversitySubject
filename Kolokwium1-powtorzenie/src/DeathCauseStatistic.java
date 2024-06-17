import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

public class DeathCauseStatistic {
    private String icd10;
    private int totalDeaths;
    private int [] stats;

    public record AgeBracketDeaths(Integer young, Integer old, Integer deathCount){
        @Override
        public String toString() {
            return "AgeBracketDeaths{" +
                    "young=" + young +
                    ", old=" + old +
                    ", deathCount=" + deathCount +
                    '}';
        }
    }

    public AgeBracketDeaths getDeathsForAge(Integer age){
        int index = (age <= 100)? stats.length - 1 : age / 5;
        return new AgeBracketDeaths(
                index * 5,
                index == stats.length -1 ? null : index * 5 + 4,
                stats[index]
        );
    }

    private static int parseIntFromCsv(String s){
        return s.equals("-") ? 0 : Integer.parseInt(s);
    }

    public static DeathCauseStatistic fromCsvLine(String line){
        Function<String, Integer> parseIntFromCsv = s -> s.equals("-") ? 0 : Integer.parseInt(s);   //Tworzymy funkcje lambda w celu zaminay stringu na liczbe
        DeathCauseStatistic result = new DeathCauseStatistic();
        String[] split = line.split("[ \t]+");      //+ oznacza wiecej niz raz
        result.icd10 = split[0];
        result.stats = new int[21];
        String[] stats = split[1].split(",");
        result.totalDeaths = Integer.parseInt(stats[1]);
        for(int i = 0; i < 20; i++){
            result.stats[i] = parseIntFromCsv.apply(stats[i+2]);
        }

        return result;
    }

    @Override
    public String toString() {
        return "DeathCauseStatistic{" +
                "icd10='" + icd10 + '\'' +
                ", stats=" + Arrays.toString(stats) +
                '}';
    }

    public String getIcd10(){
        return  icd10;
    }
}