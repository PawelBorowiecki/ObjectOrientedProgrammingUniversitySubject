import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        //DeathCauseStatistic deathCauseStatistic = DeathCauseStatistic.fromCsvLine("A02.1          ,5,-,-,-,-,-,-,-,-,-,-,-,-,1,2,-,1,1,-,-,-");
        //System.out.println(deathCauseStatistic.toString());
        //System.out.println(deathCauseStatistic.getDeathsForAge(66));

        DeathCauseStatisticList list = new DeathCauseStatisticList();
        list.repopulate(Path.of("zgony.csv"));
        list.getStats().forEach(System.out::println);
        list.mostDeadlyDiseases(45, 10).forEach(System.out::println);

        var codes = new ICDCodeTabularOptimizedForMemory(Path.of("icd10.txt"));
        list.mostDeadlyDiseases(1, 10).forEach(currentStat -> {
            String code = currentStat.getIcd10();
            System.out.println(code + ": " + codes.getDescription(code));
        });

        var codes2 = new ICDCodeTabularOptimizedForTime(Path.of("icd10.txt"));
        list.mostDeadlyDiseases(1, 10).forEach(currentStat -> {
            String code = currentStat.getIcd10();
            System.out.println(code + ": " + codes2.getDescription(code));
        });
    }
}