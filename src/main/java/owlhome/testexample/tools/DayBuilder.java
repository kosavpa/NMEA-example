package owlhome.testexample.tools;


import lombok.Data;
import owlhome.testexample.models.Coordinate;
import owlhome.testexample.models.Day;

import java.io.IOException;
import java.util.*;


@Data
public class DayBuilder {
    private TempoMapCreator tempoMapCreator = new TempoMapCreator();
    private TempoMapWatcher tempoMapWatcher = new TempoMapWatcher();
    private CoordinateCreator coordinateCreator = new CoordinateCreator();
    private Parser parser = new Parser();

    public Set<Day> createDayFromFile(String fileName) {
        Map<Date, List<String>> tempoMap = null;
        try {
            tempoMap = tempoMapCreator.createTempoMap(fileName, parser, tempoMapWatcher);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Set<Day> daySet = processMap(tempoMap);

        return daySet;
    }

    private Set<Day> processMap(Map<Date, List<String>> dateListMap){
        Set<Day> daySet = new HashSet<>();

        for(Map.Entry<Date, List<String>> entry : tempoMapCreator.checkTempoMap(dateListMap, parser).entrySet()){
            try {
                Day day = createDay(entry);
                daySet.add(day);
            } catch (IllegalArgumentException exception){
                System.out.println(exception);
            }
        }

        return daySet;
    }

    private Day createDay(Map.Entry<Date, List<String>> entry) {
        Coordinate firstCoordinate = coordinateCreator.createFirstCoordinate(entry.getValue(), parser);
        Coordinate secondCoordinate = coordinateCreator.createSecondCoordinate(entry.getValue(), parser);

        return new Day(firstCoordinate, secondCoordinate, entry.getKey());
    }
}