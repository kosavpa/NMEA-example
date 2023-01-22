package owlhome.testexample.models;


import lombok.Builder;
import lombok.Data;

import java.text.SimpleDateFormat;


@Data
@Builder
public class DistanceInfo {
    private Day day;
    private String distance;
    private String avgSpeed;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DistanceInfo info = (DistanceInfo) o;

        if (!day.equals(info.day)) return false;
        if (!distance.equals(info.distance)) return false;
        if (!avgSpeed.equals(info.avgSpeed)) return false;
        return dateFormat.equals(info.dateFormat);
    }

    @Override
    public int hashCode() {
        int result = day.hashCode();
        result = 31 * result + distance.hashCode();
        result = 31 * result + avgSpeed.hashCode();
        result = 31 * result + dateFormat.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "DistanceInfo: {\n" +
                "\tdate = " + dateFormat.format(day.getDate()) +
                ";\n\tdistance = " + distance + " (km)" +
                ";\n\taverage speed = " + avgSpeed + "(km/h)" +
                "\n}";
    }
}
