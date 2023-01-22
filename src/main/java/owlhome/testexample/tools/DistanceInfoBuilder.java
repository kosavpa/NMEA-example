package owlhome.testexample.tools;


import lombok.AllArgsConstructor;
import lombok.Data;
import owlhome.testexample.models.Day;
import owlhome.testexample.models.DistanceInfo;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.DoubleStream;


@Data
@AllArgsConstructor
public class DistanceInfoBuilder {
    private final double EARTH_RAD = 63781.1d;
    private String filename;

    private double calculateDistance(Day day){
        double distance = 0d;
        double convertConst = 1000;

        double deltaLongitude = day.getSecondCoordinate().getLongitude() - day.getFirstCoordinate().getLongitude();
        double deltaLatitude = day.getSecondCoordinate().getLatitude() - day.getFirstCoordinate().getLatitude();
        double multiplicationLatitudeCos = Math.cos(day.getFirstCoordinate().getLatitude()) * Math.cos(day.getSecondCoordinate().getLatitude());
        double latitudePowSin = Math.pow(Math.sin(deltaLatitude/2), 2);
        double longitudePowSin = Math.pow(Math.sin(deltaLongitude/2), 2);

        distance = 2 * EARTH_RAD * Math.asin(Math.sqrt(latitudePowSin + multiplicationLatitudeCos * longitudePowSin));

        return distance/convertConst;
    }

    private double calculateAvgSpeed(Day day){
        return DoubleStream.of(day.getFirstCoordinate().getSpeed(), day.getSecondCoordinate().getSpeed()).average().orElse(0d);
    }

    public Set<DistanceInfo> getDistanceInfo(){
        DayBuilder dayBuilder = new DayBuilder();

        Set<Day> days = dayBuilder.createDayFromFile(filename);
        Set<DistanceInfo> distanceInfoSet = new HashSet<>();

        days.stream().forEach(day -> {
            String distance = String.format("%.3f", calculateDistance(day));
            String avgSpeed = String.format("%.3f", calculateAvgSpeed(day));

            DistanceInfo info = DistanceInfo.builder()
                    .day(day)
                    .distance(distance)
                    .avgSpeed(avgSpeed)
                    .build();

            distanceInfoSet.add(info);
        });

        return distanceInfoSet;
    }
}
